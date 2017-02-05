package com.demo.common.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

public class canyinMode {

	//轮播
	private List<Zxad> banner;
	//餐饮美食 --3级
	private Page<Zxarticle> article ;
	//推荐排行榜
	private List<Zxarticle> paihang;
	//热门新闻
	private List<Zxarticle> xinwen;
	//餐饮二级标题
	private List<Zxarticle> title;
	
	public List<Zxad> getBanner() {
		return banner;
	}
	public void setBanner(List<Zxad> banner) {
		this.banner = banner;
	}
	public Page<Zxarticle> getArticle() {
		return article;
	}
	public void setArticle(Page<Zxarticle> article) {
		this.article = article;
	}
	public List<Zxarticle> getPaihang() {
		return paihang;
	}
	public void setPaihang(List<Zxarticle> paihang) {
		this.paihang = paihang;
	}
	public List<Zxarticle> getXinwen() {
		return xinwen;
	}
	public void setXinwen(List<Zxarticle> xinwen) {
		this.xinwen = xinwen;
	}
	public List<Zxarticle> getTitle() {
		return title;
	}
	public void setTitle(List<Zxarticle> title) {
		this.title = title;
	}
	
}
