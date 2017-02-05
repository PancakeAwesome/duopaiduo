package com.demo.index;

import java.util.List;











import com.demo.common.model.IndexMode;
import com.demo.common.model.Zxad;
import com.demo.common.model.Zxarticle;
import com.demo.common.model.Zxbrand;
import com.demo.common.model.Zxdegrees;
import com.demo.common.model.Zxnavigation;
import com.demo.common.model.Zxprice;
import com.demo.common.model.Zxscreen;
import com.demo.common.model.canyinMode;
import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		render("index.jsp");
	}
	public void register() {
		render("register.jsp");
	}
	public void login() {
		render("login.jsp");
	}
	//首页数据
	public void indexData() {
		
		IndexMode index = new IndexMode();
		
		List<Zxad> ad = Zxad.dao.getAd(1);
		List<Zxad> qushi = Zxad.dao.getAd(2);
		List<Zxnavigation> navigation = Zxnavigation.dao.getNavigation();
		
		List<Zxarticle> toutiao = Zxarticle.dao.getToutiao();
		List<Zxarticle> toutiaos = Zxarticle.dao.getTiaoArticle();
		List<Zxarticle> zxun = Zxarticle.dao.getZixun();
		List<Zxarticle> wenhua = Zxarticle.dao.getWenhua();
		List<Zxarticle> dong = Zxarticle.dao.getDongtai();
		List<Zxarticle> dongs = Zxarticle.dao.getDongArticle();
		List<Zxarticle> can = Zxarticle.dao.getCan();
		List<Zxarticle> cans = Zxarticle.dao.getCans();
		List<Zxarticle> huodong = Zxarticle.dao.getHuodong();
		List<Zxarticle> pinjian = Zxarticle.dao.getPinjian();
		
		List<Zxbrand> brand = Zxbrand.dao.getBrand();
		List<Zxprice> price = Zxprice.dao.getPrice();
		List<Zxdegrees> degrees=Zxdegrees.dao.getDegrees(1);
		List<Zxdegrees> flavor=Zxdegrees.dao.getDegrees(2);
		List<Zxscreen> boke=Zxscreen.dao.getBoke(1,1);
		List<Zxscreen> meitu=Zxscreen.dao.getBoke(7,2);
		
		index.setAd(ad);
		index.setNavigation(navigation);
		index.setQushi(qushi);
		index.setDong(dong);
		index.setDongs(dongs);
		index.setCan(can);
		index.setCans(cans);
		index.setPinjian(pinjian);
		index.setHuodong(huodong);
		
		index.setToutiao(toutiao);
		index.setToutiaos(toutiaos);
		index.setZxun(zxun);
		index.setWenhua(wenhua);
		
		index.setBrand(brand); //品牌
		index.setPrice(price); //价格
		index.setDegrees(degrees);
		index.setFlavor(flavor);
		index.setBoke(boke);
		index.setMeitu(meitu);
		
		setAttr("index",index);
		renderJson(index);
	}
	
	//餐厅美食页面数据
	public void canyinmeishi(){
	}
	public void canyinData(){ 
		canyinMode canyin = new canyinMode();
		canyin.setBanner(Zxad.dao.getAd(3));  //3美食轮播图
		canyin.setPaihang(Zxarticle.dao.getPaihang());
		canyin.setXinwen(Zxarticle.dao.getXinwen());
		canyin.setTitle(Zxarticle.dao.getErtitle());
		renderJson(canyin);
	}
	
	//页面数据
	public void commnoData(){ 
		canyinMode canyin = new canyinMode();
		canyin.setBanner(Zxad.dao.getAd(3));
		renderJson(canyin);
	}
	
	//文章分页 根据id 分
	public void pageData(){
		int page = getParaToInt("page");
		int rows = getParaToInt("rows");
		int id = getParaToInt("aids");
		int fpid = getParaToInt("fpid")==null?0:getParaToInt("fpid");
		renderJson(Zxarticle.dao.getCanyin(page,rows,id,fpid));
	}
	
	//文章详情页
	public void article(){
		//Zxarticle ac=);
		//ac.setAddTime(ac.getAddTime());
		setAttr("article",Zxarticle.dao.getById(getParaToInt("articleid")));
	}
	
	public void index_1(){
	}
	public void mingjiuku(){
	}
	public void jiuyezixun(){
	}
	public void jiuchangdongtai(){
	}
	public void jiuwenhua(){
	}
	public void jiulvyou(){
	}
	public void jiuqushi(){
	}
	public void jiupinjia(){
	}
	public void boke(){
	}
	public void huodong(){
	}
	public void zhuanti(){
	}
}





