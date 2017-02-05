package com.wfb.common.model;

public class Nation {
	private Integer id;
	
	/**
	 * 民族代码
	 */
	private Integer mzdm;
	
	/**
	 * 民族名称
	 */
	private String mzmc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMzdm() {
		return mzdm;
	}
	public void setMzdm(Integer mzdm) {
		this.mzdm = mzdm;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}
}
