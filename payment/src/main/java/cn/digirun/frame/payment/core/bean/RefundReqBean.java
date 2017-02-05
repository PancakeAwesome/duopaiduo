package cn.digirun.frame.payment.core.bean;

public class RefundReqBean {
	
	// 第三方支付
	private String payType;

    // 退款批次号
    private String refundNo;
    
    // 退款笔数, 参数detailData的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
    private String refundNum;
    
    // 退款详细数据，格式（支付宝交易号^退款金额^备注），多笔请用#隔开
    private String detailData;
    
    // 签名
    private String sign;
    
    // 订单总金额，单位为分
    private Integer totalFee;
    
    // 退款总金额，单位为分
    private Integer refundFee;
    
    // 第三方交易号
    private String transactionId;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(String refundNum) {
		this.refundNum = refundNum;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
    
}
