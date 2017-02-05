package cn.digirun.component.order.service.constant;

/**
 * 订单常量类定义
 * 
 * @author Administrator
 *
 */
public class Constant {

	/**
	 * 删除状态
	 */
	public static final Integer STATUS_DEL = 2;

	/**
	 * 可用状态
	 */
	public static final Integer STATUS_OK = 1;
	
	
    /**
     * 是否删除0否
     */
	public static final Integer IS_ACCOUNT_DEL_NO = 0;
	
	/**
     * 是否评价0否
     */
//	public static final Integer IS_ORDER_REMARK_NO = 0;
	
	/**
     * 是否评价1是
     */
//	public static final Integer IS_ORDER_REMARK_YES = 1;
	
	/**
     * 1是 用户看不到 后台可见
     */
	public static final Integer IS_ACCOUNT_DEL_OK = 1;

	/**
	 * 0取消的订单
	 */
	public static final Integer ORDER_STATUS_CANCEL = 0;

	/**
	 * 1进行中的订单
	 */
	public static final Integer ORDER_STATUS_ING = 1;

	/**
	 * 2退货中订单
	 */
	public static final Integer ORDER_STATUS_REFUND = 2;
	
	
	/**
	 * 3待评价的订单
	 */
	public static final Integer ORDER_STATUS_COMMENT = 3;

	/**
	 * 4已完成的订单
	 */
	public static final Integer ORDER_STATUS_OK = 4;
	
	/**
	 * 5换货中的订单
	 */
	public static final Integer ORDER_STATUS_CHANGE = 5;

	/**
	 * 付款状态(0=>未付款)
	 */
	public static final Integer PAY_STATUS_NO = 0;

	/**
	 * 付款状态(1=>已付款)
	 */
	public static final Integer PAY_STATUS_OK = 1;

	/**
	 * 发货确认状态('0' => '未确认')未确认的不予发货
	 */
	public static final Integer CONFIRM_STATUS_NO = 0;

	/**
	 * 发货确认状态('1'=> '已确认' )
	 */
	public static final Integer CONFIRM_STATUS_YES = 1;

	/**
	 * 发货确认状态('2' => '部分拆分')
	 */
	public static final Integer CONFIRM_STATUS_SPLIT = 2;

	/**
	 * 发货确认状态('3' => '已拆分完')
	 */
	public static final Integer CONFIRM_STATUS_SPLIT_OK = 3;

	/**
	 * 发货确认状态('4' => '取消')
	 */
	public static final Integer CONFIRM_STATUS_CANCEL = 4;

	/**
	 * 发货确认状态('5' =>'余单撤销')
	 */
	public static final Integer CONFIRM_STATUS_RECALL = 5;

	/**
	 * 发货状态(0未发货)
	 */
	public static final Integer SHIPPING_STATUS_NO = 0;

	/**
	 * 发货状态(1已发货)
	 */
	public static final Integer SHIPPING_STATUS_OK = 1;

	/**
	 * 打印状态(0=》未打印)
	 */
	public static final Integer PRINT_STATUS_NO = 0;

	/**
	 * 打印状态( 1=》打印)
	 */
	public static final Integer PRINT_STATUS_OK = 1;

	/**
	 * 备货状态(0=》未备货 )
	 */
	public static final Integer STOCK_STATUS_NO = 0;

	/**
	 * 备货状态( 1=》备货)
	 */
	public static final Integer STOCK_STATUS_OK = 1;

	/**
	 * 快递状态(0=》未快递 )
	 */
	public static final Integer EXPRESS_STATUS_NO = 0;

	/**
	 * 快递状态(1=》快递)
	 */
	public static final Integer EXPRESS_STATUS_OK = 1;
	
	/**
	 * 退换方式  1已到货申请退货 
	 */
	public static final Integer RETURN_TYPE_REFUND_ED = 1;
	
	/**
	 * 退换方式  2已到货申请换货 
	 */
	public static final Integer RETURN_TYPE_EXCHANGE_ED = 2;
	
	/**
	 * 退换方式   3未到货退款
	 */
	public static final Integer RETURN_TYPE_REFUND_NO = 3;
	
	/**
	 * 0:用户取消   
	 */
	public static final Integer RETURN_STATUS_USER_CANCAL = 0;
	
	
	/**
	 * 1:待审核 
	 */
	public static final Integer RETURN_STATUS_VERIFY = 1;
	
	/**
	 *  2：审核通过
	 */
	public static final Integer RETURN_STATUS_VERIFY_OK = 2;
	
	/**
	 *  3：驳回 (可再次申请) 
	 */
	public static final Integer RETURN_STATUS_VERIFY_REJECT = 3;
	
	/**
	 *  4：拒绝  (不可再次申请)
	 */
	public static final Integer RETURN_STATUS_VERIFY_REFUSE = 4;
	
	/**
	 *  5:买家已退货 
	 */
	public static final Integer RETURN_STATUS_REFUNDED = 5;
	
	/**
	 *  6:商户确认收到用户回寄
	 */
	public static final Integer RETURN_STATUS_CONFIRM_USER_LOGISTIC = 6;
	
	
	/**
	 * 7：卖家已换货 
	 */
	public static final Integer RETURN_STATUS_EXCHANGEED = 7;
	
	/**
	 *  8:卖家已退款 
	 */
	public static final Integer RETURN_STATUS_REFUND = 8;
	
	/**
	 *  9:用户收到换货，换货结束
	 */
	public static final Integer RETURN_STATUS_EXCHANGE_END = 9;
	
	
	
	/**
	 * 1 退货订单
	 */
	public static final Integer ORDER_TYPE_REFUND = 1;
	
	/**
	 * 2 换货订单
	 */
	public static final Integer ORDER_TYPE_EXCHANGE = 2;
	
	
}
