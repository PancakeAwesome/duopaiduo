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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "t_orders")
@DynamicInsert
@DynamicUpdate
public class Orders implements Serializable {

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders(Integer id, String orderNo, Integer userId, Integer adminUserId, String shippingSn, float freightAmount,
		    String invoiceSn, String invoCustName, Integer storeId, BigDecimal shouldAmount,
			BigDecimal discountAmount, BigDecimal amount, int isAccountDel, Integer status, Integer payStatus,
			Integer confirmStatus, Integer shippingStatus, Integer printStatus, Integer stockStatus,
			Integer expressStatus, String adminRemark, Integer source, String userRemark, Integer isO2oOrder,
			String sourceWeb, Date addTime, Date payTime, Date confirmTime, Date deliveryTime, Date signTime,
			Date closeTime, Date overTime, Date deleteTime, Integer creator, Date modTime, Integer updater,
			Date createTime,String provinceName, String cityName, String countyName, String userName, String phone,String address){
		this();
		this.userOrderAddress = new UserOrderAddress(provinceName, cityName, countyName, userName, phone, address);
		this.id = id;
		this.orderNo = orderNo;
		this.userId = userId;
		this.adminUserId = adminUserId;
		this.userOrderAddress = userOrderAddress;
		this.defray = defray;
		this.dispatching = dispatching;
		this.delivery = delivery;
		this.shippingSn = shippingSn;
		this.freightAmount = freightAmount;
		this.invoiceType = invoiceType;
		this.invoiceSn = invoiceSn;
		this.invoCustName = invoCustName;
		this.storeId = storeId;
		this.shouldAmount = shouldAmount;
		this.discountAmount = discountAmount;
		this.amount = amount;
		this.isAccountDel = isAccountDel;
		this.status = status;
		this.payStatus = payStatus;
		this.confirmStatus = confirmStatus;
		this.shippingStatus = shippingStatus;
		this.printStatus = printStatus;
		this.stockStatus = stockStatus;
		this.expressStatus = expressStatus;
		this.adminRemark = adminRemark;
		this.source = source;
		this.userRemark = userRemark;
		this.isO2oOrder = isO2oOrder;
		this.sourceWeb = sourceWeb;
		this.addTime = addTime;
		this.payTime = payTime;
		this.confirmTime = confirmTime;
		this.deliveryTime = deliveryTime;
		this.signTime = signTime;
		this.closeTime = closeTime;
		this.overTime = overTime;
		this.deleteTime = deleteTime;
		this.creator = creator;
		this.modTime = modTime;
		this.updater = updater;
		this.createTime = createTime;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Id
	private String orderNo;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 后台用户ID（分派）
	 */
	private Integer adminUserId;
	
	/**
	 * 用户订单地址对象
	 */
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	private UserOrderAddress userOrderAddress;
	
	/**
	 * 支付方式对象
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	private Defray defray;
	
	/**
	 * 配送方式
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	private Dispatching dispatching;

	/**
	 * 物流对象
	 */
	@ManyToOne(fetch=FetchType.LAZY)
    private Delivery delivery;
    
    /**
     * 物流号
     */
    private String shippingSn;
    
    /**
     * 运费
     */
    private float freightAmount;
    
    /**
     * 发票类型
     */
    @ManyToOne(fetch=FetchType.LAZY)
    private InvoiceType invoiceType;
    
    /**
     * 发票单号
     */
    private String invoiceSn;
    
    /**
     * 发票抬头
     */
    private String invoCustName;
    
    
    /**
     * 取消订单理由
     */
    private String cancelReason;
	
    public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	/**
	 * 门店ID
	 */
	private Integer storeId;
	
	/**
	 * 应收金额
	 */
	private BigDecimal shouldAmount;
	
	/**
	 * 折扣金额
	 */
	private BigDecimal discountAmount;
	
	/**
	 * 实付金额
	 */
	private BigDecimal amount;
	
	/**
	 * 是否删除0否1是 用户看不到 后台可见
	 */
	private int isAccountDel;
	
	/**
	 * 0取消的订单1进行中的订单2退货中订单3已完成的订单
	 */
	private Integer status;
	
	/**
	 * 付款状态(0=>未付款，1=>已付款)
	 */
	private Integer payStatus;
	
	/**
	 * 发货确认状态('0' => '未确认','1'=> '已确认','2' => '部分拆分','3' => '已拆分完','4' => '取消','5' =>'余单撤销')未确认的不予发货 
	 */
	private Integer confirmStatus;
	
	/**
	 * 发货状态(0未发货1已发货)
	 */
	private Integer shippingStatus;
	
	/**
	 * 打印状态(0=》未打印 1=》打印)
	 */
	private Integer printStatus;
	
	/**
	 * 备货状态(0=》未备货 1=》备货)
	 */
	private Integer stockStatus;
	
	/**
	 * 快递状态(0=》未快递 1=》快递)
	 */
	private Integer expressStatus;
	
	/**
	 * 后台订单备注
	 */
	private String adminRemark;
	
	/**
	 * 订单来源0pc端 1app端2mobile端3pad端
	 */
	private Integer source;
	
	/**
	 * 用户订单留言
	 */
	private String userRemark;
	
	/**
	 * 是否O2O订单0否1是
	 */
	private Integer isO2oOrder;
	
	/**
	 * 来源网址
	 */
	private String sourceWeb;
	
	/**
	 * 下单时间
	 */
	private Date addTime;
	
	/**
	 * 付款时间
	 */
	private Date payTime;
	
	/**
	 * 确认时间
	 */
	private Date confirmTime;
	 
	/**
	 * 发货时间
	 */
	private Date deliveryTime;
	
	/**
	 * 签收时间
	 */
	private Date signTime;
	
	/**
	 * 取消时间
	 */
	private Date closeTime;
	
	/**
	 * 订单完成时间
	 */
	private Date overTime;
	
	/**
	 * 订单删除时间
	 */
	private Date deleteTime;
	
	/**
	 * 创建人
	 */
	private Integer creator;
	
	/**
	 * 更新时间
	 */
	private Date modTime;
	
	/**
	 * 更新人
	 */
	private Integer updater;
	/**
	 * addTime
	 */
	private Date createTime;
	
	
	
	@OneToMany(mappedBy="orders",cascade=CascadeType.ALL)
	private List<OrderDetail> orderdetail = new ArrayList<OrderDetail>();
	
	@OneToMany(mappedBy="orders",cascade=CascadeType.ALL)
	private List<OrderReturn> orderReturn = new ArrayList<OrderReturn>();
	
	@Transient
	private List<OrderItems> orderItems;
	
	/**
	 * 订单折扣
	 */
	@Transient
	private OrderDiscount orderDiscount;

	public Dispatching getDispatching() {
		return dispatching;
	}

	public void setDispatching(Dispatching dispatching) {
		this.dispatching = dispatching;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public UserOrderAddress getUserOrderAddress() {
		return userOrderAddress;
	}

	public void setUserOrderAddress(UserOrderAddress userOrderAddress) {
		this.userOrderAddress = userOrderAddress;
	}

	public Defray getDefray() {
		return defray;
	}

	public void setDefray(Defray defray) {
		this.defray = defray;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String getShippingSn() {
		return shippingSn;
	}

	public void setShippingSn(String shippingSn) {
		this.shippingSn = shippingSn;
	}

	public float getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(float freightAmount) {
		this.freightAmount = freightAmount;
	}

	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceSn() {
		return invoiceSn;
	}

	public void setInvoiceSn(String invoiceSn) {
		this.invoiceSn = invoiceSn;
	}

	public String getInvoCustName() {
		return invoCustName;
	}

	public void setInvoCustName(String invoCustName) {
		this.invoCustName = invoCustName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getShouldAmount() {
		return shouldAmount;
	}

	public void setShouldAmount(BigDecimal shouldAmount) {
		this.shouldAmount = shouldAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getIsAccountDel() {
		return isAccountDel;
	}

	public void setIsAccountDel(int isAccountDel) {
		this.isAccountDel = isAccountDel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public Integer getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Integer getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(Integer printStatus) {
		this.printStatus = printStatus;
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public Integer getExpressStatus() {
		return expressStatus;
	}

	public void setExpressStatus(Integer expressStatus) {
		this.expressStatus = expressStatus;
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public Integer getIsO2oOrder() {
		return isO2oOrder;
	}

	public void setIsO2oOrder(Integer isO2oOrder) {
		this.isO2oOrder = isO2oOrder;
	}

	public String getSourceWeb() {
		return sourceWeb;
	}

	public void setSourceWeb(String sourceWeb) {
		this.sourceWeb = sourceWeb;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public OrderDiscount getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(OrderDiscount orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public List<OrderDetail> getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(List<OrderDetail> orderdetail) {
		this.orderdetail = orderdetail;
	}

	public List<OrderReturn> getOrderReturn() {
		return orderReturn;
	}

	public void setOrderReturn(List<OrderReturn> orderReturn) {
		this.orderReturn = orderReturn;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	
	
	

}
