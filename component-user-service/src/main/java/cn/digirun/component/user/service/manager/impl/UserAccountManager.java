package cn.digirun.component.user.service.manager.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import cn.digirun.component.user.model.User;
import cn.digirun.component.user.model.UserAccount;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.component.user.service.manager.IUserAccountManager;
import cn.digirun.component.user.service.repository.UserAccountRepository;
import cn.digirun.component.user.service.repository.UserRepository;
import cn.digirun.core.api.security.TokenCacheManager;
import cn.digirun.core.api.security.TokenResponse;
import cn.digirun.core.manager.Ret;
import cn.digirun.core.util.PasswordUtils;

@Service
@Transactional
public class UserAccountManager implements IUserAccountManager {

	@Autowired
	private UserAccountRepository userAccountRepository;
   
	@Autowired
	private UserRepository  userRepository;
	
	
	@Autowired
	private TokenCacheManager tokenCacheMgr;

	@Override
	public Ret<TokenResponse> login(String username, String password) {

		UserAccount account = userAccountRepository.findByUsername(username);
		if (account == null)
			return Ret.fail("您还没有注册，请先注册");

		if (!PasswordUtils.compare(account.getPassword(), password, account.getSalt()))
			return Ret.fail("密码不正确，请重新输入");

		TokenResponse resp = new TokenResponse();
		resp.setCreateTime(new Date());
		resp.setExpireTime(DateUtils.addDays(resp.getCreateTime(), 365));
		resp.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
		resp.setUser(account);
		resp.setUserId(account.getId());
		resp.setPromoterId(account.getPromoterId());
		tokenCacheMgr.put(resp);
		String existsToken = tokenCacheMgr.userTokenRefGet(account.getId());
		if (existsToken != null)
			tokenCacheMgr.del(existsToken);
		tokenCacheMgr.userTokenRefPut(account.getId(), resp.getToken());

		return Ret.success(resp);
	}

	@Override
	public Ret<UserAccount> register(UserInfo userInfo) {
		UserAccount account = userAccountRepository.findByUsername(userInfo.getUsername());
		UserAccount userAccount = new UserAccount();
		if (account != null)
				return Ret.fail("该用户已注册,请重新注册");
		if(userInfo.getPromoterCode()!=null&& userInfo.getPromoterCode()!=""&&!userInfo.getPromoterCode().isEmpty()){
			System.out.println("promoterCode:"+userInfo.getPromoterCode());
			UserAccount promote = userAccountRepository.findByPromoterCode(userInfo.getPromoterCode());
			if(promote==null){
				    return Ret.fail("推广人不存在,请重新填写");
			}
			userAccount.setPromoterId(promote.getId());
		}
		User user = new User();
		if(userInfo.getCityId()!=null){
			user.setCityId(userInfo.getCityId());
			user.setProvinceId(userInfo.getProvinceId());
			user.setCityName(userInfo.getCityName());
			user.setProvinceName(userInfo.getProvinceName());
			userRepository.save(user);
		    userAccount.setUser(user);
		}
		String salt =PasswordUtils.salt(4);//生成4位的盐
		userAccount.setPassword(DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()) + salt).getBytes()));
		userAccount.setUsername(userInfo.getUsername());
		userAccount.setSalt(salt);
		userAccount.setRegisterTime(new Date());
		userAccount.setUserType(userInfo.getUserType());
		
		
		return Ret.success(userAccountRepository.save(userAccount));
	}

}
