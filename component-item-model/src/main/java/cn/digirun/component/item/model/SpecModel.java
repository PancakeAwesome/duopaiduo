package cn.digirun.component.item.model;

import org.springframework.data.annotation.Id;

/**
 * 
 * @ClassName: SpecModel 
 * @Description: 规格
 *
 */
public class SpecModel {

	@Id
	private String id;
	
	/**
	 * 规格名称
	 */
	private String specName;
	
	/**
	 * 规格值
	 */
	private String[] specValues; 
	
	/**
	 * 规格拓展1
	 */
	private String[] specExt1;
	
	/**
	 * 规格拓展2
	 */
	private String[] specExt2;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String[] getSpecValues() {
		return specValues;
	}

	public void setSpecValues(String[] specValues) {
		this.specValues = specValues;
	}

	public String[] getSpecExt1() {
		return specExt1;
	}

	public void setSpecExt1(String[] specExt1) {
		this.specExt1 = specExt1;
	}

	public String[] getSpecExt2() {
		return specExt2;
	}

	public void setSpecExt2(String[] specExt2) {
		this.specExt2 = specExt2;
	}
	
}