package cn.digirun.component.item.service.manager.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.SpecModel;
import cn.digirun.component.item.service.manager.ISpecManager;
import cn.digirun.component.item.service.repository.SpecRepository;

@Service
@Transactional
public class SpecManager implements ISpecManager {

	@Autowired
	private SpecRepository specRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void operateSpec(SpecModel specModel) {

		SpecModel model = specRepository.findBySpecName(specModel.getSpecName());
		if (null != model) {
			specModel.setId(model.getId());
			BeanUtils.copyProperties(specModel, model);
			specRepository.save(model);
		} else {
			specRepository.save(specModel);
		}

		// build query
		// Query query = new
		// Query(Criteria.where("specName").is(specModel.getSpecName()));
		// Update update = new Update().set("specValues",
		// specModel.getSpecValues()).set("specExt1", "1");

		// build update
		// DBObject dbDoc = new BasicDBObject();
		// mongoOperations.getConverter().write(specModel, dbDoc);
		// Update update = Update.fromDBObject(dbDoc);
		// mongoTemplate.upsert(query, update, "descriptions");
		// mongoOperations.upsert(query, update, SpecModel.class);
		// mongoOperations.insert(specModel);
	}

	@Override
	public List<SpecModel> getAllSpecs() {
		return specRepository.findAll();
	}

	@Override
	public void removeSpec(String id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), SpecModel.class);
		// mongoOperations.upsert(Query.query(Criteria.where("").is(""),
		// Update.fromDBObject(object, exclude), entityClass)
		// mongoOperations.findAndModify(query, update, entityClass)
	}

}
