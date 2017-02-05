package com.demo.advert;



import com.demo.common.model.Goods;
import com.demo.common.model.Orders;
import com.demo.common.model.Zxadmin;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.demo.common.model.Package;
import com.demo.util.PrimaryGenerater;

/**
 * 广告业务提交订单
 * @author wj
 *
 */
public class ItemController extends Controller {
	
	public void index(){
		
	}
	public void getGoods(){
		renderJson(Goods.dao.findById(getParaToInt("goodsId")));
	}
	
	public void getOrder(){
		Orders orders = Orders.dao.getOrder(getPara("orderno"));
		renderJson(orders);
	}
	
	public void saveOrder(){
		Orders orders = getModel(Orders.class, "menu");
		
		Goods goods = Goods.dao.findById(orders.getGoodsId());
		Package pack=Package.dao.findById(orders.getPackageId());
		Integer number = pack.getPackageNumber()/2;
		
		Double price = goods.getPrice().doubleValue()*number;
		java.math.BigDecimal bd1 = new java.math.BigDecimal( price );
		
		orders.setOrderMoney(bd1);
		//订单号生成
		String maxOrderId = orders.getPhone().substring(7, 11);
        String nextOrderId = PrimaryGenerater.getInstance().generaterNextNumber(maxOrderId);
        orders.setOrderNo(nextOrderId);
        if(orders.save()){
        	renderJson(nextOrderId);    
        }else{
        	renderJson(0);    
        }
          
       
		
	}
	
}
