package com.workapp.auto.carterminal.module.main.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.ProgressSubscriber;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.FindReturnCarDetailReturnBean;
import com.workapp.auto.carterminal.widget.SelectDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 还车——已完成页
 * Created by Administrator on 2017/8/3 0003.
 */

public class ReturnCarCompleteActivity extends BaseActivity {


    @Bind(R.id.returnCarInfoAct_tv_returnSite)
    TextView tvReturnSite;
    @Bind(R.id.returnCarInfoAct_tv_startTime)
    TextView tvStartTime;
    @Bind(R.id.returnCarInfoAct_tv_endTime)
    TextView tvEndTime;
    @Bind(R.id.returnCarInfoAct_tv_rentCarName)
    TextView tvRentCarName;
    @Bind(R.id.returnCarInfoAct_tv_tel)
    TextView tvTel;
    @Bind(R.id.returnCarInfoAct_tv_carModel)
    TextView tvCarModel;
    @Bind(R.id.returnCarInfoAct_tv_plateNo)
    TextView tvPlateNo;
    @Bind(R.id.returnCarInfoAct_tv_power)
    TextView tvPower;
    @Bind(R.id.returnCarInfoAct_tv_canRange)
    TextView tvCanRange;
    @Bind(R.id.returnCarInfoAct_iv_certificate)
    ImageView ivCertificate;
    @Bind(R.id.returnCarInfoAct_tv_certificate)
    TextView tvCertificate;
    @Bind(R.id.returnCarInfoAct_tv_certificate_status)
    TextView tvCertificateStatus;
    @Bind(R.id.returnCarInfoAct_ll_certificate)
    LinearLayout llCertificate;
    @Bind(R.id.returnCarInfoAct_iv_carBodyPart)
    ImageView ivCarBodyPart;
    @Bind(R.id.returnCarInfoAct_tv_carBodyPart)
    TextView tvCarBodyPart;
    @Bind(R.id.returnCarInfoAct_tv_carBodyPart_status)
    TextView tvCarBodyPartStatus;
    @Bind(R.id.returnCarInfoAct_ll_carBodyPart)
    LinearLayout llCarBodyPart;
    @Bind(R.id.returnCarInfoAct_iv_carInPart)
    ImageView ivCarInPart;
    @Bind(R.id.returnCarInfoAct_tv_carInPart)
    TextView tvCarInPart;
    @Bind(R.id.returnCarInfoAct_tv_carInPart_status)
    TextView tvCarInPartStatus;
    @Bind(R.id.returnCarInfoAct_ll_carInPart)
    LinearLayout llCarInPart;
    @Bind(R.id.returnCarInfoAct_iv_tools)
    ImageView ivTools;
    @Bind(R.id.returnCarInfoAct_tv_tools)
    TextView tvTools;
    @Bind(R.id.returnCarInfoAct_tv_tools_status)
    TextView tvToolsStatus;
    @Bind(R.id.returnCarInfoAct_ll_tools)
    LinearLayout llTools;
    @Bind(R.id.returnCarInfoAct_iv_carData)
    ImageView ivCarData;
    @Bind(R.id.returnCarInfoAct_tv_carData)
    TextView tvCarData;
    @Bind(R.id.returnCarInfoAct_tv_carData_status)
    TextView tvCarDataStatus;
    @Bind(R.id.returnCarInfoAct_ll_carData)
    LinearLayout llCarData;
    @Bind(R.id.returnCarInfoAct_tv_status)
    TextView tvStatus;
    @Bind(R.id.returnCarInfoAct_tv_remarks)
    TextView tvRemarks;
    private String mTaskId;//任务id
    private String pic_url1;
    private String pic_url2;
    private String pic_url3;
    private String pic_url4;
    private String video_url;

    @Override
    protected int getLayout() {
        return R.layout.activity_return_car_complete;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("已完成");
    }

    @Override
    protected void initData() {
        mTaskId = getIntent().getStringExtra("taskId");
        getNetData();
    }

    @Override
    protected void initListener() {
        tvTel.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvTel.getText().toString()));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        });

        llCertificate.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "1");
            intent.putExtra("taskId", mTaskId);
            intent.putExtra("isCanSelect",false);
            startActivity(intent);
        });
        llCarBodyPart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "2");
            intent.putExtra("taskId", mTaskId);
            intent.putExtra("isCanSelect",false);
            startActivity(intent);
        });
        llCarInPart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "3");
            intent.putExtra("taskId", mTaskId);
            intent.putExtra("isCanSelect",false);
            startActivity(intent);
        });
        llTools.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "4");
            intent.putExtra("taskId", mTaskId);
            intent.putExtra("isCanSelect",false);
            startActivity(intent);
        });
        llCarData.setOnClickListener(v -> {
            SelectDialog.Builder builder = new SelectDialog.Builder(this);
            builder.setNegativeButton(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent record=new Intent(ReturnCarCompleteActivity.this,PlayActivity.class);
                    record.putExtra("taskId",mTaskId);
                    startActivityForResult(record,0);
                }
            });
            builder.setPositiveButton(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent pic=new Intent(ReturnCarCompleteActivity.this,CarPictureDetailActivity.class);
                    pic.putExtra("taskId",mTaskId);
                    startActivityForResult(pic,0);
                }
            });
            builder.create().show();
        });

    }

    private void getNetData() {
        RetrofitUtil.getInstance().api().findReturnCarDetail(mTaskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<FindReturnCarDetailReturnBean>(ReturnCarCompleteActivity.this) {
                    @Override
                    public void onNext(FindReturnCarDetailReturnBean findReturnCarDetailReturnBean) {
                        if (findReturnCarDetailReturnBean.isSuccess()) {
                            showView(findReturnCarDetailReturnBean);
                        } else {
                            showMsg(findReturnCarDetailReturnBean.getMessage());
                        }
                    }
                });
    }

    private void showView(FindReturnCarDetailReturnBean findReturnCarDetailReturnBean) {
        FindReturnCarDetailReturnBean.DataBean data = findReturnCarDetailReturnBean.getData();
        tvReturnSite.setText(data.getReturnSite());
        tvStartTime.setText(data.getStartTime());
        tvEndTime.setText(data.getEndTime());
        tvRentCarName.setText(data.getRentCarName());
        tvTel.setText(data.getTel());
        tvCarModel.setText(data.getCarModel());
        tvPlateNo.setText(data.getPlateNo());
        tvPower.setText(data.getPower() + "%");
        tvCanRange.setText(data.getCanRange() + "公里");
        if (data.isCertificate()) {
            tvCertificateStatus.setText("正常");
            tvCertificateStatus.setTextColor(Color.parseColor("#2c2c2c"));
        } else {
            tvCertificateStatus.setText("异常");
            tvCertificateStatus.setTextColor(Color.parseColor("#ff4141"));
        }

        if (data.isBodywork()) {
            tvCarBodyPartStatus.setText("正常");
            tvCarBodyPartStatus.setTextColor(Color.parseColor("#2c2c2c"));
        } else {
            tvCarBodyPartStatus.setText("异常");
            tvCarBodyPartStatus.setTextColor(Color.parseColor("#ff4141"));
        }

        if (data.isCarParts()) {
            tvCarInPartStatus.setText("正常");
            tvCarInPartStatus.setTextColor(Color.parseColor("#2c2c2c"));
        } else {
            tvCarInPartStatus.setText("异常");
            tvCarInPartStatus.setTextColor(Color.parseColor("#ff4141"));
        }

        if (data.isToolObject()) {
            tvToolsStatus.setText("正常");
            tvToolsStatus.setTextColor(Color.parseColor("#2c2c2c"));
        } else {
            tvToolsStatus.setText("异常");
            tvToolsStatus.setTextColor(Color.parseColor("#ff4141"));
        }

        if (data.isCarData()) {
            tvCarDataStatus.setText("正常");
            tvCarDataStatus.setTextColor(Color.parseColor("#2c2c2c"));
        } else {
            tvCarDataStatus.setText("异常");
            tvCarDataStatus.setTextColor(Color.parseColor("#ff4141"));
        }

        switch (data.getState()) {
            //验车说明 0 正常入库 1 待维修 2 报废
            case "0":
                tvStatus.setText("正常入库");
                tvStatus.setTextColor(Color.parseColor("#5e9ed8"));
                break;
            case "1":
                tvStatus.setText("待维修");
                tvStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            case "2":
                tvStatus.setText("报废");
                tvStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            default:
                tvStatus.setText("正常入库");
                tvStatus.setTextColor(Color.parseColor("#5e9ed8"));
                break;
        }

        tvRemarks.setText(data.getRemarks());
    }


}
