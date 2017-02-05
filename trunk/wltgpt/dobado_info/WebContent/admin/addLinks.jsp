<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>
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
		 }else{
			 layer.msg("登录超时！");
			window.location.href="login.jsp";
		 }
	});
	function save(){
		var navigation_name=$("#navigation_name").val();
		var navigation_url=$("#navigation_url").val();
		if(navigation_name == null || navigation_name == "" || navigation_url == null || navigation_url == ""){
			layer.msg("信息填写完整！");
			return;
		}
		var status=$('input[name=isShows]:checked').val();
		var param ={
				"menu.navigation_name" : navigation_name,
				"menu.navigation_url" : navigation_url,
				"menu.status" : status,
				"menu.type" : 2
		};
		$.ajax({
			url : "saveDaohang",
			type : 'POST',
			dataType : 'JSON',
			data : param,
			async:false,
			success : function(data) {
				if(data){
					alert("保存成功!");
					window.location.href="system.jsp";
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
            <div class="f_r">欢迎 <span id="sp"></span></div>
        </div>
        <div class="header">
            <nav>
                <ul>
                    <li><a href="">管理首页</a></li>
                    <li><a href="wenz.jsp" >文章管理</a></li>
                    <li><a href="advert.jsp">广告管理</a></li>
                    <li><a href="">用户管理</a></li>
                    <li><a href="">评论管理</a></li>
                    <li><a href="system.jsp" class="choice">系统管理</a></li>
                </ul>
            </nav>
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->


<!--wrap-->
    <div class="wrap_bg thewidth100">    
        <div class="wrap clearfix bghui">

            <div class="wrap_l">
            <h2>系统管理</h2>
                <ul>
                   <li><a href="system.jsp">友情链接</a></li>
                    <li><a href="" class="choice">添加链接</a></li>
                    <li><a href="nva.jsp">导航列表</a></li>
                    
                </ul>
            </div>
            <div class="wrap_r">
                <div class="clearfix SearchOperation">
                    <h4>添加链接</h4>
                </div>
                <div class="modify">
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td width="20%">链接名称：</td>
                                <td><input type="text" class="form-control f_l" id="navigation_name" placeholder=""></td>
                            </tr>
                            <tr>
                                <td>链接地址：</td>
                                <td><input type="text" class="form-control f_l" id="navigation_url" placeholder=""></td>
                            </tr>
                            <tr>
                                <td>是否显示：</td>
                                <td>
                                    <label class="f_l"><input type="radio" name="isShows" value="1">是</label>
                                    <label class="ma_l20 f_l"><input type="radio" name="isShows" value="0">否</label>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td >
                                    <button type="submit" onclick="save()"  class="btn btn-default f_l">确定</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br><br><br>
                </div> 
            </div>
        </div>    
    </div>
<!--/wrap-->


<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_bg thewidth100">
        <div class="footer">
        
        
        
        </div>
    </div>
<!--/footer-->

</div>


</body>
</html>