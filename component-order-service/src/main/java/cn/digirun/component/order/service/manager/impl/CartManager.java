package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.CartModel;
import cn.digirun.component.order.service.manager.ICartManager;
import cn.digirun.component.order.service.repository.CartRepository;

@Service
@Transactional
public class CartManager implements ICartManager {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public String saveCart(CartModel cart) {
		cartRepository.save(cart);
		return null;
	}

	@Override
	public CartModel findByUserId(CartModel cart) {
		CartModel cartModel =cartRepository.findByUserId(cart.getUserId());
		return cartModel;
	}

	@Override
	public void delItemsFromCart(List<CartModel> cartModels) {
		cartRepository.delete(cartModels);
		
	}

	
}
