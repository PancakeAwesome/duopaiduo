package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.Dispatching;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IDispatchingManager;
import cn.digirun.component.order.service.repository.DispatchingRepository;

@Service
@Transactional
public class DispatchingManager implements IDispatchingManager {

	@Autowired
	private DispatchingRepository dispatchingRepository;


	@Override
	public List<Dispatching> getDispatchings() {
		return (List<Dispatching>) dispatchingRepository.findAll();
	}

	@Override
	public Dispatching getDispatchingById(Long id) {
		return dispatchingRepository.findOne(id);
	}

	@Override
	public void saveDispatching(Dispatching vo) {
		if(vo.getId()==null){
			vo.setStatus(Constant.STATUS_OK);
		}
		dispatchingRepository.save(vo);
		
	}

	@Override
	public void delDispatchingById(Long id) {
		
		Dispatching vo = dispatchingRepository.findOne(id);
		vo.setStatus(Constant.STATUS_DEL);
		dispatchingRepository.save(vo);
	}
	
	
}
