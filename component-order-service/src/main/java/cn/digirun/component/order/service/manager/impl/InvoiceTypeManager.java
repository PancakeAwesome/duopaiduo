package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.InvoiceType;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IInvoiceTypeManager;
import cn.digirun.component.order.service.repository.InvoiceTypeRepository;

@Service
@Transactional
public class InvoiceTypeManager implements IInvoiceTypeManager {

	@Autowired
	private InvoiceTypeRepository invoiceTypeRepository;


	@Override
	public List<InvoiceType> getInvoiceTypes() {
		return (List<InvoiceType>) invoiceTypeRepository.findAll();
	}

	@Override
	public InvoiceType getInvoiceTypeById(Long id) {
		return invoiceTypeRepository.findOne(id);
	}

	@Override
	public void saveInvoiceType(InvoiceType vo) {
		if(vo.getId()==null){
			vo.setStatus(Constant.STATUS_OK);
		}
		invoiceTypeRepository.save(vo);
		
	}

	@Override
	public void delInvoiceTypeById(Long id) {
		
		InvoiceType vo = invoiceTypeRepository.findOne(id);
		vo.setStatus(Constant.STATUS_DEL);
		invoiceTypeRepository.save(vo);
	}
	
	
}
