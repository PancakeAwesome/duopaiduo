package cn.digirun.admin.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.admin.service.model.Admin;

/** 
 * @ClassName: AdminRepository 
 * @Description: 管理员数据访问
 * @author 管东海
 *  
 */
@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer>{

	Admin findByUsername(String username);
}
