package com.xluo.base.utils;

import android.content.Context;

import com.xluo.base.widget.PrivacyAgreementDialog;

public class PrivacyDialogBuilder {

    private PrivacyDialogConfig privacyDialogConfig;
    private PrivacyAgreementDialog privacyAgreementDialog;
    private Context context;

    private PrivacyDialogBuilder(Context context) {
        this.context = context;
        this.privacyDialogConfig = new PrivacyDialogConfig();
    }

    public static PrivacyDialogBuilder create(Context context) {
        return new PrivacyDialogBuilder(context);
    }

    public PrivacyDialogBuilder setDialogTitle(String title) {
        privacyDialogConfig.setDialogTitle(title);
        return this;
    }

    public PrivacyDialogBuilder setCancelName(String name) {
        privacyDialogConfig.setCancelName(name);
        return this;
    }

    public PrivacyDialogBuilder setConfirmName(String name) {
        privacyDialogConfig.setConfirmName(name);
        return this;
    }

    public PrivacyDialogBuilder setPrivacyDialogListener(PrivacyDialogConfig.PrivacyDialogListener privacyDialogListener) {
        privacyDialogConfig.setPrivacyDialogListener(privacyDialogListener);
        return this;
    }

    public PrivacyDialogBuilder setReplayCompanyName(String replayCompanyName) {
        privacyDialogConfig.setReplayCompanyName(replayCompanyName);
        return this;
    }


    public PrivacyDialogBuilder setReplayAppName(String replayAppName) {
        privacyDialogConfig.setReplayAppName(replayAppName);
        return this;
    }

    public PrivacyDialogBuilder setCustomJump(Boolean customJump) {
        privacyDialogConfig.setCustomJump(customJump);
        return this;
    }


    public PrivacyDialogBuilder setBackIconId(int backIconId) {
        privacyDialogConfig.setBackIconId(backIconId);
        return this;
    }

    public PrivacyDialogBuilder setBarColor(int barColor) {
        privacyDialogConfig.setBarColor(barColor);
        return this;
    }

    public PrivacyDialogBuilder setDialogStyle(int stylePosition) {
        privacyDialogConfig.setDialogStyle(stylePosition);
        return this;
    }

    public PrivacyAgreementDialog build() {
        privacyAgreementDialog = new PrivacyAgreementDialog(context,privacyDialogConfig);
        privacyAgreementDialog.setCancelable(true);
        return privacyAgreementDialog;
    }


}
