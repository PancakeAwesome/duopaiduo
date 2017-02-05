package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.Delivery;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IDeliveryManager;
import cn.digirun.component.order.service.repository.DeliveryRepository;

@Service
@Transactional
public class DeliveryManager implements IDeliveryManager {

	@Autowired
	private DeliveryRepository deliveryRepository;

	

	@Override
	public List<Delivery> getDeliverys() {
		return (List<Delivery>) deliveryRepository.findAll();
	}

	@Override
	public Delivery getDeliveryById(Long id) {
		return deliveryRepository.findOne(id);
	}

	@Override
	public void saveDelivery(Delivery vo) {
		
		if(vo.getId()==null){
			vo.setStatus(Constant.STATUS_OK);
		}
		deliveryRepository.save(vo);
		
	}

	@Override
	public void delDeliveryById(Long id) {
		Delivery vo = deliveryRepository.findOne(id);
		vo.setStatus(Constant.STATUS_DEL);
		deliveryRepository.save(vo);
	}
	
	
}
