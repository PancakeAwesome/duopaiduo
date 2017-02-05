package cn.digirun.component.item.service.manager.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.CategoryModel;
import cn.digirun.component.item.service.manager.ICategoryManager;
import cn.digirun.component.item.service.repository.CategoryRepository;
import cn.digirun.component.item.service.util.commons.PinyinUitl;

@Service
@Transactional
public class CategoryManager implements ICategoryManager {

	@Autowired
	private CategoryRepository CategoryRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void operateItemCategory(CategoryModel categoryModel) {

		if (StringUtils.isNotEmpty(categoryModel.getCateZHName())) {
			categoryModel.setPhonetic(PinyinUitl.getPinyiin(categoryModel.getCateZHName()));
		}
		CategoryRepository.save(categoryModel);
	}

	@Override
	public List<CategoryModel> getAllCategorys() {
		return CategoryRepository.findAll();
	}

	@Override
	public CategoryModel getCategoryById(String id) {
		return CategoryRepository.findById(id);
	}

	@Override
	public void removeCategory(String id) {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), CategoryModel.class);
	}

}
