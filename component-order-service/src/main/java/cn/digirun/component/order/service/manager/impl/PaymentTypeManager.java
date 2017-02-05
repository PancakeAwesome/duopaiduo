package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.PaymentType;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IPaymentTypeManager;
import cn.digirun.component.order.service.repository.paymentTypeRepository;

@Service
@Transactional
public class PaymentTypeManager implements IPaymentTypeManager {

	@Autowired
	private paymentTypeRepository paymentTypeRepository;


	@Override
	public List<PaymentType> getPaymentTypes() {
		return (List<PaymentType>) paymentTypeRepository.findAll();
	}

	@Override
	public PaymentType getPaymentTypeById(Long id) {
		return paymentTypeRepository.findOne(id);
	}

	@Override
	public void savePaymentType(PaymentType vo) {
		if(vo.getId()==null){
			vo.setStatus(Constant.STATUS_OK);
		}
		paymentTypeRepository.save(vo);
		
	}

	@Override
	public void delPaymentTypeById(Long id) {
		
		PaymentType vo = paymentTypeRepository.findOne(id);
		vo.setStatus(Constant.STATUS_DEL);
		paymentTypeRepository.save(vo);
	}
	
	
}
