package cn.digirun.component.user.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.user.model.UserAccount;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{

	UserAccount findByPromoterCode(String promoterCode);
	UserAccount findByUsername(String username);
}
