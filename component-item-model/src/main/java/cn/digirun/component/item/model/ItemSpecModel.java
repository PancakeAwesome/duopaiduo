package cn.digirun.component.item.model;

public class ItemSpecModel {

	/**
	 * 商品规格1
	 */
	private String itemSpecName1;
	
	/**
	 * 商品规格2
	 */
	private String itemSpecName2;
	
	/**
	 * 库存
	 */
	private Integer stock;
	
	/**
	 * 价格
	 */
	private String price;
	
	/**
	 * 商品扩展规格1
	 */
	private String itemSpecExt1;
	
	/**
	 * 商品扩展规格2
	 */
	private String itemSpecExt2;

	public String getItemSpecName1() {
		return itemSpecName1;
	}

	public void setItemSpecName1(String itemSpecName1) {
		this.itemSpecName1 = itemSpecName1;
	}

	public String getItemSpecName2() {
		return itemSpecName2;
	}

	public void setItemSpecName2(String itemSpecName2) {
		this.itemSpecName2 = itemSpecName2;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getItemSpecExt1() {
		return itemSpecExt1;
	}

	public void setItemSpecExt1(String itemSpecExt1) {
		this.itemSpecExt1 = itemSpecExt1;
	}

	public String getItemSpecExt2() {
		return itemSpecExt2;
	}

	public void setItemSpecExt2(String itemSpecExt2) {
		this.itemSpecExt2 = itemSpecExt2;
	}
	
}
