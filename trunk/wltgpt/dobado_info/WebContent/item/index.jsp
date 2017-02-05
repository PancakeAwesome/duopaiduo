<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String goodsId =request.getParameter("goodsId")==null?"1":request.getParameter("goodsId");
 	String fchannel_id = request.getParameter("fchannel_id")==null?"10":request.getParameter("fchannel_id");
	String zchannel_id = request.getParameter("zchannel_id")==null?"10":request.getParameter("zchannel_id");
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

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?b00791ff1c1174fe27bf3b281c72d595";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</head>
<script type="text/javascript">
$(function(){
	info();
    var time = new Date('2016-8-22 00:00:00').getTime();//月份是实际月份-1
    countDown(time,"#colockbox1");
});
function info(){
	var  goodsId = <%=goodsId %>;
	$.ajax({
		url : "getGoods",
		type : 'POST',
		dataType : 'JSON',
		data : {
			goodsId : goodsId
		},
		async:false,
		success : function(data) {
			console.log(data);
			$("#goods_name").html(data.goods_name);
			$("#img_uri").attr('src','/'+data.img_uri);
			$("#price").html(data.price);
			$("#orprice").html(data.orprice);
			$("#discount").html(data.discount);
			$("#save").html(data.save);
			$("#number").html(data.number+data.number_actual);
			$("#residual_time").val(data.residual_time);
			$("#introduce_uri").html(data.introduce_uri);
			$("#customer").html(data.customer);
			$("#comment_content").html(data.comment_content);
			$("#real_time_content").html(data.real_time_content);
			$("#real_time_content1").html(data.real_time_content);
		},
		error : function() {
			alert("网络异常！");
		}
	});
}
function setOrder(){
	if(!postcheck())
		return;
	var fchannel_id = <%=fchannel_id %>;
	var zchannel_id = <%=zchannel_id %>;
	var goodsId = <%=goodsId %>;
	var userName = $("#userName").val();
	var package_id = parseInt($("input[name='product']:checked").val());
	var address=$('select[name="province"]').val()+' ';
	address += $('select[name="city"]').val()+' ';
	address += $('select[name="area"]').val()+' ';
	address += $('input[name="address"]').val();
	var phone = $("#phone").val();
	var userRemark = $("#userRemark").val();
	var param ={
			"menu.fchannel_id" : fchannel_id ,
			"menu.zchannel_id" : zchannel_id ,
			"menu.goodsId" : goodsId ,
			"menu.userName" : userName ,
			"menu.package_id" : package_id ,
			"menu.address" : address ,
			"menu.phone" : phone ,
			"menu.userRemark" : userRemark 
	}
	$.ajax({
		url : "saveOrder",
		type : 'POST',
		dataType : 'JSON',
		data : param,
		async:false,
		success : function(data) {
			console.log(data);
			if(data !=0){
				window.location.href="success.jsp?orderno="+data;
			}else{
				
			}
		},
		error : function() {
			alert("网络异常！");
		}
	});
}

</script>
<body>

<div class="thewidth100">
    <div class="thewidth">
        <header id="header " class="ba_g">
             <h1><span id="goods_name"></span><span class="share" onclick="tanchu()" ></span></h1>
        </header>
        <!-- /header -->
        <div class="fudongs">
			<div class="fudong" >
			    <div id="real_time_content1" class="diqiehuans">
			        <div>张**（手机号：138****4309）12分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
			        <div>李**（手机号：138****4309）6分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
			        <div>王**（手机号：138****4309）9分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
			        <div>赵**（手机号：138****4309）3分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
			        <div>周**（手机号：138****4309）5分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
			        <div>刘**（手机号：138****4309）1分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
			    </div>        
			</div>
		</div>	
        <div class="img">
            <img id="img_uri" src="" alt="">
        </div>
        <div class="buy clearfix ba_g">
                <div class="row1 clearfix">
                    <strong>￥<span id="price"></span></strong>
                    <ol>
                        <li><p>节省</p>￥<span id="save"></span></li>
                        <li><p>折扣</p><span id="discount"></span></li>
                        <li><p>原价</p>￥<span id="orprice"></span></li>
                    </ol>
                </div>
                <div class="row2 clearfix">
                    <strong><span id="number"></span>人已购买</strong>
                    <div class="djs">
                       <div class="time-count">
                            <div class="colockbox" id="colockbox1"> 
                                <span class="day">0<samp>天</samp></span>
                                <span class="hour">00<samp>时</samp></span>
                                <span class="minute">00<samp>分</samp></span>
                                <span class="second">00<samp>秒</samp></span>
                            </div>
                        </div>
                    </div>          
                </div>
                <article class="des"></article>
                <a class="btn" href="#buy">立即购买</a>
        </div>
        
        <div id="introduce_uri" class="img">
            <img src="" alt="">
        </div>
		<h4>客户服务:</h4>
        <div class="kehui">
            <p id="customer">
               	 首批1000瓶，在线下单，热线订购，欲购从速。订购量大热线繁忙，会不能及时接通您的来电，请在线下单。厂家保障，货到付款，顺丰包邮。
            </p>
            <a  href="tel:15376446817">热线订购:15376446817</a>
            <a href="tencent://message/?uin=1156547082&Site=qq&Menu=yes">QQ咨询:1156547082</a>
        </div>
        <h4>客户评价：</h4>
        <div class="penlun">
            <div id="comment_content" class="diqiehuans">
                <div><span>2016-7-25 山西的1 131xxxx1626</span>产品不错，1值得购买  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div><span>2016-7-25 山西的2 131xxxx1626</span>产品不错，2值得购买  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div><span>2016-7-25 山西的3 131xxxx1626</span>产品不错，3值得购买  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div><span>2016-7-25 山西的4 131xxxx1626</span>产品不错，4值得购买  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div><span>2016-7-25 山西的5 131xxxx1626</span>产品不错，5值得购买  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div><span>2016-7-25 山西的6 131xxxx1626</span>产品不错，6值得购买  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
            </div>

        </div>
        <h4>订单信息：</h4>
        <a name="buy"></a> 
        <!--订单-->
        <div id="order" class="clearfix">

            <div class="warp">
                <form id="form" name="form" action="" method="post" onsubmit="return postcheck()">
                <input type="hidden" name="mun" value="1">
                <input type="hidden" name="orderid" id="orderid" value="">
                <div class="bdbox">
                    <label class="bdxx"><em>*</em>产品</label>
                    <div class="dxbox red" id="package_ids" >
                        <label><input type="radio" name="product"  id="a0" value="1" alt="398" checked onclick="pricea(this)">398元2瓶*500ml</label><br>
                    
                        <label><input type="radio" name="product" id="a1" value="2" alt="796"  onclick="pricea(this)">796元4瓶*500ml</label><br>
                    
                    
                    </div>
                </div>
        <!--附加属性b-->
         
        <!--附加属性e-->
                <div class="bdbox">
                    <label class="bdxx ma_t1"><em>*</em>姓名</label>
                    <div class="textbox">
                        <input id="userName" type="text" name="name">
                    </div>
                </div>
                <div class="bdbox">
                    <label class="bdxx ma_t1"><em>*</em>手机</label>
                    <div class="textbox">
                        <input id="phone" type="text" name="mob">
                    </div>
                </div>
                <div class="bdbox">
                    <label class="bdxx ma_t1"><em>*</em>地区</label>
                    <div class="xlbox">
                        <select name="province" class="dqxl"></select>
                        <select name="city" class="dqxl"><option value="">选城市</option></select>
                        <select name="area" class="dqxl"><option value="">选地区</option></select>
                    </div>
                </div>
                <div class="bdbox">
                    <label class="bdxx ma_t1"><em>*</em>地址</label>
                    <div class="textbox">
                        <input type="text" name="address">
                    </div>
                </div>
                <div class="bdbox">
                    <label class="bdxx ma_t1"><em>*</em>金额</label>
                    <div class="text3box">
                      
                        <input name="price" value="398" readonly  style="width:80px;"> 元 <span id="zfbyh"></span>
                    </div>
                </div>
                <div class="bdbox">
                    <label class="bdxx"><em>*</em>付款</label>
                    <div class="dxbox">
                        <input type="radio" checked="checked" >货到付款
                    </div>
                </div>

                <div class="bdbox">
                    <label class="bdxx">留言</label>
                    <div class="text2box">
                        <textarea id="userRemark" name="guest"></textarea>
                    </div>
                </div>
                  
                <div class="subbox">
                    <input type="button"  onclick="setOrder()" name="submit" value="立即提交订单">
                </div>
                </form>
            </div>
        </div>
        <!-- /订单填写 -->
        <!-- 购买信息 -->
        <div class="diqiehuan">
            <div id="real_time_content" class="diqiehuans">
                <div>张**（手机号：138****4309）8分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div>李**（手机号：138****4309）6分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div>王**（手机号：138****4309）9分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div>赵**（手机号：138****4309）3分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div>周**（手机号：138****4309）5分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
                <div>刘**（手机号：138****4309）1分钟前下了一笔订单  <i onclick="shancu(this)" class="f_r  icon-remove"></i></div>
            </div>        
        </div>
        <!-- /购买信息 -->
    </div>    
</div>


<nav>
  <ul class="Transverse">
     <li><a href="#buy"><strong>在线下单</strong></a></li> 
     <li><a class="wed" href="tel:15376446817"><strong>电话咨询</strong></a></li>  
     <li><a class="wed1" href="sms:15376446817"><strong>短信订购</strong></a></li>
  </ul>
</nav>

					
<script src="https://s95.cnzz.com/z_stat.php?id=1259980364&web_id=1259980364" language="JavaScript"></script>
</body>
<script type="text/javascript" src="js/scrollgress.js"></script>
<script>
$(function(){
    new PCAS("province","city","area");
    $('body>.thewidth100').scrollgress({
        success: function() {            
        console.log('Scrollgress has been initiated.');
        }
    });
    scollDown("diqiehuan",3000);
    scollDownd("penlun",2000);
    scollDowns("fudong",3000);
});
//平台、设备和操作系统 
var system = { 
    win: false, 
    mac: false, 
    xll: false, 
    ipad:false 
}; 
//检测平台 
var p = navigator.platform; 
system.win = p.indexOf("Win") == 0; 
system.mac = p.indexOf("Mac") == 0; 
system.x11 = (p == "X11") || (p.indexOf("Linux") == 0); 
system.ipad = (navigator.userAgent.match(/iPad/i) != null)?true:false; 
//跳转语句，如果是手机访问就自动跳转到wap.baidu.com页面 
if (system.win || system.mac || system.xll||system.ipad) { 
       $('.Transverse').css('display','none');
} else { 
       /*  alert('手机端'); */
} 
$(window).scroll(
	    function (){
	        var st = $(this).scrollTop();
	        if(st >= $('header').height()){
	            $('.fudongs .fudong').css({'position':'fixed','top':'0px'});
	        }else{
	             $('.fudongs .fudong').css({'position':'relative'});
	        }
	    });
function tanchu(){
	layer.open({
	  type: 1,
	  title: '分享',
	  area: ['80%','150px'],
	  content: $('.bdsharebuttonbox')
	});
	}

	</script> 
</html>
  
<div class="bdsharebuttonbox none" data-tag="share_1">
    <a href="javascript:;" onclick="layer.closeAll()" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间">QQ空间</a>
    <a href="javascript:;" onclick="layer.closeAll()" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博">新浪微博</a>
    <a href="javascript:;" onclick="layer.closeAll()" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博">腾讯微博</a>
    <a href="javascript:;" onclick="layer.closeAll()" class="bds_renren" data-cmd="renren" title="分享到人人网">人人网</a>
    <a href="javascript:;" onclick="layer.closeAll()" class="bds_weixin" data-cmd="weixin" title="分享到微信">微信</a>
</div>
<script>
window._bd_share_config = {
        common : {
            bdText : '桂香坊2瓶装', 
            bdDesc : '自定义分享摘要', 
            bdUrl : 'http://jiu.dobado.cn/item/index.jsp',   
            bdPic : 'http://jiu.dobado.cn/upload/2016-07-29/top.png'
        },
        share : [{
            "bdSize" : 16
        }]
    }
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
</script>

