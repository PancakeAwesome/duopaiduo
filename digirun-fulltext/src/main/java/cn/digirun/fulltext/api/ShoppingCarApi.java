package cn.digirun.fulltext.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.fulltext.model.ShoppingCar;
import cn.digirun.fulltext.model.ShoppingCarItem;
import cn.digirun.fulltext.repository.ShoppingCarRepository;

/**
 * 购物车
 * 
 * @author 管东海
 *
 */
@RestController
@RequestMapping("/fulltext/shopping-car")
public class ShoppingCarApi {

	@Autowired
	private ShoppingCarRepository shoppingCarRepository;
	
	private ShoppingCar getCar(Long id){
		ShoppingCar car = shoppingCarRepository.findOne(id);

		if (car == null || car.getId() == null) {
			car = new ShoppingCar();
			car.setId(id);
			shoppingCarRepository.save(car);
		}
		return car;
	}

	/**
	 * 获取用户购物车信息
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ShoppingCar get(@PathVariable Long id) {
		return getCar(id);
	}

	/**
	 * 追加用户购物车信息
	 * 
	 * @param id
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ShoppingCar put(@PathVariable Long id, @RequestBody ShoppingCarItem item) {
		ShoppingCar car = getCar(id);
	  if(car.getItems().size()==0)
		  car.getItems().add(item);
	  else{
		 car.getItems().parallelStream().filter(ii->{
			if( ii.getItemId().equals(item.getItemId())){
				 ii.setItemAmount(ii.getItemAmount()+item.getItemAmount());
			}
			 return true;
		 });
	  }
		return shoppingCarRepository.save(car);
	}

	/**
	 * 清空用户购物车信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/clear/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		shoppingCarRepository.delete(id);
		return true;
	}

	/**
	 * 移除购物车中某个商品
	 * 
	 * @param id
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ShoppingCar remove(@PathVariable Long id, @RequestParam String[] itemId) {
		ShoppingCar car = getCar(id);
		int[] pos = new int[itemId.length];

		List<ShoppingCarItem> items = car.getItems();
		for (int i = 0; i < items.size(); i++) {
			ShoppingCarItem item = items.get(i);
			for (int j = 0; j < itemId.length; j++) {
				if (item.getItemId().equals(itemId[j])) {
					pos[j] = i;
				}
			}
		}
		for (int k = 0; k < pos.length; k++) {
			items.remove(pos[k]);
		}
		car.setItems(items);
		return shoppingCarRepository.save(car);
	}

	/**
	 * 创建购物车
	 * 
	 * @param car
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ShoppingCar post(@RequestBody ShoppingCar car) {
		return shoppingCarRepository.save(car);
	}
}
