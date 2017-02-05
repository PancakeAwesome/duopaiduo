<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String type=request.getParameter("STATUS")==null?"":request.getParameter("STATUS");
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
	
	var name=$("#cd").val();
	var role=$("#role").val();
	if(name !=null && name !=""){
		 $.cookie('name', name);
		 $.cookie('role', role);
	}
	 if($.cookie('name') != null && $.cookie('name') != ""){
		 $("#sp").html( $.cookie('name'));
		 
		 $("#astatus").val(0);
			getOrders(1);
	 }else{
		window.location.href="login.jsp";
	 }
	
	$('.wrap_l li a').click(function(){
		$('.wrap_l li a').removeClass('current');
		$(this).addClass('current');
		
	})
	
});
function getOrders(page){
	var orno = $("#orno").val();
	var orname = $("#orname").val();
	var usname = $("#usname").val();
	var usphon = $("#usphon").val();
	var atime = $("#atime").val();
	var btime = $("#btime").val();
	$.ajax({
		url : "pageOrders",
		type : 'POST',
		data :{
			page:page,
			size:10,
			orno:orno,
			orname:orname,
			usname:usname,
			usphon:usphon,
			atime:atime,
			btime:btime,
			status:$("#astatus").val()
		},
		async:false,
		success : function(data) {
			console.log(data);
			var htm='';
			$.each(data.list,function(i,obj){
				htm += '<tr>';
				htm += '<td><a href="xqorder.jsp?orderid='+obj.id+'">'+obj.orderNo+'</a></td>';
				htm += '<td>'+obj.addTime+'</td>';
				htm += '<td>'+obj.userName+'<br>'+obj.address+',电话:'+obj.phone+'</td>';
				htm += '<td>'+obj.orderMoney+'</td>';
				htm += '<td>'+obj.zchannel_name+'</td>';
				if(obj.status==1){htm += '<td>待发货</td>';}
				if(obj.status==2){htm += '<td>待收款</td>';}
				if(obj.status==3){htm += '<td>已收款</td>';}
				if(obj.status==4){htm += '<td>已退货</td>';}
				htm += '<td><a href="upOrder.jsp?orderid='+obj.id+'"><button class="btn">编辑</button></a></td>';
				htm += '</tr>';
			});
			$("#pagetable").html(htm);
			
			laypage({
			    cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象,
			    pages: (data.totalPage), //总页数
			    curr: page, //当前页
			    jump: function(obj, first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			        	getOrders(obj.curr)
			        }
			    }
			});
		},
		error : function() {
			alert("网络异常！");
		}
	}); 
}
function getstatus(s){
	$("#astatus").val(s);
	getOrders(1);
}
</script>
</head>

<body>
<input type="hidden" id="cd" value="${admin.admin }" />
<input type="hidden" id="role" value="${admin.roleId }" />
<div id="container" class="thewidth100">

<!--header-->
    <div class="header_bg clearfix thewidth100"> 
        <div class="header_top clearfix">
            <div class="f_l">网络推广后台管理</div>
            <div class="f_r">欢迎 <span id="sp"></span></div>
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
                    <li><a class="current" href="javascript:getstatus(0);">订单列表</a></li>
                    <li><a href="javascript:getstatus(1);">待发货</a></li>
                    <li><a href="javascript:getstatus(2);">待收款</a></li>
                    <li><a href="javascript:getstatus(3);">已收款</a></li>
                    <li><a href="javascript:getstatus(4);">已退货</a></li> 
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
                    <li><a href="http://tongji.baidu.com/web/welcome/login">百度统计</a></li>
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
                    <div class="form-group f_l">
                        <input type="text" class="form-control wn200" id="orno" placeholder="请输入订单号">
                    </div>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="orname" placeholder="请输入渠道名称">
                    </div>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="usname" placeholder="请输入收货人姓名">
                    </div>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200" id="usphon" placeholder="请输入收货人手机">
                    </div>
                </div>
                <div class="clearfix ma_t10">
                    <span class="f_l ma_t10">下单时间：</span>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200 laydate-icon" id="atime" placeholder="">
                    </div>
                    <span class="f_l ma_t10 ma_l20">至</span>
                    <div class="form-group f_l ma_l20">
                        <input type="text" class="form-control wn200 laydate-icon" id="btime" placeholder="">
                    </div>
                    <button onclick="getOrders(1)" type="" class="btn f_l btn-primary ma_l20">查询</button>
                </div>
                <input type="hidden" id="astatus" value="" >
                <div class="goods">
                    <table class="table table-bordered ma_t10">
                        <thead>
                            <tr>
                                <th>订单号</th>
                                <th>下单时间</th>
                                <th>收货人</th>
                                <th>总金额</th>
                                <th>渠道名称</th>
                                <th>订单状态</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="pagetable">
                            
                        </tbody>
                    </table>
                    <div id="page1">
                        
                    </div>
                </div> 
            </div>
        </div>      
    </div>
<!--/wrap-->
          
<br><br><br><br>

</div>
<script type="text/javascript">
$('.wrap_l h2').click(function(){
    if($(this).find('i').hasClass('icon-angle-up')){
        $(this).find('i').removeClass('icon-angle-up').addClass('icon-angle-down');
        $(this).next().hide();
    }else{
        $(this).find('i').addClass('icon-angle-up').removeClass('icon-angle-down');
        $(this).next().show();
    }
});

var start = {
	    elem: '#atime',
	    format: 'YYYY-MM-DD hh:mm:ss',
	    istime: true,
	    istoday: true,
	};
	var end = {
	    elem: '#btime',
	    format: 'YYYY-MM-DD hh:mm:ss',
	    istime: true,
	    istoday: false,
	};
	laydate(start);
	laydate(end);
</script>
</body>
</html>