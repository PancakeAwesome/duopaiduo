package cn.digirun.frame.payment.unionpay.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 银联支付配置
 * @author Administrator
 *
 */

@ConfigurationProperties(value="unionpay.config")
public class UnionpayConfig {
	//
	public static String encoding;
	//全渠道固定值
	public static String version;
	
	public static String merId;
	
	//后台服务对应的写法参照 FrontRcvResponse.java
	public static String frontUrl;

	//后台服务对应的写法参照 BackRcvResponse.java
	public static String backUrl;//后台通知地址
	
	//交易请求地址 
	public static String frontTransUrl;
	public static String backTransUrl;
	public static String singleQueryUrl;
	public static String batchTransUrl;
	public static String fileTransUrl;
	public static String appTransUrl;
	public static String cardTransUrl;

	//#以下缴费产品使用，其余产品用不到
	public static String jfFrontTransUrl;
	//=https://101.231.204.80:5000/jiaofei/api/frontTransReq.do
	public static String jfBackTransUrl; //=https://101.231.204.80:5000/jiaofei/api/backTransReq.do
	public static String jfSingleQueryUrl;//=https://101.231.204.80:5000/jiaofei/api/queryTrans.do
	public static String jfCardTransUrl;//=https://101.231.204.80:5000/jiaofei/api/cardTransReq.do
	public static String jfAppTransUrl;//=https://101.231.204.80:5000/jiaofei/api/appTransReq.do

	//#########################入网测试环境签名证书配置 ################################

	//##签名证书路径，必须使用绝对路径，如果不想使用绝对路径，可以自行实现相对路径获取证书的方法；测试证书所有商户共用开发包中的测试签名证书，生产环境请从cfca下载得到
	//#windows下
	public static String signCertPath;//=D:/certs/acp_test_sign.pfx
	//#linux下（注意：在linux下读取证书需要保证证书有被应用读的权限）
	//#acpsdk.signCert.path=/SERVICE01/usr/ac_frnas/conf/ACPtest/acp700000000000001.pfx

	//##签名证书密码，测试环境固定000000，生产环境请修改为从cfca下载的正式证书的密码，正式环境证书密码位数需小于等于6位，否则上传到商户服务网站会失败
	public static String signCertPwd;//=000000
	//##签名证书类型，固定不需要修改
	public static String signCertType;//=PKCS12

	//##########################验签证书配置################################
	//##验证签名证书目录，只配置到目录即可，必须使用绝对路径，如果不想使用绝对路径，可以自行实现相对路径获取证书的方法；测试证书所有商户共用开发包中的测试验证证书，生产环境所有商户共用开发包中的生产验签证书
	//#windows下
	public static String validateCertDir;//=D:/certs/
	//#linux下（注意：在linux下读取证书需要保证证书有被应用读的权限）
	//#acpsdk.validateCert.dir=/SERVICE01/usr/ac_frnas/conf/ACPtest/

	//##########################加密证书配置################################
	//##敏感信息加密证书路径(商户号开通了商户对敏感信息加密的权限，需要对 卡号accNo，pin和phoneNo，cvn2，expired加密（如果这些上送的话），对敏感信息加密使用)
	public static String encryptCertPath;

	//##是否启用多证书模式(true:单证书|false:多证书---没有配置此项时,默认为单证书模式)
	public static String singleMode;
	
	/** 磁道加密证书路径. */
	public static String encryptTrackCertPath;
	/** 磁道加密公钥模数. */
	public static String encryptTrackKeyModulus;
	/** 磁道加密公钥指数. */
	public static String encryptTrackKeyExponent;

	public static String getMerId() {
		return merId;
	}

	public static void setMerId(String merId) {
		UnionpayConfig.merId = merId;
	}

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		UnionpayConfig.encoding = encoding;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		UnionpayConfig.version = version;
	}

	public static String getFrontUrl() {
		return frontUrl;
	}

	public static void setFrontUrl(String frontUrl) {
		UnionpayConfig.frontUrl = frontUrl;
	}

	public static String getBackUrl() {
		return backUrl;
	}

	public static void setBackUrl(String backUrl) {
		UnionpayConfig.backUrl = backUrl;
	}

	public static String getFrontTransUrl() {
		return frontTransUrl;
	}

	public static void setFrontTransUrl(String frontTransUrl) {
		UnionpayConfig.frontTransUrl = frontTransUrl;
	}

	public static String getBackTransUrl() {
		return backTransUrl;
	}

	public static void setBackTransUrl(String backTransUrl) {
		UnionpayConfig.backTransUrl = backTransUrl;
	}

	public static String getSingleQueryUrl() {
		return singleQueryUrl;
	}

	public static void setSingleQueryUrl(String singleQueryUrl) {
		UnionpayConfig.singleQueryUrl = singleQueryUrl;
	}

	public static String getBatchTransUrl() {
		return batchTransUrl;
	}

	public static void setBatchTransUrl(String batchTransUrl) {
		UnionpayConfig.batchTransUrl = batchTransUrl;
	}

	public static String getFileTransUrl() {
		return fileTransUrl;
	}

	public static void setFileTransUrl(String fileTransUrl) {
		UnionpayConfig.fileTransUrl = fileTransUrl;
	}

	public static String getAppTransUrl() {
		return appTransUrl;
	}

	public static void setAppTransUrl(String appTransUrl) {
		UnionpayConfig.appTransUrl = appTransUrl;
	}

	public static String getCardTransUrl() {
		return cardTransUrl;
	}

	public static void setCardTransUrl(String cardTransUrl) {
		UnionpayConfig.cardTransUrl = cardTransUrl;
	}

	public static String getJfFrontTransUrl() {
		return jfFrontTransUrl;
	}

	public static void setJfFrontTransUrl(String jfFrontTransUrl) {
		UnionpayConfig.jfFrontTransUrl = jfFrontTransUrl;
	}

	public static String getJfBackTransUrl() {
		return jfBackTransUrl;
	}

	public static void setJfBackTransUrl(String jfBackTransUrl) {
		UnionpayConfig.jfBackTransUrl = jfBackTransUrl;
	}

	public static String getJfSingleQueryUrl() {
		return jfSingleQueryUrl;
	}

	public static void setJfSingleQueryUrl(String jfSingleQueryUrl) {
		UnionpayConfig.jfSingleQueryUrl = jfSingleQueryUrl;
	}

	public static String getJfCardTransUrl() {
		return jfCardTransUrl;
	}

	public static void setJfCardTransUrl(String jfCardTransUrl) {
		UnionpayConfig.jfCardTransUrl = jfCardTransUrl;
	}

	public static String getJfAppTransUrl() {
		return jfAppTransUrl;
	}

	public static void setJfAppTransUrl(String jfAppTransUrl) {
		UnionpayConfig.jfAppTransUrl = jfAppTransUrl;
	}

	public static String getSignCertPath() {
		return signCertPath;
	}

	public static void setSignCertPath(String signCertPath) {
		UnionpayConfig.signCertPath = signCertPath;
	}

	public static String getSignCertPwd() {
		return signCertPwd;
	}

	public static void setSignCertPwd(String signCertPwd) {
		UnionpayConfig.signCertPwd = signCertPwd;
	}

	public static String getSignCertType() {
		return signCertType;
	}

	public static void setSignCertType(String signCertType) {
		UnionpayConfig.signCertType = signCertType;
	}

	public static String getValidateCertDir() {
		return validateCertDir;
	}

	public static void setValidateCertDir(String validateCertDir) {
		UnionpayConfig.validateCertDir = validateCertDir;
	}

	public static String getEncryptCertPath() {
		return encryptCertPath;
	}

	public static void setEncryptCertPath(String encryptCertPath) {
		UnionpayConfig.encryptCertPath = encryptCertPath;
	}

	public static String getSingleMode() {
		return singleMode;
	}

	public static void setSingleMode(String singleMode) {
		UnionpayConfig.singleMode = singleMode;
	}

	public static String getEncryptTrackCertPath() {
		return encryptTrackCertPath;
	}

	public static void setEncryptTrackCertPath(String encryptTrackCertPath) {
		UnionpayConfig.encryptTrackCertPath = encryptTrackCertPath;
	}

	public static String getEncryptTrackKeyModulus() {
		return encryptTrackKeyModulus;
	}

	public static void setEncryptTrackKeyModulus(String encryptTrackKeyModulus) {
		UnionpayConfig.encryptTrackKeyModulus = encryptTrackKeyModulus;
	}

	public static String getEncryptTrackKeyExponent() {
		return encryptTrackKeyExponent;
	}

	public static void setEncryptTrackKeyExponent(String encryptTrackKeyExponent) {
		UnionpayConfig.encryptTrackKeyExponent = encryptTrackKeyExponent;
	}
	
}
