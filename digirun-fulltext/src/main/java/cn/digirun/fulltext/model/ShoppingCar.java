package cn.digirun.fulltext.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 购物车
 * @author gdh
 *
 */
@Document(indexName="shopping-car")
public class ShoppingCar implements Serializable {

	private static final long serialVersionUID = 5037311826962018715L;

	@Id
	private Long id; // 用户ID
	private List<ShoppingCarItem> items = new ArrayList<ShoppingCarItem>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ShoppingCarItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCarItem> items) {
		this.items = items;
	}
}
