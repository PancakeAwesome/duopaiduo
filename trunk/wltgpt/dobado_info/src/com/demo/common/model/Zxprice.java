package com.demo.common.model;

import java.util.List;

import com.demo.common.model.base.BaseZxprice;

/**
 * Generated by JFinal.
 * 价格
 */
@SuppressWarnings("serial")
public class Zxprice extends BaseZxprice<Zxprice> {
	public static final Zxprice dao = new Zxprice();
	public List<Zxprice> getPrice(){
		return find("SELECT * FROM t_zxprice t LIMIT 8");
	}
 }