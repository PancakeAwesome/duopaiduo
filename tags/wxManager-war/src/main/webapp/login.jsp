<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/from.css">
<title>用户登录</title>
</head>
<script src="js/jquery-1.10.2.min.js"></script>
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
<body>
	<div class="login_top1">
		<div class="login_top">
			<h1>江苏省厅出入境微信警务平台</h1>
		</div>
	</div>
	<div class="clearfix signin">

    <div class="clearfix divss">
            <div class="signina">
                <img src="images/denlu_03.png" alt="">
            </div>
            <div class="signinb">
                <form id="adminLogin">
                    <div class="divs">
                        <table>
                            <tr>
                                <td>
                                    <strong>管理员登录</strong>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" class="form-css" placeholder=" 用户名/工号"  id="username">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="password" class="form-css" placeholder=" 密码" id="password">
                                </td>
                            </tr>
                            <tr>
                                <td class="yzm clearfix">
                                    <input type="text" class="form-css" placeholder="验证码" id="inputCode">
                                    <a href="javascript:;" onclick="createCode()"> 
                                        <div class="code" id="checkCode">
                                            ASD1
                                        </div>
                                        <span>刷新</span>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button class="btn btn-lg btn-primary btn-block" type="submit" name="loginfrom">立即登录</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
    </div>
</div>
<footer>
    <p>江苏省厅出入境微信警务平台</p>
</footer>
</body>
<script>
	$(document).ready(function() {
		var $form = $('#adminLogin');
		$form.find('[name="loginfrom"]').on("click", function() {
			var inputCode = $("#inputCode").val();
			var username = $form.find('#username').val();
			var password = $form.find('#password').val();
			if (inputCode.length <= 0) {
			        alert("请输入验证码！");
			} else if (inputCode.toUpperCase() != code.toUpperCase()) {
	            alert("验证码输入有误！");
	            createCode();
	        } else {
	        	var aj = $.ajax({
					url : "/wxManager-war/webapp/utils/adminLogin",
					type : 'POST',
					data : {
						username : username,
						password : password,
					},
					dataType : 'JSON',
					success : function(data) {
						data = JSON.parse(data.data);
						console.log(data.code);
						if (data.code != "0") {
							alert(data.msg);
						} else {
							console.log(data.data);
							if (data.data.adminType == 1) {
								window.location.replace("franchisee.jsp");
							}
							if (data.data.adminType == 2) {
								window.location.replace("logistics.jsp");
							}
							if(data.data.adminType == 3){
								window.location.replace("main.jsp");
							}
						}
					},
					error : function() {
						alert("请输入正确的帐号、密码");
					}
				});
			}
			
		});
		$form.on("submit", function() {
			return false;
		});
	});
	var code;
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
	createCode();
</script>
</html>