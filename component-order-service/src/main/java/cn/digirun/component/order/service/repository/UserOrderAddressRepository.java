package cn.digirun.component.order.service.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.UserOrderAddress;

@Repository
public interface UserOrderAddressRepository extends PagingAndSortingRepository<UserOrderAddress, Long>{
	
	List<UserOrderAddress> findByUserId(Long userId);
	
	UserOrderAddress findByUserIdAndIsDef(Long userId,Integer isDef);
}
