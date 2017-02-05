package cn.digirun.frame.payment.unionpay;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.core.constant.Thirtypart;
import cn.digirun.frame.payment.entity.Payment;
import cn.digirun.frame.payment.repo.PaymentRepo;
import cn.digirun.frame.payment.unionpay.util.AcpService;
import cn.digirun.frame.payment.unionpay.util.LogUtil;
import cn.digirun.frame.payment.unionpay.util.SDKConstants;

@Component
public class UnionpayService implements PayService {
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		//UnionpayConfig.encryptCertPath;
		return null;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		LogUtil.writeLog("BackRcvResponse接收后台通知开始");
		NoticeResBean noticeResBean = new NoticeResBean();
		String encoding = request.getParameter(SDKConstants.param_encoding);
		// 获取银联通知服务器发送的后台通知参数
		Map<String, String> reqParam = getAllRequestParam(request);
		System.out.println(reqParam);
		LogUtil.printRequestLog(reqParam);

		Map<String, String> valideData = null;
		if (null != reqParam && !reqParam.isEmpty()) {
			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
			valideData = new HashMap<String, String>(reqParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				try {
					value = new String(value.getBytes(encoding), encoding);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				valideData.put(key, value);
			}
		}

		//重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
		if (!AcpService.validate(valideData, encoding)) {
			LogUtil.writeLog("验证签名结果[失败].");
			//验签失败，需解决验签问题
			
		} else {
			LogUtil.writeLog("验证签名结果[成功].");
			//【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
			//String orderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
			//String respCode =valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
			noticeResBean.setOrderNo(valideData.get("orderId"));
			noticeResBean.setThirdNo(valideData.get("respCode"));
			// noticeResBean.setPayType(""+Thirtypart.UnionpayPC);
			noticeResBean.setPayNoticeRes("ok");
			
			/*Payment payment = new Payment();
			payment.setPayType("UNIONPAY");
			
			paymentRepo.save(payment);*/
		}
		LogUtil.writeLog("BackRcvResponse接收后台通知结束");
		//返回给银联服务器http 200  状态码
		return noticeResBean;
	}

	@Override
	public String refund(RefundReqBean req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
}
