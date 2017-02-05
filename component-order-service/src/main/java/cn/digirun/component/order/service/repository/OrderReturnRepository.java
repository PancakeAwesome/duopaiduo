package cn.digirun.component.order.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.OrderReturn;

@Repository
public interface OrderReturnRepository extends PagingAndSortingRepository<OrderReturn, Long>,JpaSpecificationExecutor<OrderReturn>{
	
	OrderReturn findByOrderNo(String orderNo);
	
	List<OrderReturn> findByUserIdAndStatus(Integer userId,Integer status);
	
	List<OrderReturn> findByStatus(Integer status);
	
	List<OrderReturn> findByUserId(Integer userId);
}
