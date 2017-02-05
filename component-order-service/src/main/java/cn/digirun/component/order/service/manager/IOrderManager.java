package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.OrderReturn;
import cn.digirun.component.order.model.Orders;
import cn.digirun.component.order.model.RemarkModel;

public interface IOrderManager {
	
	/**
	 * 保存订单
	 * @param order
	 */
	String saveOrder(Orders order);
	
	
	/**
	 * 修改订单
	 * @param order
	 */
	void updOrder(Orders order);
	
	/**
	 * 查询订单
	 * @param userId
	 * @param status
	 * @return
	 */
	List<Orders> getOrders(Orders vo);
	
	
	/**
	 * 获取订单详情
	 * @param orderNo
	 * @return
	 */
	Orders getOrderDetail(String orderNo);
	
	/**
	 * 取消订单
	 * @param orderNo
	 */
	void cancelOrder(String orderNo,String cancelReason);
	
	/**
	 * 删除订单
	 * @param orderNo
	 */
	void delOrder(String orderNo);

	/**
	 * 发货状态确认
	 * @param orderNo
	 */
	void deliveryConfirm(Orders order);
	
	/**
	 * 打印订单
	 * @param orderNo
	 */
	void orderPrint(Orders order);
	
	/**
	 * 订单备货
	 * @param orderNo
	 */
	void orderStock(Orders order);
	
	/**
	 * 订单快递
	 * @param orderNo
	 */
	void orderExpress(Orders order);
	
	
	/**
	 * 确认发货
	 * @param orderNo
	 */
	void deliveryOrder(Orders order);
	
	/**
	 * 确认收货
	 * @param orderNo
	 */
	void takeOrder(String orderNo);
	
	/**
	 * 订单评价
	 * @param orderNo
	 */
	void commentOrder(String orderNo);
	
	/**
	 * 订单评价
	 * @param orderNo
	 */
	void saveComment(RemarkModel remark);
	
	/**
	 * 申请退货
	 * @param order
	 */
	void refund(OrderReturn order);
	
	/**
	 * 申请换货
	 * @param order
	 */
	void exchange(OrderReturn order);
	
	
	/**
	 * 审核换货
	 * @param order
	 */
	void reviewedReturnOrder(OrderReturn order);
	
	/**
	 * 用户退/换货填写物流信息
	 * @param orderNo
	 */
	void userAddLogisticsNo(OrderReturn order);
	
	/**
	 * 仓库退/换货发货填写物流信息
	 * @param order
	 */
	void adminAddLogisticsNo(OrderReturn order);
	
	/**
	 * 商户确认收到用户回寄货物
	 * @param order
	 */
	void confirmUserLogistics(String orderNo);
	
	/**
	 * 用户确认收到换货物品
	 * @param orderNo
	 */
	void userTakeDelivery(String orderNo);
	
	/**
	 * 商户退款
	 * @param orderNo
	 */
	void adminRefund(String orderNo);
	
	
	/**
	 * 根据userId/status查询
	 * @param userId/status
	 * @return
	 */
	List<OrderReturn> getOrderReturn(OrderReturn order);
	
	/**
	 * 根据userId/status查询
	 * @param userId/status
	 * @return
	 */
	OrderReturn getOrderReturnByOrderNo(String orderNo);
	
	
	
}
