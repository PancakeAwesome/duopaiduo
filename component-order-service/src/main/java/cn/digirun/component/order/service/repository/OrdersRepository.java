package cn.digirun.component.order.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.Orders;
import cn.digirun.component.order.service.DBRepository;

@Repository
public interface OrdersRepository extends DBRepository<Orders, Long>,JpaSpecificationExecutor<Orders>{
	
	List<Orders> findByUserIdAndIsAccountDel(Integer userId,Integer isAccountDel);
	
	List<Orders> findByUserIdAndStatus(Integer userId,Integer status);
	
	List<Orders> findByUserId(Integer userId);
	
	List<Orders> findByStatus(Integer status);
	
	Orders findByOrderNo(String orderNo);
	
	Orders findFirstByUserIdOrderByIdDesc(Integer userId);
}
