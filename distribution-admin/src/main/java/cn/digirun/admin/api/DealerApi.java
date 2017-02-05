package cn.digirun.admin.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.admin.api.req.DealerReq;
import cn.digirun.admin.service.manager.IDealerItemMapManager;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderDefrayApi 
 * @Description: 经销商维护
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/dealer")
public class DealerApi extends BasicApi{
	
	/**
	 * 用户类型(1:经销商; 2:业务员)
	 */
	private static final Integer DEALER = 1;
	@Autowired
	private IDealerItemMapManager dealerManager;
	
	
	
	/**
	 * 保存经销商
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveDealer")
	public Ret<Boolean> saveDealer(@ApiBody DealerReq req) {
		UserInfo user = new UserInfo();
		BeanUtils.copyProperties(req, user);
		user.setUserType(DealerApi.DEALER);
		dealerManager.save(user, req.getItemIds());
		return Ret.success(true);
	}
	
	
}
