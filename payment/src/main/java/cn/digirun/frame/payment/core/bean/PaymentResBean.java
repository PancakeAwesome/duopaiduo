package cn.digirun.frame.payment.core.bean;

/** 
 * @ClassName: PaymentResBean 
 * @Description: 支付响应
 * @author 管东海
 *  
 */
public class PaymentResBean {

	private Integer code = 0;
	
	private String msg = "";
	
	private PaymentResDataBean data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PaymentResDataBean getData() {
		return data;
	}

	public void setData(PaymentResDataBean data) {
		this.data = data;
	}

}
