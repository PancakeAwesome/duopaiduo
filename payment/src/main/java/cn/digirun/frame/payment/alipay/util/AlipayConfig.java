package cn.digirun.frame.payment.alipay.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "alipay.config")
public class AlipayConfig {

	// 支付宝提供给商户的服务接入网关URL(新)
	public static String gateway_new;

	// 支付宝消息验证地址
	public static String https_verify_url;

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner;

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id;

	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String key;

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url;

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url;

	// 签名方式
	public static String sign_type;

	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path;

	// 字符编码格式
	public static String input_charset;

	// 支付类型 ，无需修改
	public static String payment_type;

	// 调用的接口名，pc即时到帐
	public static String pc_service;

	// 调用的接口名，手机网站支付
	public static String wap_service;

	// 调用的接口名，移动支付
	public static String app_service;

	// 调用的接口名，扫描支付
	public static String scan_service;
	
	// 调用接口名，退款
	public static String refund_service;

	// 防钓鱼时间戳 若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key;

	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip;

	public static String getGateway_new() {
		return gateway_new;
	}

	public static void setGateway_new(String gateway_new) {
		AlipayConfig.gateway_new = gateway_new;
	}

	public static String getHttps_verify_url() {
		return https_verify_url;
	}

	public static void setHttps_verify_url(String https_verify_url) {
		AlipayConfig.https_verify_url = https_verify_url;
	}

	public static String getPartner() {
		return partner;
	}

	public static void setPartner(String partner) {
		AlipayConfig.partner = partner;
	}

	public static String getSeller_id() {
		return seller_id;
	}

	public static void setSeller_id(String seller_id) {
		AlipayConfig.seller_id = seller_id;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		AlipayConfig.key = key;
	}

	public static String getNotify_url() {
		return notify_url;
	}

	public static void setNotify_url(String notify_url) {
		AlipayConfig.notify_url = notify_url;
	}

	public static String getReturn_url() {
		return return_url;
	}

	public static void setReturn_url(String return_url) {
		AlipayConfig.return_url = return_url;
	}

	public static String getSign_type() {
		return sign_type;
	}

	public static void setSign_type(String sign_type) {
		AlipayConfig.sign_type = sign_type;
	}

	public static String getLog_path() {
		return log_path;
	}

	public static void setLog_path(String log_path) {
		AlipayConfig.log_path = log_path;
	}

	public static String getInput_charset() {
		return input_charset;
	}

	public static void setInput_charset(String input_charset) {
		AlipayConfig.input_charset = input_charset;
	}

	public static String getPayment_type() {
		return payment_type;
	}

	public static void setPayment_type(String payment_type) {
		AlipayConfig.payment_type = payment_type;
	}

	public static String getPc_service() {
		return pc_service;
	}

	public static void setPc_service(String pc_service) {
		AlipayConfig.pc_service = pc_service;
	}

	public static String getWap_service() {
		return wap_service;
	}

	public static void setWap_service(String wap_service) {
		AlipayConfig.wap_service = wap_service;
	}

	public static String getApp_service() {
		return app_service;
	}

	public static void setApp_service(String app_service) {
		AlipayConfig.app_service = app_service;
	}

	public static String getScan_service() {
		return scan_service;
	}

	public static void setScan_service(String scan_service) {
		AlipayConfig.scan_service = scan_service;
	}

	public static String getRefund_service() {
		return refund_service;
	}

	public static void setRefund_service(String refund_service) {
		AlipayConfig.refund_service = refund_service;
	}

	public static String getAnti_phishing_key() {
		return anti_phishing_key;
	}

	public static void setAnti_phishing_key(String anti_phishing_key) {
		AlipayConfig.anti_phishing_key = anti_phishing_key;
	}

	public static String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public static void setExter_invoke_ip(String exter_invoke_ip) {
		AlipayConfig.exter_invoke_ip = exter_invoke_ip;
	}

}
