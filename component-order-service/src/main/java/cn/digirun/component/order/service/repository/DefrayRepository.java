package cn.digirun.component.order.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.Defray;

@Repository
public interface DefrayRepository extends PagingAndSortingRepository<Defray, Long>{
	
	
}
