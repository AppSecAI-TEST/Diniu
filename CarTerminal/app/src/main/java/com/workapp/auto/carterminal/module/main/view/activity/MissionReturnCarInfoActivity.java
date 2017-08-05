package com.workapp.auto.carterminal.module.main.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.ReturnCarDetailReturnBean;
import com.workapp.auto.carterminal.widget.SelectDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 还车信息-还车任务详情
 * Created by Administrator on 2017/8/3 0003.
 */

public class MissionReturnCarInfoActivity extends BaseActivity {
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
    @Bind(R.id.returnCarInfoAct_tv_carBodyPart_status)
    TextView tvCarBodyPartStatus;
    @Bind(R.id.returnCarInfoAct_tv_carBodyPart)
    TextView tvCarBodyPart;
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

    @Bind(R.id.returnCarInfoAct_radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.returnCarInfoAct_edt)
    EditText edt;
    @Bind(R.id.returnCarInfoAct_tv_edt_count)
    TextView tvEdtCount;
    @Bind(R.id.returnCarInfoAct_btn_submit)
    Button btnSubmit;


    private String mTaskId;//任务id
    private int mState = -1; //验车说明 0、正常入库 1、待维修 2、报废
    private int REQUEST_CODE=12;

    @Override
    protected int getLayout() {
        return R.layout.activity_return_car_info;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("还车信息");
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
            startActivity(intent);
        });
        llCarBodyPart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "2");
            intent.putExtra("taskId", mTaskId);
            startActivity(intent);
        });
        llCarInPart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "3");
            intent.putExtra("taskId", mTaskId);
            startActivity(intent);
        });
        llTools.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoCheckActivity.class);
            intent.putExtra("type", "4");
            intent.putExtra("taskId", mTaskId);
            startActivity(intent);
        });
        llCarData.setOnClickListener(v -> {
            /*Intent intent = new Intent(this,CarInfoCheckActivity.class);
            intent.putExtra("type","5");
            intent.putExtra("taskId",mTaskId);
            startActivity(intent);*/
            SelectDialog.Builder builder = new SelectDialog.Builder(this);
            builder.setNegativeButton(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                    Intent record=new Intent(MissionReturnCarInfoActivity.this,RecordActivity.class);
                    startActivityForResult(record,REQUEST_CODE);
                }
            });
           builder.setPositiveButton(new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
                   Intent pic=new Intent(MissionReturnCarInfoActivity.this,CarPictureActivity.class);
                   startActivityForResult(pic,REQUEST_CODE);
               }
           });
          builder.create().show();
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.returnCarInfoAct_radio_normal:
                        mState = 0;
                        break;
                    case R.id.returnCarInfoAct_radio_repair:
                        mState = 1;
                        break;
                    case R.id.returnCarInfoAct_radio_scrapped:
                        mState = 2;
                        break;
                    default:
                        break;
                }
            }
        });

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = edt.getText().toString();
                if (content.length() > 0) {
                    int size = 200 - content.length();
                    tvEdtCount.setText(size + "");
                } else {
                    tvEdtCount.setText("200");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit.setOnClickListener(v -> {
            submitData();
        });
    }

    private void getNetData() {
        RetrofitUtil.getInstance().api().returnCarReceiveDetail(mTaskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnCarDetailReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(ReturnCarDetailReturnBean returnCarDetailReturnBean) {
                        if (returnCarDetailReturnBean.isSuccess()) {
                            showView(returnCarDetailReturnBean);
                        } else {
                            showMsg(returnCarDetailReturnBean.getMessage());
                        }
                    }
                });
    }

    private void showView(ReturnCarDetailReturnBean returnCarDetailReturnBean) {
        ReturnCarDetailReturnBean.DataBean data = returnCarDetailReturnBean.getData();
        tvReturnSite.setText(data.getReturnSite());
        tvStartTime.setText(data.getStartTime());
        tvEndTime.setText(data.getEndTime());
        tvRentCarName.setText(data.getRentCarName());
        tvTel.setText(data.getTel());
        tvCarModel.setText(data.getCarModel());
        tvPlateNo.setText(data.getPlateNo());
        tvPower.setText(data.getPower() + "%");
        tvCanRange.setText(data.getCanRange() + "公里");

        switch (data.getCertificateClick()) {
            //0 未点击 1 正常 2异常
            case "0":
                ivCertificate.setImageResource(R.mipmap.book);
                tvCertificate.setTextColor(Color.parseColor("#989898"));
                tvCertificateStatus.setText("去检查");
                tvCertificateStatus.setTextColor(Color.parseColor("#989898"));
                break;
            case "1":
                ivCertificate.setImageResource(R.mipmap.book_01);
                tvCertificate.setTextColor(Color.parseColor("#363636"));
                tvCertificateStatus.setText("正常");
                tvCertificateStatus.setTextColor(Color.parseColor("#2c2c2c"));
                break;
            case "2":
                ivCertificate.setImageResource(R.mipmap.book_01);
                tvCertificate.setTextColor(Color.parseColor("#363636"));
                tvCertificateStatus.setText("异常");
                tvCertificateStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            default:
                break;
        }

        switch (data.getCarBodyPartClick()) {
            //0 未点击 1 正常 2异常
            case "0":
                ivCarBodyPart.setImageResource(R.mipmap.bady);
                tvCarBodyPart.setTextColor(Color.parseColor("#989898"));
                tvCarBodyPartStatus.setText("去检查");
                tvCarBodyPartStatus.setTextColor(Color.parseColor("#989898"));
                break;
            case "1":
                ivCarBodyPart.setImageResource(R.mipmap.bady_01);
                tvCarBodyPart.setTextColor(Color.parseColor("#363636"));
                tvCarBodyPartStatus.setText("正常");
                tvCarBodyPartStatus.setTextColor(Color.parseColor("#2c2c2c"));
                break;
            case "2":
                ivCarBodyPart.setImageResource(R.mipmap.bady_01);
                tvCarBodyPart.setTextColor(Color.parseColor("#363636"));
                tvCarBodyPartStatus.setText("异常");
                tvCarBodyPartStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            default:
                break;
        }

        switch (data.getCarInPartClick()) {
            //0 未点击 1 正常 2异常
            case "0":
                ivCarInPart.setImageResource(R.mipmap.inside);
                tvCarInPart.setTextColor(Color.parseColor("#989898"));
                tvCarInPartStatus.setText("去检查");
                tvCarInPartStatus.setTextColor(Color.parseColor("#989898"));
                break;
            case "1":
                ivCarInPart.setImageResource(R.mipmap.inside_01);
                tvCarInPart.setTextColor(Color.parseColor("#363636"));
                tvCarInPartStatus.setText("正常");
                tvCarInPartStatus.setTextColor(Color.parseColor("#2c2c2c"));
                break;
            case "2":
                ivCarInPart.setImageResource(R.mipmap.inside_01);
                tvCarInPart.setTextColor(Color.parseColor("#363636"));
                tvCarInPartStatus.setText("异常");
                tvCarInPartStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            default:
                break;
        }

        switch (data.getToolsClick()) {
            //0 未点击 1 正常 2异常
            case "0":
                ivTools.setImageResource(R.mipmap.tool);
                tvTools.setTextColor(Color.parseColor("#989898"));
                tvToolsStatus.setText("去检查");
                tvToolsStatus.setTextColor(Color.parseColor("#989898"));
                break;
            case "1":
                ivTools.setImageResource(R.mipmap.tool_01);
                tvTools.setTextColor(Color.parseColor("#363636"));
                tvToolsStatus.setText("正常");
                tvToolsStatus.setTextColor(Color.parseColor("#2c2c2c"));
                break;
            case "2":
                ivTools.setImageResource(R.mipmap.tool_01);
                tvTools.setTextColor(Color.parseColor("#363636"));
                tvToolsStatus.setText("异常");
                tvToolsStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            default:
                break;
        }

        switch (data.getCarDataClick()) {
            //0 未点击 1 正常 2异常
            case "0":
                ivCarData.setImageResource(R.mipmap.information);
                tvCarData.setTextColor(Color.parseColor("#989898"));
                tvCarDataStatus.setText("去上传");
                tvCarDataStatus.setTextColor(Color.parseColor("#989898"));
                break;
            case "1":
                ivCarData.setImageResource(R.mipmap.information_01);
                tvCarData.setTextColor(Color.parseColor("#363636"));
                tvCarDataStatus.setText("正常");
                tvCarDataStatus.setTextColor(Color.parseColor("#2c2c2c"));
                break;
            case "2":
                ivCarData.setImageResource(R.mipmap.information_01);
                tvCarData.setTextColor(Color.parseColor("#363636"));
                tvCarDataStatus.setText("异常");
                tvCarDataStatus.setTextColor(Color.parseColor("#ff4141"));
                break;
            default:
                break;
        }


    }

    private void submitData() {
        if (mState == -1) {
            showMsg("请选择验车说明");
            return;
        }
        RetrofitUtil.getInstance().api().updateCarStatus(mTaskId, String.valueOf(mState), edt.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse.isSuccess()) {
                            showMsg("提交成功");
                        } else {
                            showMsg(baseResponse.getMessage());
                        }
                    }
                });
    }

}
