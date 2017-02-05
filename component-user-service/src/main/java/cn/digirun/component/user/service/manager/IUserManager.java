package cn.digirun.component.user.service.manager;

import cn.digirun.component.user.model.User;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.core.manager.Ret;

public interface IUserManager {
	
	Ret<User> get(Long id);
	Ret<Boolean> modifyUserInfo(UserInfo userInfo);
	Ret<User> updateByAccountId(User user,Long accountId);
}
