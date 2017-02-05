package cn.digirun.component.order.api.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: OrderRequest
 * @Description: 用户退/换货填写物流信息
 * @author 胡贵兵
 * 
 */
public class UserlogisticsReq {

	@NotEmpty(message = "订单ID不允许为空")
	private String orderNo;
	
	@NotEmpty(message = "物流号不允许为空")
	private String shippingSn;
	
	/**
	 * 物流公司ID
	 */
	@Min(1)
	@Max(Long.MAX_VALUE)
	private Long deliveryId;
	
	

	public String getShippingSn() {
		return shippingSn;
	}

	public void setShippingSn(String shippingSn) {
		this.shippingSn = shippingSn;
	}

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
