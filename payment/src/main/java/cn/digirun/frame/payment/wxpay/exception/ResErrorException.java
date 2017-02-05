package cn.digirun.frame.payment.wxpay.exception;

import cn.digirun.frame.payment.core.exception.BaseException;

public class ResErrorException extends BaseException{

	private static final long serialVersionUID = 1L;

	public ResErrorException(String message) {
		super(message);
	}

}
