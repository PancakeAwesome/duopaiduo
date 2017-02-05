package cn.digirun.component.user.api;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.user.api.bean.ModifyUserRequest;
import cn.digirun.component.user.model.User;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.component.user.service.manager.IUserManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.api.security.NeedSign;
import cn.digirun.core.api.security.NeedToken;
import cn.digirun.core.api.security.SessionToken;
import cn.digirun.core.api.security.TokenResponse;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: UserApi 
 * @Description: 用户api
 * @author 管东海
 *  
 */
@RestController
@RequestMapping("/user")
public class UserApi extends BasicApi {

	@Autowired
	private IUserManager userMgr;

	@RequestMapping(method = RequestMethod.GET)
	@NeedToken
	public Ret<User> get(@SessionToken TokenResponse tokenResp) {
		return userMgr.get(tokenResp.getUser().getId());
	}

	@RequestMapping(method = RequestMethod.PUT)
	@NeedToken
	@NeedSign
	public Ret<User> put(@ApiBody ModifyUserRequest req, @SessionToken TokenResponse tokenResp) {
		User user = new User();
		BeanUtils.copyProperties(req, user);
		return userMgr.updateByAccountId(user,tokenResp.getUser().getId());
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/post")
	public Ret<User> post(@RequestParam Long userId) {
		System.out.println(userId);
		return userMgr.get(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/modifyUserInfo")
	public Ret<Boolean>modifyUserInfo(@ApiBody UserInfo userInfo){
		return userMgr.modifyUserInfo(userInfo);
		
	}
 	
}
