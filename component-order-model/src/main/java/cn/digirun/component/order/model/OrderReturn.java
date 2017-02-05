package cn.digirun.component.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 退/换货订单
 * @author Administrator
 *
 */
@Entity(name = "t_order_return")
@DynamicInsert
@DynamicUpdate
@JSONType(ignores={"orders"})
public class OrderReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Id
	private String orderNo;
	
	/**
	 * 原订单ID
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	private Orders orders;
	
	
	private Integer userId;
	
	/**
	 * 订单类型(1:退货订单,2:换货订单)
	 */
	private Integer orderType;
	
	/**
	 * 退换方式  1已到货申请退货 2已到货申请换货  3未到货退款
	 */
	private Integer returnType;
	
	/**
	 * 申请时间
	 */
	private Date applyTime;
	
	/**
	 * 退款金额
	 */
	private BigDecimal returnMoney;
	
	/**
	 * 退换货原因
	 */
	private String applyRemark;
	
	/**
	 * 0:用户取消    1:待审核  2：审核通过  3：驳回   4：拒绝   5:买家已退货  6：卖家已换货 7:卖家已退款 8:用户收到换货，换货结束
	 */
	private Integer status;
	
	/**
	 * 用户寄回物流对象
	 */
	@ManyToOne(fetch=FetchType.LAZY)
    private Delivery userDelivery;
	
	
	/**
	 * 用户寄回物流单号
	 */
	private String userLogisticsNo;
	
	/**
	 * 仓库发货_物流对象
	 */
	@ManyToOne(fetch=FetchType.LAZY)
    private Delivery adminDelivery;
	
	/**
	 * 仓库发货_物流单号
	 */
	private String adminLogisticsNo;
	
	/**
	 * 卖家审核时间
	 */
	private Date agreeTime;
	
	/**
	 * 卖家审核备注
	 */
	private String adminRemark;
	
	/**
	 * 用户回寄货物时间
	 */
	private Date returnProductTime;
	
	/**
	 * 商户确认收到用户回寄时间
	 */
	private Date confirmUserLogisticsTime;
	
	/**
	 * 商户收到回寄货物发货时间
	 */
	private Date changeProductTime;
	
	/**
	 * 完成时间
	 */
	private Date overTime;
	
	
	private String img1Url;
	
	private String img2Url;
	
	private String img3Url;
	
	@OneToMany(mappedBy="orderReturn",cascade=CascadeType.ALL)
	private List<OrderReturnDetail> orderReturnDetail = new ArrayList<OrderReturnDetail>();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getConfirmUserLogisticsTime() {
		return confirmUserLogisticsTime;
	}

	public void setConfirmUserLogisticsTime(Date confirmUserLogisticsTime) {
		this.confirmUserLogisticsTime = confirmUserLogisticsTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<OrderReturnDetail> getOrderReturnDetail() {
		return orderReturnDetail;
	}

	public void setOrderReturnDetail(List<OrderReturnDetail> orderReturnDetail) {
		this.orderReturnDetail = orderReturnDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	

	public Delivery getUserDelivery() {
		return userDelivery;
	}

	public void setUserDelivery(Delivery userDelivery) {
		this.userDelivery = userDelivery;
	}

	public String getUserLogisticsNo() {
		return userLogisticsNo;
	}

	public void setUserLogisticsNo(String userLogisticsNo) {
		this.userLogisticsNo = userLogisticsNo;
	}

	public Delivery getAdminDelivery() {
		return adminDelivery;
	}

	public void setAdminDelivery(Delivery adminDelivery) {
		this.adminDelivery = adminDelivery;
	}

	public String getAdminLogisticsNo() {
		return adminLogisticsNo;
	}

	public void setAdminLogisticsNo(String adminLogisticsNo) {
		this.adminLogisticsNo = adminLogisticsNo;
	}

	public Date getAgreeTime() {
		return agreeTime;
	}

	public void setAgreeTime(Date agreeTime) {
		this.agreeTime = agreeTime;
	}

	public Date getReturnProductTime() {
		return returnProductTime;
	}

	public void setReturnProductTime(Date returnProductTime) {
		this.returnProductTime = returnProductTime;
	}

	public Date getChangeProductTime() {
		return changeProductTime;
	}

	public void setChangeProductTime(Date changeProductTime) {
		this.changeProductTime = changeProductTime;
	}

	public String getImg1Url() {
		return img1Url;
	}

	public void setImg1Url(String img1Url) {
		this.img1Url = img1Url;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getImg2Url() {
		return img2Url;
	}

	public void setImg2Url(String img2Url) {
		this.img2Url = img2Url;
	}

	public String getImg3Url() {
		return img3Url;
	}

	public void setImg3Url(String img3Url) {
		this.img3Url = img3Url;
	}

}
