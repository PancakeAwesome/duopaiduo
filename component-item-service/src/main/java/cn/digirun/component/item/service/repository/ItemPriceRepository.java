package cn.digirun.component.item.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.digirun.component.item.model.ItemPriceModel;

@Repository
public interface ItemPriceRepository extends MongoRepository<ItemPriceModel, Long> {

	ItemPriceModel findOneById(String id);
	
	ItemPriceModel findOneByItemId(String itemId);

}
