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
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
$(function(){
	if($.cookie('name') != null && $.cookie('name') != ""){
		 $("#sp").html( $.cookie('name'));
		 getPack();
	 }else{
		window.location.href="login.jsp";
	 }
	
});
function getPack(){
	$.ajax({
		url : "getPackage",
		type : 'POST',
		async:false,
		success : function(data) {
			console.log(data);
			var htm='';
			$.each(data,function(i,obj){
				htm += '<label class="ma_l20 f_l"><input type="checkbox" name="package_ids" value="'+obj.package_id+'" >'+obj.package_name+'</label>';
			});
			$("#getpack").html(htm);
		},
		error : function() {
			alert("网络异常！");
		}
	});
}
function save(){
	var s=''; 
	$('input[name="package_ids"]:checked').each(function(){
		s+=$(this).val()+',';
	});
	var package_ids=s.substr(0,(s.length)-1);//规格
	var goods_name=$("#goods_name").val(); //商品名称
	var img_uri=$("#img_uri").val();//封面图
	var price=$("#price").val(); //价格
	var orprice=$("#orprice").val();
	var discount=$("#discount").val();
	var save=$("#save").val();
	var number=$("#number").val();
	var residual_time=$("#residual_time").val();
	var describe=$("#describe").val();
	var purchase_uri=$("#purchase_uri").val(); //购买流程图                                                                                                                                                     
	var introduce_uri=$("#file_imgs").html();  //产品介绍多图
	var customer=$("#customer").val();

//	var payment='';//付款方式
	var real_time_orderno=$('input[name="real_time_orderno"]:checked').val();//实时购买
	var real_time_position=$('input[name="real_time_position"]:checked').val(); //位置
	var real_time_ss=$('input[name="real_time_ss"]:checked').val(); //时隔
	var real_time_content=$('.real_time_content').html(); //实时内容
	var comment=$('input[name="comment"]:checked').val(); //是否评论
	var comment_ss =$('input[name="comment_ss"]:checked').val(); //评论时隔
	var comment_content=$('.comment_content').html(); //评论内容
	
	if(goods_name=='' || goods_name == null){layer.msg("请填写商品名称");return;}
	if(img_uri=='' || img_uri == null){layer.msg("请上传封面图");return;}
	if(!(/^[0-9]+(.[0-9]{1,2})?$/).test(price)){ layer.msg("请输入正确价格!"); return;} 
	if(!/^[0-9]+(.[0-9]{1,2})?$/.test(orprice)){ layer.msg("请输入正确原价!"); return;} 
	if(!/^[0-9]+(.[0-9]{1,2})?$/.test(discount)){ layer.msg("请输入正确折扣!"); return;} 
	if(!/^[0-9]+(.[0-9]{1,2})?$/.test(save)){ layer.msg("请输入正确节省!"); return;} 
	if(!/^[0-9]+([0-9]{0})?$/.test(number)){ layer.msg("请输入正确购买基数!"); return;} 
	if(residual_time=='' || residual_time == null){layer.msg("请填写剩余时间");return;}
	if(describe=='' || describe == null){layer.msg("请填写抢购描述");return;}
	if(purchase_uri=='' || purchase_uri == null){layer.msg("请上传购买流程图");return;}
	if(introduce_uri=='' || introduce_uri == null){layer.msg("请上传产品介绍图");return;}
	if(customer=='' || customer == null){layer.msg("请填写客服服务");return;}
	
	if(real_time_orderno==0){
		if(real_time_content=='' || real_time_content == null){layer.msg("请填写实时购买信息");return;}
	}
	if(comment==0){
		if(comment_content=='' || comment_content == null){layer.msg("请填写评论信息");return;}
	}
	
	var param ={
			"menu.goods_name" : goods_name ,
			"menu.img_uri" : img_uri ,
			"menu.price" : price ,
			"menu.orprice" : orprice ,
			"menu.discount" : discount ,
			"menu.save" : save ,
			"menu.number" : number ,
			"menu.residual_time" : residual_time ,
			"menu.describe" : describe ,
			"menu.purchase_uri" : purchase_uri ,
			"menu.introduce_uri" : introduce_uri ,
			"menu.customer" : customer ,
			"menu.package_ids" : package_ids ,
//			"menu.payment" : payment ,
			"menu.real_time_orderno" : real_time_orderno ,
			"menu.real_time_position" : real_time_position ,
			"menu.real_time_ss" : real_time_ss ,
			"menu.real_time_content" : real_time_content ,
			"menu.comment" : comment ,
			"menu.comment_ss" : comment_ss ,
			"menu.comment_content" : comment_content ,
	};
	//console.log(param);
	$.ajax({
		url : "saveGoods",
		type : 'POST',
		dataType : 'JSON',
		data : param,
		async:false,
		success : function(data) {
			if(data){
				alert("保存成功!");
			}else{
				alert("失败!");
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
            <div class="f_r">欢迎 X X X</div>
        </div>
        <div class="header">
            <nav>
                <ul>
                   <li><a class="choice">广告业务</a></li>
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
                <h2>商品管理 <i class="f_r icon-angle-up"></i></h2>
                <ul class="">
                    <li><a href="goods.jsp">广告商品列表</a></li>
                    <li><a class="current" href="addGoods.jsp">广告商品添加</a></li>
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
                <div class="clearfix SearchOperation">
                    <h4>广告商品添加</h4>
                </div>
                <div class="modify">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <td class="wb15">商品名称:</td>
                                    <td><input type="text" class="form-control f_l" id="goods_name" placeholder="请输入商品名称"></td>
                                </tr>
                                <tr>
                                    <td>商品封面图:</td>
                                    <td>
                                        <div class="file clearfix">
                                            <div>
                                                <img src="" alt="" id="img_uris">
                                            </div>
                                            <label class="f_l">
                                                <input id="imgfile" type="file" name="imgfile" value="" onchange="btnUpload('imgfile','img_uri')" placeholder="">+
                                            </label>
                                            <input type="hidden"  style="display: none;" id="img_uri" name="img_uri" >
                                            <div class="f_l"><span class="red">尺寸：640*640</span></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>商品价格:</td>
                                    <td><input type="text" class="form-control f_l" id="price" placeholder="请填写商品价格"></td>
                                </tr>
                                <tr>
                                    <td>原价:</td>
                                    <td><input type="text" class="form-control f_l" id="orprice" placeholder="请填写原价"></td>
                                </tr>
                                <tr>
                                    <td>折扣:</td>
                                    <td><input type="text" class="form-control f_l" id="discount" placeholder="请填写折扣"></td>
                                </tr>
                                <tr>
                                    <td>节省:</td>
                                    <td><input type="text" class="form-control f_l" id="save" placeholder="请填写节省"></td>
                                </tr>
                                <tr>
                                    <td>已购人数:</td>
                                    <td><input type="text" class="form-control f_l" id="number" placeholder="请输入购买基数"></td>
                                </tr>
                                <tr>
                                    <td>剩余时间:</td>
                                    <td><input type="text" class="form-control f_l" id="residual_time" placeholder="请填写剩余时间"></td>
                                </tr>
                                <tr>
                                    <td>抢购描述:</td>
                                    <td><textarea class="form-control" rows="5" name="" id="describe" placeholder="请输入抢购描述" ></textarea></td>
                                </tr>
                                <tr>
                                    <td>购买流程:</td>
                                    <td>
                                        <div class="file clearfix">
                                            <div>
                                                <img src="" alt="" id="purchase_uris" >
                                            </div>
                                            <label class="f_l">
                                                <input  type="file" name="purchase" id="purchase" value="" onchange="btnUpload('purchase','purchase_uri')" placeholder="">+
                                            </label>
                                            <div class="f_l"><span class="red">尺寸：宽为640高不限</span></div>
                                            <input type="hidden" style="display: none;" id="purchase_uri" name="purchase_uri" >
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>产品简介:</td>
                                    <td>
                                        <div class="file clearfix" id="introduce_uri">
                                            <div id="file_imgs">
                                            </div>
                                            <label class="f_l">
                                                <input  type="file" name="files" id="files" value="" onchange="btnUpload('files','files_img')" placeholder="">+
                                            </label>
                                            <input type="hidden" style="display: none;" id="files_img" name="files_img" >
                                            <div class="f_l"><span class="red">尺寸：宽为640高不限</span></div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>客户服务:</td>
                                    <td><textarea id="customer" class="form-control" rows="5" name="" placeholder="请输入客户服务" ></textarea></td>
                                </tr>
                                <tr>
                                    <td>商品规格:</td>
                                    <td align="left ">
                                    <div class="wb80 f_l" id="getpack">
                                    </div>
                                    <button id="tianjias" class="btn ma_l20">添加</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>付款方式:</td>
                                    <td>
                                        <label class="f_l"><input type="radio" name="" value="" checked>货到付款</label>
                                        <label class="ma_l20 f_l"><input type="radio" name="" value="" disabled>在线支付（禁用）</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>实时购买:</td>
                                    <td>
                                        <label class="f_l" onclick="dakai(this)"><input type="radio" name="real_time_orderno" value="0">启用</label>
                                        <label class="ma_l20 f_l" onclick="dakais(this)"><input type="radio" name="real_time_orderno" value="1" checked>禁用</label>
                                        <div class="thewidth100 clearfix peizhis"style="margin-top: 30px;border: 1px solid #ddd;display:none;">
                                            <table class="table">
                                                <tbody>
                                                    <tr>
                                                        <td class="wb20">显示位置:</td>
                                                        <td>
                                                            <label class="f_l"><input type="radio" name="real_time_position" value="1" checked>顶部</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="real_time_position" value="2">底部</label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>显示间隔:</td>
                                                        <td>
                                                            <label class="f_l"><input type="radio" name="real_time_ss" value="1" checked>1s</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="real_time_ss" value="2">2s</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="real_time_ss" value="3">3s</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="real_time_ss" value="4">4s</label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>配置文字:</td>
                                                        <td align="left">
                                                            <div id="peizhi" class="real_time_content" style=" width: 500px;">
                                                                <div>刘**（手机号：138****4309）3分钟前下了一笔订单  <i  onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                                                            </div>
                                                            <button id="tianjia1" class="btn">添加</button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div class="clearfix pa1">
                                                <button class="btn f_r"onclick="guanbi(this)">确定</button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>交易评论:</td>
                                    <td>
                                        <label class="f_l" onclick="dakai(this)"><input type="radio" name="comment" value="0">启用</label>
                                        <label class="ma_l20 f_l" onclick="dakais(this)"><input type="radio" name="comment" value="1" checked>禁用</label>
                                        <div class="thewidth100 clearfix peizhis"style="margin-top: 30px;border: 1px solid #ddd;display:none;">
                                            <table class="table">
                                                <tbody>
                                                    <tr>
                                                        <td class="wb20">显示间隔:</td>
                                                        <td>
                                                            <label class="f_l"><input type="radio" name="comment_ss" value="1" checked>1s</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="comment_ss" value="2">2s</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="comment_ss" value="3">3s</label>
                                                            <label class="ma_l20 f_l"><input type="radio" name="comment_ss" value="4">4s</label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>配置文字:</td>
                                                        <td align="left">
                                                            <div id="peizhi" class="comment_content" style=" width: 500px;">
                                                            	<div><span>2016-7-25 山西的3 131xxxx1626</span>产品不错，值得购买  <i  onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                                                            </div>
                                                            <button id="tianjia" class="btn">添加</button>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div class="clearfix pa1">
                                                <button class="btn f_r" onclick="guanbi(this)">确定</button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                        <button type="submit" onclick="save()" class="btn btn-default f_l">保存</button>
                                        <button type="reset" class="btn btn-default ma_l20 f_l">预览</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                </div> 
            </div>
        </div>      
    </div>
<!--/wrap-->

<br><br><br><br>
</div>

<div id="aids">
<input  type="text" name="files" id="aids1" value=""  placeholder="">
<input  type="text" name="files" id="aids2" value=""  placeholder="">
<div class="clearfix">
<button class="f_l btn" onclick="queding()">确定</button>
<button class="f_r btn" onclick="layer.closeAll()">取消</button>
</div>
</div>

<script>

function dakai(a){
$(a).siblings('.peizhis').show();
}
function dakais(a){
$(a).siblings('.peizhis').hide();
}
function guanbi(a){
$(a).parents('.peizhis').hide();
}

$('.table').find('#tianjias').click(function(){
    var aaa=$(this)
    layer.prompt(function(val){
    	
    	$.ajax({
    		url : "savePackage",
    		type : 'POST',
    		dataType : 'JSON',
    		data : {
    			"menu.package_name":val
    		},
    		async:false,
    		success : function(data) {
    			if(data){
    				layer.msg("保存成功!");
    				getPack();
    				layer.closeAll();
    			}else{
    				alert("失败!");
    			}
    		},
    		error : function() {
    			alert("网络异常！");
    		}
    	});
    });
})
$('.table').find('#tianjia1').click(function(){
    layer.prompt({title:'test'},function(val){
        $('.real_time_content').append('<div>'+val+'<i onclick="shancu(this)" class="f_r icon-remove"></i>');
        layer.closeAll();
    }); 
})
function queding(){
	var aids1=$('#aids1').val()+'';
	var aids2=$('#aids2').val()+'';
	var aids='<div><span>'+aids1+'</span>'+aids2+'<i  onclick="shancu(this)" class="f_r  icon-remove"></i></div>';
	$('.comment_content').append(aids);
	layer.closeAll();
}
$('.table').find('#tianjia').click(function(){
    layer.open({
    	  type: 1,
    	  title: '添加评论',
    	  closeBtn: 0,
    	  area: '350px',
    	  content: $('#aids')
    	});
})

function shancu(a){
    var aaa=$(a);
    layer.confirm('是否删除', {
        btn: ['确定','取消'] //按钮
    }, function(){
       aaa.parent().remove();
       layer.closeAll();
    }, function(){
        layer.closeAll();
    });
}

</script>

<!-- 图片上传 -->
<script type="text/javascript">
//参数（来源id，展示id）
function btnUpload(fileid,showid){  
	var elementIds=[showid]; //flag为id、name属性名
	 $.ajaxFileUpload({
          url: '/admin/upload', 
          type: 'post',
          secureuri: false, //
          fileElementId: fileid, // 
          dataType: 'application/json', //
          elementIds: elementIds, //
          success: function(data){ 
        	 //console.log(data);
        	 var dat = JSON.parse(data.substring(5,data.indexOf("</pre>")));
        	 if(dat.data){
        		 //alert("成功");
	          	 $('#'+showid).val(dat.re);
	          	 //alert($('#'+showid).val());
	          	aaa(showid);
        	 }else{
        		 alert("失败");
        	 }
          }
	})
} 
function aaa(a){
	if(a=='files_img'){
		var htm=$("#file_imgs").html();
		htm +='<img src="/'+$("#"+a).val()+'" alt="">'
		$("#file_imgs").html(htm);
	}else{
		 $('#'+a+'s').attr('src',"/"+$("#"+a).val()).show();
		 $('#'+a+'s').parents('.file').find('label').hide();
	}
	
}
</script>
</body>
</html>