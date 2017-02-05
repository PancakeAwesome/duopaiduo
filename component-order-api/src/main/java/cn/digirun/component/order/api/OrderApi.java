package cn.digirun.component.order.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.digirun.component.order.api.bean.DeliveryConfirmReq;
import cn.digirun.component.order.api.bean.QueryOrderReturnReq;
import cn.digirun.component.order.api.bean.RefundReq;
import cn.digirun.component.order.api.bean.RemarkDetails;
import cn.digirun.component.order.api.bean.ReviewedReturnOrderReq;
import cn.digirun.component.order.api.bean.SaveOrderRequest;
import cn.digirun.component.order.api.bean.UpdOrderReque;
import cn.digirun.component.order.api.bean.UpdOrderStatusRequest;
import cn.digirun.component.order.api.bean.UserlogisticsReq;
import cn.digirun.component.order.model.Delivery;
import cn.digirun.component.order.model.Dispatching;
import cn.digirun.component.order.model.InvoiceType;
import cn.digirun.component.order.model.OrderItems;
import cn.digirun.component.order.model.OrderReturn;
import cn.digirun.component.order.model.OrderReturnDetail;
import cn.digirun.component.order.model.Orders;
import cn.digirun.component.order.model.RemarkModel;
import cn.digirun.component.order.model.UserOrderAddress;
import cn.digirun.component.order.service.manager.IOrderManager;
import cn.digirun.core.api.BasicApi;
import cn.digirun.core.api.security.ApiBody;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: OrderApi 
 * @Description: 订单api
 * @author 胡贵兵
 *  
 */
@RestController
@RequestMapping("/order")
public class OrderApi extends BasicApi{
	
	@Autowired
	private IOrderManager orderManager;

	/**
	 * 获取订单详情
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getOrderDetail")
	public Ret<Orders> getOrderDetail(String orderNo) {
		return Ret.success(orderManager.getOrderDetail(orderNo));
	}
	
	
	/**
	 * 查询订单
	 * @param userId
	 * @param status
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/getOrders")
	public Ret<List<Orders>> getOrders(@ApiBody QueryOrderReturnReq req) {
		Orders vo = new Orders();
		BeanUtils.copyProperties(req, vo);
		return Ret.success(orderManager.getOrders(vo));
	}
	
	/**
	 * 保存订单
	 */
	@RequestMapping(method = RequestMethod.POST, value="/saveOrder")
	public Ret<String> saveOrder(@ApiBody SaveOrderRequest req) {
		Orders order = new Orders();
		BeanUtils.copyProperties(req, order);
		OrderItems os = new OrderItems();
		List<OrderItems> orderdetail=new ArrayList<OrderItems>();
		for (cn.digirun.component.order.api.bean.OrderItems o : req.getOrderItems()) {
			BeanUtils.copyProperties(o, os);
			orderdetail.add(os);
		}
		order.setOrderItems(orderdetail);
	    
		
		if(null != req.getUserOrderAddressId()){
			 //用户订单地址对象
		    order.setUserOrderAddress(new UserOrderAddress(req.getUserOrderAddressId()));
		}
	   
	    if(null!= req.getInvoiceTypeId()){
	    	//发票类型 
		    order.setInvoiceType(new InvoiceType(req.getInvoiceTypeId()));
	    }
	    
	    if(null != req.getDispatchingId()){
	    	 //配送方式
		    order.setDispatching(new Dispatching(req.getDispatchingId()));
	    }
	   
		
	    String orderNo = orderManager.saveOrder(order);
	    
	    return Ret.success(orderNo);
	    
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/updOrder")
	public Ret<Boolean> updOrder(@ApiBody UpdOrderReque req) {
		Orders order = new Orders();
		BeanUtils.copyProperties(req, order);
		
		if(null != req.getUserOrderAddressId()){
			 //用户订单地址对象
		    order.setUserOrderAddress(new UserOrderAddress(req.getUserOrderAddressId()));
		}
	   
	    if(null!= req.getInvoiceTypeId()){
	    	//发票类型 
		    order.setInvoiceType(new InvoiceType(req.getInvoiceTypeId()));
	    }
	    
	    if(null != req.getDispatchingId()){
	    	 //配送方式
		    order.setDispatching(new Dispatching(req.getDispatchingId()));
	    }
	    
	    orderManager.updOrder(order);
	    
	    return Ret.success(true);
	    
	}
	
	
	/**
	 * 取消订单
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/cancelOrder")
	public Ret<Boolean> cancelOrder(@ApiBody UpdOrderStatusRequest req) {
		orderManager.cancelOrder(req.getOrderNo(),req.getCancelReason());
	    return Ret.success(true);
	    
	}
	
	
	/**
	 * 删除订单 (用户看不到 后台可见)
	 * @param updOrderStatusRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/delOrder")
	public Ret<Boolean> delOrder(@ApiBody UpdOrderStatusRequest req) {
		orderManager.delOrder(req.getOrderNo());
	    return Ret.success(true);
	    
	}
	
	
	/**
	 * 发货确认 ('1'=> '已确认','2' => '部分拆分','3' => '已拆分完','4' => '取消','5' =>'余单撤销')未确认的不予发货 
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/deliveryConfirm")
	public Ret<Boolean> deliveryConfirm(@ApiBody DeliveryConfirmReq req) {
		
		
		Orders o = new Orders();
		o.setOrderNo(req.getOrderNo());
		o.setConfirmStatus(req.getConfirmStatus());
	    orderManager.deliveryConfirm(o);
	    return Ret.success(true);
	}
	
	/**
	 * 订单备货
	 * @param req
	 */
	@RequestMapping(method = RequestMethod.POST, value="/orderStock")
	public Ret<Boolean> orderStock(@ApiBody UpdOrderStatusRequest req) {
		Orders o = new Orders();
		o.setOrderNo(req.getOrderNo());
	    orderManager.orderStock(o);
	    return Ret.success(true);
	}
	
	
	/**
	 * 订单快递
	 * @param req
	 */
	@RequestMapping(method = RequestMethod.POST, value="/orderExpress")
	public Ret<Boolean> orderExpress(@ApiBody UpdOrderStatusRequest req) {
		Orders o = new Orders();
		o.setOrderNo(req.getOrderNo());
	    orderManager.orderExpress(o);
	    return Ret.success(true);
	}
	
	
	/**
	 * 打印订单
	 * @param req
	 */
	@RequestMapping(method = RequestMethod.POST, value="/orderPrint")
	public Ret<Boolean> orderPrint(@ApiBody UpdOrderStatusRequest req) {
		Orders o = new Orders();
		o.setOrderNo(req.getOrderNo());
	    orderManager.orderPrint(o);
	    return Ret.success(true);
	}
	

	
	/**
	 * 仓库发货
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/deliveryOrder")
	public Ret<Boolean> deliveryOrder(@ApiBody UserlogisticsReq req) {
		
		
		Orders o = new Orders();
		o.setOrderNo(req.getOrderNo());
		o.setShippingSn(req.getShippingSn());
		o.setDelivery(new Delivery(req.getDeliveryId()));
		
	    orderManager.deliveryOrder(o);
	    return Ret.success(true);
	}
	
	/**
	 * 用户收货
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/takeOrder")
	public Ret<Boolean> takeOrder(@ApiBody UpdOrderStatusRequest req) {
	    orderManager.takeOrder(req.getOrderNo());
	    return Ret.success(true);
	}
	
	/**
	 * 订单评价
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/commentOrder")
	public Ret<Boolean> commentOrder(@ApiBody UpdOrderStatusRequest req) {
//	    保存用户评价信息
	    RemarkModel remark;
	    String orderNo = req.getOrderNo();
	    
	    for (RemarkDetails remakrdetail : req.getRemarkDetails()) {
	    		remark = new RemarkModel();
	    		BeanUtils.copyProperties(remakrdetail, remark);
	    		remark.setOrderNo(orderNo);
	    		orderManager.saveComment(remark);
		}
	    
	    orderManager.commentOrder(req.getOrderNo());
	    
	    return Ret.success(true);
	}
	
	
	/**
	 * 用户申请退货
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/refund")
	public Ret<Boolean> refund(@ApiBody RefundReq req) {
		OrderReturn order = new OrderReturn();
		List<OrderReturnDetail> orderdetail = new ArrayList<OrderReturnDetail>();
		String[] orderDetailIds = req.getOrderDetailIds().split(",");
		OrderReturnDetail o=null;
		for (String deatilId : orderDetailIds) {
			o = new OrderReturnDetail();
			o.setId(Integer.parseInt(deatilId));
			orderdetail.add(o);
		}
		BeanUtils.copyProperties(req, order);
		order.setOrderReturnDetail(orderdetail);
		
		Orders ord = new Orders();
		ord.setOrderNo(req.getReturnOrderNo());
		order.setOrders(ord);
		if(StringUtils.isEmpty(req.getAmount())){
			order.setReturnMoney(new BigDecimal("-1"));
		}else{
			order.setReturnMoney(new BigDecimal(req.getAmount()));
		}
		
		orderManager.refund(order);
		return Ret.success(true);
	}
	
	/**
	 * 申请换货
	 * @param updOrderStatusRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/exchange")
	public Ret<Boolean> exchange(@ApiBody RefundReq req) {
		OrderReturn order = new OrderReturn();
		List<OrderReturnDetail> orderdetail = new ArrayList<OrderReturnDetail>();
		String[] orderDetailIds = req.getOrderDetailIds().split(",");
		OrderReturnDetail o=null;
		for (String deatilId : orderDetailIds) {
			o = new OrderReturnDetail();
			o.setId(Integer.parseInt(deatilId));
			orderdetail.add(o);
		}
		BeanUtils.copyProperties(req, order);
		order.setOrderReturnDetail(orderdetail);
		
		Orders ord = new Orders();
		ord.setOrderNo(req.getReturnOrderNo());
		order.setOrders(ord);
		orderManager.exchange(order);
		return Ret.success(true);
	}
	
	/**
	 * 后台审核退/换货
	 * @param updOrderStatusRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/reviewedReturnOrder")
	public Ret<Boolean> reviewedReturnOrder(@ApiBody ReviewedReturnOrderReq req) {
		
		OrderReturn o = new OrderReturn();
		BeanUtils.copyProperties(req, o);
	    orderManager.reviewedReturnOrder(o);
	    return Ret.success(true);
	}
	
	
	/**
	 * 用户退/换货填写物流信息
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/userAddLogisticsNo")
	public Ret<Boolean> userAddLogisticsNo(@ApiBody UserlogisticsReq req) {
		OrderReturn o = new OrderReturn();
		o.setOrderNo(req.getOrderNo());
		o.setUserLogisticsNo(req.getShippingSn());
		o.setUserDelivery(new Delivery(req.getDeliveryId()));
		
		orderManager.userAddLogisticsNo(o);
	    return Ret.success(true);
	}
	
	
	/**
	 * 商户确认收到用户回寄的货物
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/confirmUserLogistics")
	public Ret<Boolean> confirmUserLogistics(@ApiBody UpdOrderStatusRequest req) {
	    orderManager.confirmUserLogistics(req.getOrderNo());
	    return Ret.success(true);
	}
	
	
	/**
	 *  仓库换货发货填写物流信息
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/adminAddLogisticsNo")
	public Ret<Boolean> adminAddLogisticsNo(@ApiBody UserlogisticsReq req) {
		OrderReturn o = new OrderReturn();
		o.setOrderNo(req.getOrderNo());
		o.setAdminLogisticsNo(req.getShippingSn());
		o.setAdminDelivery(new Delivery(req.getDeliveryId()));
		
		orderManager.adminAddLogisticsNo(o);
	    return Ret.success(true);
	}
	
	
	/**
	 * 用户确认收到换货物品
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/userTakeDelivery")
	public Ret<Boolean> userTakeDelivery(@ApiBody UpdOrderStatusRequest req) {
	    orderManager.userTakeDelivery(req.getOrderNo());
	    return Ret.success(true);
	}
	
	/**
	 * 商户退款
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/adminRefund")
	public Ret<Boolean> adminRefund(@ApiBody UpdOrderStatusRequest req) {
	    orderManager.adminRefund(req.getOrderNo());
	    return Ret.success(true);
	}
	
	/**
	 * 查询退换货订单
	 * @param userId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/getOrderReturn")
	public Ret<List<OrderReturn>> getOrderReturn(@ApiBody QueryOrderReturnReq req) {
		
		OrderReturn vo = new OrderReturn();
		BeanUtils.copyProperties(req, vo);
		return Ret.success(orderManager.getOrderReturn(vo));
	}
	
	
	/**
	 * 查询退换货订单
	 * @param userId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value="/getOrderReturnDetail")
	public Ret<OrderReturn> getOrderReturnDetail(String orderNo) {
		return Ret.success(orderManager.getOrderReturnByOrderNo(orderNo));
	}
	
	
	
	
}
