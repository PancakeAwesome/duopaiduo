package cn.digirun.frame.payment.wxpay.service;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.wxpay.util.ClientCustomSSL;
import cn.digirun.frame.payment.wxpay.util.CommonUtil;
import cn.digirun.frame.payment.wxpay.util.HtmlUtil;
import cn.digirun.frame.payment.wxpay.util.RequestHandler;
import cn.digirun.frame.payment.wxpay.util.WxpayConfig;

@SuppressWarnings({ "unchecked"})
@Component
public class WxpayScanService implements PayService {

	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		Map<String, String> paramMap = new HashMap<String, String>();
		PaymentResDataBean paymentResDataBean = new PaymentResDataBean(); 
		paramMap.put("trade_type", WxpayConfig.trade_type); // 交易类型
		// paramMap.put("spbill_create_ip",localIp()); //本机的Ip
		paramMap.put("spbill_create_ip", WxpayConfig.ip);
		paramMap.put("product_id", "160531143800100010002"); // 商户根据自己业务传递的参数 必填
		paramMap.put("body", req.getSubject()); // 描述
		paramMap.put("out_trade_no", req.getOrderNo()); // 商户 后台的贸易单号
		paramMap.put("total_fee", "" + req.getTotalFee()); // 金额必须为整数 单位为分
		//paramMap.put("notify_url", WxpayConfig.notify_url); // 支付成功后，回调地址
		paramMap.put("notify_url", req.getNoticeUrl()); // 支付成功后，回调地址  临时使用
		paramMap.put("appid", WxpayConfig.app_id); // appid
		paramMap.put("mch_id", WxpayConfig.mch_id); // 商户号
		paramMap.put("nonce_str", CommonUtil.getRandomStringByLength(32)); // 随机数
		paramMap.put("sign", CommonUtil.getSign(paramMap, WxpayConfig.key));

		String reqXml = CommonUtil.mapToXML(paramMap);
		String resultStr = HtmlUtil.postData(WxpayConfig.pay_url, reqXml);
		Map<String, String> map = CommonUtil.getResFromXml(resultStr);
		System.out.println(map);
		String codeUrl = map.get("code_url");
		if(null == codeUrl || "".equals(codeUrl)){
			//throws new ResErrorException("支付失败！").setThirtypart(Thirtypart.WxpayScan).setBizCode(001);
		}
		paymentResDataBean.setResUrl(CommonUtil.getCodeUrlFromRes(resultStr));
		return paymentResDataBean;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		return null;
	}

	@Override
	public String refund(RefundReqBean req) {
		String out_refund_no = req.getRefundNo();// 退款单号
		/**
		 * 订单号 out_trade_no
		 * 第三方交易号 transaction_id
		 * 二选一
		 */
		String transaction_id = req.getTransactionId(); // 微信流水号
		String total_fee = ""+req.getTotalFee();// 总金额
		String refund_fee = ""+req.getRefundFee();// 退款金额
		String nonce_str = CommonUtil.getRandomStringByLength(32);// 随机字符串
		String appid = WxpayConfig.app_id; //微信公众号apid
		String appsecret = WxpayConfig.app_secret; //微信公众号appsecret
		String mch_id = WxpayConfig.mch_id;  //微信商户id
		String op_user_id = WxpayConfig.mch_id;//就是MCHID
		String partnerkey = WxpayConfig.key;//商户平台上的那个KEY
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("transaction_id", transaction_id);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", total_fee);
		packageParams.put("refund_fee", refund_fee);
		packageParams.put("op_user_id", op_user_id);

		RequestHandler reqHandler = new RequestHandler(
				null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		packageParams.put("sign", sign);
		String xml =  CommonUtil.mapToXML(packageParams);
		
		String createOrderURL = WxpayConfig.refund_url;
		try {
			String s= ClientCustomSSL.doRefund(createOrderURL, xml);
			CommonUtil.getResFromXml(s);
			System.out.println(CommonUtil.getResFromXml(s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
