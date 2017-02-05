package cn.digirun.component.user.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.digirun.component.user.model.User;
import cn.digirun.component.user.model.UserAccount;
import cn.digirun.component.user.model.UserInfo;
import cn.digirun.component.user.service.manager.IUserManager;
import cn.digirun.component.user.service.repository.UserAccountRepository;
import cn.digirun.component.user.service.repository.UserRepository;
import cn.digirun.core.manager.Ret;

@Service
@Transactional
public class UserManager implements IUserManager {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public Ret<User> get(Long id) {
		UserAccount account = userAccountRepository.findOne(id);
		return Ret.success(account.getUser());
	}

	@Override
	public Ret<User> updateByAccountId(User user, Long accountId) {
		UserAccount account = userAccountRepository.findOne(accountId);

		User userObj = account.getUser();

		if (userObj != null) {
			if (!StringUtils.isEmpty(user.getIdCard()))
				userObj.setIdCard(user.getIdCard());
			if (!StringUtils.isEmpty(user.getAddress()))
				userObj.setAddress(user.getAddress());
			if (!StringUtils.isEmpty(user.getBirthday()))
				userObj.setBirthday(user.getBirthday());
			if (!StringUtils.isEmpty(user.getEmail()))
				userObj.setEmail(user.getEmail());
			if (!StringUtils.isEmpty(user.getName()))
				userObj.setName(user.getName());
		} else {
			userObj = user;
			userRepository.save(userObj);
		}

		account.setUser(userObj);

		userAccountRepository.save(account);
		return Ret.success(userObj);
	}

	@Override
	public Ret<Boolean> modifyUserInfo(UserInfo userInfo) {
		User user = new User();
		UserAccount userAccount = new UserAccount();
		//修改用户头像
		if(userInfo.getUserPortrait()!=null)
			user.setUserPortrait(userInfo.getUserPortrait());
		
		//修改用户密码
		if(userInfo.getPassword()!=null){
			userAccount.setPassword(userInfo.getPassword());
			userAccountRepository.save(userAccount);
			Ret.success(true);
		}
        //绑定用户推广人(传的是推广码)
		if(userInfo.getPromoterCode()!=null)
			userAccount.setPromoterCode(userInfo.getPromoterCode());

		//修改城市信息
		if(userInfo.getCityName()!=null){
			user.setCityId(userInfo.getCityId());
			user.setProvinceId(userInfo.getProvinceId());
			user.setProvinceName(userInfo.getProvinceName());
			user.setCityName(userInfo.getCityName());
		}
		
		//修改用户生日
		if(userInfo.getBirthday()!=null)
			user.setBirthday(userInfo.getBirthday());
		
		//修改用户的email
		if(userInfo.getEmail()!=null)
			user.setEmail(userInfo.getEmail());
		
		//修改用户昵称(注意：这里的字段usename字段在注册里是指用户名而不是昵称，因为昵称和用户名字段类型相同所以在这里拿来做昵称字段)
		if(userInfo.getUsername()!=null)
			user.setName(userInfo.getUsername());
		
		//修改用户的手机号
		if(userInfo.getCellphone()!=null)
			user.setCellphone(userInfo.getCellphone());
		
		//修改用户地址
		if(userInfo.getAddress()!=null)
			user.setAddress(userInfo.getAddress());
		
		 userRepository.save(user);
		return Ret.success(true);
	}

}
