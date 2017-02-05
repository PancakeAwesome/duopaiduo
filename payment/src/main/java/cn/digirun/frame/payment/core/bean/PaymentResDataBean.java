package cn.digirun.frame.payment.core.bean;

public class PaymentResDataBean {

	// 服务端反馈url
	private String resUrl;
	
	// 服务器反馈url请求方法
	private String resMethod;

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResMethod() {
		return resMethod;
	}

	public void setResMethod(String resMethod) {
		this.resMethod = resMethod;
	}
	
}
