package cn.digirun.component.order.service.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.item.model.ItemModelOrder;
import cn.digirun.component.order.model.OrderDetail;
import cn.digirun.component.order.model.OrderItems;
import cn.digirun.component.order.model.OrderReturn;
import cn.digirun.component.order.model.OrderReturnDetail;
import cn.digirun.component.order.model.Orders;
import cn.digirun.component.order.model.RemarkModel;
import cn.digirun.component.order.model.UserOrderAddress;
import cn.digirun.component.order.service.constant.Constant;
import cn.digirun.component.order.service.manager.IOrderManager;
import cn.digirun.component.order.service.otherapi.ItemAPI;
import cn.digirun.component.order.service.repository.CommentRepository;
import cn.digirun.component.order.service.repository.OrderDetailRepository;
import cn.digirun.component.order.service.repository.OrderReturnRepository;
import cn.digirun.component.order.service.repository.OrdersRepository;
import cn.digirun.component.order.service.util.PrimaryGenerater;

@Service
@Transactional
public class OrderManager implements IOrderManager {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ItemAPI itemServ;
	
	@Autowired
	private OrderReturnRepository orderReturnRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<Orders> getOrders(Orders order) {
		return ordersRepository.findAll(new Specification<Orders>() {
			@Override
			public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				Join<Orders, UserOrderAddress> addrRef = root.join("userOrderAddress",JoinType.INNER);
				query.multiselect(root.get("id"),
						 root.get("orderNo"),
						 root.get("userId"),
						 root.get("adminUserId"),						
						 root.get("shippingSn"),
						 
						 root.get("freightAmount"),
						 root.get("invoiceSn"),
						 root.get("invoCustName"),
						 root.get("storeId"),						
						 root.get("shouldAmount"),
						 
						 root.get("discountAmount"),
						 root.get("amount"),
						 root.get("isAccountDel"),
						 root.get("status"),						
						 root.get("payStatus"),
						 
						 root.get("confirmStatus"),
						 root.get("shippingStatus"),
						 root.get("printStatus"),
						 root.get("stockStatus"),
						 root.get("expressStatus"),
						 
						 
						 root.get("adminRemark"),						
						 root.get("source"),
						 root.get("userRemark"),
						 root.get("isO2oOrder"),
						 root.get("sourceWeb"),
						 
						 root.get("addTime"),
						 root.get("payTime"),						
						 root.get("confirmTime"),
						 root.get("deliveryTime"),
						 root.get("signTime"),
						 
						 root.get("closeTime"),
						 root.get("overTime"),						
						 root.get("deleteTime"),
						 root.get("creator"),
						 root.get("modTime"),
						 
						 root.get("updater"),
						 root.get("createTime"),

						addrRef.get("provinceName"),
						addrRef.get("cityName"),
						addrRef.get("countyName"),
						addrRef.get("userName"),
						addrRef.get("phone"),
						addrRef.get("address"));
				
				if(null != order.getUserId()){
					list.add(cb.equal(root.get("userId"),  order.getUserId()));
				}
				if(null != order.getStatus()){
					list.add(cb.equal(root.get("status"),  order.getStatus()));
				}
				return cb.and(list.toArray(new Predicate[]{}));
			}
		});
		
		/*if(null != order.getUserId() && null != order.getStatus()){
			return  ordersRepository.findByUserIdAndStatus(order.getUserId(), order.getStatus());
		}else if(null != order.getUserId()){
			return ordersRepository.findByUserId(order.getUserId());
		}else if(null != order.getStatus()){
			return ordersRepository.findByStatus(order.getStatus());
		}
		else{
			return (List<Orders>) ordersRepository.findAll();
		}*/
	}
	
	@Override
	public Orders getOrderDetail(String orderNo) {
		
		Orders order =  ordersRepository.findByOrderNo(orderNo);
		order.setOrderdetail(orderDetailRepository.findByOrders_orderNo(orderNo));
		return order;
	}
	
	@Override
	public String saveOrder(Orders order) {
		BigDecimal amount = new BigDecimal("0");
		BigDecimal shouldAmount = new BigDecimal("0");
		BigDecimal discountAmount = new BigDecimal("0");

		ItemModelOrder item = null;
		OrderDetail detail = null;

		String orderNo = getOrderId(order.getUserId());
		List<OrderDetail> datas=new ArrayList<OrderDetail>();
		for (OrderItems orderItems : order.getOrderItems()) {

			// 调用商品接口,获取价格
			item = itemServ.getItemById(orderItems.getItemId());

			amount = amount.add(item.getPrice().multiply(new BigDecimal(String.valueOf(orderItems.getQuantity()))));
			detail = new OrderDetail();
			BeanUtils.copyProperties(orderItems, detail);
			detail.setPrice(item.getPrice());
			detail.setOrders(order);
			datas.add(detail);

		}
		
		order.setOrderNo(orderNo);
		order.setOrderdetail(datas);
		shouldAmount = amount;
		order.setAmount(amount);
		order.setShouldAmount(shouldAmount);
		order.setDiscountAmount(discountAmount);
		order.setStatus(Constant.ORDER_STATUS_ING);
		order.setPayStatus(Constant.PAY_STATUS_NO);
		order.setConfirmStatus(Constant.CONFIRM_STATUS_NO);
		order.setShippingStatus(Constant.SHIPPING_STATUS_NO);
		order.setPrintStatus(Constant.PRINT_STATUS_NO);
		order.setStockStatus(Constant.STOCK_STATUS_NO);
		order.setExpressStatus(Constant.EXPRESS_STATUS_NO);
		order.setIsAccountDel(Constant.IS_ACCOUNT_DEL_NO);
		order.setAddTime(new Date());
		ordersRepository.save(order);
		
		return orderNo;
	}
	
	@Override
	public void updOrder(Orders order){
		Orders  o = getOrderDetail(order.getOrderNo());
		o.setUserOrderAddress(order.getUserOrderAddress());
		o.setInvoiceType(order.getInvoiceType());
		o.setInvoCustName(order.getInvoCustName());
		o.setUserRemark(order.getUserRemark());
		o.setDispatching(order.getDispatching());
		ordersRepository.save(o);
		
	}
	
	
	@Override
	public void cancelOrder(String orderNo,String cancelReason){
		Orders order = ordersRepository.findByOrderNo(orderNo);
		order.setOrderNo(orderNo);
		order.setCancelReason(cancelReason);
		order.setCloseTime(new Date());
		order.setStatus(Constant.ORDER_STATUS_CANCEL);
		
		//释放库存
		List<OrderDetail> datas =orderDetailRepository.findByOrders_orderNo(orderNo);
		for (OrderDetail orderdetail : datas) {
			itemServ.updStock(orderdetail.getItemId(), orderdetail.getQuantity());
		}
		
		ordersRepository.save(order);
	}
	
	@Override
	public void delOrder(String orderNo){
		Orders order = ordersRepository.findByOrderNo(orderNo);
		order.setOrderNo(orderNo);
		order.setIsAccountDel(Constant.IS_ACCOUNT_DEL_OK);
		
		ordersRepository.save(order);
	}

	//  订单号生成
    private String getOrderId(Integer userId) {
        
    	String maxOrderId = "";
    	Orders o = ordersRepository.findFirstByUserIdOrderByIdDesc(userId);
    	if(null!= o){
    		maxOrderId = o.getOrderNo();
    	}
        String nextOrderId = PrimaryGenerater.getInstance().generaterNextNumber(maxOrderId);
        return userId + nextOrderId;
    }
    
    @Override
	public void deliveryConfirm(Orders vo) {
		
		Orders order = ordersRepository.findByOrderNo(vo.getOrderNo());
		order.setConfirmStatus(vo.getConfirmStatus());
		order.setConfirmTime(new Date());
		ordersRepository.save(order);
	}
    
    
    @Override
	public void orderPrint(Orders vo){
    	Orders order = ordersRepository.findByOrderNo(vo.getOrderNo());
		order.setPrintStatus(Constant.PRINT_STATUS_OK);
		ordersRepository.save(order);
    }
	
    @Override
	public void orderStock(Orders vo){
    	Orders order = ordersRepository.findByOrderNo(vo.getOrderNo());
		order.setStockStatus(Constant.STOCK_STATUS_OK);
		ordersRepository.save(order);
    }
	
    @Override
	public void orderExpress(Orders vo){
    	Orders order = ordersRepository.findByOrderNo(vo.getOrderNo());
		order.setExpressStatus(Constant.EXPRESS_STATUS_OK);
		ordersRepository.save(order);
    }
	
    

	@Override
	public void deliveryOrder(Orders vo) {
		
		Orders order = ordersRepository.findByOrderNo(vo.getOrderNo());
		
		if(null ==order.getConfirmStatus() || order.getConfirmStatus()==0){
			//待异常处理
			System.out.println("未确认的不予发货 ");
			return;
		}
		order.setDelivery(vo.getDelivery());
		order.setShippingSn(vo.getShippingSn());
		order.setDeliveryTime(new Date());
		order.setShippingStatus(Constant.SHIPPING_STATUS_OK);
		ordersRepository.save(order);
	}
	
	
	@Override
	public void takeOrder(String orderNo) {
		Orders order = ordersRepository.findByOrderNo(orderNo);
		order.setSignTime(new Date());
		order.setStatus(Constant.ORDER_STATUS_COMMENT);
		ordersRepository.save(order);
		
	}

	@Override
	public void commentOrder(String orderNo) {
		Orders order = ordersRepository.findByOrderNo(orderNo);
		order.setOrderNo(orderNo);
		order.setStatus(Constant.ORDER_STATUS_OK);
		order.setOverTime(new Date());
//		order.setIsRemark(Constant.IS_ORDER_REMARK_YES);
		ordersRepository.save(order);
		
	}
	
	@Override
	public void refund(OrderReturn order){
		
		Orders o = ordersRepository.findByOrderNo(order.getOrders().getOrderNo());
		o.setStatus(Constant.ORDER_STATUS_REFUND);
		ordersRepository.save(o);
		
		refundOrExchangeOrder(order,Constant.ORDER_TYPE_REFUND);
	}
	
	@Override
	public void exchange(OrderReturn order){
		
		Orders o = ordersRepository.findByOrderNo(order.getOrders().getOrderNo());
		o.setStatus(Constant.ORDER_STATUS_CHANGE);
		ordersRepository.save(o);
		
		refundOrExchangeOrder(order,Constant.ORDER_TYPE_EXCHANGE);
	}
	
	
	/**
	 * 审核换货
	 * @param order
	 */
	public void reviewedReturnOrder(OrderReturn vo){
		OrderReturn order = orderReturnRepository.findByOrderNo(vo.getOrderNo());
		order.setStatus(vo.getStatus());
		order.setAdminRemark(vo.getAdminRemark());
		order.setAgreeTime(new Date());
		orderReturnRepository.save(order);
	}

	
	
	@Override
	public void userAddLogisticsNo(OrderReturn vo) {
		OrderReturn order = orderReturnRepository.findByOrderNo(vo.getOrderNo());
		order.setOrderNo(vo.getOrderNo());
		order.setUserDelivery(vo.getUserDelivery());
		order.setUserLogisticsNo(vo.getUserLogisticsNo());
		order.setReturnProductTime(new Date());
		order.setStatus(Constant.RETURN_STATUS_REFUNDED);
		orderReturnRepository.save(order);
		
	}
	
	/**
	 * 商户确认收到用户回寄货物
	 * @param order
	 */
	@Override
	public void confirmUserLogistics(String orderNo){
		OrderReturn order = orderReturnRepository.findByOrderNo(orderNo);
		order.setStatus(Constant.RETURN_STATUS_CONFIRM_USER_LOGISTIC);
		order.setConfirmUserLogisticsTime(new Date());
		
		orderReturnRepository.save(order);
	}
	
	@Override
	public void adminAddLogisticsNo(OrderReturn vo) {
		OrderReturn order = orderReturnRepository.findByOrderNo(vo.getOrderNo());
		order.setOrderNo(vo.getOrderNo());
		order.setAdminDelivery(vo.getAdminDelivery());
		order.setAdminLogisticsNo(vo.getAdminLogisticsNo());
		order.setReturnProductTime(new Date());
		order.setStatus(Constant.RETURN_STATUS_EXCHANGEED);
		orderReturnRepository.save(order);
		
	}
	
	@Override
	public void userTakeDelivery(String orderNo){
		OrderReturn order = orderReturnRepository.findByOrderNo(orderNo);
		order.setStatus(Constant.RETURN_STATUS_EXCHANGE_END);
		order.setOverTime(new Date());
		orderReturnRepository.save(order);
		
		//修改订单主表状态为已完成
		takeOrder(order.getOrders().getOrderNo());
	}
	
	@Override
	public void adminRefund(String orderNo){
		OrderReturn order = orderReturnRepository.findByOrderNo(orderNo);
		order.setStatus(Constant.RETURN_STATUS_REFUND);
		order.setOverTime(new Date());
		
		orderReturnRepository.save(order);
		
		//修改订单主表状态为已完成
		takeOrder(order.getOrders().getOrderNo());
	}
	
	@Override
	public List<OrderReturn> getOrderReturn(OrderReturn order){
		
		
		
		return orderReturnRepository.findAll(new Specification<OrderReturn>() {
			@Override
			public Predicate toPredicate(Root<OrderReturn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(null != order.getUserId()){
					list.add(cb.equal(root.get("userId"),  order.getUserId()));
				}
				if(null != order.getStatus()){
					list.add(cb.equal(root.get("status"),  order.getStatus()));
				}
				return cb.and(list.toArray(new Predicate[]{}));
			}
		});
		
		
		/*if(null != order.getUserId() && null != order.getStatus()){
			return  orderReturnRepository.findByUserIdAndStatus(order.getUserId(), order.getStatus());
		}else if(null != order.getUserId()){
			return orderReturnRepository.findByUserId(order.getUserId());
		}else if(null != order.getStatus()){
			return orderReturnRepository.findByStatus(order.getStatus());
		}
		else{
			return (List<OrderReturn>) orderReturnRepository.findAll();
		}*/
		
	}
	
	
	@Override
	public OrderReturn getOrderReturnByOrderNo(String orderNo){
		return orderReturnRepository.findByOrderNo(orderNo);
	}
	
	private void refundOrExchangeOrder(OrderReturn o,Integer orderType){
		
		
		BigDecimal amount = new BigDecimal("0");
		
		List<OrderReturnDetail> details = o.getOrderReturnDetail();
		
		List<OrderReturnDetail> detailDatas = new ArrayList<OrderReturnDetail>();
		
		OrderReturnDetail data = null;
		
		OrderDetail od = null ;
		for (OrderReturnDetail orderdetail : details) {
            od = orderDetailRepository.findById(orderdetail.getId());
			data = new OrderReturnDetail();
			BeanUtils.copyProperties(od, data);
			detailDatas.add(data);
			amount = amount.add(od.getPrice().multiply(new BigDecimal(String.valueOf(od.getQuantity()))));
			data.setOrderReturn(o);
		    //orderDetailRepository.delete(od); 
		}
		
		o.setOrderReturnDetail(detailDatas);
		String orderNo = getOrderId(o.getUserId());
		o.setOrderNo(orderNo);
		
		if(String.valueOf(o.getReturnMoney()).equals("-1")){
			o.setReturnMoney(amount);
		}
		o.setApplyTime(new Date());
		o.setStatus(Constant.RETURN_STATUS_VERIFY);
		o.setOrderType(orderType);
		orderReturnRepository.save(o);
	
	}

	@Override
	public void saveComment(RemarkModel remark) {
		// TODO Auto-generated method stub
		commentRepository.save(remark);
	}
}
