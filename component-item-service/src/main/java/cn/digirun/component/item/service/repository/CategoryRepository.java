package cn.digirun.component.item.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.item.model.CategoryModel;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel,Long>{

	CategoryModel findById(String id);
}
