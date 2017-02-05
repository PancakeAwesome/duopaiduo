package cn.digirun.component.item.service.manager;

import java.util.List;

import cn.digirun.component.item.model.SpecModel;

public interface ISpecManager {

	/**
	 * 
	 * @Title: operateSpec 
	 * @Description: 规格的保存\修改
	 * @return void
	 * @throws
	 */
	void operateSpec(SpecModel specModel);
	
	/**
	 * 
	 * @Title: getAllSpecs 
	 * @Description: 获取规格列表
	 * @return List<SpecModel>
	 * @throws
	 */
	List<SpecModel> getAllSpecs();
	
	/**
	 * 
	 * @Title: removeSpec 
	 * @Description: 删除规格
	 * @return void
	 * @throws
	 */
	void removeSpec(String id);
	
}
