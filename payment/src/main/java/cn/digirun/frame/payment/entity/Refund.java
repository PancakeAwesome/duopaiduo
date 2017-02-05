package cn.digirun.frame.payment.entity;

import org.springframework.data.annotation.Id;

public class Refund {

	@Id
	private String id;
	
	// 第三方流水号
	private String thirdNo;
	
	// 系统订单号
	private String orderNo;
	
	// 第三方反馈时间
	private String thirdResTime;
	
	// 回调时间
	private String requestTime;
	
	// 支付状态
	private String status;
	
	// 金额
	private String totalFee;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThirdNo() {
		return thirdNo;
	}

	public void setThirdNo(String thirdNo) {
		this.thirdNo = thirdNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getThirdResTime() {
		return thirdResTime;
	}

	public void setThirdResTime(String thirdResTime) {
		this.thirdResTime = thirdResTime;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	
}
