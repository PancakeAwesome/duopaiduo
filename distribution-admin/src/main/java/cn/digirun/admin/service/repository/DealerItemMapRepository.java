package cn.digirun.admin.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.admin.mode.DealerItemMapModel;


@Repository
public interface DealerItemMapRepository extends PagingAndSortingRepository<DealerItemMapModel, Long>{

	void deleteByUserId(Long userId);
}
