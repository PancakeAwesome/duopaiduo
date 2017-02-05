package cn.digirun.component.item.model;

import org.springframework.data.annotation.Id;

public class ItemPriceModel {

	@Id
	private String id;
	
	/**
	 * 商品ID
	 */
	private String itemId;
	
	/**
	 * 商品最终价格
	 */
	private String realPrice;
	
	/**
	 * 备用字段
	 */
	private String extColumn1;
	
	private String extColumn2;
	
	private String extColumn3;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}

	public String getExtColumn1() {
		return extColumn1;
	}

	public void setExtColumn1(String extColumn1) {
		this.extColumn1 = extColumn1;
	}

	public String getExtColumn2() {
		return extColumn2;
	}

	public void setExtColumn2(String extColumn2) {
		this.extColumn2 = extColumn2;
	}

	public String getExtColumn3() {
		return extColumn3;
	}

	public void setExtColumn3(String extColumn3) {
		this.extColumn3 = extColumn3;
	}

}
