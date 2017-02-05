package cn.digirun.component.order.api.bean;

import java.util.Date;

/**
 * 保存支付类型请求类
 * @author Administrator
 *
 */
public class SaveDeliveryReq{
	
	private Long id;
	
	/**
	 * 物流名称(顺丰、圆通...)
	 */
	private String name;
	
	private Date createTime;
	
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
