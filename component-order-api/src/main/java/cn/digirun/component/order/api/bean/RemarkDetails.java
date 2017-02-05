package cn.digirun.component.order.api.bean;

public class RemarkDetails {
	/**
	 * 商品ID
	 */
	private String itemId;
	
	private String orderNo;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	private Integer buyerReview;
	
	private String buyerContent;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getBuyerReview() {
		return buyerReview;
	}

	public void setBuyerReview(Integer buyerReview) {
		this.buyerReview = buyerReview;
	}

	public String getBuyerContent() {
		return buyerContent;
	}

	public void setBuyerContent(String buyerContent) {
		this.buyerContent = buyerContent;
	}
}
