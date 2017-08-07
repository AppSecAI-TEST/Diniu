package com.workapp.auto.carterminal.module.main.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.workapp.auto.carterminal.R;
import com.workapp.auto.carterminal.base.BaseActivity;
import com.workapp.auto.carterminal.base.MyApplication;
import com.workapp.auto.carterminal.http.RetrofitUtil;
import com.workapp.auto.carterminal.module.main.bean.CarInfoCheckReturnBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wanggibin on 2017/8/7.
 */

public class PlayActivity extends BaseActivity {
    @Bind(R.id.video_view)
    VideoView videoView;
    @Bind(R.id.voide_layout)
    RelativeLayout voideLayout;
    private String videoPath;
    private Intent video_Intent;
    private MediaController mediaController;

    @Override
    protected int getLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setTitle("视频播放");
        video_Intent=getIntent();
    }

    @Override
    protected void initData() {
        RetrofitUtil.getInstance().api().checkCarLogs(video_Intent.getStringExtra("taskId"), "5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CarInfoCheckReturnBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
                    }

                    @Override
                    public void onNext(CarInfoCheckReturnBean carInfoCheckReturnBean) {
                        if (carInfoCheckReturnBean.isSuccess()) {
                            videoPath=carInfoCheckReturnBean.getData().getVideoUrl();
                            play();
                        } else {
                            showMsg(carInfoCheckReturnBean.getMessage());
                        }
                    }
                });
    }

    private void play() {
        Uri uri = Uri.parse(videoPath);
        showLoadingView();
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
//                mp.setLooping(true);
                hideLoadingView();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
//                videoView.setVideoPath(path);
//                videoView.start();
                hideLoadingView();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                String err = "未知错误";
                switch (what) {
                    case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                        break;
                    case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                        err = "媒体服务终止";
                        break;
                    case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                        err="请求超时";
                        break;
                    default:
                        break;
                }
                showMsg(err);
                return true;
            }
        });
    }

    @Override
    protected void initListener() {

    }

}
