package com.workapp.auto.carterminal.module.main.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.BaseResponse;
import com.workapp.auto.carterminal.http.ProgressSubscriber;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.utils.BitmapUtil;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanggibin on 2017/8/4.
 */

public class CarPictureActivity extends BaseActivity {
    @Bind(R.id.tv_carmodel)
    TextView tvCarmodel;
    @Bind(R.id.iv_pic1)
    ImageView ivPic1;
    @Bind(R.id.btn_pic1)
    Button btnPic1;
    @Bind(R.id.iv_pic2)
    ImageView ivPic2;
    @Bind(R.id.btn_pic2)
    Button btnPic2;
    @Bind(R.id.iv_pic3)
    ImageView ivPic3;
    @Bind(R.id.btn_pic3)
    Button btnPic3;
    @Bind(R.id.iv_pic4)
    ImageView ivPic4;
    @Bind(R.id.btn_pic4)
    Button btnPic4;
    private static final int TAKE_CAPTURE = 1;
    private static final int TAKE_CAPTURE2 = 2;
    private static final int TAKE_CAPTURE3 = 3;
    private static final int TAKE_CAPTURE4 = 4;
    private String srcPath1;
    private String srcPath2;
    private String srcPath3;
    private String srcPath4;
    private String taskId;

    @Override
    protected int getLayout() {
        return R.layout.activity_carpicture;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("照片上传");
        taskId = getIntent().getStringExtra("taskId");
        showRightTitle("完成", new RightClick() {
            @Override
            public void click(View view) {
                uploadPic();
            }
        });
    }

    private void uploadPic() {
        if (TextUtils.isEmpty(srcPath1)) {
            showMsg("车身前侧照片未添加");
            return;
        }
        if (TextUtils.isEmpty(srcPath2)) {
            showMsg("车身左侧照片未添加");
            return;
        }
        if (TextUtils.isEmpty(srcPath3)) {
            showMsg("车身后侧照片未添加");
            return;
        }
        if (TextUtils.isEmpty(srcPath4)) {
            showMsg("车身右侧照片未添加");
            return;
        }
        showLoadingView();
        File file1 = new File(srcPath1);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        File file2 = new File(srcPath2);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        File file3 = new File(srcPath3);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), file3);
        File file4 = new File(srcPath4);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), file4);
        RetrofitUtil.getInstance().api().postImages(taskId, requestBody1, requestBody2, requestBody3, requestBody4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BaseResponse>(CarPictureActivity.this) {
                    @Override
                    public void onError(Throwable e) {
                        hideLoadingView();
                        showMsg(e.getMessage());
                        Log.e("CarPictureActivity", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        hideLoadingView();
                        if (baseResponse.getCode() == 0) {
                            showMsg("图片上传成功");
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            showMsg(baseResponse.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.btn_pic1, R.id.btn_pic2, R.id.btn_pic3, R.id.btn_pic4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pic1:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //图片扑获
                startActivityForResult(intent, TAKE_CAPTURE);// 意图，返回码；
                break;
            case R.id.btn_pic2:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //图片扑获
                startActivityForResult(intent2, TAKE_CAPTURE2);// 意图，返回码；
                break;
            case R.id.btn_pic3:
                Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //图片扑获
                startActivityForResult(intent3, TAKE_CAPTURE3);// 意图，返回码；
                break;
            case R.id.btn_pic4:
                Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //图片扑获
                startActivityForResult(intent4, TAKE_CAPTURE4);// 意图，返回码；
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_CAPTURE:
                    if (data != null) {
                        if (data.hasExtra("data")) { //intent-> key(data):value(bitmap)
                            Bitmap bitmap = data.getParcelableExtra("data");
                            btnPic1.setText("重新上传");
                            ivPic1.setImageBitmap(bitmap);
                            BitmapUtil.saveBitmap(bitmap, this);
                            srcPath1 = BitmapUtil.getSrcPath();
                        }
                    }
                    break;
                case TAKE_CAPTURE2:
                    if (data != null) {
                        if (data.hasExtra("data")) { //intent-> key(data):value(bitmap)
                            Bitmap bitmap = data.getParcelableExtra("data");
                            btnPic2.setText("重新上传");
                            ivPic2.setImageBitmap(bitmap);
                            BitmapUtil.saveBitmap(bitmap, this);
                            srcPath2 = BitmapUtil.getSrcPath();
                        }
                    }
                    break;
                case TAKE_CAPTURE3:
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Bitmap bitmap = data.getParcelableExtra("data");
                            btnPic3.setText("重新上传");
                            ivPic3.setImageBitmap(bitmap);
                            BitmapUtil.saveBitmap(bitmap, this);
                            srcPath3 = BitmapUtil.getSrcPath();
                        }
                    }
                    break;
                case TAKE_CAPTURE4:
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Bitmap bitmap = data.getParcelableExtra("data");
                            btnPic4.setText("重新上传");
                            ivPic4.setImageBitmap(bitmap);
                            BitmapUtil.saveBitmap(bitmap, this);
                            srcPath4 = BitmapUtil.getSrcPath();
                        }
                    }
                    break;
            }
        }
    }
}
