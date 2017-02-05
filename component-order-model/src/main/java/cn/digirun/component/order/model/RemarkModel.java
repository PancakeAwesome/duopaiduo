package cn.digirun.component.order.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;

@Entity(name = "t_remark")
@DynamicInsert
@DynamicUpdate
public class RemarkModel {

	private static final long serialVersionUID = 1L;
	/**
	 * id 自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getBuyerReview() {
		return buyerReview;
	}

	public void setBuyerReview(Integer buyerReview) {
		this.buyerReview = buyerReview;
	}

	public String getBuyerContent() {
		return buyerContent;
	}

	public void setBuyerContent(String buyerContent) {
		this.buyerContent = buyerContent;
	}

	/**
	 * 订单ID
	 */
	private String orderNo;
	
	/**
	 * 商品ID
	 */
	private String itemId;
	
	private Integer buyerReview;
	
	private String buyerContent;
	
	
}	
