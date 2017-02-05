<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = "http://jiu.dobado.cn/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="酒,酒业,白酒,葡萄酒,啤酒,果酒,酱香酒,浓香酒,米香酒,贵香坊,酒业资讯,多拍多酒业" /> 
<meta name="description" content="多拍多酒业频道，以互联网+的媒体视野，权威、深刻的独立视角，全景呈现酒类舆情走向、市场趋势、产业转型、业态创新、消费价值，融合酒业新生态，创领酒道新主流。多拍多酒业，让饮酒更更快乐。" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>多拍多酒业网络推广平台</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/index.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
$(function(){
	bannerData();
});
function bannerData(){
	$.ajax({
		url : "indexData",
		type : 'POST',
		dataType : 'JSON',
		async:false,
		success : function(data) {
			//var datas=JSON.parse(data);
			console.log(data);
			
			//导航
			var htms='';
			var link='';
			$.each(data.navigation,function(i,obj){
				if(obj.type == 1){
					var c_bai=(i==0)?c_bai='c_bai':c_bai='';
					if(obj.navigation_name == '商城'){
						htms +='<a class="'+c_bai+'" href="'+obj.navigation_url+'" target="view_window" >'+obj.navigation_name+'</a> ';
					}else{
						htms +='<a class="'+c_bai+'" href="'+obj.navigation_url+'"  target="centerFrame">'+obj.navigation_name+'</a> ';
					}
				}else if(obj.type == 2){
					link +='<a href="'+obj.navigation_url+'"  target="view_window" >'+obj.navigation_name+'</a>';
				}
			});
			
			$("#link").html(link);
			$("#navigation").html(htms);
			$("#navigation a").click(function(){
				$('#navigation a').removeClass('c_bai');
				$(this).addClass('c_bai');
			})
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
<div class="header_bg b_hui clearfix thewidth100"> 
        <div class="header thewidth clearfix">
          <div class="f_l">欢迎来到 多拍多网络推广平台</div>
         <!--  <div class="f_r">
            <a href="/register">注册</a>
            |
            <a href="/login">登录</a>
          </div> -->
        </div>
    </div>
    <div class="header_login clearfix thewidth100"> 
        <div class="header thewidth">
            <div class="login clearfix ">
                <a href="index.html"><img src="images/login/login_03.png" alt=""></a>
                <p class="c_lan">多拍多酒业<span class="b_lan c_bai">网络推广平台</span></p>
            </div>
            <div class="f_r">
              <input type="text" name="" value="" placeholder="请输入名酒/资讯关键词">
              <span class="b_lan"><img src="images/index/sou.png" alt=""></span>
            </div>
        
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->
<nav class="nav_bg thewidth100 b_lan">    
  <div id="navigation" class="nav thewidth">
    
  </div>  
  
</nav>

<!--wrap-->
<div >
 <iframe src="view/index_1.jsp" scrolling="no" frameborder=0
			style="width: 100%; height: 800px;" name="centerFrame" id="centerFrame"></iframe>
</div>

<!--/wrap-->



<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_bg thewidth100 mt_40">
        <div class="footer thewidth clearfix">
          <div class="footer_l">
            <h4>友情链接</h4>
            <div id="link" class="clearfix">
              <a href="javascript:;">中国酒业协会</a>
              <a href="javascript:;">中国酒类流通协会</a>
              <a href="javascript:;">华夏酒报</a>
              <a href="javascript:;">新华食品</a>
              <a href="javascript:;">意酒网</a>
              <a href="javascript:;">佳酿网</a>
              <a href="javascript:;">酒媒网</a>
              <a href="javascript:;">90天使汇</a>
              <a href="javascript:;">凤凰周刊</a>
              <a href="javascript:;">路透</a>
              <a href="javascript:;">凤凰金融</a>
              <a href="javascript:;">全景网</a>
              <a href="javascript:;">华夏酒报</a>
              <a href="javascript:;">新华食品</a>
              <a href="javascript:;">意酒网</a>
            </div>
          </div>
          <div class="footer_z">
            <h4>优秀栏目</h4>

            <div class="clearfix">
              <ul>
                <li><a href="javascript:;">名酒库</a></li>
                <li><a href="javascript:;">酒业资讯</a></li>
                <li><a href="javascript:;">酒厂动态</a></li>
                <li><a href="javascript:;">餐厅美食</a></li>
              </ul>
              <ul>
                <li><a href="javascript:;">酒文化</a></li>
                <li><a href="javascript:;">酒旅游</a></li>
                <li><a href="javascript:;">酒趣事</a></li>
                <li><a href="javascript:;">品鉴</a></li>
              </ul>
              <ul>
                <li><a href="javascript:;">播客</a></li>
                <li><a href="javascript:;">活动</a></li>
                <li><a href="javascript:;">专区</a></li>
                <li><a href="javascript:;">商城</a></li>
              </ul>
            </div>
          </div>
          <div class="footer_r">
            <h4><img src="images/login/login_03.png" alt=""> 多拍多网络推广平台</h4>
            <div class="clearfix">
              <p>站点：jiu.dobado.cn</p>
              <p>邮箱：qinghaoshang@digirun.cn</p>
              <p>电话：17751046080</p>
              <p>地址：南京市雨花台区软件大道168号润和软件园</p>
            </div>
            <img style="width: 150px; margin-top: -29px;margin-right: -52px;" src="images/public/Newpage_28.jpg" alt="">
          </div>
        </div>
    </div>
<!--/footer-->

</div>
</body>
<script>
  
</script>
</html>