<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String orderid= request.getParameter("orderid");
%>    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>广告推广后台管理</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/public/font-awesome.min.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/public/laydate/laydate.js"></script>
<script type="text/javascript" src="js/public/laypage/laypage.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
$(function(){
	 if($.cookie('name') != null && $.cookie('name') != ""){
		 $("#sp").html( $.cookie('name'));
		 info();
	 }else{
		window.location.href="login.jsp";
	 }
	
	$('.wrap_l li a').click(function(){
		$('.wrap_l li a').removeClass('current');
		$(this).addClass('current');
		
	})
	
});
function upstatus(status){
	var logistId=$("#logistId").val();
	var logistNos=$("#logistNos").val();
	var logistMoneys=$("#logistMoneys").val();
	var adminRemark=$("#adminRemark").val();
	if(logistId==0){layer.msg("请选择物流");return;}
	if(logistNos==""){layer.msg("请填写物流单号");return;}
	if(logistMoneys==""){layer.msg("请填写物费用");return;}
	$.ajax({
		url:"updateOrder",
		type : 'POST',
		data :{
			"menu.id":<%=orderid %>,
			"menu.status":status,
			"menu.logistId":logistId,
			"menu.logistNo":logistNos,
			"menu.adminRemark":adminRemark,
			"menu.logistMoney":logistMoneys
		},
		async:false,
		success : function(data) {
			alert(data);
		},
		error : function() {
			alert("网络异常！");
		}
	});
}
function info(){
	$.ajax({
		url:"setOrder",
		type : 'POST',
		data :{
			orderid:<%=orderid %>
		},
		async:false,
		success : function(data) {
			console.log(data);
			var s='';
			if(data.status==1)
				s='待发货';
			if(data.status==2)
				s='待付款';
			if(data.status==3)
				s='已付款';
			if(data.status==4)
				s='已退货';
			$("#status").html(s);
			$("#goods_name").html(data.goods_name);
			$("#price").html(data.price);
			$("#goodsNumber").html(data.goodsNumber);
			$("#orderNo").html(data.orderNo);
			$("#addTime").html(data.addTime);
			$("#userName").html(data.userName);
			$("#userName1").html(data.userName);
			$("#address").html(data.address);
			$("#phone").html(data.phone);
			var a='';
			if(data.invoice==0)
				a='否';
			if(data.invoice==1)
				a='是';
			$("#invoice").html(a);
			$("#invoiceTitle").html(data.invoiceTitle);
			$("#orderMoney").html(data.orderMoney);
			$("#userRemark").html(data.userRemark);
			$("#adminRemark").val(data.adminRemark);
			$("#logistId").val(data.logistId==null?0:data.logistId);
			$("#logistNo").html(data.logistNo);
			$("#logistMoney").html(data.logistMoney);
				if(data.status==1){
					$("#btn_wuLiu").addClass("none");
				}
				if(data.status==2){
					$("#btn_wuLiu").removeClass("none");
				}
				if(data.status==3){
					$("#btn_wuLiu").removeClass("none");
				}
				if(data.status==4){
					$("#btn_wuLiu").removeClass("none");
				}
			},
			error : function() {
				alert("网络异常！");
			}
	});
}
function btn_wuLiu(){
	$.ajax({
		url:"kuaidi",
		type : 'POST',
		data:{
			com:"yuantong",
			nu:"882127003606870652"
		},
		async:false,
		success : function(data) {
			console.log(data);
			if(data.status == 1){
				var htm  ='<iframe src="'+data.url+'" scrolling="no" frameborder=0 style="width: 560px; height: 260px;" name="centerFrame" id="centerFrame" ></iframe>';
				$("#wl_conts").html(htm);
			}else{
				alert("暂无物流信息");
			}
		},
		error : function() {
			alert("网络异常！");
		}
	});
	
}
</script>
</head>

<body>

<div id="container" class="thewidth100">

<!--header-->
    <div class="header_bg clearfix thewidth100"> 
        <div class="header_top clearfix">
            <div class="f_l">logo 多拍多资讯站后台管理</div>
            <div class="f_r">欢迎 </div>
        </div>
        <div class="header">
            <nav>
                <ul>
                    <li><a class="choice" href="javascript:;">广告管理</a></li>
                </ul>
            </nav>
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->


<!--wrap-->
    <div class="wrap_bg thewidth100">    
        <div class="wrap clearfix">
            <div class="wrap_l">
                <h2>订单管理 <i class="f_r icon-angle-up"></i></h2>
                <ul class="">
                    <li><a href="orders.jsp">订单列表</a></li>
                    <li><a href="orders.jsp">待发货</a></li>
                    <li><a href="orders.jsp">待收款</a></li>
                    <li><a href="orders.jsp">已收款</a></li>
                    <li><a href="orders.jsp">已退货</a></li> 
                </ul>
                <h2>渠道管理 <i class="f_r icon-angle-down"></i></h2>
                <ul class="none">
                    <li><a href="javascript:;">渠道列表</a></li>
                    <li><a href="javascript:;">已结束渠道列表</a></li>
                </ul>
                <h2>商品管理 <i class="f_r icon-angle-down"></i></h2>
                <ul class="none">
                     <li><a href="goods.jsp">广告商品列表</a></li>
                     <li><a href="addGoods.jsp">广告商品添加</a></li>
                </ul>
                <h2>报表统计 <i class="f_r icon-angle-down"></i></h2>
                <ul class="none">
                    <li><a href="tjsettlement.jsp">结算统计</a></li>
                    <li><a href="tjorder.jsp">订单统计</a></li>
                    <li><a href="tjchnnel.jsp">渠道统计</a></li>
                    <li><a href="https://tongji.cnzz.com/main.php?c=user&a=login">友盟统计</a></li>
                    <li><a href="http://tongji.baidu.com/web/welcome/login;">百度统计</a></li>
                </ul>
                <h2>权限管理 <i class="f_r icon-angle-down"></i></h2>
                <ul class="none">
                    <li><a href="javascript:;">管理员列表</a></li>
                    <li><a href="javascript:;">添加管理员</a></li>
                </ul>
                <h2>系统管理 <i class="f_r icon-angle-down"></i></h2>
                <ul class="none">
                    <li><a href="javascript:;">修改密码</a></li>
                </ul>
            </div>
            <div class="wrap_r">
                <div class="clearfix ma_t10">
                    <h5>编辑订单（<span id="status"></span>）</h5>
                </div>
                
                <div class="goods">
                    <table class="table table-bordered ma_t10">
                        <thead>
                            <tr>
                                <th>商品信息</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr>
                                <td align="left">
                                  	  商品名称：<span id="goods_name"></span>
                                    <span class="ma_l20">商品价格：<span id="price"></span></span>
                                    <span class="ma_l20">商品数量：<span id="goodsNumber"></span></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table class="table table-bordered ma_t10">
                        <thead>
                            <tr>
                                <th colspan="2">订单信息</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td align="left" class="wn200">订单号：</td>
                                <td align="left"><span id="orderNo"></span></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">付款方式：</td>
                                <td align="left">货到付款</td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">下单时间：</td>
                                <td align="left"><span id="addTime"></span></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">用户名：</td>
                                <td align="left"><span id="userName"></span></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">收货信息：</td>
                                <td align="left"><span id="userName1"></span><br><span id="address"></span><span id="phone"></span></td>
                            </tr>
                             <tr>
                                <td align="left" class="wn200">是否需要发票：</td>
                                <td align="left"><span id="invoice"></span></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">发票抬头：</td>
                                <td align="left"><span id="invoiceTitle"></span></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">订单金额：</td>
                                <td align="left">订单总金额：  <span id="orderMoney"></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">买家留言：</td>
                                <td align="left"><span id="userRemark"></span></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">商家备注：</td>
                                <td align="left"><textarea class="form-control" name="" id="adminRemark"  placeholder="请输入商家备注"></textarea></td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">物流选择：</td>
                                <td align="left">
                                    <select id="logistId" class="form-control wn200">
                                        <option value="0">请选择物流</option>
                                        <option value="1">顺丰快递</option>
                                        <option value="2">申通快递</option>
                                        <option value="3">韵达快递</option>
                                        <option value="4">中通快递</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" class="wn200">物流单号：</td>
                                <td align="left">
	                                <span id="logistNo"></span>
	                                <input id="btn_wuLiu" class="none" value="物流查询" type="button" onclick="btn_wuLiu()" />
                                	<div id="wl_conts">
                                		
                                	</div>
                                </td>
                                
                            </tr>
                            <tr>
                                <td align="left" class="wn200">快递费用：</td>
                                <td align="left"><span id="logistMoney"></span></td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="typename"></div>
                    
                </div> 
            </div>
        </div>      
    </div>
<!--/wrap-->

<br><br><br><br>

</div>


<script>
$('.wrap_l h2').click(function(){
    if($(this).find('i').hasClass('icon-angle-up')){
        $(this).find('i').removeClass('icon-angle-up').addClass('icon-angle-down');
        $(this).next().hide();
    }else{
        $(this).find('i').addClass('icon-angle-up').removeClass('icon-angle-down');
        $(this).next().show();
    }
});

</script>
</body>
</html>