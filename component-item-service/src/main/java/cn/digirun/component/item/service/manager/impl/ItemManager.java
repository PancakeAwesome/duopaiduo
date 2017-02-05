package cn.digirun.component.item.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.ItemModel;
import cn.digirun.component.item.service.manager.IItemManager;
import cn.digirun.component.item.service.repository.ItemRepository;

@Service
@Transactional
public class ItemManager implements IItemManager {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void operateItem(ItemModel item) {
		itemRepository.save(item);
	}

	@Override
	public void removeItem(String id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), ItemModel.class);
	}

	@Override
	public List<ItemModel> getItems() {
		return itemRepository.findAll();
	}
	
	@Override
	public List<ItemModel> getItemsByCategoryId(String categoryId){
		return itemRepository.findByCategoryId(categoryId);
	}
	
	@Override
	public ItemModel getItem(String id) {
		return itemRepository.findByIdOrItemName(id, "");
	}

	@Override
	public void updateItemStock(ItemModel item) {
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("id").is(item.getId()),
				Criteria.where("itemSpecs.itemSpecName1").is(item.getItemSpecs()[0].getItemSpecName1()),
				Criteria.where("itemSpecs.itemSpecName2").is(item.getItemSpecs()[0].getItemSpecName2()));
		Query query = new Query(criteria);
		Update update = new Update().set("itemSpecs.$.stock", item.getItemSpecs()[0].getStock());

		mongoTemplate.updateFirst(query, update, ItemModel.class);
	}



}
