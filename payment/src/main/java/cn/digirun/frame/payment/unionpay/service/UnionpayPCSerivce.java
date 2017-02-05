package cn.digirun.frame.payment.unionpay.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import cn.digirun.frame.payment.core.PayService;
import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;
import cn.digirun.frame.payment.unionpay.bean.UnionpayReqBean;
import cn.digirun.frame.payment.unionpay.util.AcpService;
import cn.digirun.frame.payment.unionpay.util.LogUtil;
import cn.digirun.frame.payment.unionpay.util.SDKUtil;
import cn.digirun.frame.payment.unionpay.util.UnionpayConfig;

@Component
public class UnionpayPCSerivce implements PayService{

	@Override
	public PaymentResDataBean payment(PaymentReqBean req) {
		UnionpayReqBean unionpayReqBean = new UnionpayReqBean();
		PaymentResDataBean paymentResDataBean = new PaymentResDataBean();
		BeanUtils.copyProperties(req, unionpayReqBean);
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("version", UnionpayConfig.version); //版本号，全渠道默认值
		reqMap.put("encoding", UnionpayConfig.encoding);  //字符集编码，可以使用UTF-8,GBK两种方式
		reqMap.put("signMethod", "01"); //签名方法，只支持 01：RSA方式证书加密
		reqMap.put("txnType", "01");  //交易类型 ，01：消费
		reqMap.put("txnSubType", "01");            			  //交易子类型， 01：自助消费
		reqMap.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
		reqMap.put("channelType", "07");           			  //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
		
		/***商户接入参数***/
		reqMap.put("merId", UnionpayConfig.merId);    	          			  //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
		reqMap.put("accessType", "0");             			  //接入类型，0：直连商户 
		reqMap.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）		
		reqMap.put("txnAmt",""+Integer.parseInt(unionpayReqBean.getTotalFee())); 						//交易金额，单位分，不要带小数点
		reqMap.put("orderId",unionpayReqBean.getOrderNo());             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则		
		reqMap.put("txnTime", SDKUtil.getCurrentTime());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
		  
		//requestData.put("reqReserved", "透传字段");        		      //请求方保留域，如需使用请启用即可；透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节		
		
		//前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
		//如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
		//异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
		reqMap.put("frontUrl", UnionpayConfig.frontUrl);
		
		//后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
		//后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
		//注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码 
		//    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
		//    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
		reqMap.put("backUrl", UnionpayConfig.backUrl);
		
		Map<String, String> submitFromData = AcpService.sign(reqMap,UnionpayConfig.encoding);  //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		
		String requestFrontUrl = UnionpayConfig.frontTransUrl; //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
		String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData,UnionpayConfig.encoding);   //生成自动跳转的Html表单
		
		LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据："+html);
		paymentResDataBean.setResUrl(html);
		paymentResDataBean.setResMethod("post");
		//将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
		return paymentResDataBean;
	}

	@Override
	public NoticeResBean notice(HttpServletRequest request) {
		return null;
	}

	@Override
	public String refund(RefundReqBean req) {
		
		String out_refund_no = req.getRefundNo();// 退款单号
		String origQryId = req.getTransactionId(); // 交易流水号
		String total_fee = ""+req.getTotalFee();// 总金额
		String txnAmt = ""+req.getRefundFee();// 退款金额
		
		Map<String, String> reqMap = new HashMap<String, String>();
		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		reqMap.put("version", UnionpayConfig.version); //版本号，全渠道默认值
		reqMap.put("encoding", UnionpayConfig.encoding);  //字符集编码，可以使用UTF-8,GBK两种方式
		reqMap.put("signMethod", "01");                        //签名方法 目前只支持01-RSA方式证书加密
		reqMap.put("txnType", "04");                           //交易类型 04-退货		
		reqMap.put("txnSubType", "00");                        //交易子类型  默认00		
		reqMap.put("bizType", "000201");                       //业务类型 B2C网关支付，手机wap支付	
		reqMap.put("channelType", "07");                       //渠道类型，07-PC，08-手机		
		
		
		/***商户接入参数***/
		reqMap.put("merId", UnionpayConfig.merId);    	          			  //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
		reqMap.put("accessType", "0");             			  //接入类型，0：直连商户 
		reqMap.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）		
		reqMap.put("txnAmt",""+txnAmt); 						 //****退货金额，单位分，不要带小数点。退货金额小于等于原消费金额，当小于的时候可以多次退货至退货累计金额等于原消费金额		
		reqMap.put("orderId",out_refund_no);             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则		
		reqMap.put("txnTime", SDKUtil.getCurrentTime());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效

		reqMap.put("backUrl", UnionpayConfig.backUrl);               //后台通知地址，后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 退货交易 商户通知,其他说明同消费交易的后台通知
		
		/***要调通交易以下字段必须修改***/
		reqMap.put("origQryId", origQryId);      //****原消费交易返回的的queryId，可以从消费交易后台通知接口中或者交易状态查询接口中获取
		
		/**请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文------------->**/
		Map<String, String> reqData  = AcpService.sign(reqMap,UnionpayConfig.version);//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		String url = UnionpayConfig.backTransUrl;//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl

		 Map<String, String> rspData = AcpService.post(reqData,url,UnionpayConfig.version);//这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
		
		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		if(!rspData.isEmpty()){
			if(AcpService.validate(rspData, UnionpayConfig.version)){
				LogUtil.writeLog("验证签名成功");
				String respCode = rspData.get("respCode");
				if("00".equals(respCode)){
					//交易已受理，等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
					//TODO
				}else if("03".equals(respCode)|| 
						 "04".equals(respCode)||
						 "05".equals(respCode)){
					//后续需发起交易状态查询交易确定交易状态
					//TODO
				}else{
					//其他应答码为失败请排查原因
					//TODO
				}
			}else{
				LogUtil.writeErrorLog("验证签名失败");
				//TODO 检查验证签名失败的原因
			}
		}else{
			//未返回正确的http状态
			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
		}
		
		String reqMessage = SDKUtil.genHtmlResult(reqData);
		String rspMessage = SDKUtil.genHtmlResult(rspData);
		LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据："+reqMessage);
		LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据："+rspMessage);
		//resp.getWriter().write("</br>请求报文:<br/>"+reqMessage+"<br/>" + "应答报文:</br>"+rspMessage+"");
		return null;
	}

}
