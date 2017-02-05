package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.Defray;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IDefrayManager;
import cn.digirun.component.order.service.repository.DefrayRepository;

@Service
@Transactional
public class DefrayManager implements IDefrayManager {

	@Autowired
	private DefrayRepository defrayRepository;


	@Override
	public List<Defray> getDefrays() {
		return (List<Defray>) defrayRepository.findAll();
	}

	@Override
	public Defray getDefrayById(Long id) {
		return defrayRepository.findOne(id);
	}

	@Override
	public void saveDefray(Defray vo) {
		if(vo.getId()==null){
			vo.setStatus(Constant.STATUS_OK);
		}
		defrayRepository.save(vo);
		
	}

	@Override
	public void delDefrayById(Long id) {
		
		Defray vo = defrayRepository.findOne(id);
		vo.setStatus(Constant.STATUS_DEL);
		defrayRepository.save(vo);
	}
	
	
}
