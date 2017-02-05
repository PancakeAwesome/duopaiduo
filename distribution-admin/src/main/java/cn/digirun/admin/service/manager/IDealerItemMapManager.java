package cn.digirun.admin.service.manager;

import cn.digirun.component.user.model.UserInfo;

/**
 * 支付类型Serv
 * @author Administrator
 *
 */
public interface IDealerItemMapManager {
	void save(UserInfo user,String itemIds);
	
}
