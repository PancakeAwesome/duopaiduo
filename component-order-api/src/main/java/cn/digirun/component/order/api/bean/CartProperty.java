package cn.digirun.component.order.api.bean;

/**
 * @ClassName: CartProperty
 * @Description: 购物车
 * @author zhanghd
 *
 */

public class CartProperty {
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 产品id
	 */
	private String itemId;

	/**
	 * 商品数量
	 */
	private int  amount;

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
