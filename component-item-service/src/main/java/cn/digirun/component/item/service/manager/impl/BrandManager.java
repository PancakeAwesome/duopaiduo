package cn.digirun.component.item.service.manager.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.BrandModel;
import cn.digirun.component.item.service.manager.IBrandManager;
import cn.digirun.component.item.service.repository.BrandRepository;
import cn.digirun.component.item.service.util.commons.PinyinUitl;

@Service
@Transactional
public class BrandManager implements IBrandManager {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void operateItemBrand(BrandModel brandModel) {

		if (StringUtils.isNotEmpty(brandModel.getBrandZHName())) {
			brandModel.setPhonetic(PinyinUitl.getPinyiin(brandModel.getBrandZHName()));
		}
		brandRepository.save(brandModel);
	}

	@Override
	public List<BrandModel> getAllBrands() {
		return brandRepository.findAll();
	}

	@Override
	public BrandModel getBrandById(String id) {
		return brandRepository.findById(id);
	}

	@Override
	public void removeBrand(String id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), BrandModel.class);
	}

}
