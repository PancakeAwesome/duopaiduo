package cn.digirun.component.item.service.manager;

import cn.digirun.component.item.model.ItemPriceModel;;

public interface IItemPriceManager {

	/**
	 * 商品 价格的保存\修改
	 * 
	 * @param item
	 */
	void operateItemPrice(ItemPriceModel itemPrice);

	/**
	 * 
	 * @Title: removeItemPrice 
	 * @Description: 删除商品真是价格
	 * @return void
	 * @throws
	 */
	void removeItemPrice(String id);

	/**
	 * 
	 * @Title: updateItemPrice 
	 * @Description: 更新商品最终价格
	 * @return void
	 * @throws
	 */
	void updateItemPrice(ItemPriceModel item);
	
	/**
	 * 
	 * @Title: getItemPriceByItemId 
	 * @Description: 根据商品id获取最终价格
	 * @return ItemPriceModel
	 * @throws
	 */
	ItemPriceModel getItemPriceByItemId(String itemId);
	
}
