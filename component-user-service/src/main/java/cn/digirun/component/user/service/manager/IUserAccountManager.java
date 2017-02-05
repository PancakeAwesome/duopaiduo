package cn.digirun.component.user.service.manager;

import cn.digirun.component.user.model.UserAccount;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.core.api.security.TokenResponse;
import cn.digirun.core.manager.Ret;

public interface IUserAccountManager{
	
	Ret<TokenResponse> login(String username,String password);
	Ret<UserAccount> register(UserInfo userInfo);
}
