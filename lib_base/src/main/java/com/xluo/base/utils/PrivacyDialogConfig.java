package com.xluo.base.utils;

import android.graphics.Color;

import com.xluo.base.R;

public class PrivacyDialogConfig {

    /**
     * 黑色风格
     */
    public static int DIALOG_STYLE_BLACK = 1;
    /**
     * 白色风格1
     */
    public static int DIALOG_STYLE_WHITE_1 = 2;
    /**
     * 白色风格2
     */
    public static int DIALOG_STYLE_WHITE_2 = 3;


    private String confirmName = "阅读并同意";
    private String cancelName = "拒绝并退出";
    private String dialogTitle = "温馨提示";
    private PrivacyDialogListener privacyDialogListener;
    private String replayCompanyName = "";//替换的公司的名字
    private String replayAppName = "";//替换的产品的名字
    private Boolean isCustomJump = false;//是否要自定义界面跳转事件
    private int backIconId = R.drawable.base_fanhui;
    private int barColor = Color.parseColor("#ffffff");
    private int dialogStyle = DIALOG_STYLE_WHITE_1; //默认白色风格


    public PrivacyDialogConfig() {

    }

    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    public String getCancelName() {
        return cancelName;
    }

    public void setCancelName(String cancelName) {
        this.cancelName = cancelName;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public String getReplayCompanyName() {
        return replayCompanyName;
    }

    public void setReplayCompanyName(String replayCompanyName) {
        this.replayCompanyName = replayCompanyName;
    }

    public String getReplayAppName() {
        return replayAppName;
    }

    public void setReplayAppName(String replayAppName) {
        this.replayAppName = replayAppName;
    }

    public PrivacyDialogListener getPrivacyDialogListener() {
        return privacyDialogListener;
    }

    public void setPrivacyDialogListener(PrivacyDialogListener privacyDialogListener) {
        this.privacyDialogListener = privacyDialogListener;
    }

    public Boolean getCustomJump() {
        return isCustomJump;
    }

    public void setCustomJump(Boolean customJump) {
        isCustomJump = customJump;
    }

    public void setBackIconId(int backIconId) {
        this.backIconId = backIconId;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    public int getBackIconId() {
        return backIconId;
    }

    public int getBarColor() {
        return barColor;
    }

    public int getDialogStyle() {
        return dialogStyle;
    }

    public void setDialogStyle(int dialogStyle) {
        this.dialogStyle = dialogStyle;
    }

    public interface PrivacyDialogListener {

        /**
         * 点击取消按钮
         */
        void cancel();

        /**
         * 点击取消按钮
         */
        void confirm();

        /**
         * 点击隐私政策文字,仅在自定义跳转事件时生效
         */
        void clickPrivacyText();

        /**
         * 点击用户协议文字,仅在自定义跳转事件时生效
         */
        void clickAgreementText();

    }

}
