package cn.digirun.component.item.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.ItemModel;
import cn.digirun.component.item.model.ItemPriceModel;
import cn.digirun.component.item.service.manager.IItemPriceManager;
import cn.digirun.component.item.service.repository.ItemPriceRepository;

@Service
@Transactional
public class ItemPirceManager implements IItemPriceManager {

	@Autowired
	private ItemPriceRepository itemPirceRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void operateItemPrice(ItemPriceModel itemPrice) {
		itemPirceRepository.save(itemPrice);
	}

	@Override
	public void removeItemPrice(String id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), ItemModel.class);
	}

	@Override
	public void updateItemPrice(ItemPriceModel itemPrice) {
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("id").is(itemPrice.getId()),
				Criteria.where("itemId").is(itemPrice.getItemId()));
		Query query = new Query(criteria);
		Update update = new Update().set("realPrice", itemPrice.getRealPrice());

		mongoTemplate.updateFirst(query, update, ItemModel.class);
	}

	@Override
	public ItemPriceModel getItemPriceByItemId(String itemId) {
		return itemPirceRepository.findOneByItemId(itemId);
	}

}
