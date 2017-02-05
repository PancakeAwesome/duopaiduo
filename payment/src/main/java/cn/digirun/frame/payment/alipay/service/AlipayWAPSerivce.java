package cn.digirun.frame.payment.alipay.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.alipay.util.AlipayConfig;
import cn.digirun.frame.payment.alipay.util.AlipaySubmit;
import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;

@Component
public class AlipayWAPSerivce implements PayService{

	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		// TODO Auto-generated method stub
		PaymentResDataBean paymentResDataBean = new PaymentResDataBean();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.wap_service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		// sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("notify_url", req.getNoticeUrl()); // 临时使用
		sParaTemp.put("return_url", req.getReturnUrl());
		sParaTemp.put("out_trade_no", req.getOrderNo());
		sParaTemp.put("subject", req.getSubject());
		sParaTemp.put("total_fee", ""+(float)Integer.parseInt(req.getTotalFee())/100);
		sParaTemp.put("show_url", req.getShowUrl());
		sParaTemp.put("body", "AlipayWap");
		
		paymentResDataBean.setResUrl(AlipaySubmit.buildRequest(sParaTemp,"get","确认"));
		paymentResDataBean.setResMethod("get");
		return paymentResDataBean;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		return null;
	}

	@Override
	public String refund(RefundReqBean req) {
		// TODO Auto-generated method stub
		return null;
	}

}
