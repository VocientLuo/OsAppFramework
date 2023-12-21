package com.xluo.base.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.allen.library.SuperTextView;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.xluo.base.BaseActivity;
import com.xluo.base.R;
import com.xluo.base.utils.PrivacyConstantsUtils;

public class AboutUsActivity extends BaseActivity {

    private ImageView icon;
    private TextView appName;
    private TextView appVersion;
    private SuperTextView stPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initStatusBarColor();
        icon = findViewById(R.id.icon);
        appName = findViewById(R.id.appName);
        appVersion = findViewById(R.id.appVersion);
        stPrivacy = findViewById(R.id.stPrivacy);
        stPrivacy.setOnClickListener(v -> OsWebActivity.start(AboutUsActivity.this, PrivacyConstantsUtils.ProtocolType.PRIVACY));
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion.setText(packageInfo.versionName);
            Drawable iconDrawable = AppUtils.getAppIcon();
            if (iconDrawable != null) {
                icon.setImageDrawable(iconDrawable);
            }
            String name = AppUtils.getAppName();
            if (!TextUtils.isEmpty(name)) {
                appName.setText(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initStatusBarColor() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            //白天
            BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.white));
            BarUtils.setStatusBarLightMode(this, true);
        } else {
            //黑天
            BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.black));
            BarUtils.setStatusBarLightMode(this, false);
        }
    }
}
