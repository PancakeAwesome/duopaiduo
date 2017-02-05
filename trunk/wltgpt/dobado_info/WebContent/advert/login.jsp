<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="css/from.css"> -->
<title>登录</title>
</head>
<script src="/jquery-easyui-1.3.3/jquery.min.js"> </script>
<style>
.clearfix:after {
  content: "\0020";
  display: block;
  height: 0;
  clear: both;
}
.clearfix {
  _zoom: 1;
}
* + html .clearfix {
  overflow: auto;
}
/* E Reset */
* html,
* html body {
  background-image: url(about:blank);
  background-attachment: fixed;
}
body {
  line-height: 20px;
  font: 14px 'Microsoft YaHei', "hiragino sans gb", '黑体', '宋体', Arial, Lucida Grande, Tahoma, sans-serif;
  background: #FFF;
  margin:0;
}
* {
  font-family: 'Microsoft YaHei', "hiragino sans gb", '黑体', '宋体', Arial, Lucida Grande, Tahoma, sans-serif;
}

.login_top1{background-color: #999999;  margin-bottom: 110px;}
.login_top{height: 110px; width: 1000px; margin: 0 auto;}
.login_top img{ float: left;   margin: 12px 10px 0 35px;}
.login_top h1{float: left;font-size: 35px;margin-top: 30px;}
.signin{ width: 1000px; margin: 0 auto;}
.signina{ width: 620px; margin-left: 30px;float: left; border: 1px solid #ddd;}
.signina img{
    margin: 45px 85px;
    width: 450px;
    height: 215px;
}
.signinb{ 
    width: 270px;
    float: right;
    margin-right:25px; 
    background-color: #f3f3f3; 
    padding: 15px;
   
}
.divss {padding-bottom: 30px; margin-bottom: 110px; }
.divs input{ width: 88%; height: 35px;padding: 0 10px;}
.divs table{ width: 100%;}
.divs table td{height: 50px;}
.divs table td strong{font-size: 20px;}
.divs .yzm>input{width: 90px; float: left;}
.divs .yzm div{
    float: left;
    margin-left: 5px;
    width: 90px;
    background-color: #FAF7F0;
    text-align: center;
    font-style: oblique;
    height: 30px;
    border: 1px solid #A9A9A9;
    font-size: 20px;
    padding: 3px;
    color: #000;
}

.divs .yzm span{position: relative;top: 8px;margin-left: 5px;color: #fd2e3c
}
.divs button{
    width: 100%;
    color: #fff; 
    background-color: #8fc30e;
    border: 0; 
    padding: 13px; 
    border-radius: 5px;
    margin-top: 10px;
}
footer{ height: 60px;background-color: #f3f3f3; border-top: 1px #ddd solid;}
footer p{ margin:20px 0 0; text-align: center;}
</style>
<script type="text/javascript">
$(function(){
	var errors = $("#errors").val();
	if(errors != null && errors != ""){
		alert(errors);
	}
	createCode();
});
</script>
<body>
	<input type="hidden" id="errors" value=${error } >
	<div class="login_top1">
		<div class="login_top">
			<!-- <img src="images/logo.jpg" alt=""> -->
			<h1>后台管理</h1>
		</div>
	</div>
	<div class="clearfix signin">

    <div class="clearfix divss">
            <div class="signina">
                <!-- <img src="images/denlu_03.png" alt=""> -->
            </div>
            <div class="signinb">
             	<form id="adminlogin_x" method="post" action="login" >
                    <div class="divs">
                        <table>
                            <tr>
                                <td>
                                    <strong>管理员登录</strong>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" placeholder=" 用户名/工号" name="userName"  id="userName" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="password"  placeholder=" 密码" name="password" id="password" />
                                </td>
                            </tr>
                            <tr>
                                <td class="yzm clearfix">
                                    <input type="text" class="form-css" placeholder="验证码" id="inputCode">
                                    <a onclick="createCode()"> 
                                        <div class="code" id="checkCode">
                                            
                                        </div>
                                        <span>刷新</span>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button onclick="javascript:login();return false;" >立即登录</button>
                                </td>
                            </tr>
                        </table>
                    </div>
               </form>
            </div>
    </div>
</div>
<footer>
    <!-- <p>泉州伍饭宝企业管理有限公司 | 闽ICP证34353433号</p> -->
</footer>
</body>
<script type="text/javascript">
var code;
	
function login(){
		
		var userName=$("#userName").val();
		var password=$("#password").val();
		var inputCode = $("#inputCode").val();
		
		if(userName==null||userName==""){
			alert("用户名不能为空！");
			return;
		}
		if(password==null||password==""){
			alert("密码不能为空！");
			return;
		}
		if(inputCode.length <= 0){
	        alert("请输入验证码！");
	        return;
		}else if(inputCode.toUpperCase() != code.toUpperCase()){
	        alert("验证码输入有误！");
	        createCode();
	        return;
	    }else{
	    	$("#adminlogin_x").submit();
	    }
	}
function createCode() {
	    code = "";
	    var codeLength = 4; //验证码的长度
	    var checkCode = document.getElementById("checkCode");
	    var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c',
	            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
	            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
	            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
	    for (var i = 0; i < codeLength; i++) {
	        var charNum = Math.floor(Math.random() * 52);
	        code += codeChars[charNum];
	    }
	    if (checkCode) {
	        checkCode.className = "code";
	        checkCode.innerHTML = code;
	    }
	}
</script>

	
	

</html> -->