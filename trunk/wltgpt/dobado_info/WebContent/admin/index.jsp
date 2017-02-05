<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台首页</title>
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
</head>

<body>

<input type="hidden" id="cd" value="${admin.admin }" />
<input type="hidden" id="role" value="${admin.roleId }" />
<div id="container" class="thewidth100">
<script type="text/javascript">
$(function(){
	 var name=$("#cd").val();
	 var role=$("#role").val();
	 $.cookie('name', name);
	 $.cookie('role', role);
	
	 if($.cookie('name') != null && $.cookie('name') != ""){
		 $("#sp").html( $.cookie('name'));
	 }else{
		window.location.href="login.jsp";
	 }
	
});

</script>
<!--header-->
    <div class="header_bg clearfix thewidth100"> 
        <div class="header_top clearfix">
            <div class="f_l">logo 多拍多资讯站后台管理</div>
            <div class="f_r">欢迎<span id="sp"></span> </div>
        </div>
        <div class="header">
            <nav>
                <ul>
                    <li><a href="index.jsp"  class="choice">管理首页</a></li>
                    <li><a href="wenz.jsp" >文章管理</a></li>
                    <li><a href="advert.jsp">广告管理</a></li>
                    <li><a href="">用户管理</a></li>
                    <li><a href="">评论管理</a></li>
                    <li><a href="system.jsp">系统管理</a></li>
                </ul>
            </nav>
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->




<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_bg thewidth100">
        <div class="footer">
        
        
        
        </div>
    </div>
<!--/footer-->

</div>


</body>
</html>