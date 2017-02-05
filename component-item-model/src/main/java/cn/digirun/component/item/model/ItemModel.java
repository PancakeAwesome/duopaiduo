package cn.digirun.component.item.model;

import org.springframework.data.annotation.Id;

public class ItemModel {
	
	@Id
	private String id;
	
	private String itemName;
	
	private String itemTitle;

	/**
	 * 默认价格
	 */
	private Double defaultPrice;

	/**
	 * 佣金
	 */
	private Double brokerage;

	/**
	 * 提成
	 */
	private  Double percentage;

	/**
	 * 默认库存
	 */
	private  Integer defaultStock;
	
	/**
	 * 商品描述
	 */
	private String desc;
	
	/**
	 * 商品卖点
	 */
	private String USP;
	
	/**
	 * 商品规格
	 */
	private ItemSpecModel[] itemSpecs;
	
	/**
	 * 商品图片
	 */
	private PicModel[] pics;
	
	/**
	 * 商品品牌ID
	 */
	private String brandId;
	
	
	/**
	 * 商品类别
	 */
	private String categoryId;
	
	/**
	 * 创建日期
	 * 默认为 当前时间
	 */
//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String createTime;
	
	private String extColumn1;
	
	private String extColumn2;
	
	private String extColumn3;
	
//	private Map pictures; 图片
	
	//private Map spec; 规格
	
	//默认是否上下架(1:是;2否)
	private Integer isOnSale;
	
	//排序
	private Integer sort;
	
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}

	public String getDesc() {
		return desc;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getUSP() {
		return USP;
	}

	public void setUSP(String USP) {
		this.USP = USP;
	}

	public ItemSpecModel[] getItemSpecs() {
		return itemSpecs;
	}

	public void setItemSpecs(ItemSpecModel[] itemSpecs) {
		this.itemSpecs = itemSpecs;
	}

	public PicModel[] getPics() {
		return pics;
	}

	public void setPics(PicModel[] pics) {
		this.pics = pics;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExtColumn1() {
		return extColumn1;
	}

	public void setExtColumn1(String extColumn1) {
		this.extColumn1 = extColumn1;
	}

	public String getExtColumn2() {
		return extColumn2;
	}

	public void setExtColumn2(String extColumn2) {
		this.extColumn2 = extColumn2;
	}

	public String getExtColumn3() {
		return extColumn3;
	}

	public void setExtColumn3(String extColumn3) {
		this.extColumn3 = extColumn3;
	}

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Double getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}

	public Integer getDefaultStock() {
		return defaultStock;
	}

	public void setDefaultStock(Integer defaultStock) {
		this.defaultStock = defaultStock;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
}
