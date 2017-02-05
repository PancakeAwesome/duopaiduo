package cn.digirun.component.user.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.user.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
 
	
	
	
	User findByName(String name);
}
