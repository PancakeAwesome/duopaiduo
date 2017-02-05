package com.demo.advert;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.common.model.Delivery;
import com.demo.common.model.Goods;
import com.demo.common.model.Orders;
import com.demo.common.model.Package;
import com.demo.common.model.Setmode;
import com.demo.common.model.Zxadmin;
import com.demo.util.Kuaidi;
import com.demo.util.MD5Util;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.excel.PoiRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * 广告业务
 * @author wj
 *
 */
public class AdvertController extends Controller {
	 /**
     * 生成MD5加密
     * @return
     */
    public static String createmd5(String password){
       return MD5Util.MD5Encode(MD5Util.MD5Encode("931107", "UTF-8").substring(0, 10) + password, "UTF-8");
    }
    public void login() {
		String name = getPara("userName");
		String pwd = getPara("password");
		if(name!=null && pwd !=null){
			Zxadmin admin = Zxadmin.dao.findLogin(name,pwd);
			if(admin != null){
				setSessionAttr("admin", admin);
				setSessionAttr("error",null);
				render("/advert/orders.jsp");
			}else{
				setSessionAttr("error", "用户名或密码错误！");
				render("/advert/login.jsp");
			}
		}else{
			render("/advert/login.jsp");
		}
	}
	//商品管理
	public void saveGoods(){
		Goods goods = getModel(Goods.class, "menu");
		
		renderJson(goods.save());    //添加商品
	}
	public void updateGoods(){
		renderJson(getModel(Goods.class, "menu").update());
	}
	public void pageGoods(){
		Page<Goods> pages = Goods.dao.pageGoods(getParaToInt("page"),getParaToInt("size"));
		renderJson(pages);
	}
	public void ugoods(){
		setSessionAttr("gd",Goods.dao.findById(getParaToInt("gid")));
	}
	//订单管理
	public void pageOrders(){
		String orno =getPara("orno")==null?"":getPara("orno");
		String orname =getPara("orname")==null?"":getPara("orname");
		String usname =getPara("usname")==null?"":getPara("usname");
		String usphon =getPara("usphon")==null?"":getPara("usphon");
		String atime =getPara("atime")==null?"":getPara("atime");
		String btime =getPara("btime")==null?"":getPara("btime");
		Integer page = getParaToInt("page");
		Integer size = getParaToInt("size");
		Integer status = getParaToInt("status");
		Page<Orders> pages = Orders.dao.pageOrders(page,size,orno,orname,usname,usphon,atime,btime,status);
		renderJson(pages);
	}
	public void setOrder(){
		renderJson(Orders.dao.setOrderByid(getParaToInt("orderid")));
	}
	public void updateOrder(){
		renderJson(getModel(Orders.class, "menu").update());
	}
	public void kuaidi(){
		Map<String, String> params = new HashMap<String, String>(); 
		String com = getPara("com");
		String nu = getPara("nu");
		String kuaidi_url = "";
		try {
			kuaidi_url=Kuaidi.sendSms(com, nu);
			params.put("status", "1");
			params.put("url", kuaidi_url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			params.put("status", "0");
			params.put("url", "");
		}
		renderJson(params);
	}
	//规格
	public void getPackage(){
		renderJson(Package.dao.getPack()); //规格列表
	}
	public void savePackage(){
		renderJson(getModel(Package.class, "menu").save());    //添加规格
	}
	//渠道管理
	public void fchannel(){
		
	}
	//订单统计
	public void tjorder(){
		String atime =getPara("atime")==null?"":getPara("atime");
		String btime =getPara("btime")==null?"":getPara("btime");
		String goodsName =getPara("goodsName")==null?"":getPara("goodsName");
		String zchName =getPara("zchName")==null?"":getPara("zchName");
		Integer page = getParaToInt("page");
		Integer size = getParaToInt("size");
		Page<Orders> pages = Orders.dao.pageTjorder(page,size,atime,btime,goodsName,zchName);
		renderJson(pages); 
	}  
	//结算统计
	public void tjset(){
		String atime =getPara("atime")==null?"":getPara("atime");
		String btime =getPara("btime")==null?"":getPara("btime");
		String zchName =getPara("zchName")==null?"":getPara("zchName");
		Integer page = getParaToInt("page");
		Integer size = getParaToInt("size");
		Page<Delivery> pages =Delivery.dao.pageTjset(page,size,atime,btime,zchName);
		renderJson(pages); 
	}
	//渠道统计
	public void tjchnnel(){
		String atime =getPara("atime")==null?"":getPara("atime");
		String btime =getPara("btime")==null?"":getPara("btime");
		String goodsName =getPara("goodsName")==null?"":getPara("goodsName");
		String zchName =getPara("zchName")==null?"":getPara("zchName");
		Integer page = getParaToInt("page");
		Integer size = getParaToInt("size");
		Page<Delivery> pages = Delivery.dao.channel(page,size,atime,btime,goodsName,zchName);
		renderJson(pages); 
	}  
	//订单统计导出
	public void toExcel(){
		String atime =getPara("atime")==null?"":getPara("atime");
		String btime =getPara("btime")==null?"":getPara("btime");
		String goodsName =getPara("goodsName")==null?"":getPara("goodsName");
		String zchName =getPara("zchName")==null?"":getPara("zchName");
		String[]  header={"商品名称","商品价格","购买数量","渠道名称","下单时间"};
	    String[]  columns={"goods_name","price","package_number","zchannel_name","addTime"};
	    //List<Student> list= Student.me.find("select * from student");
	    List<Record> list = Orders.dao.list(atime, btime, goodsName, zchName);
	    try{
	        PoiRender me = PoiRender.me(list).fileName("订单统计.xls").sheetName("rank").headers(header).columns(columns).cellWidth(5000).headerRow(1);
	        render(me);
	    }catch(Exception e){
	        renderNull();
	    }    
	}
	//渠道统计导出
	public void toExcela(){
		String atime =getPara("atime")==null?"":getPara("atime");
		String btime =getPara("btime")==null?"":getPara("btime");
		String goodsName =getPara("goodsName")==null?"":getPara("goodsName");
		String zchName =getPara("zchName")==null?"":getPara("zchName");
		String[]  header={"渠道名称","投放时间","结束时间","商品名称","渠道提成比例","运费总额","总订单量","有效订单","订单总额","有效订单总额","渠道提成"};
	    String[]  columns={"zchannel_name","start_time","end_time","goods_name","goods_channel_rate","logistMoney","ordersNumber","ordersNumbers","orderMoney","orderMoneys","orderMoneyx"};
	    //List<Student> list= Student.me.find("select * from student");
	    List<Record> list = Delivery.dao.list(atime, btime, goodsName, zchName);
	    try{
	        PoiRender me = PoiRender.me(list).fileName("渠道统计.xls").sheetName("rank").headers(header).columns(columns).cellWidth(5000).headerRow(1);
	        render(me);
	    }catch(Exception e){
	        renderNull();
	    }    
	}
}
