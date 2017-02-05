package cn.digirun.component.item.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cn.digirun.component.item.model.BrandModel;

@Repository
public interface BrandRepository extends MongoRepository<BrandModel, Long>{

	BrandModel findById(String id);
	
}
