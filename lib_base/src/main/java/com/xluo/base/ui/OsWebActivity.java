package com.xluo.base.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.xluo.base.AppConf;
import com.xluo.base.BaseActivity;
import com.xluo.base.R;
import com.xluo.base.utils.PrivacyConstantsUtils;
import com.xluo.base.widget.MyActionBar;
import com.xluo.base.widget.ProgressWebview;

public class OsWebActivity extends BaseActivity {

    private static final String TYPE = "type";

    private ProgressWebview webView;

    private MyActionBar myActionBar;
    private String currentType = "";
    private static final String privacyUrl = "https://www.baidu.com";
    private static final String userProtocolUrl = "https://www.baidu.com";

    /**
     * 跳转协议
     *
     * @param context
     * @param type
     */
    public static void start(Context context, PrivacyConstantsUtils.ProtocolType type) {
        Intent intent = new Intent(context, OsWebActivity.class);
        intent.putExtra(TYPE, type.name());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_web);
        myActionBar = findViewById(R.id.myActionBar);
        currentType = getIntent().getStringExtra(TYPE);
        String contentUrl = "";
        if (PrivacyConstantsUtils.ProtocolType.PRIVACY.name().equals(currentType)) {
            contentUrl = privacyUrl;
            if (!TextUtils.isEmpty(AppConf.PRIVACY_URL)) {
                contentUrl = AppConf.PRIVACY_URL;
            }
            myActionBar.setTitle(getString(R.string.setting_privacy));
        } else if (PrivacyConstantsUtils.ProtocolType.USER_PROTOCOL.name().equals(currentType)) {
            contentUrl = userProtocolUrl;
            if (!TextUtils.isEmpty(AppConf.USER_PROTOCOL)) {
                contentUrl = AppConf.USER_PROTOCOL;
            }
            myActionBar.setTitle(getString(R.string.setting_protocol));
        }
        webView = new ProgressWebview(this);
        webView.loadUrl(contentUrl);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
    }
}