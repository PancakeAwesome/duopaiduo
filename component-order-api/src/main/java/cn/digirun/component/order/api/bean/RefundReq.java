package cn.digirun.component.order.api.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 退货接口参数
 * @author Administrator
 *
 */
public class RefundReq {

	@Min(1)
	@Max(Integer.MAX_VALUE)
	private Integer userId;
	
	@NotEmpty(message = "订单ID不允许为空")
	private String returnOrderNo;
	
	private String orderDetailIds;
	
	/**
	 * 退换方式  1已到货申请退货 2已到货申请换货  3未到货退款
	 */
	@Min(1)
	@Max(Integer.MAX_VALUE)
	private Integer returnType;
	
	/**
	 * 退换货原因
	 */
	@NotEmpty(message = "退换货原因不允许为空")
	private String applyRemark;
	
	private String img3url;
	
	private String img2url;
	
	private String img1url;
	
	private String amount;
	
	
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public String getImg3url() {
		return img3url;
	}

	public void setImg3url(String img3url) {
		this.img3url = img3url;
	}

	public String getImg2url() {
		return img2url;
	}

	public void setImg2url(String img2url) {
		this.img2url = img2url;
	}

	public String getImg1url() {
		return img1url;
	}

	public void setImg1url(String img1url) {
		this.img1url = img1url;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public String getReturnOrderNo() {
		return returnOrderNo;
	}

	public void setReturnOrderNo(String returnOrderNo) {
		this.returnOrderNo = returnOrderNo;
	}

	public String getOrderDetailIds() {
		return orderDetailIds;
	}

	public void setOrderDetailIds(String orderDetailIds) {
		this.orderDetailIds = orderDetailIds;
	}

}
