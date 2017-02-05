package cn.digirun.admin.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;

import cn.digirun.admin.annotation.RequiredAuthority;
import cn.digirun.admin.annotation.RequiredModule;
import cn.digirun.admin.service.model.Admin;
import cn.digirun.admin.service.model.Authority;
import cn.digirun.admin.service.model.Module;
import cn.digirun.admin.service.model.Role;
import cn.digirun.admin.service.repository.AdminRepository;
import cn.digirun.admin.service.repository.AuthorityRepository;
import cn.digirun.admin.service.repository.ModuleRepository;
import cn.digirun.admin.service.repository.RoleRepository;

@Service
@Transactional
public class InitDataManager {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	public void saveAdmin(Admin admin) {

		if (adminRepository.findByUsername(admin.getUsername()) != null)
			return;

		adminRepository.save(admin);
	}

	public void saveRole(Role role) {

		if (roleRepository.findFirstByName(role.getName()) != null)
			return;

		roleRepository.save(role);
	}

	public void saveAuthority(Authority authority) {
		authorityRepository.save(authority);
	}

	public void saveAuthority(HandlerMethod method) {
		Authority authority = new Authority();
		RequiredAuthority requiredAuthority = method.getMethodAnnotation(RequiredAuthority.class);

		if (requiredAuthority == null)
			return;

		authority.setId(DigestUtils.md5DigestAsHex(method.toString().getBytes()));
		authority.setName(requiredAuthority.name());
		authority.setCode(requiredAuthority.code());

		Module module = new Module();
		module.setId(DigestUtils.md5DigestAsHex(method.getBeanType().getName().getBytes()));

		authority.setModule(module);
		authorityRepository.save(authority);
	}

	public void saveModule(HandlerMethod method) {

		RequiredModule requiredModule = method.getBeanType().getAnnotation(RequiredModule.class);

		if (requiredModule == null)
			return;

		Module module = new Module();
		module.setId(DigestUtils.md5DigestAsHex(method.getBeanType().getName().getBytes()));

		module.setName(requiredModule.name());

		if (!requiredModule.parent().getName().equals(Object.class.getName()))
			module.setParent(DigestUtils.md5DigestAsHex(requiredModule.parent().getName().getBytes()));

		moduleRepository.save(module);
	}

}
