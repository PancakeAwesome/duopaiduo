package cn.digirun.frame.payment.core.constant;

/**
 * @ClassName: Thirtypart
 * @Description: 第三方支付平台
 * @author 管东海
 * 
 */
public enum Thirtypart {

	AlipayPC(90101, "支付宝PC即时到账"), AlipayWAP(90102, "支付宝wap支付"), AlipayAPP(90103, "支付宝移动支付"), AlipayScan(90104,
			"支付宝扫码支付"), WxpayScan(90301, "微信扫码支付"), WxpayJS(90302, "微信扫码支付"), WxpayAPP(90303, "微信扫码支付"),UnionpayPC(90201,"银联网关支付"),
	UnionpayWAP(90202,"银联Wap支付"),UnionpayAPP(90203,"银联APP支付");

	private final Integer code;
	private final String name;

	private Thirtypart(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
