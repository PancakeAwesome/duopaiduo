package cn.digirun.frame.payment.wxpay.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("wxpay.config")
public class WxpayConfig {

	//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	public static String pay_url;
	
	public static String refund_url;
	
	public static String key;
	
	public static String app_secret;

	//微信分配的公众号ID（开通公众号之后可以获取到）
	public static String app_id;
	
	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static String mch_id;

	//受理模式下给子商户分配的子商户号
	public static String subMchID;

	//HTTPS证书的本地路径
	public static String certLocalPath;

	//HTTPS证书密码，默认密码等于商户号MCHID
	public static String certPassword;

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	public static boolean useThreadToDoReport = true;

	//机器IP
	public static String ip;
	
	//回调地址
	public static String notify_url;
	
	// 交易类型
	public static String trade_type;
	
	// 证书路径
	public static String cert_path;

	public static String getPay_url() {
		return pay_url;
	}

	public static void setPay_url(String pay_url) {
		WxpayConfig.pay_url = pay_url;
	}

	public static String getRefund_url() {
		return refund_url;
	}

	public static void setRefund_url(String refund_url) {
		WxpayConfig.refund_url = refund_url;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		WxpayConfig.key = key;
	}

	public static String getApp_secret() {
		return app_secret;
	}

	public static void setApp_secret(String app_secret) {
		WxpayConfig.app_secret = app_secret;
	}

	public static String getApp_id() {
		return app_id;
	}

	public static void setApp_id(String app_id) {
		WxpayConfig.app_id = app_id;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		WxpayConfig.mch_id = mch_id;
	}

	public static String getSubMchID() {
		return subMchID;
	}

	public static void setSubMchID(String subMchID) {
		WxpayConfig.subMchID = subMchID;
	}

	public static String getCertLocalPath() {
		return certLocalPath;
	}

	public static void setCertLocalPath(String certLocalPath) {
		WxpayConfig.certLocalPath = certLocalPath;
	}

	public static String getCertPassword() {
		return certPassword;
	}

	public static void setCertPassword(String certPassword) {
		WxpayConfig.certPassword = certPassword;
	}

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		WxpayConfig.useThreadToDoReport = useThreadToDoReport;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		WxpayConfig.ip = ip;
	}

	public static String getNotify_url() {
		return notify_url;
	}

	public static void setNotify_url(String notify_url) {
		WxpayConfig.notify_url = notify_url;
	}

	public static String getTrade_type() {
		return trade_type;
	}

	public static void setTrade_type(String trade_type) {
		WxpayConfig.trade_type = trade_type;
	}

	public static String getCert_path() {
		return cert_path;
	}

	public static void setCert_path(String cert_path) {
		WxpayConfig.cert_path = cert_path;
	}
	
}
