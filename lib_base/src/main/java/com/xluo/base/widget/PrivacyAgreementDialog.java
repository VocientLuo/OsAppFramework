package com.xluo.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;

import com.xluo.base.R;
import com.xluo.base.ui.OsWebActivity;
import com.xluo.base.utils.PrivacyConstantsUtils;
import com.xluo.base.utils.PrivacyDialogConfig;
import com.tencent.mmkv.MMKV;

import java.util.Locale;

/**
 * 隐私政策弹框
 */
public class PrivacyAgreementDialog extends Dialog implements View.OnClickListener {

    private PrivacyDialogConfig.PrivacyDialogListener privacyDialogListener;
    private String content = "";
    private TextView tvContent;
    PrivacyDialogConfig privacyDialogConfig;
    private TextView dialogTitle;
    private Button btnCancel;
    private Button btnConfirm;
    private int blackBgTextColor = Color.parseColor("#A89556");
    private int whiteBgTextColor = Color.parseColor("#1DB1FF");
    private int foregroundColor;

    public PrivacyAgreementDialog(@NonNull Context context, PrivacyDialogConfig privacyDialogConfig) {
        super(context, R.style.StyleBaseDialog);
        this.privacyDialogConfig = privacyDialogConfig;
        setPrivacyDialogListener(privacyDialogConfig.getPrivacyDialogListener());
    }


    public void setPrivacyDialogListener(PrivacyDialogConfig.PrivacyDialogListener privacyDialogListener) {
        this.privacyDialogListener = privacyDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_privacy_white_style1_layout);
        foregroundColor = whiteBgTextColor;
        tvContent = findViewById(R.id.tvContent);
        dialogTitle = findViewById(R.id.dialogTitle);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        content = getContext().getResources().getString(R.string.privacyTipsText);
        SpannableString spannableString = new SpannableString(content);
        ClickableSpan clickableSpanPrivacy = new ClickableSpan() {

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(foregroundColor);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View view) {
                if (privacyDialogConfig.getCustomJump()) {
                    if (privacyDialogListener != null) {
                        privacyDialogListener.clickPrivacyText();
                    }
                } else {
                    OsWebActivity.start(getContext(), PrivacyConstantsUtils.ProtocolType.PRIVACY);
                }
            }
        };
        ClickableSpan clickableSpanAgreement = new ClickableSpan() {

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setColor(foregroundColor);
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View view) {
                if (privacyDialogConfig.getCustomJump()) {
                    if (privacyDialogListener != null) {
                        privacyDialogListener.clickAgreementText();
                    }
                } else {
                    OsWebActivity.start(getContext(), PrivacyConstantsUtils.ProtocolType.USER_PROTOCOL);
                }
            }
        };
        int userAgreementStart;
        int userAgreementEnd;
        int privacyStart;
        int privacyEnd;
        String language = Locale.getDefault().getLanguage();
        if (TextUtils.equals("CHN",language)){
            userAgreementStart = content.indexOf("《用户协议》");
            userAgreementEnd = userAgreementStart + 6;
            privacyStart = content.indexOf("《隐私政策》");
            privacyEnd = privacyStart + 6;
        }else {
            userAgreementStart = content.indexOf("User Agreement");
            userAgreementEnd = userAgreementStart + 14;
            privacyStart = content.indexOf("Privacy Policy");
            privacyEnd = privacyStart + 14;
        }
        try{
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(foregroundColor);
            spannableString.setSpan(colorSpan, userAgreementStart, userAgreementEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(colorSpan, privacyStart, privacyEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(clickableSpanAgreement, userAgreementStart, userAgreementEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(clickableSpanPrivacy, privacyStart, privacyEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        }catch (Throwable e) {
            e.printStackTrace();
        }
        tvContent.setText(spannableString);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnCancel) {
            if (privacyDialogListener != null) {
                privacyDialogListener.cancel();
            }
            dismiss();
        } else if (i == R.id.btnConfirm) {
            MMKV kv = MMKV.defaultMMKV();
            kv.encode(PrivacyConstantsUtils.MMKVKEY.privacyDialogIsShow.name(), true);
            if (privacyDialogListener != null) {
                privacyDialogListener.confirm();
            }
            dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("onStart: "+getWindow().getDecorView().getHeight());
    }
}
