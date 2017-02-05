package cn.digirun.admin.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.admin.service.model.Module;

/** 
 * @ClassName: ModuleRepository 
 * @Description: 模块数据访问
 * @author 管东海
 *  
 */
@Repository
public interface ModuleRepository extends PagingAndSortingRepository<Module, String>{

	Module findFirstByName(String name);
}
