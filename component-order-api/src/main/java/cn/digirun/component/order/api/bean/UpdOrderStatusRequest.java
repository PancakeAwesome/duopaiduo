package cn.digirun.component.order.api.bean;

import java.util.List;

import javax.persistence.Id;

/**
 * @ClassName: OrderRequest
 * @Description: 取消订单请求
 * @author 胡贵兵
 * 
 */
public class UpdOrderStatusRequest {

	private String orderNo;
	
	private String cancelReason;
	
	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	private List<RemarkDetails> remarkDetails;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setRemarkDetails(List<RemarkDetails> remarkDetails) {
		this.remarkDetails = remarkDetails;
	}

	public List<RemarkDetails> getRemarkDetails() {
		return remarkDetails;
	}
}
