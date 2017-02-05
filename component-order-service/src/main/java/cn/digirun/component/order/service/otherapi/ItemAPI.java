package cn.digirun.component.order.service.otherapi;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.ItemModelOrder;

@Service
@Transactional
public class ItemAPI{
	public ItemModelOrder getItemById(Integer itemId){
		
		ItemModelOrder item = new ItemModelOrder();
		item.setItemId(1);
		item.setStock(10);
		item.setPrice(new BigDecimal("10"));
		return item;
	}

	public void updStock(Integer itemId, Integer stock) {
		// TODO Auto-generated method stub
		
	}
}
