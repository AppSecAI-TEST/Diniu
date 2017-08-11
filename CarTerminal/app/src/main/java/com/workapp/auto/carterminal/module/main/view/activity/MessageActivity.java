package com.workapp.auto.carterminal.module.main.view.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.ProgressSubscriber;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.MessageReturnBean;
import com.workapp.auto.carterminal.module.main.view.adapter.MessageAdapter;
import com.workapp.auto.carterminal.utils.ToastUtils;
import com.workapp.auto.carterminal.widget.CustomIconDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 公告
 * Created by Administrator on 2017/8/8 0008.
 */

public class MessageActivity extends BaseActivity {
    @Bind(R.id.common_recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refreshLayout)
    SmartRefreshLayout refreshLayout;

    private MessageAdapter mMessageAdapter;

    @Override
    protected int getLayout() {
        return R.layout.common_single_list;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ivRight.setVisibility(View.VISIBLE);
        setTitle("公告");
        initRecyclerView();
    }

    @Override
    protected void initData() {
        getNoticeList();
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getNoticeList();
            }
        });

        ivRight.setOnClickListener(v -> {
            showPopView();
        });
    }

    private void initRecyclerView() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMessageAdapter = new MessageAdapter(this);
        recyclerView.setAdapter(mMessageAdapter);
    }

    private void getNoticeList() {
        RetrofitUtil.getInstance().api().getNoticeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(MessageReturnBean messageReturnBean) {
                        if (messageReturnBean.isSuccess()) {
                            mMessageAdapter.setNewData(messageReturnBean.getData());
                        } else {
                            showMsg(messageReturnBean.getMessage());
                        }
                    }
                });
    }
    
    private void showPopView(){
        final PopupWindow popupWindow = new PopupWindow(MessageActivity.this);
        View contentView = LayoutInflater.from(MessageActivity.this).inflate(R.layout.popupwindow_noti_center, null, false);
        View readAll = contentView.findViewById(R.id.tv_read_all);
        readAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Network.getCustomManagerServece(MessageActivity.this).readAll()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new BaseSubscriber<DefaultReturnBean>(MessageActivity.this) {
//                            @Override
//                            public void onNext(DefaultReturnBean returnBean) {
//                                popupWindow.dismiss();
//                                resetData();
//                            }
//                        });
            }
        });
        View deleteAll = contentView.findViewById(R.id.tv_delete_all);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                CustomIconDialog.Builder builder = new CustomIconDialog.Builder(MessageActivity.this);
                builder.setMessage("确定要删除全部消息吗？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
//                        Network.getCustomManagerServece(MessageActivity.this).deleteAll()
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new BaseSubscriber<DefaultReturnBean>(MessageActivity.this) {
//                                    @Override
//                                    public void onNext(DefaultReturnBean returnBean) {
//                                        dialog.dismiss();
//                                        resetData();
//                                    }
//                                });
                    }
                });
                builder.create().show();
            }
        });
        popupWindow.setContentView(contentView);
        popupWindow.setHeight((int)(getResources().getDimension(R.dimen.y85)+0.5));
        popupWindow.setWidth((int)(getResources().getDimension(R.dimen.x124)+0.5));
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00FFFFFF")));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        setBackgroundAlpha(0.4f);
        popupWindow.update();
        popupWindow.showAsDropDown(ivRight,-(int)(getResources().getDimension(R.dimen.x85)+0.5),0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

}
