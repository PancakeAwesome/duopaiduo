package cn.digirun.component.item.service.manager;

import java.util.List;

import cn.digirun.component.item.model.ItemModel;;

public interface IItemManager {

	/**
	 * 商品的保存\修改
	 * 
	 * @param item
	 */
	void operateItem(ItemModel item);

	/**
	 * 
	 * @Title: removeItem 
	 * @Description: 根据id删除商品
	 * @return void
	 * @throws
	 */
	void removeItem(String id);

	/**
	 * 
	 * @Title: updateItemStock 
	 * @Description: 更新商品库存
	 * @return void
	 * @throws
	 */
	void updateItemStock(ItemModel item);
	
	/**
	 * 
	 * @Title: getItems 
	 * @Description: 获取所有商品
	 * @return List<ItemModel>
	 * @throws
	 */
	List<ItemModel> getItems();
	
	
	/**
	 * 
	 * @Title: getItems 
	 * @Description: 获取所有商品
	 * @return List<ItemModel>
	 * @throws
	 */
	ItemModel getItem(String id);
    
	
	/**
	 * 
	 * 
	 * @Title:getItemsByCategoryId
	 * @Description:根据类别获取商品
	 * @return List<ItemModel>
	 * @throws
	 */
	List<ItemModel> getItemsByCategoryId(String id);
}
