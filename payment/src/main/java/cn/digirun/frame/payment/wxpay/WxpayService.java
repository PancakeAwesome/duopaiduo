package cn.digirun.frame.payment.wxpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.entity.Payment;
import cn.digirun.frame.payment.repo.PaymentRepo;
import cn.digirun.frame.payment.wxpay.util.ClientCustomSSL;
import cn.digirun.frame.payment.wxpay.util.CommonUtil;
import cn.digirun.frame.payment.wxpay.util.RequestHandler;
import cn.digirun.frame.payment.wxpay.util.WxpayConfig;

/**
 * @ClassName: WeChatPayService
 * @Description: 微信支付服务
 * @author 管东海
 * 
 */
@Component
public class WxpayService implements PayService {

	@Autowired
	private PaymentRepo paymentRepo;

	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {

		return null;
	}

	public static String getCodeUrlFromRes(String resXml) {
		Document dd = null;
		String code_url = null;
		try {
			dd = DocumentHelper.parseText(resXml);
		} catch (DocumentException e) {
			return "";
		}
		if (dd != null) {
			Element root = dd.getRootElement();
			if (root == null) {
				return "";
			}
			Element codeUrl = root.element("code_url");
			if (codeUrl == null) {
				return "";
			}
			code_url = codeUrl.getText(); // 解析 xml 获得 code_url
		}
		makeWaterPic(code_url, "png");
		return code_url;
	}

	/** 
	 * @Title: notice 
	 * @Description: TODO
	 * @return NoticeResBean
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		
		NoticeResBean noticeResBean = new NoticeResBean();
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
	    Document document;
	    String resXml= "";
		try {
			InputStream inputStream = request.getInputStream();
			document = reader.read(inputStream);
			 // 得到xml根元素
		    Element root = document.getRootElement();
		    // 得到根元素的所有子节点
		    List<Element> elementList = root.elements();
		    // 遍历所有子节点
		    for (Element e : elementList)
		        map.put(e.getName(), e.getText());
		    // 释放资源
		    inputStream.close();
		    // inputStream = null;
		    System.out.println(map);
			if (map.get("return_code").equals("SUCCESS") /* && "SUCCESS".equals(map.get("result_code")) */) {
				String orderNo = map.get("out_trade_no");
				String resultCode = map.get("result_code");
				String thirdNo = map.get("transaction_id");
				String totalFee = map.get("total_fee");
				String thirdResTime = map.get("time_end");
				String requestTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				noticeResBean.setIsSuccess(resultCode);
				noticeResBean.setOrderNo(orderNo);
				// noticeResBean.setPayType("");
				noticeResBean.setThirdNo(thirdNo);
				noticeResBean.setTotalFee(totalFee);
				
				Payment payment = new Payment();
				payment.setOrderNo(orderNo);
				payment.setRequestTime(requestTime);
				payment.setStatus(resultCode);
				payment.setThirdNo(thirdNo);
				payment.setThirdResTime(thirdResTime);
				payment.setTotalFee(totalFee);
				payment.setPayType("WXPAY");
				paymentRepo.save(payment);
				
			}
		    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "</xml> ";
		} catch (Exception e1) {
    		resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
    		+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		noticeResBean.setPayNoticeRes(resXml);
		System.out.println(JSON.toJSONString(noticeResBean));
		return noticeResBean;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static String makeWaterPic(String content, String picType) {
		int width = 400;
		int height = 400;
		
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		String classPath = Class.class.getClass().getResource("/").getPath();
		String filePath = classPath.replace("target", "src").replace("classes", "main") + "/resources/static/pay/";
		String result = "";
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			/*WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	        ServletContext servletContext = webApplicationContext.getServletContext(); */
	        String saveDir =  filePath/*servletContext.getRealPath("/upload/erweima//")*/;
	        
	        SimpleDateFormat dirFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        /*SimpleDateFormat picFormat = new SimpleDateFormat("HHmmss");
	        String dirPath = saveDir + dirFormat.format(new Date());
	        File file =new File(dirPath);
	        if  (!file .exists()  && !file .isDirectory()){
	        	file.mkdir();
	        }
	        String picName = picFormat.format(new Date());*/
	        String picPath = saveDir + "/" +dirFormat.format(new Date()) +"." + picType;
			File outputFile = new File(picPath);
			MatrixToImageWriter.writeToFile(bitMatrix, picType, outputFile);
			
			FileInputStream inputFile = new FileInputStream(outputFile);
			byte[] buffer = new byte[(int) outputFile.length()];
			inputFile.read(buffer);
			inputFile.close();
//			result = picPath.substring(picPath.indexOf("wfb-war")-1, picPath.length()).replace("\\", "/");
			result = picPath.replace("\\", "/");
			System.out.println("http://localhost:9090/pay/" + dirFormat.format(new Date()) +"." + picType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		OSFileUtils.uploadFile(picType, Base64.getEncoder().encodeToString(buffer));
		System.out.println(result);
		
		return result;
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
		
		/*String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<transaction_id>" + transaction_id + "</transaction_id>"
				+ "<out_refund_no>" + out_refund_no + "</out_refund_no>"
				+ "<total_fee>" + total_fee + "</total_fee>"
				+ "<refund_fee>" + refund_fee + "</refund_fee>"
				+ "<op_user_id>" + op_user_id + "</op_user_id>" + "</xml>";*/
		String createOrderURL = WxpayConfig.refund_url;
		try {
			String s= ClientCustomSSL.doRefund(createOrderURL, xml);
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/*@SuppressWarnings("rawtypes")
	private String localIp() {
		String ip = null;
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			// logger.warn("获取本机Ip失败:异常信息:"+e.getMessage());
		}
		return ip;
	}*/

}
