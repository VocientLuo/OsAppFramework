package com.xluo.base;

import android.app.Application;
import android.graphics.Color;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatDelegate;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mmkv.MMKV;

public abstract class BaseApplication extends Application {

    public static BaseApplication instance;

    @AppCompatDelegate.NightMode
    public abstract int getDayNightMode();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initSDK();
        initOther();
    }

    private void initSDK() {
        AppCompatDelegate.setDefaultNightMode(getDayNightMode());
        ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.getDefaultMaker().setBgColor(Color.BLACK);
        ToastUtils.getDefaultMaker().setTextColor(Color.WHITE);
        LogUtils.getConfig().setLogSwitch(false);
        MMKV.initialize(this);
    }

    public void initOther() {

    }

}
