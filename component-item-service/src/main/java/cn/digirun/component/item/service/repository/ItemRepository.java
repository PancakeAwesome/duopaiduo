package cn.digirun.component.item.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.item.model.ItemModel;

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, Long> {

	ItemModel findOneById(String id);

	ItemModel findByIdOrItemName(String id, String itemName);

	
	List<ItemModel> findByCategoryId(String categoryId);

}
