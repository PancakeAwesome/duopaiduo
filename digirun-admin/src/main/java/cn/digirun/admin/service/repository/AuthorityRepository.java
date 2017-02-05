package cn.digirun.admin.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.admin.service.model.Authority;

/** 
 * @ClassName: AuthorityRepository 
 * @Description: 权限数据访问
 * @author 管东海
 *  
 */
@Repository
public interface AuthorityRepository extends PagingAndSortingRepository<Authority,String>{

}
