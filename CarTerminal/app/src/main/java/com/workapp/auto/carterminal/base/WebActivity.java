package com.workapp.auto.carterminal.base;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.workapp.auto.carterminal.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 网页
 * Created by Administrator on 2017/8/8 0008.
 */

public class WebActivity extends BaseActivity {
    @Bind(R.id.webAct_webView)
    WebView webView;

    private String mUrl;
    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    protected void initData() {
        mUrl = getIntent().getStringExtra("url");
        webView.loadUrl(mUrl);
    }

    @Override
    protected void initListener() {

    }

}
