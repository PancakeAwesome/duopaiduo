package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePackage<M extends BasePackage<M>> extends Model<M> implements IBean {

	public void setPackageId(java.lang.Long packageId) {
		set("package_id", packageId);
	}

	public java.lang.Long getPackageId() {
		return get("package_id");
	}

	public void setPackageName(java.lang.String packageName) {
		set("package_name", packageName);
	}

	public java.lang.String getPackageName() {
		return get("package_name");
	}

	public void setPackageNumber(java.lang.Integer packageNumber) {
		set("package_number", packageNumber);
	}

	public java.lang.Integer getPackageNumber() {
		return get("package_number");
	}

	public void setAddTime(java.util.Date addTime) {
		set("add_time", addTime);
	}

	public java.util.Date getAddTime() {
		return get("add_time");
	}

}
