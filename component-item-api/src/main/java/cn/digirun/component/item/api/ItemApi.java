package cn.digirun.component.item.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.digirun.component.item.api.bean.OperateBrandRequest;
import cn.digirun.component.item.api.bean.OperateCategoryRequest;
import cn.digirun.component.item.api.bean.OperateItemPirceRequest;
import cn.digirun.component.item.api.bean.OperateItemRequest;
import cn.digirun.component.item.api.bean.OperateSpecRequest;
import cn.digirun.component.item.api.bean.UpdateItemPriceRequest;
import cn.digirun.component.item.api.bean.UpdateItemStockRequest;
import cn.digirun.component.item.model.BrandModel;
import cn.digirun.component.item.model.CategoryModel;
import cn.digirun.component.item.model.ItemModel;
import cn.digirun.component.item.model.ItemPriceModel;
import cn.digirun.component.item.model.ItemSpecModel;
import cn.digirun.component.item.model.SpecModel;
import cn.digirun.component.item.service.manager.IBrandManager;
import cn.digirun.component.item.service.manager.ICategoryManager;
import cn.digirun.component.item.service.manager.IItemManager;
import cn.digirun.component.item.service.manager.IItemPriceManager;
import cn.digirun.component.item.service.manager.ISpecManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/**
 * @ClassName: ItemApi
 * @Description: 商品api
 * 
 */
@RestController
@RequestMapping("/item")
public class ItemApi extends BasicApi {

	private static Logger LOG = LoggerFactory.getLogger(ItemApi.class);

	@Autowired
	private IItemManager itemManager;
	
	@Autowired
	private IItemPriceManager itemPriceManager;

	@Autowired
	private IBrandManager brandManager;
	
	
	@Autowired
	private ICategoryManager categoryManager;

	@Autowired
	private ISpecManager specManager;

	/**
	 * 
	 * @Title: 保存商品信息 @Description: TODO @return Ret<Boolean> @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveItem")
	public Ret<Boolean> operateItem(@ApiBody OperateItemRequest itemRequest) {
		LOG.info("Item's method operateItem inner, params is " + JSON.toJSONString(itemRequest));
		itemManager.operateItem(itemRequest);
		return Ret.success(true);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeItem")
	public Ret<Boolean> removeItem(String id) {
		// itemManager.operateItem(itemRequest);
		return Ret.success(true);
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/getItems")
	public Ret<List<ItemModel>> getItems() {
		LOG.info("Item's method getItems inner.");
		return Ret.success(itemManager.getItems());
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getItem")
	public Ret<ItemModel> getItem(String id) {
		LOG.info("Item's method getItem inner...id="+id);
		return Ret.success(itemManager.getItem(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateItemStock")
	public Ret<Boolean> updateItemStock(@ApiBody UpdateItemStockRequest itemStockRequest) {
		LOG.info("Item's method updateItemStock inner, params is " + JSON.toJSONString(itemStockRequest));
		ItemModel itemModel = new ItemModel();
		ItemSpecModel specModel = new ItemSpecModel();
		specModel.setItemSpecName1(itemStockRequest.getItemSpecName1());
		specModel.setItemSpecName2(itemStockRequest.getItemSpecName2());
		specModel.setStock(itemStockRequest.getStock());
		itemModel.setId(itemStockRequest.getId());
		ItemSpecModel[] itemSpecs = { specModel };
		itemModel.setItemSpecs(itemSpecs);
		itemManager.updateItemStock(itemModel);
		return Ret.success(true);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/operateItemPrice")
	public Ret<Boolean> operateItemPrice(@ApiBody OperateItemPirceRequest pirceRequest){
		LOG.info("Item's method operateItemPrice inner, params is " + JSON.toJSONString(pirceRequest));
		itemPriceManager.operateItemPrice(pirceRequest);
		return Ret.success(true);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getItemPrice")
	public Ret<ItemPriceModel> getItemPrice(String itemId){
		LOG.info("Item's method getItemPrice inner, params is " + itemId);
		return Ret.success(itemPriceManager.getItemPriceByItemId(itemId));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/removeItemPrice")
	public Ret<Boolean> removeItemPrice(String id){
		LOG.info("Item's method removeItemPrice inner, params is " + id);
		itemPriceManager.removeItemPrice(id);
		return Ret.success(true);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateItemPrice")
	public Ret<Boolean> updateItemPrice(@ApiBody UpdateItemPriceRequest updateItemPriceRequest){
		LOG.info("Item's method updateItemPrice inner, params is " + JSON.toJSONString(updateItemPriceRequest));
		ItemPriceModel itemPriceModel = new ItemPriceModel();
		BeanUtils.copyProperties(updateItemPriceRequest, itemPriceModel);
		itemPriceManager.updateItemPrice(itemPriceModel);
		return Ret.success(true);
	}
	
	/**
	 * 
	 * @Title: 保存商品品牌信息 @Description: TODO @return Ret<Boolean> @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveItemBrand")
	public Ret<Boolean> saveItemBrand(@ApiBody OperateBrandRequest brandRequest) {
		LOG.info("Item's method saveItemBrand inner, params is " + JSON.toJSONString(brandRequest));
		brandManager.operateItemBrand(brandRequest);
		return Ret.success(true);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllBrands")
	public Ret<List<BrandModel>> getAllBrands() {
		LOG.info("Item's method getAllBrands inner.");
		return Ret.success(brandManager.getAllBrands());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getByBrandId")
	public Ret<BrandModel> getByBrandId(String id) {
		LOG.info("Item's method getByBrandId inner, param is " + id);
		return Ret.success(brandManager.getBrandById(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeItemBrand")
	public Ret<Boolean> removeItemBrand(String id) {
		LOG.info("Item's method removeItemBrand inner, params is " + id);
		brandManager.removeBrand(id);
		return Ret.success(true);
	}
	
	
	
	/**
	 * 
	 * @Title: 保存商品类别信息 @Description: TODO @return Ret<Boolean> @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveItemCategory")
	public Ret<Boolean> saveItemCategory(@ApiBody OperateCategoryRequest CategoryRequest) {
		LOG.info("Item's method saveItemCategory inner, params is " + JSON.toJSONString(CategoryRequest));
		categoryManager.operateItemCategory(CategoryRequest);
		return Ret.success(true);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllCategorys")
	public Ret<List<CategoryModel>> getAllCategorys() {
		LOG.info("Item's method getAllCategorys inner.");
		return Ret.success(categoryManager.getAllCategorys());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getByCategoryId")
	public Ret<CategoryModel> getByCategoryId(String id) {
		LOG.info("Item's method getByCategoryId inner, param is " + id);
		return Ret.success(categoryManager.getCategoryById(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeItemCategory")
	public Ret<Boolean> removeItemCategory(String id) {
		LOG.info("Item's method removeItemCategory inner, params is " + id);
		categoryManager.removeCategory(id);
		return Ret.success(true);
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/operateSpec")
	public Ret<Boolean> operateSpec(@ApiBody OperateSpecRequest specRequest) {
		LOG.info("Item's method operateSpec inner, params is " + JSON.toJSONString(specRequest));
		specManager.operateSpec(specRequest);
		return Ret.success(true);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllSpecs")
	public Ret<List<SpecModel>> getAllSpecs() {
		LOG.info("Item's method getAllSpecs inner.");
		return Ret.success(specManager.getAllSpecs());
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/getItemsByCategoryId")
	public Ret<List<ItemModel>>getItemsByCategoryId(String categoryId){
		LOG.info("Item's method getItemsByCategoryId inner, params is " + categoryId);
		//return Ret.success(categoryManager.getCategoryById(id));
		return Ret.success(itemManager.getItemsByCategoryId(categoryId));  
		
	}

	/**
	 * 
	 * @Title: 上传图片 @Description: OSS @return 图片URL @throws
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	public String uploadFile(BrandModel brandModel) {
		return "";
	}

}
