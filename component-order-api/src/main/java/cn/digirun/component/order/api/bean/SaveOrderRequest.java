package cn.digirun.component.order.api.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName: OrderRequest
 * @Description: 订单请求
 * @author 胡贵兵
 * 
 */
public class SaveOrderRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Min(1)
	@Max(Integer.MAX_VALUE)
	private Integer userId;
	
	
	private List<OrderItems> orderItems;
	
	@Min(1)
	@Max(Integer.MAX_VALUE)
	private Integer source;
	
	private String sourceWeb;
	
	private Long userOrderAddressId;
	
	private Long invoiceTypeId;
	
	private String invoCustName;
	
	
	private float freightAmount;
	
	private String userRemark;
	
	private Integer storeId;
	
	private Integer creator;
	
	private Long dispatchingId;
	
	public Long getDispatchingId() {
		return dispatchingId;
	}

	public void setDispatchingId(Long dispatchingId) {
		this.dispatchingId = dispatchingId;
	}

	public Long getInvoiceTypeId() {
		return invoiceTypeId;
	}

	public void setInvoiceTypeId(Long invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}

	public Long getUserOrderAddressId() {
		return userOrderAddressId;
	}

	public void setUserOrderAddressId(Long userOrderAddressId) {
		this.userOrderAddressId = userOrderAddressId;
	}


	public String getInvoCustName() {
		return invoCustName;
	}

	public void setInvoCustName(String invoCustName) {
		this.invoCustName = invoCustName;
	}

	public float getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(float freightAmount) {
		this.freightAmount = freightAmount;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSourceWeb() {
		return sourceWeb;
	}

	public void setSourceWeb(String sourceWeb) {
		this.sourceWeb = sourceWeb;
	}


	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
