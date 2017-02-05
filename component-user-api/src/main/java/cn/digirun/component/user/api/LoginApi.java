package cn.digirun.component.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.user.api.bean.LoginRequest;
import cn.digirun.component.user.service.manager.IUserAccountManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.api.security.TokenResponse;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: LoginApi 
 * @Description: 登录api
 * @author 管东海
 *  
 */
@RestController
@RequestMapping("/login")
public class LoginApi extends BasicApi{
	
	@Autowired
	private IUserAccountManager userAccountMgr;

	@RequestMapping(method = RequestMethod.POST)
	public Ret<TokenResponse> post(@ApiBody LoginRequest loginRequest) {
		return userAccountMgr.login(loginRequest.getUsername(), loginRequest.getPassword());
	}
}
