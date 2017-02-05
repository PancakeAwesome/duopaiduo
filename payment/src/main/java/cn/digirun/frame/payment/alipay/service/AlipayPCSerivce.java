package cn.digirun.frame.payment.alipay.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.alipay.bean.AlipayReqBean;
import cn.digirun.frame.payment.alipay.util.AlipayConfig;
import cn.digirun.frame.payment.alipay.util.AlipaySubmit;
import cn.digirun.frame.payment.alipay.util.UtilDate;
import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;

@Component
public class AlipayPCSerivce implements PayService{

	
	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		AlipayReqBean alipayReqBean = new AlipayReqBean();
		PaymentResDataBean paymentResDataBean = new PaymentResDataBean();
		BeanUtils.copyProperties(req, alipayReqBean);
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("service", AlipayConfig.pc_service);
		reqMap.put("partner", AlipayConfig.partner);
		reqMap.put("seller_id", AlipayConfig.seller_id);
		reqMap.put("_input_charset", AlipayConfig.input_charset);
		reqMap.put("payment_type", AlipayConfig.payment_type);
		// reqMap.put("notify_url", AlipayConfig.notify_url);
		reqMap.put("notify_url", req.getNoticeUrl()); // 临时使用
		reqMap.put("return_url", req.getReturnUrl());
		reqMap.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		reqMap.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		reqMap.put("out_trade_no", alipayReqBean.getOrderNo());
		reqMap.put("subject", alipayReqBean.getSubject());
		reqMap.put("total_fee", ""+(float)Integer.parseInt(alipayReqBean.getTotalFee())/100);
		reqMap.put("body", "itemDesc");
		paymentResDataBean.setResUrl(AlipaySubmit.buildRequest(reqMap,"get","确认"));
		paymentResDataBean.setResMethod("get");
		return paymentResDataBean;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		return null;
	}

	@Override
	public String refund(RefundReqBean req) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.refund_service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("seller_user_id", AlipayConfig.seller_id);
		sParaTemp.put("refund_date", UtilDate.getDateFormatter());
		sParaTemp.put("batch_no", req.getRefundNo());
		sParaTemp.put("batch_num", req.getRefundNum());
		sParaTemp.put("detail_data", req.getDetailData());
		
		return AlipaySubmit.buildRequest(sParaTemp,"get","确认");
	}

}
