package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.Dispatching;

/**
 * 配送方式Serv
 * @author Administrator
 *
 */
public interface IDispatchingManager {
	
	/**
	 * 获取所有 配送方式
	 * @param userlogisticsReq
	 * @return
	 */
	List<Dispatching> getDispatchings();
	
	/**
	 * 根据ID获取 配送方式
	 * @param id
	 * @return
	 */
	Dispatching getDispatchingById(Long id);
	
	/**
	 * 保存 配送方式
	 * @param DispatchingReq
	 * @return
	 */
	public void saveDispatching(Dispatching vo);
	
	/**
	 * 删除 配送方式
	 * @param id
	 * @return
	 */
	void delDispatchingById(Long id);
	
}
