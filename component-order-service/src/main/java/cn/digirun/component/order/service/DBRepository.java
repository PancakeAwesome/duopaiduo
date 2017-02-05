package cn.digirun.component.order.service;
import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/** 
 * @ClassName: DBRepository 
 * @Description: JPA扩展
 * @author 管东海
 * 
 * @param <T>
 * @param <ID> 
 */
@NoRepositoryBean
public interface DBRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
	
}
