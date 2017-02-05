package cn.digirun.component.order.api.bean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName: OrderRequest
 * @Description: 订单请求
 * @author 胡贵兵
 * 
 */
public class OrderRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Min(1l)
	@Max(Integer.MAX_VALUE)
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
