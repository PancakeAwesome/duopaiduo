package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.CartModel;

/**
 * 
 * @author: zhanghd
 * @Date 2016 7 25
 */
public interface ICartManager {
 
	/**
	 * 获取购物里商品
	 */
	
	//List<> getCartItems(String userId);
        /**
		 * 保存购物车
		 * @param cart
		 */
		String saveCart(CartModel cart);
	
		CartModel findByUserId(CartModel cart);
		
		/**
		 * 删除订单
		 * @param orderNo
		 */
		void delItemsFromCart(List<CartModel> cartModels);
		
	
	
		
	
	

}
