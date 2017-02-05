package cn.digirun.component.order.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.item.model.ItemModelOrder;
import cn.digirun.component.order.api.bean.CartItems;
import cn.digirun.component.order.api.bean.CartProperty;
import cn.digirun.component.order.model.CartModel;
import cn.digirun.component.order.service.manager.ICartManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/**
 * @Description: 购物车api
 * @author :zhanghd
 * @ClassName:CartApi
 * @Date 2016 7 25
 */
@RestController
@RequestMapping("/cart")
public class CartApi extends BasicApi {
   

	@Autowired
	private ICartManager cartManager;

	/**
	 * 获取购物车里面商品
	 * @param userId
	 * @return
	 */
//	@RequestMapping(method = RequestMethod.GET, value="/getOrderDetail")
//	public Ret<Orders> getCartItems(String userId) {
//		return Ret.success(cartManager.getOrderDetail(userId));
//	}
	/**
	 * 添加商品到购物车
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveCart")
	public Ret<String> saveCart(@ApiBody CartProperty req ) {
		CartModel cart = new CartModel();
		BeanUtils.copyProperties(req, cart);
		CartModel cartModel = cartManager.findByUserId(cart);
		if(cartModel!=null){
			cartModel.setAmount(cartModel.getAmount()+req.getAmount());
			cartManager.saveCart(cartModel);
		}else
			cartManager.saveCart(cart);
	    
	    return Ret.success("");
	    
	}
	
	/**
	 * 
	 *将"我的购物车中商品删掉"
	 *进入"我的购物车",将不需要的（参见淘宝的购物车中删除商品）商品从"我的购物车"中删除掉
	 *“删除”是指在“我的购物车”页面不在显示该商品，但是在所有商品中还是会展示（除非商品下架）
	 *
	 *param:cartItems
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/removeItem")
	public Ret<Boolean> removeItem(@ApiBody CartItems cartItems) {
		List<CartModel> cartModels = new ArrayList<CartModel>();
		
		for(int i=0;i<cartItems.getItemIds().size();i++){
			CartModel cart = new CartModel();
			cart.setItemId(cartItems.getItemIds().get(i));
			cart.setUserId(cartItems.getUserId());
			cartModels.add(cart);
		}
		cartManager.delItemsFromCart(cartModels);
		return Ret.success(true);
	}
	
	/*@RequestMapping(method = RequestMethod.GET,value = "")
	public Ret<List<ItemModelOrder>> getItemsFromMyCart(){
		//return Ret.success(cartManager.getItem(""));
	}
	*/
	
//	@RequestMapping(method = RequestMethod.POST, value="/updOrder")
//	public Ret<Boolean> updOrder(@ApiBody UpdOrderReque req) {
//		Orders order = new Orders();
//		BeanUtils.copyProperties(req, order);
//		
//		if(null != req.getUserOrderAddressId()){
//			 //用户订单地址对象
//		    order.setUserOrderAddress(new UserOrderAddress(req.getUserOrderAddressId()));
//		}
//	   
//	    if(null!= req.getInvoiceTypeId()){
//	    	//发票类型 
//		    order.setInvoiceType(new InvoiceType(req.getInvoiceTypeId()));
//	    }
//	    
//	    if(null != req.getDispatchingId()){
//	    	 //配送方式
//		    order.setDispatching(new Dispatching(req.getDispatchingId()));
//	    }
//	    
//	    orderManager.updOrder(order);
//	    
//	    return Ret.success(true);
//	    
//	}
}
