package cn.digirun.fulltext.model;

import java.io.Serializable;

/**
 * 购物车明细
 * @author gdh
 *
 */
public class ShoppingCarItem implements Serializable{

	private static final long serialVersionUID = 3072878115666033327L;
	
	public ShoppingCarItem() {
		super();
	}


	public ShoppingCarItem(String itemPic, String itemName, Integer itemAmount, String itemId, Double itemPrice,String itemUrl) {
		super();
		this.itemPic = itemPic;
		this.itemName = itemName;
		this.itemAmount = itemAmount;
		this.itemId = itemId;
		this.itemPrice = itemPrice;
		this.itemUrl = itemUrl;
	}

	private String itemPic; // 封面图
	private String itemName; // 商品名
	private Integer itemAmount; // 购买数量
	private String itemId; // 商品ID	
	private Double itemPrice; // 商品单价
	private String itemUrl;//商品url；

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemPic() {
		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}


	public String getItemUrl() {
		return itemUrl;
	}


	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
    
	
}
