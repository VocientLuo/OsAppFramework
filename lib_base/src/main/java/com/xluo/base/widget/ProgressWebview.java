package com.xluo.base.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.ToastUtils;

public class ProgressWebview extends WebView {
    private ProgressView progressView;//进度条
    private Context context;
    private String data = "";

    private boolean mLightModeStatus = false;// 是否配置黑天模式

    private WebViewListener webViewListener;

    public ProgressWebview(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ProgressWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ProgressWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public void setWebViewListener(WebViewListener webViewListener) {
        this.webViewListener = webViewListener;
    }

    private void init() {
        //初始化进度条
        progressView = new ProgressView(context);
        progressView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, 4)));
        progressView.setColor(Color.BLUE);
        progressView.setProgress(10);
        //把进度条加到Webview中
        addView(progressView);
        //初始化设置
        initWebSettings();
        setWebChromeClient(new MyWebCromeClient());
        setWebViewClient(new MyWebviewClient());
    }

    private void initWebSettings() {
        WebSettings settings = getSettings();
        //默认是false 设置true允许和js交互
        settings.setJavaScriptEnabled(true);
        //  WebSettings.LOAD_DEFAULT 如果本地缓存可用且没有过期则使用本地缓存，否加载网络数据 默认值
        //  WebSettings.LOAD_CACHE_ELSE_NETWORK 优先加载本地缓存数据，无论缓存是否过期
        //  WebSettings.LOAD_NO_CACHE  只加载网络数据，不加载本地缓存
        //  WebSettings.LOAD_CACHE_ONLY 只加载缓存数据，不加载网络数据
        //Tips:有网络可以使用LOAD_DEFAULT 没有网时用LOAD_CACHE_ELSE_NETWORK
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启 DOM storage API 功能 较大存储空间，使用简单
        settings.setDomStorageEnabled(true);
        //开启 Application Caches 功能 方便构建离线APP 不推荐使用
//        settings.setAppCacheEnabled(true);
        settings.setBlockNetworkImage(false);//解决图片不显示
        final String cachePath = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
//        settings.setAppCachePath(cachePath);
//        settings.setAppCacheMaxSize(5 * 1024 * 1024);
        //设置数据库缓存路径 存储管理复杂数据 方便对数据进行增加、删除、修改、查询 不推荐使用
        settings.setDatabaseEnabled(true);
        final String dbPath = context.getApplicationContext().getDir("db", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dbPath);
    }

    //weui-flex__item

    private class MyWebCromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                //加载完毕进度条消失
                progressView.setVisibility(View.GONE);
            } else {
                //更新进度
                progressView.setProgress(newProgress);
            }
            //移除网页中的阅读原文元素
            view.loadUrl("javascript:function setTop(){document.querySelector('.weui-flex__item').style.display=\"none\";}setTop();");
            super.onProgressChanged(view, newProgress);
        }


        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (webViewListener != null) {
                webViewListener.openFile(filePathCallback, fileChooserParams);
            }
            return true;
        }

    }

    /**
     * 设置隐私政策里的权限说明数据
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 设置黑白天主题样式
     */
    public void setStatusLightMode(boolean status){
        this.mLightModeStatus = status;
    }

    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                String mobile = url.substring(url.lastIndexOf(":") + 1);
                ClipboardUtils.copyText(mobile);
                ToastUtils.showShort("复制成功");
                openBrowser(getContext(), url);
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:function setTop(){document.querySelector('.weui-flex__item').style.display=\"none\";}setTop();");
            if (!TextUtils.isEmpty(data)) {
                String js = "window.localStorage.setItem('privacyKey','" + data + "');";
                String jsUrl = "javascript:(function({ var localStorage = window.localStorage; localStorage.setItem('privacyKey','" + data + "') })()";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    view.evaluateJavascript(js, null);
                } else {
                    view.loadUrl(jsUrl);
                    view.reload();
                }
                //通过js注入修改网页背景颜色和文字颜色
                if (mLightModeStatus){
                    //黑天
                    loadUrl("javascript:function getSub(){" +
                            "document.getElementsByTagName('body')[0].style.color='#FFFFFF'" +
                            "};getSub();");
                    loadUrl("javascript:function getSub(){" +
                            "document.getElementsByTagName('body')[0].style.background='#000000'" +
                            "};getSub();");
                } else {
                    //白天
                    loadUrl("javascript:function getSub(){" +
                            "document.getElementsByTagName('body')[0].style.color='#000000'" +
                            "};getSub();");
                    loadUrl("javascript:function getSub(){" +
                            "document.getElementsByTagName('body')[0].style.background='#FFFFFF'" +
                            "};getSub();");
                }
            }
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

    }

    public void openBrowser(Context context, String url) {
        try {
            final Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } catch (Exception e) {

        }
    }

    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public interface WebViewListener {

        /**
         * 触发了打开文件选择的操作
         *
         * @param filePathCallback
         * @param fileChooserParams
         */
        void openFile(ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams);


    }
}
