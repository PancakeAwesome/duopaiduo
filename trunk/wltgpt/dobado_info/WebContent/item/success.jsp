<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String orderno =request.getParameter("orderno")==null?"1":request.getParameter("orderno");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="yes" name="apple-touch-fullscreen">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="320" name="MobileOptimized">
<meta content="telephone=no" name="format-detection"><title>首页</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/PCASClass.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</head>
<script type="text/javascript">
$(function(){
    var time = new Date('2016-8-22 00:00:00').getTime();//月份是实际月份-1
    countDown(time,"#colockbox1");
    var orderno = <%=orderno %>;
    $.ajax({
		url : "getOrder",
		type : 'POST',
		dataType : 'JSON',
		data : {
			orderno:orderno
		},
		async:false,
		success : function(data) {
			console.log(data);
			$("#goods_name").html(data.goods_name);
			$("#userName").html(data.userName);
			$("#phone").html(data.phone);
			$("#address").html(data.address);
			$("#orderMoney").html(data.orderMoney);
		},
		error : function() {
			alert("网络异常！");
		}
	});
});
function btn_index(){
	window.location.href="index.jsp?";
}
</script>
<body>

<div class="thewidth100">
    <div class="thewidth success">
        <h4>
            <img src="images/u351.png" alt="">恭喜您，订单提交成功！
        </h4>
        <p>
            <b>
			            恭喜您，订单提交成功！
			            尊敬的用户您好，订单已提交成功稍后会短信通知发货情况。您的订单信息如下：
            </b>
        </p>
        <p>产品：<span id="goods_name"></span></p>
        <p>姓名：<span  id="userName"></span></p>
        <p>手机：<span  id="phone"></span></p>
        <p>地址：<span  id="address"></span></p>
        <p>付款方式：货到付款</p>
        <p>支付金额：<span  id="orderMoney"></span>元</p>
        <a class="btn" onclick="btn_index()" >继续购买</a>
    </div>
</div>
</body>

</html>
