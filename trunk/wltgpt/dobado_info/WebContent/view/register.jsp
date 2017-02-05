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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/son.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="../js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/public/layer/layer.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>

<body>

<div id="container" class="thewidth100">

<!--header-->
    <div class="header_login clearfix thewidth100"> 
        <div class="header thewidth">
            <div class="login clearfix ">
                <a href="/"><img src="images/login/login_03.png" alt=""></a>
                <p class="c_lan">多拍多酒业<span class="b_lan c_bai">资讯站</span></p>
            </div>
       
        
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->


<!--wrap-->
    <div class="wrap_login thewidth100 b_hui register">    
        <div class="wrap thewidth clearfix">
        
        <div class="f_l">
            <img src="images/login/login_11.jpg" alt="">
        </div>
        <div class="f_r">
            <div>
                <h3 class="c_lan">注册</h3>
                   <input type="text" name="" id="phone" value="" placeholder="请输入手机号码">
                   <div class="dxyz">
                     <input type="password" name="" value="" id="dxyz" placeholder="短信验证码">
                     <span class="c_bai b_lan" onclick="jishi()">
                       发送验证码
                     </span>
                   </div>
                   <input type="password" name="" id="password" value="" placeholder="请输入登录密码">
                   <input type="password" name="" value="" id="passwords" placeholder="请重新输入登录密码">
                   <div class="clearfix yzm" >
                      <input type="text" name="" id="yzmval" value="" placeholder="验证码">
                      <span  id="yzm" onclick="yzm()"></span>
                   </div>
                   <div class="clearfix xuan c_lan">
                       <label class="f_l" onclick="a()">
                            <input id="yuedu" type="checkbox" name="" value="" checked> 
                            <span>我已阅读并同意《多拍多商城用户注册协议》</span>   
                       </label>
                       
                   </div>
                   <input class="b_hei c_bai btn-primary" onclick="submit()" type="submit" name="" value="注册">
                   <p class="a_c ma_b20">已注册 直接 <a href="/login" class="c_lan">登录</a></p>
            </div>
        </div>
        
        </div>    
    </div>
<!--/wrap-->


<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_login thewidth100">
        <div class="footer thewidth a_c">
            <a href="javascript:;">多拍多酒咨询</a>
            <span>|</span>
            <a href="javascript:;">多拍多商城</a>
            <span>|</span>
            <a href="javascript:;">多拍多酒金所</a>
        </div>
    </div>
<!--/footer-->

</div>

<script>
yzm();

function submit(){
var sMobile=$('#phone').val();
var sMobiles=/^1[3|4|5|8][0-9]\d{4,8}$/;
var dxyz=$('#dxyz').val();
var password=$('#password').val();
var passwords=$('#passwords').val();
var yzmval=$('#yzmval').val();
var yzm=new RegExp($('#yzm').text(),'i');
    if(sMobile==''){
      layer.msg('请输入手机号码！');
      $('#phone').focus();
      return;
    }else if(!(sMobiles.test(sMobile))){
      layer.msg('请有效的手机号码！');
      $('#phone').focus();
      return;
    }
    if(dxyz == ''){
      layer.msg('请输入短信验证码！');
      $('#dxyz').focus();
      return;
    }
    if(password==''){
      layer.msg('请输入密码！');
      $('#password').focus()
      return;
    }
    if(passwords==''){
      layer.msg('请重新输入登录密码！');
      $('#passwords').focus()
      return;
    }else if(passwords != password){
      layer.msg('登录密码不一致！');
      $('#passwords').focus()
      return;
    }
    if(yzmval==''){
      layer.msg('请输入验证码！');
      $('#yzmval').focus()
      return;
    }else if(!yzm.test(yzmval)){
      layer.msg('验证码错误！');
      $('#yzmval').focus()
      return;
    }

   
}

function a(){
    var sMobile=$('#phone').val();
    var sMobiles=/^1[3|4|5|8][0-9]\d{4,8}$/;
    var dxyz=$('#dxyz').val();
    var password=$('#password').val();
    var passwords=$('#passwords').val();
    var yzmval=$('#yzmval').val();
    var yzm=new RegExp($('#yzm').text(),'i');
    var yuedu=$('#yuedu');
    if((sMobiles.test(sMobile))&& password!='' && dxyz!='' && passwords == password && (yzm.test(yzmval)) && (yuedu.is(':checked'))){
         $('input[type="submit"]').addClass('b_lan');
    }else{
      $('input[type="submit"]').removeClass('b_lan');
    }  
}
$("input").keyup(function(){
    a();
});
</script>
</body>
</html>