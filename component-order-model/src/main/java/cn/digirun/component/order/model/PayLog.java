package cn.digirun.component.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "t_pay_log")
@DynamicInsert
@DynamicUpdate
public class PayLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	private Orders orderNo;

	@OneToOne(fetch = FetchType.LAZY)
	private PaymentType paymentType;

	/**
	 * 支付金额
	 */
	private float amount;

	/**
	 * 第三方流水号
	 */
	private String payNo;

	/**
	 * 支付状态(1成功;2:失败)
	 */
	private String status;

	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orders getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Orders orderNo) {
		this.orderNo = orderNo;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
