package cn.digirun.frame.payment.core.bean;

import java.io.Serializable;

/**
 * @ClassName: NoticeResBean
 * @Description: 支付回调响应
 * @author 管东海
 * 
 */
public class NoticeResBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 系统订单号
	private String orderNo;

	// 支付是否成功
	private String isSuccess;

	// 第三方支付流水号
	private String thirdNo;

	// 支付方式
	private String payType;
	
	// 金额
	private String totalFee;

	// 第三方支付回调反馈
	private String payNoticeRes;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getThirdNo() {
		return thirdNo;
	}

	public void setThirdNo(String thirdNo) {
		this.thirdNo = thirdNo;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayNoticeRes() {
		return payNoticeRes;
	}

	public void setPayNoticeRes(String payNoticeRes) {
		this.payNoticeRes = payNoticeRes;
	}

}
