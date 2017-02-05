package cn.digirun.component.order.api.bean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: OrderRequest
 * @Description: 订单请求
 * @author 胡贵兵
 * 
 */
public class UpdOrderReque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userOrderAddressId;
	
	private Long invoiceTypeId;
	
	private String invoCustName;
	
	private String userRemark;
	
	@NotEmpty(message = "订单编号不允许为空")
	private String orderNo;
	
	private Long dispatchingId;
	
	public Long getDispatchingId() {
		return dispatchingId;
	}

	public void setDispatchingId(Long dispatchingId) {
		this.dispatchingId = dispatchingId;
	}

	public Long getUserOrderAddressId() {
		return userOrderAddressId;
	}

	public void setUserOrderAddressId(Long userOrderAddressId) {
		this.userOrderAddressId = userOrderAddressId;
	}

	public Long getInvoiceTypeId() {
		return invoiceTypeId;
	}

	public void setInvoiceTypeId(Long invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}

	public String getInvoCustName() {
		return invoCustName;
	}

	public void setInvoCustName(String invoCustName) {
		this.invoCustName = invoCustName;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
	
	
}
