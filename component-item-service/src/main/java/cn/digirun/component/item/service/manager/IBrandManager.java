package cn.digirun.component.item.service.manager;

import java.util.List;

import cn.digirun.component.item.model.BrandModel;

public interface IBrandManager {

	/**
	 * @Title: 商品品牌的保存\修改
	 * @return void
	 */
	void operateItemBrand(BrandModel brandModel);
	
	/**
	 * 
	 * @Title: getBrandById 
	 * @Description: 根据品牌id获取品牌信息
	 * @return BrandModel
	 * @throws
	 */
	BrandModel getBrandById(String id);
	
	/**
	 * 
	 * @Title: getAllBrands 
	 * @Description: 获取所有品牌
	 * @return List<BrandModel>
	 * @throws
	 */
	List<BrandModel> getAllBrands();
	
	/**
	 * 
	 * @Title: removeBrand 
	 * @Description: 删除品牌
	 * @return void
	 * @throws
	 */
	void removeBrand(String id);
}
