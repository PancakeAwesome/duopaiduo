package cn.digirun.admin.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.admin.service.manager.IAdminManager;
import cn.digirun.admin.service.model.Admin;
import cn.digirun.admin.service.repository.AdminRepository;

/** 
 * @ClassName: AdminManager 
 * @Description: 管理员管理
 * @author 管东海
 *  
 */
@Service
@Transactional
public class AdminManager implements IAdminManager {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Admin admin = adminRepository.findByUsername(username);
		if (admin == null)
			throw new UsernameNotFoundException("没有找到匹配的用户信息:" + username);

		admin.getAuthorities().size();

		admin.getAuthorities().forEach(g -> {
			System.out.println(g.getAuthority());
		});
		return admin;
	}

}
