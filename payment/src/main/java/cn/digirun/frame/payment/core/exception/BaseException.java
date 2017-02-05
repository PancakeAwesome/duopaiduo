package cn.digirun.frame.payment.core.exception;

import cn.digirun.frame.payment.core.constant.Thirtypart;

public class BaseException extends RuntimeException{

	private static final long serialVersionUID = -7701669626651720007L;
	
//	private  final Integer modelCode = 90;
	private Thirtypart thirtypart;
	private Integer bizCode;
	
	public BaseException(String message) {
		super(message);
	}

	public final String getCode(){
		return this.thirtypart.getCode()+""+this.bizCode;
	}

	public Thirtypart getThirtypart() {
		return thirtypart;
	}

	public BaseException setThirtypart(Thirtypart thirtypart) {
		this.thirtypart = thirtypart;
		return this;
	}

	public Integer getBizCode() {
		return bizCode;
	}

	public BaseException setBizCode(Integer bizCode) {
		
		this.bizCode = bizCode;
		return this;
	}
}
