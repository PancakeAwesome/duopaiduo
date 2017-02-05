package cn.digirun.component.order.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.SaveDefrayReq;
import cn.digirun.component.order.model.Defray;
import cn.digirun.component.order.service.manager.IDefrayManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderDefrayApi 
 * @Description: 订单_支付类型维护
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order_defray")
public class OrderDefrayApi extends BasicApi{
	
	@Autowired
	private IDefrayManager defrayManager;
	
	/**
	 * 获取所有支付类型
	 * @param userlogisticsReq
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getDefrays")
	public Ret<List<Defray>> getDefrays() {
		
	    return Ret.success(defrayManager.getDefrays());
	}
	
	/**
	 * 根据ID获取支付类型
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getDefrayById")
	public Ret<Defray> getDefrayById(Long id) {
		return Ret.success(defrayManager.getDefrayById(id));
	}
	
	/**
	 * 保存支付类型
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveDefray")
	public Ret<Boolean> saveDefray(@ApiBody SaveDefrayReq req) {
		Defray vo = new Defray();
		BeanUtils.copyProperties(req, vo);
		defrayManager.saveDefray(vo);
		return Ret.success(true);
	}
	
	/**
	 * 删除支付类型
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/delDefrayById")
	public Ret<Boolean> delDefrayById(Long id) {
		defrayManager.delDefrayById(id);
		return Ret.success(true);
	}
}
