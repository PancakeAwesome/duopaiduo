package cn.digirun.admin.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.admin.service.model.Role;

/** 
 * @ClassName: RoleRepository 
 * @Description: 角色数据访问
 * @author 管东海
 *  
 */
@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>{

	Role findFirstByName(String name);
}
