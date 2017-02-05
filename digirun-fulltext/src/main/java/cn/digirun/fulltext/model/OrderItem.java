package cn.digirun.fulltext.model;

import java.io.Serializable;
import java.util.List;

/**
 * 订单明细
 * @author zhd
 *
 */
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 3072878115666033327L;
	
	public OrderItem() {
		super();
	}




	public OrderItem(String orderNo,List<ShoppingCarItem> shoppingCarItemlist, String invoiceName, String delivery, String orderMessage, Integer status,
			Double orderItemsPrice, Double orderDeliveryPrice, Double orderTotalPrice, String createTime,
			String creater, String address, String phone) {
		super();
		this.orderNo = orderNo;
		this.shoppingCarItemlist = shoppingCarItemlist;
		this.invoiceName = invoiceName;
		this.delivery = delivery;
		this.orderMessage = orderMessage;
		this.status = status;
		this.orderItemsPrice = orderItemsPrice;
		this.orderDeliveryPrice = orderDeliveryPrice;
		this.orderTotalPrice = orderTotalPrice;
		this.createTime = createTime;
		this.creater = creater;
		this.address = address;
		this.phone = phone;
	}




	private String orderNo; // 订单号·
	private List<ShoppingCarItem> shoppingCarItemlist;
	private String invoiceName;//发票名称
	private String delivery;//配送方式
	private String orderMessage;//订单留言
	private Integer status;//订单状态
	private Double orderItemsPrice; // 订单商品总价
	private Double orderDeliveryPrice;//订单的配送费；
	private Double orderTotalPrice;//一个订单的总费用（包括，配送费和商品费用）
	private String createTime;//创建订单时间
	private String creater;//创建订单者
	private String address;//寄货地址
	private String phone;//联系方式

	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}





	public List<ShoppingCarItem> getShoppingCarItemlist() {
		return shoppingCarItemlist;
	}




	public void setShoppingCarItemlist(List<ShoppingCarItem> shoppingCarItemlist) {
		this.shoppingCarItemlist = shoppingCarItemlist;
	}




	public String getInvoiceName() {
		return invoiceName;
	}


	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}


	public String getDelivery() {
		return delivery;
	}


	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}


	public String getOrderMessage() {
		return orderMessage;
	}


	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Double getOrderItemsPrice() {
		return orderItemsPrice;
	}


	public void setOrderItemsPrice(Double orderItemsPrice) {
		this.orderItemsPrice = orderItemsPrice;
	}


	public Double getOrderDeliveryPrice() {
		return orderDeliveryPrice;
	}


	public void setOrderDeliveryPrice(Double orderDeliveryPrice) {
		this.orderDeliveryPrice = orderDeliveryPrice;
	}


	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}


	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getCreater() {
		return creater;
	}


	public void setCreater(String creater) {
		this.creater = creater;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}



	

}
