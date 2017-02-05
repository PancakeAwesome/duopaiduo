package cn.digirun.frame.payment.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.alipay.exception.ArgErrorException;
import cn.digirun.frame.payment.alipay.util.AlipayNotify;
import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.core.constant.Thirtypart;
import cn.digirun.frame.payment.entity.Payment;
import cn.digirun.frame.payment.repo.PaymentRepo;
 
/**
 * @ClassName: AlipayService
 * @Description: 支付宝服务
 * @author 管东海
 * 
 */
@SuppressWarnings({"rawtypes"})
@Component
public class AlipayService implements PayService {

	@Autowired
	private PaymentRepo paymentRepo;
	
	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		return null;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		NoticeResBean noticeResBean = new NoticeResBean();
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		System.out.println(params);
		try {
			if (AlipayNotify.verify(params) && params.get("trade_status").equals("TRADE_SUCCESS")) {// 验证成功
				String orderNo = params.get("out_trade_no");
				String thirdNo = params.get("trade_no");
				String resultCode = params.get("trade_status");
				
				noticeResBean.setOrderNo(orderNo);
				noticeResBean.setThirdNo(thirdNo);
				noticeResBean.setIsSuccess(resultCode);
				//noticeResBean.setPayType(""+Thirtypart.AlipayPC);
				noticeResBean.setPayNoticeRes("success");
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date1 = dateFormat1.parse(params.get("notify_time")) ;
				String thirdResTime = dateFormat.format(date1);
				String requestTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());	
				Integer totalFee = (int)(Float.parseFloat(params.get("price"))*100);
				Payment payment = new Payment();
				payment.setOrderNo(orderNo);
				payment.setRequestTime(requestTime);
				payment.setStatus(resultCode);
				payment.setThirdNo(thirdNo);
				payment.setThirdResTime(thirdResTime);
				payment.setTotalFee(""+totalFee);
				payment.setPayType("ALIPAY");
				paymentRepo.save(payment);
				
			} else {// 验证失败
				noticeResBean.setPayNoticeRes("fail");
			}
		} catch (Exception e) {
			throw new ArgErrorException("参数有误").setThirtypart(Thirtypart.AlipayPC).setBizCode(0001);
		}
		return noticeResBean;
	}

	@Override
	public String refund(RefundReqBean req) {
		// TODO Auto-generated method stub
		return null;
	}

}
