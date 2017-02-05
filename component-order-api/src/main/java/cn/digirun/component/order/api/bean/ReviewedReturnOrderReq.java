package cn.digirun.component.order.api.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: OrderRequest
 * @Description: 审核退换货请求
 * @author 胡贵兵
 * 
 */
public class ReviewedReturnOrderReq {

	@NotEmpty(message = "订单号不允许为空")
	private String orderNo;

	/**
	 * 2：审核通过   4：拒绝  
	 */
	@Min(2)
	@Max(4)
	private Integer status;
	
	private String adminRemark;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}
}
