package cn.digirun.component.item.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.item.model.SpecModel;

@Repository
public interface SpecRepository extends MongoRepository<SpecModel, Long>{

	SpecModel findById(String id);
	
	SpecModel findBySpecName(String specName);
	
}
