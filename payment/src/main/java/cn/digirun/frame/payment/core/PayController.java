package cn.digirun.frame.payment.core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

import cn.digirun.frame.payment.alipay.util.AlipayConfig;
import cn.digirun.frame.payment.alipay.util.AlipayCore;
import cn.digirun.frame.payment.alipay.util.MD5;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.core.bean.RefundResBean;
import cn.digirun.frame.payment.core.constant.Thirtypart;
import cn.digirun.frame.payment.entity.Client;
import cn.digirun.frame.payment.repo.ClientRepo;

/** 
 * @ClassName: PayController 
 * @Description: Pay API
 * @author 管东海
 *  
 */
@RestController
@RequestMapping("/pay")
public class PayController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private PayServiceFactory serviceFactory; 
	
	@Autowired
	private ClientRepo clientRepo;
	
	/** 
	 * @Title: thirtyparts 
	 * @Description: 支持的第三方支付平台
	 * @return List<Map<String,Object>>
	 * @throws 
	 */
	@RequestMapping(method=RequestMethod.GET,value="/thirtyparts")
	public List<Map<String, Object>> thirtyparts(){
		Thirtypart [] thirtyparts = Thirtypart.values();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(thirtyparts.length);
		for(Thirtypart thirtypart : thirtyparts){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("name", thirtypart.toString());
			data.put("code", thirtypart.getCode());
			data.put("text", thirtypart.getName());
			list.add(data);
		}
		return list;
	}
	
	@RequestMapping("/a")
	public void a(){}
	
	@RequestMapping("/form")
	public void form(){}
	/** 
	 * @Title: notice 
	 * @Description: 支付宝回调
	 * @return String
	 * @throws 
	 */
	@RequestMapping(method=RequestMethod.POST,value="/alipayNotice")
	public void alipayNotice(HttpServletRequest request, HttpServletResponse response){
		PayService payService = serviceFactory.getService("ALIPAY");
		/*Enumeration<String> headers =  request.getHeaderNames();
		Map map = request.getParameterMap();
		
		while(headers.hasMoreElements()){
			String name = headers.nextElement();
			System.out.println(request.getHeader(name));
		}*/
		System.out.println("Pay Type is : " + request.getParameter("payType"));
		// 发送报文至处理服务
		NoticeResBean noticeResBean = payService.notice(request);
		try {
			response.getWriter().print(noticeResBean.getPayNoticeRes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jmsTemplate.send("payment_pay", (session)->{
			return session.createTextMessage(JSON.toJSONString(noticeResBean)) ;
		});
	}
	
	/** 
	 * @Title: notice 
	 * @Description: 微信回调
	 * @return String
	 * @throws 
	 */
	@RequestMapping(method=RequestMethod.POST,value="/wxpayNotice")
	public void wxpayNotice(HttpServletRequest request, HttpServletResponse response){
		PayService payService = serviceFactory.getService("WXPAY");
		// 发送报文至处理服务
		NoticeResBean noticeResBean = payService.notice(request);
		try {
			//response.getWriter().print(noticeResBean.getPayNoticeRes());
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(noticeResBean.getPayNoticeRes().getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jmsTemplate.send("payment_pay", (session)->{
			return session.createTextMessage(JSON.toJSONString(noticeResBean)) ;
		});
	}
	
	/** 
	 * @Title: notice 
	 * @Description: 微信回调
	 * @return String
	 * @throws 
	 */
	@RequestMapping(method=RequestMethod.POST,value="/unionpayNotice")
	public void unionpayNotice(HttpServletRequest request, HttpServletResponse response){
		PayService payService = serviceFactory.getService("UNIONPAY");
		// 发送报文至处理服务
		NoticeResBean noticeResBean = payService.notice(request);
		try {
			response.getWriter().print(noticeResBean.getPayNoticeRes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jmsTemplate.send("payment_pay", (session)->{
			return session.createTextMessage(JSON.toJSONString(noticeResBean)) ;
		});
		//return noticeResBean.getPayNoticeRes();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/payment")
	@ResponseBody
	public PaymentResBean payment(PaymentReqBean reqBean, RedirectAttributes attr) throws IOException{
		PaymentResBean resBean = new PaymentResBean();
		PayService payService = serviceFactory.getService(reqBean.getPayType());
		PaymentResDataBean paymentResDataBean = payService.payment(reqBean);
		System.out.println("result is : " + paymentResDataBean);
		
		resBean.setData(paymentResDataBean);
		return resBean;
	}
	
	@RequestMapping("/alipayResp")
	public String alipayResp(@ModelAttribute("html") String html,Model model){
		model.addAttribute("html", html);
		return "/pay/alipayResp";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST, value="/getSign")
	public String getSign(PaymentReqBean reqBean){
		Client client = clientRepo.findFirstByProjectName(reqBean.getResource());
		String sign = "";
		Map<String, String> reqMap = AlipayCore.getValue(reqBean);
		String alipay = AlipayCore.createLinkString(AlipayCore.paraFilter(reqMap));
		if(AlipayConfig.sign_type.equals("MD5") ) {
			//sign = MD5.sign(alipay, client.getProjectKey(), AlipayConfig.input_charset);
			sign = MD5.sign(alipay, "payment", AlipayConfig.input_charset);
        }
		System.out.println("+++++" + sign);
		return sign;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.POST, value="/refund")
	public RefundResBean refund(RefundReqBean reqBean){
		RefundResBean resBean = new RefundResBean();
		/*Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("resource", "wufanbao");
		reqMap.put("orderNo", reqBean.getRefundNo());
		reqMap.put("totalFee", reqBean.getDetailData());
		String alipay = AlipayCore.createLinkString(AlipayCore.paraFilter(reqMap));
		System.out.println(reqMap);
		String serverSign = MD5.sign(alipay, "payment", AlipayConfig.input_charset);
		if(!serverSign.equals(reqBean.getSign())){
			resBean.setCode(-1);
			resBean.setMsg("签名校验失败！");
			resBean.setData(null);
			return resBean;
		}*/
		PayService payService = serviceFactory.getService(90301);
		String refund = payService.refund(reqBean);
		//resBean.setData(refund);
		return resBean;
	}
	
	//refund
}
