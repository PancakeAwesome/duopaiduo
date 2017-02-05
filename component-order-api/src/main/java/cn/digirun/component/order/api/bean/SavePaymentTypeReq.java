package cn.digirun.component.order.api.bean;

/**
 * 保存支付类型请求类
 * @author Administrator
 *
 */
public class SavePaymentTypeReq{
	
	private Long id;
	
	private String name;
	
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
