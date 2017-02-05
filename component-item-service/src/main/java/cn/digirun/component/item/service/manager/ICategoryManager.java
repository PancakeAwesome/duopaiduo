package cn.digirun.component.item.service.manager;

import java.util.List;

import cn.digirun.component.item.model.CategoryModel;

public interface ICategoryManager {

	/**
	 * @Title: 商品品牌的保存\修改
	 * @return void
	 */
	void operateItemCategory(CategoryModel categoryModel);
	
	/**
	 * 
	 * @Title: getCategoryById 
	 * @Description: 根据品牌id获取品牌信息
	 * @return CategoryModel
	 * @throws
	 */
	CategoryModel getCategoryById(String id);
	
	/**
	 * 
	 * @Title: getAllCategorys 
	 * @Description: 获取所有品牌
	 * @return List<CategoryModel>
	 * @throws
	 */
	List<CategoryModel> getAllCategorys();
	
	/**
	 * 
	 * @Title: removeCategory 
	 * @Description: 删除品牌
	 * @return void
	 * @throws
	 */
	void removeCategory(String id);
}
