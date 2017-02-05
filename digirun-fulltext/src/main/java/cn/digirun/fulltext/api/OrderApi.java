package cn.digirun.fulltext.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.fulltext.model.Order;
import cn.digirun.fulltext.model.OrderItem;
import cn.digirun.fulltext.repository.OrderRepository;

/**
 *  订单
 * 
 * @author 张海东
 *
 */
@RestController
@RequestMapping("/fulltext/order-info")
public class OrderApi {

	@Autowired
	private OrderRepository orderRepository;
	
	private Order getOrder(Long id){
		Order order = orderRepository.findOne(id);

		if (order == null || order.getId() == null) {
			order = new Order();
			order.setId(id);
			orderRepository.save(order);
		}
		return order;
	}

	/**
	 * 获取“我的订单”信息
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order get(@PathVariable Long id) {
		Order order = orderRepository.findOne(id);
		
		return  order;
	}
	

	/**
	 * 追加用户订单信息
	 * 
	 * @param id
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Order put(@PathVariable Long id, @RequestBody OrderItem item) {
		Order order =getOrder(id);
		List<OrderItem> list = new ArrayList<>();
		list.add(item);
		order.setOrderList(list);//getItems().add(item);
		return orderRepository.save(order);
	}

	/**
	 * 清空用户订单信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/clear/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		orderRepository.delete(id);
		return true;
	}

	/**
	 * 移除订单
	 * 
	 * @param id
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public Order remove(@PathVariable Long id, @RequestParam String[] orderNo) {
		Order order = orderRepository.findOne(id);
		int[] pos = new int[orderNo.length];

		List<OrderItem> orderItems = order.getOrderList();
		for (int i = 0; i < orderItems.size(); i++) {
			OrderItem item = orderItems.get(i);
			for (int j = 0; j < orderNo.length; j++) {
				if (item.getOrderNo().equals(orderNo[j])) {
					pos[j] = i;
				}
			}
		}
		for (int k = 0; k < pos.length; k++) {
			orderItems.remove(pos[k]);
		}
		order.setOrderList(orderItems);
		return orderRepository.save(order);
	}
	
}
