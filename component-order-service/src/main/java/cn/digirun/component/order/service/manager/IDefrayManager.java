package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.Defray;

/**
 * 支付类型Serv
 * @author Administrator
 *
 */
public interface IDefrayManager {
	
	/**
	 * 获取所有支付类型
	 * @param userlogisticsReq
	 * @return
	 */
	List<Defray> getDefrays();
	
	/**
	 * 根据ID获取支付类型
	 * @param id
	 * @return
	 */
	Defray getDefrayById(Long id);
	
	/**
	 * 保存支付类型
	 * @param defrayReq
	 * @return
	 */
	public void saveDefray(Defray vo);
	
	/**
	 * 删除支付类型
	 * @param id
	 * @return
	 */
	void delDefrayById(Long id);
	
}
