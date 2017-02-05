package cn.digirun.component.order.api.bean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: OrderRequest
 * @Description: 后台发货确认状态
 * @author 胡贵兵
 * 
 */
public class DeliveryConfirmReq {

	@NotEmpty(message = "订单号不允许为空")
	private String orderNo;
	
	private Integer confirmStatus;
	
	public Integer getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
