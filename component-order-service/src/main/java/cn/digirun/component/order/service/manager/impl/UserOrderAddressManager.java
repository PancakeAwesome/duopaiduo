package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.UserOrderAddress;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IUserOrderAddressManager;
import cn.digirun.component.order.service.repository.UserOrderAddressRepository;

@Service
@Transactional
public class UserOrderAddressManager implements IUserOrderAddressManager {

	@Autowired
	private UserOrderAddressRepository userOrderAddressRepository;


	@Override
	public List<UserOrderAddress> getUserOrderAddresss(Long userId) {
		return (List<UserOrderAddress>) userOrderAddressRepository.findByUserId(userId);
	}

	@Override
	public UserOrderAddress getUserOrderAddressById(Long id) {
		return userOrderAddressRepository.findOne(id);
	}

	@Override
	public void saveUserOrderAddress(UserOrderAddress vo) {
		if(vo.getId()==null){
			vo.setStatus(Constant.STATUS_OK);
		}
		userOrderAddressRepository.save(vo);
		
	}

	@Override
	public void delUserOrderAddressById(Long id) {
		
		UserOrderAddress vo = userOrderAddressRepository.findOne(id);
		vo.setStatus(Constant.STATUS_DEL);
		userOrderAddressRepository.save(vo);
	}

	@Override
	public void updIsDef(UserOrderAddress model) {
		UserOrderAddress vo = userOrderAddressRepository.findOne(model.getId());
		
		UserOrderAddress vo1 = userOrderAddressRepository.findByUserIdAndIsDef(vo.getUserId(), 2);
		
		if(null != vo1){
			vo1.setIsDef(1);
			userOrderAddressRepository.save(vo1);
		}
		
		vo.setIsDef(2);
		userOrderAddressRepository.save(vo);
		
	}
	
	
}
