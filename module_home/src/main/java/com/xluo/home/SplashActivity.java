package com.xluo.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xluo.base.utils.MmkvUtils;
import com.xluo.base.utils.MyStatusBarUtils;
import com.xluo.base.utils.PrivacyConstantsUtils;
import com.xluo.base.utils.PrivacyDialogBuilder;
import com.xluo.base.utils.PrivacyDialogConfig;
import com.xluo.base.widget.PrivacyAgreementDialog;
import com.xluo.home.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyStatusBarUtils.setStatusBar(this, "#00FFFFFF");
        showDialog();
    }

    private void showDialog() {
        if (!MmkvUtils.get(PrivacyConstantsUtils.MMKVKEY.privacyDialogIsShow.name(), false)) {
            PrivacyAgreementDialog privacyAgreementDialog = PrivacyDialogBuilder.create(this)
                    .setDialogStyle(PrivacyDialogConfig.DIALOG_STYLE_WHITE_1)
                    .setCustomJump(false)
                    .setPrivacyDialogListener(new PrivacyDialogConfig.PrivacyDialogListener() {
                        @Override
                        public void cancel() {
                            finish();
                        }

                        @Override
                        public void confirm() {
                            doSomething();
                        }

                        @Override
                        public void clickPrivacyText() {

                        }

                        @Override
                        public void clickAgreementText() {

                        }
                    })
                    .build();
            privacyAgreementDialog.setCancelable(false);
            privacyAgreementDialog.show();
        } else {
            doSomething();
        }
    }

    private void goMain() {
//        进入业务模块
//        finish();
    }


    @Override
    public void onBackPressed() {
        //启动页不响应返回操作
    }

    private void doSomething() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        goMain();
                    }
                });
            }
        }.start();
    }
}