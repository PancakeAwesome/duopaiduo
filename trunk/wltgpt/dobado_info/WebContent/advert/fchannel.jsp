<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>广告业务</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/public/font-awesome.min.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/public/laydate/laydate.js"></script>
<script type="text/javascript" src="js/public/laypage/laypage.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	
});
function info(){
	$.ajax({
		url : "pageGoods",
		type : 'POST',
		data :{
			page:page,
			size:10
		},
		async:false,
		success : function(data) {
			console.log(data);
			var htm='';
			$.each(data.list,function(i,obj){
				htm += '<tr>';
				
			});
			$("#pagetable").html(htm);
			tanchu();
			laypage({
			    cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象,
			    pages: (data.totalPage), //总页数
			    curr: page, //当前页
			    jump: function(obj, first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			        	getGoods(obj.curr)
			        }
			    }
			});
		},
		error : function() {
			alert("网络异常！");
		}
	}); 
};
</script>
</head>

<body>

<div id="container" class="thewidth100">

<!--header-->
    <div class="header_bg clearfix thewidth100"> 
        <div class="header_top clearfix">
            <div class="f_l">logo 多拍多资讯站后台管理</div>
            <div class="f_r">欢迎 X X X</div>
        </div>
        <div class="header">
            <nav>
                <ul>
                    <li><a href="javascript:;">广告管理</a></li>
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
                <ul class="">
                    <li><a class="current" href="javascript:;">渠道列表</a></li>
                    <li><a href="javascript:;">已结束渠道列表</a></li>
                </ul>
                <h2>商品管理 <i class="f_r icon-angle-up"></i></h2>
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
                <div class="clearfix SearchOperation bo_bb">
                    <h4>渠道列表 <a href="javascript:tianjia();" class="f_r btn btn-primary ma-t10">添加渠道</a></h4>
                </div>
                <div class="clearfix ma_t10">
                    <div class="form-group f_l">
                        <input type="text" class="form-control wn200" id="" placeholder="请输入渠道ID">
                    </div>
                    <button type="" class="btn f_l btn-primary ma_l20">查询</button>
                </div>
                <div class="goods">
                    <table class="table table-bordered ma_t10">
                        <thead>
                            <tr>
                                <th>渠道ID</th>
                                <th>父渠道名称</th>
                                <th>总订单量</th>
                                <th>订单总额</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>百度</td>
                                <td>336</td>
                                <td>29538.00</td>
                                <td>
                                    <a href="order_list.html" class="btn">订单</a>
                                    <button class="btn xq">编辑</button>
                                    <a href="channel_list_01.html" class="btn">查看子渠道</a>
                                </td>
                            </tr>
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
<div id="tianjia" class="none">
    <div class="clearfix pa1">
        <span class="f_l ma_t10 ">渠道类型：</span>
        <div class="form-group f_l ma_l20">
            <select class="form-control wn300 ">
                <option>父级渠道</option>
                <option>子级渠道</option>
            </select>
        </div>
    </div>
    <div class="clearfix bo_bb pa1">
        <span class="f_l ma_t10">渠道名称：</span>
        <div class="form-group f_l ma_l20">
            <input type="text" class="form-control " id="" placeholder="">
        </div>
    </div>
    <div class="pa1 clearfix">
        <button class="btn f_r ma_l20" onclick="layer.closeAll();">取消</button>
        <button type=""class="btn f_r">添加</button>
    </div>
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

laypage({
    cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象,
    pages: 11, //总页数
    skin: 'molv', //皮肤
    first: 1, //将首页显示为数字1,。若不显示，设置false即可
    last: 11, //将尾页显示为总页数。若不显示，设置false即可
    prev: '<', //若不显示，设置false即可
    next: '>' //若不显示，设置false即可
});
function tianjia(){
layer.open({
  type: 1,
  title: '渠道添加',
  area: '410px',
  content: $('#tianjia')
});
}
</script>
</body>
</html>