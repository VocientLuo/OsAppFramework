package com.xluo.osframework

import androidx.appcompat.app.AppCompatDelegate
import com.xluo.base.AppConf
import com.xluo.base.BaseApplication

class MyApplication: BaseApplication() {
    override fun getDayNightMode(): Int {
        return AppCompatDelegate.MODE_NIGHT_NO
    }

    override fun onCreate() {
        super.onCreate()
        initAppConf()
    }

    private fun initAppConf() {
        AppConf.PRIVACY_URL = BuildConfig.PRIVACY_URL
        AppConf.USER_PROTOCOL = BuildConfig.USER_PROTOCOL
    }

    override fun initOther() {

    }
}