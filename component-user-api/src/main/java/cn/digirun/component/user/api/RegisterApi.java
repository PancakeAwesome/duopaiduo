package cn.digirun.component.user.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.user.api.bean.RegisterRequest;
import cn.digirun.component.user.model.UserAccount;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.component.user.service.manager.IUserAccountManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: RegisterApi 
 * @Description: 注册api
 * @author zhanghd
 * 
 *  
 */
@RestController
@RequestMapping("/register")
public class RegisterApi extends BasicApi{
	
	@Autowired
	private IUserAccountManager userAccountMgr;

	@RequestMapping(method = RequestMethod.POST)
	public Ret<UserAccount> post(@ApiBody RegisterRequest registerRequest) {
		UserInfo userInfo = new  UserInfo();
		//UserAccount userAccount = new  UserAccount();
		
		BeanUtils.copyProperties(registerRequest, userInfo);
		return userAccountMgr.register(userInfo);
	}

}
