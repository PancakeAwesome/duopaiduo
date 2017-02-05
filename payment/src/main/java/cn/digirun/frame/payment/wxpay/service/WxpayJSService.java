package cn.digirun.frame.payment.wxpay.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.wxpay.util.CommonUtil;
import cn.digirun.frame.payment.wxpay.util.HtmlUtil;
import cn.digirun.frame.payment.wxpay.util.WxpayConfig;

@Component
public class WxpayJSService implements PayService {

	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		Map<String, String> paramMap = new HashMap<String, String>();
		PaymentResDataBean paymentResDataBean = new PaymentResDataBean(); 
		paramMap.put("trade_type", "JSAPI"); // 交易类型
		// paramMap.put("spbill_create_ip",localIp()); //本机的Ip
		paramMap.put("spbill_create_ip", WxpayConfig.ip);
		//paramMap.put("product_id", "160531143800100010001"); // 商户根据自己业务传递的参数 必填
		paramMap.put("body", req.getSubject()); // 描述
		paramMap.put("out_trade_no", req.getOrderNo()); // 商户 后台的贸易单号
		paramMap.put("total_fee", "" + req.getTotalFee()); // 金额必须为整数 单位为分
		paramMap.put("notify_url", WxpayConfig.notify_url); // 支付成功后，回调地址
		paramMap.put("appid", WxpayConfig.app_id); // appid
		paramMap.put("mch_id", WxpayConfig.mch_id); // 商户号
		paramMap.put("openid", "openid");
		paramMap.put("nonce_str", CommonUtil.getRandomStringByLength(32)); // 随机数
		paramMap.put("sign", CommonUtil.getSign(paramMap, WxpayConfig.key));
		

		String reqXml = CommonUtil.mapToXML(paramMap);
		String resultStr = HtmlUtil.postData(WxpayConfig.pay_url, reqXml);
		System.out.println(CommonUtil.getResFromXml(resultStr));
		paymentResDataBean.setResUrl(CommonUtil.getCodeUrlFromRes(resultStr));
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
