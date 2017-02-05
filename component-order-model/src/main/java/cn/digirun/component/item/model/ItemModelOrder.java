package cn.digirun.component.item.model;

import java.math.BigDecimal;

public class ItemModelOrder {
	private Integer itemId;

	private Integer stock;

	private BigDecimal price;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	

}
