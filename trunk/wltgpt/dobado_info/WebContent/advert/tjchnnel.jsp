<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>渠道统计</title>
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
			 info(1);
		 }else{
			window.location.href="login.jsp";
		 }
	});
	function info(page){
		var goodsName = $("#goodsName").val();
		var zchName = $("#zchName").val();
		var atime = $("#atime").val();
		var btime = $("#btime").val();
		$.ajax({
			url : "tjchnnel",
			type : 'POST',
			data :{
				page:page,
				size:10,
				goodsName:goodsName,
				zchName:zchName,
				atime:atime,
				btime:btime
			},
			async:false,
			success : function(data) {
				console.log(data);
				var htm='';
				$.each(data.list,function(i,obj){
					htm +='<tr>';
					htm +='<td>'+obj.zchannel_name+'</td>';
					htm +='<td>'+obj.start_time+'</td>';
					htm +='<td>'+obj.end_time+'</td>';
					htm +='<td>'+obj.goods_name+'</td>';
					htm +='<td>'+obj.goods_channel_rate+'%</td>';
					htm +='<td>'+obj.logistMoney+'</td>';
					htm +='<td>'+obj.ordersNumber+'</td>';
					htm +='<td>'+obj.ordersNumbers+'</td>';
					htm +='<td>'+obj.orderMoney+'</td>';
					htm +='<td>'+obj.orderMoneys+'</td>';
					htm +='<td>'+obj.orderMoneyx+'</td>';
					htm +='</tr>';
				});
				$("#tablehtm").html(htm);
				laypage({
				    cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象,
				    pages: (data.totalPage), //总页数
				    curr: page, //当前页
				    jump: function(obj, first){ //触发分页后的回调
				        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
				        	info(obj.curr)
				        }
				    }
				});
			},
			error : function() {
				alert("网络异常！");
			}
		}); 
	}
	function toexcel(){
		var goodsName = $("#goodsName").val();
		var zchName = $("#zchName").val();
		var atime = $("#atime").val();
		var btime = $("#btime").val();
		$("#goodsName1").val(goodsName);
		$("#zchName1").val(zchName);
		$("#atime1").val(atime);
		$("#btime1").val(btime);
		$("#frm").submit();
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
                <h2>订单管理 <i class="f_r icon-angle-down"></i></h2>
                <ul class="none">
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
                    <li><a class="current" href="goods.jsp">广告商品列表</a></li>
                    <li><a href="addGoods.jsp">广告商品添加</a></li>
                </ul>
                <h2>报表统计 <i class="f_r icon-angle-up"></i></h2>
                <ul class="">
                    <li><a href="tjsettlement.jsp">结算统计</a></li>
                    <li><a href="tjorder.jsp">订单统计</a></li>
                    <li><a class="current" href="javascript:;">渠道统计</a></li>
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
                    <span class="f_l ma_t10">时间检索：</span>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="atime" placeholder="">
                    </div>
                    <span class="f_l ma_t10 ma_l20">至</span>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="btime" placeholder="">
                    </div>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="goodsName" placeholder="请输入商品名称">
                    </div>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="zchName" placeholder="请输入渠道名称">
                    </div>
                    <button type="" onclick="info(1)" class="btn f_l btn-primary ma_l20">查询</button>
                </div>
                <div class="goods">
                    <table class="table table-bordered ma_t10">
                        <thead>
                            <tr>
                                <th>渠道名称</th>
                                <th>投放时间</th>
                                <th>结束时间</th>
                                <th>商品名称</th>
                                <th>渠道提成比例</th>
                                <th>运费总额</th>
                                <th>总订单量</th>
                                <th>有效订单</th>
                                <th>订单总额</th>
                                <th>有效订单总额</th>
                                <th>渠道提成</th>
                            </tr>
                        </thead>
                        <tbody id="tablehtm">
                           
                        </tbody>
                    </table>
                    <div id="page1">
                    </div>
                    <form id="frm" action="toExcela" method="post">
                    	<input type="hidden" id="goodsName1" name="goodsName" value="" >
                    	<input type="hidden" id="zchName1" name="zchName" value="" >
                    	<input type="hidden" id="atime1" name="atime" value="" >
                    	<input type="hidden" id="btime1" name="btime" value="" >
                    	<input type="button" onclick="toexcel()" value="导出数据">
                    </form>
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