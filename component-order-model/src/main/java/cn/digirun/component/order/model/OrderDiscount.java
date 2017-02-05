package cn.digirun.component.order.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 订单折扣信息
 * @author Administrator
 *
 */
@Entity(name = "t_order_discount")
@DynamicInsert
@DynamicUpdate
public class OrderDiscount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 用户优惠卷ID
	 */
	private Integer userCouponId;
	
	/**
	 * 优惠卷抵扣金额
	 */
	private Float couponDed;
	
	/**
	 * 积分抵扣金额
	 */
	private Float integralDed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Orders orderNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Integer userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Float getCouponDed() {
		return couponDed;
	}

	public void setCouponDed(Float couponDed) {
		this.couponDed = couponDed;
	}

	public Float getIntegralDed() {
		return integralDed;
	}

	public void setIntegralDed(Float integralDed) {
		this.integralDed = integralDed;
	}

	public Orders getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Orders orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
