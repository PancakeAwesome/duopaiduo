package cn.digirun.frame.payment.core.bean;

/** 
 * @ClassName: PaymentBean 
 * @Description: 支付请求
 * @author 管东海
 *  
 */
public class PaymentReqBean {
	
	private Integer payType;
	
	private String orderNo; // 订单编号
	
	private String totalFee; // 总金额
	
	private String subject; // 商品描述、订单描述 
	
	private String productId; // NATIVE参数必传  id为二维码中包含的商品ID
	
	private String resource;
	
	private String showUrl; // 商品展示网址
	
	private String sign;
	
	private String returnUrl; // 同步通知页面
	
	private String noticeUrl; // 异步通知页面  临时使用
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}
	
}
