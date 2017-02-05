package cn.digirun.fulltext.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 订单
 * @author zhd
 *
 */
@Document(indexName="order-info")
public class Order implements Serializable {

	private static final long serialVersionUID = 5037311826962018715L;

	@Id
	private Long id; // 用户ID
	
	private List<OrderItem>orderList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderItem> orderList) {
		this.orderList = orderList;
	}
}
