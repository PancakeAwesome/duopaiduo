package cn.digirun.component.item.model;

import org.springframework.data.annotation.Id;

/**
 * 分类模型
 * @author qinjing
 *
 */
public class CategoryModel {

	@Id
	private String id;
	
	/**
	 * 分类名称
	 */
	private String name;
	

	/**
	 * 父级ID
	 */
	private String parentId;
	
	/**
	 * 偏移量
	 */
	private String left;
	
	/**
	 * 偏移量
	 */
	private String right;
	
	/**
	 * 排序
	 */
	private String sort;
	
	/**
	 * 分类状态
	 */
	private Integer status; 
	
	/**
	 * 分类中文名
	 */
	private String cateZHName;
	
	/**
	 * 分类英文名
	 */
	private String cateENName;
	
	private String cateIcon;
	
	private String primaryAttribute;
	
	private String phonetic;
	
	private String logo;

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}


	public String getCateZHName() {
		return cateZHName;
	}

	public void setCateZHName(String cateZHName) {
		this.cateZHName = cateZHName;
	}

	public String getCateENName() {
		return cateENName;
	}

	public void setCateENName(String cateENName) {
		this.cateENName = cateENName;
	}

	public String getCateIcon() {
		return cateIcon;
	}

	public void setCateIcon(String cateIcon) {
		this.cateIcon = cateIcon;
	}

	public String getPrimaryAttribute() {
		return primaryAttribute;
	}

	public void setPrimaryAttribute(String primaryAttribute) {
		this.primaryAttribute = primaryAttribute;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
