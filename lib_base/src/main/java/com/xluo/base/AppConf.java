package com.xluo.base;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class AppConf {


    public static String APP_NAME = "";
    public static String APP_COMPANY = "";
    public static Boolean APP_DEBUG = false;
    public static String APPLICATION_ID = "";
    public static String CHANNEL = "";
    public static int VERSION_CODE = 0;

    public static String ICP = "";

    /**
     * 测试环境的服务器地址   目前后台已全部支持https  所以这里默认要为https  就不用在进行加密处理
     */
    public static String BASE_DEBUG_URL = "";

    /**
     * 正式环境的服务器地址
     */
    public static String BASE_RELEASE_URL = "";


    public static String HTTP_HOST = "";


    /**
     * 微信支付的ID
     */
    public static String WECHAT_APP_ID = "";

    /**
     * 微信支付的secret
     */
    public static String WECHAT_APP_SECRET = "";

    /**
     * 一键登录的key
     */
    public static String ALI_LOGIN_KEY = "";


    /**
     * 支付渠道  1表示微信
     */
    public static String PAY_CHANNEL_WECHAT = "1";
    /**
     * 支付渠道  2表示支付宝
     */
    public static String PAY_CHANNEL_ZFB = "2";

    /**
     * 新增小程序支付，需要把服务器地址处理一下 获取域名
     *
     * @return
     */
    public static String getWechatMiniPayHost() {
        String host = "";
        try {
            URL url = new URL(AppUtils.isAppDebug() ? BASE_DEBUG_URL : BASE_RELEASE_URL);
            host = url.getHost();
        } catch (MalformedURLException e) {
        }
        return host;
    }

    public static String getUrl() {
        return AppUtils.isAppDebug() ?
                AppConf.BASE_DEBUG_URL
                : AppConf.BASE_RELEASE_URL;
    }

    /**
     * 是否显示购买协议
     */
    public static boolean isShowBuyAgreement = false;

    /**
     * 是否显示订阅协议
     */
    public static boolean isShowSubscribeAgreement = false;

    /**
     * 订阅协议的地址
     */
    public static String SUBSCRIBE_AGREEMENT_URL = "";


    /**
     * 意见反馈页面跳转，参数0-10共11个页面可以选择
     */
    public static int FEEDBACK_PAGE = 0;

    public static String PRIVACY_URL = "";

    public static String USER_PROTOCOL = "";

    /**
     * bugly的appid
     */

    public static String BUGLY_APPID = "";
}