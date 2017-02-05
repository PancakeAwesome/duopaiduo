package cn.digirun.component.order.api.bean;

import java.util.List;

/**
 * @ClassName: CartItems
 * @Description: 从购物车删除商品
 * @author zhanghd
 *
 */
public class CartItems  {
	
	/**
	 * "我购物车"中商品id
	 */
  List<String> itemIds;
  Long userId;
public List<String> getItemIds() {
	return itemIds;
}
public void setItemIds(List<String> itemIds) {
	this.itemIds = itemIds;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}


}
