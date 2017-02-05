package cn.digirun.component.order.service.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.order.model.AreaModel;


@Repository
public interface AreaRepository extends PagingAndSortingRepository<AreaModel, Long>{
	List<AreaModel> findByFPid(Integer fPid);
}
