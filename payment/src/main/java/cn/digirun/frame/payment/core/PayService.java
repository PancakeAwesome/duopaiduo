package cn.digirun.frame.payment.core;

import javax.servlet.http.HttpServletRequest;

import cn.digirun.frame.payment.core.bean.NoticeResBean;
import cn.digirun.frame.payment.core.bean.PaymentReqBean;
import cn.digirun.frame.payment.core.bean.PaymentResDataBean;
import cn.digirun.frame.payment.core.bean.RefundReqBean;

/** 
 * @ClassName: Pay 
 * @Description: 支付业务
 * @author 管东海
 *  
 */
public interface PayService {

	/** 
	 * @Title: payment 
	 * @Description: 支付
	 * @return PaymentResBean
	 * @throws 
	 */
	PaymentResDataBean payment(PaymentReqBean req);
	
	/** 
	 * @Title: notice 
	 * @Description: 支付回调
	 * @return NoticeResBean
	 * @throws 
	 */
	NoticeResBean notice(HttpServletRequest request);
	
	/** 
	 * @Title: refund 
	 * @Description: 退款
	 * @return RefundReqBean
	 * @throws 
	 */
	String refund(RefundReqBean req);
	
}
