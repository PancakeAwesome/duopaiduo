package cn.digirun.component.order.service.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long>{
	List<OrderDetail> findByOrders_orderNo(String Orders_orderNo);
	
	
	OrderDetail findById(Integer id);
	
	void deleteById(Integer id);
	
}
