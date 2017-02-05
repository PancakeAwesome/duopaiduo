package cn.digirun.frame.payment.wxpay.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;

@Component
public class WxAPPService implements PayService {

	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String refund(RefundReqBean req) {
		// TODO Auto-generated method stub
		return null;
	}

}
