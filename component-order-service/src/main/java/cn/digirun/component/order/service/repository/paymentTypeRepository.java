package cn.digirun.component.order.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.PaymentType;

@Repository
public interface paymentTypeRepository extends PagingAndSortingRepository<PaymentType, Long>{
	
	
}
