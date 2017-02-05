package cn.digirun.frame.payment.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.alipay.AlipayService;
import cn.digirun.frame.payment.alipay.service.AlipayPCSerivce;
import cn.digirun.frame.payment.alipay.service.AlipayWAPSerivce;
import cn.digirun.frame.payment.core.constant.Thirtypart;
import cn.digirun.frame.payment.unionpay.UnionpayService;
import cn.digirun.frame.payment.unionpay.service.UnionpayAPPService;
import cn.digirun.frame.payment.unionpay.service.UnionpayPCSerivce;
import cn.digirun.frame.payment.unionpay.service.UnionpayWAPService;
import cn.digirun.frame.payment.wxpay.WxpayService;
import cn.digirun.frame.payment.wxpay.service.WxpayJSService;
import cn.digirun.frame.payment.wxpay.service.WxpayScanService;

/** 
 * @ClassName: PayServiceFactory 
 * @Description: 支付服务工厂
 * @author 管东海
 *  
 */
@Component
public class PayServiceFactory {

	private PayServiceFactory() {
	}
	
	@Autowired
	private AlipayService alipayService;

	@Autowired
	private AlipayPCSerivce alipayPCService;
	
	@Autowired
	private AlipayWAPSerivce alipayWAPService;
	
	@Autowired
	private WxpayService wxPayService;
	
	@Autowired
	private WxpayScanService wxPayScanService;
	
	@Autowired
	private WxpayJSService wxPayJSService;
	
	@Autowired
	private UnionpayService unionPayService;
	
	@Autowired
	private UnionpayPCSerivce unionPayPCService;
	
	@Autowired
	private UnionpayWAPService unionPayWAPService;
	
	@Autowired
	private UnionpayAPPService unionPayAPPService;
	

	public  PayService getService(Thirtypart thirtypart) {
		switch (thirtypart) {
		case AlipayPC:
			return alipayPCService;
		case AlipayWAP:
			return alipayWAPService;
		case WxpayScan:
			return wxPayScanService;
		case UnionpayPC:
			return unionPayPCService;
		case UnionpayWAP:
			return unionPayWAPService;
		case UnionpayAPP:
			return unionPayAPPService;
		default:
			throw new RuntimeException("不支持的支付类型");
		}
	}
	
	public PayService getService(Integer payType){
		switch (payType) {
		case 90101:
			return alipayPCService;
		case 90102:
			return alipayWAPService;
		case 90201:
			return unionPayPCService;	
		case 90202:
			return unionPayWAPService;
		case 90203:
			return unionPayAPPService;
		case 90301:
			return wxPayScanService;
		case 90302:
			return wxPayJSService;
		default:
			throw new RuntimeException("不支持的支付类型");
		}
	}
	
	public PayService getService(String host){
		switch (host) {
		case "ALIPAY":
			return alipayService;
		case "WXPAY":
			return wxPayService;
		case "UNIONPAY":
			return unionPayService;
		default:
			throw new RuntimeException("不支持的支付类型");
		}
	}
	
}
