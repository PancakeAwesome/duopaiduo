package cn.digirun.component.item.model;

import org.springframework.data.annotation.Id;

/**
 * 
 * @ClassName: BrandModel 
 * @Description: 品牌
 * @author 
 *
 */
public class BrandModel {

	@Id
	private String id;
	
	/**
	 * 品牌中文名
	 */
	private String brandZHName;
	
	/**
	 * 品牌英文名
	 */
	private String brandENName;
	
	private String brandLogo;
	
	/**
	 * 拼音
	 */
	private String phonetic;
	
	private String sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandZHName() {
		return brandZHName;
	}

	public void setBrandZHName(String brandZHName) {
		this.brandZHName = brandZHName;
	}

	public String getBrandENName() {
		return brandENName;
	}

	public void setBrandENName(String brandENName) {
		this.brandENName = brandENName;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}
	
}
