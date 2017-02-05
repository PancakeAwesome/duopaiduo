package cn.digirun.component.item.api.bean;

public class UpdateItemStockRequest {

	private String id;
	
	private String itemSpecName1;
	
	private String itemSpecName2;
	
	private Integer stock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
}
