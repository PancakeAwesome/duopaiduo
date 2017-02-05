package cn.digirun.admin.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.admin.mode.DealerItemMapModel;
import cn.digirun.admin.service.manager.IDealerItemMapManager;
import cn.digirun.admin.service.repository.DealerItemMapRepository;
import cn.digirun.component.user.model.UserAccount;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.component.user.service.manager.IUserAccountManager;
import cn.digirun.component.user.service.manager.IUserManager;
import cn.digirun.core.manager.Ret;

@Service
@Transactional
public class DealerItemMapManager implements IDealerItemMapManager {

	@Autowired
	private DealerItemMapRepository dealerItemMapRepository;
	
	@Autowired 
	IUserAccountManager userAccountManager;

	@Override
	public void save(UserInfo user,String itemIds) {
		
		
		Ret<UserAccount> uaR = userAccountManager.register(user);
		UserAccount ua = uaR.getData();
		
		Long userId = ua.getId();
		dealerItemMapRepository.deleteByUserId(userId);
		
		DealerItemMapModel vo = null;
		for (String itemId : itemIds.split(",")) {
			vo = new DealerItemMapModel();
			vo.setUserId(userId);
			vo.setItemId(itemId);
			dealerItemMapRepository.save(vo);
		}
	}

	
	
}
