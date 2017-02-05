<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>预约办证大厅</title>
<link rel="stylesheet" href="css/public/reset.css" type="text/css" media="screen" />
<link rel="Stylesheet" href="css/public/bootstrap.min.css" type="text/css" />
<link rel="Stylesheet" href="css/public/font-awesome.min.css" type="text/css" />
<link rel="Stylesheet" href="css/style.css" type="text/css" />

<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>

<script type="text/javascript">
	$(function(){
		$.ajax({
			url : "/wx-war/webapp/dot/dot-info/searchDotByCityName",
			type : 'POST',
			dataType : 'JSON',
			async:false,
			data :{
				cityName:'南京市'
			},
			success : function(data) {
				var datas = eval(data);
				console.log(datas);
				var allDotNameHtml ='<div class="clearfix " >';
				allDotNameHtml +='<label>服务大厅选择</label>';
				allDotNameHtml +='<select name="appointAddress">';
				$.each(datas.data,function(i,obj){
					allDotNameHtml +='<option value="'+obj.id+'">'+obj.cityName+'</option>';
				});
				allDotNameHtml +='</select>';
				allDotNameHtml +='</div>';
				
				allDotNameHtml +='<div class="clearfix">';
				allDotNameHtml +='<label>预约办证日期</label>';
				allDotNameHtml +='<select name="appointDate">';
				var allTimeHtml='';
				$.each(datas.data,function(i,obj){
					for (var i = 0;i < obj.dateList.length;i++){
						allDotNameHtml +='<option value="'+obj.dateList[i]+'">'+obj.dateList[i]+'</option>';
					}
					var timeArr = obj.time.split(",");
					for (var i = 0;i < timeArr.length;i++){
						allTimeHtml+='<li>';
						allTimeHtml+='<p>'+timeArr[i]+'</p>';
						if (obj.stateList[i] == 1){
							allTimeHtml+='<label class="hui">';
							allTimeHtml+= '不可预约';
						}else{
							allTimeHtml+='<label class="">';
							allTimeHtml+= '可预约';
						}
						allTimeHtml+='<input type="radio" name="appointTime" value="'+timeArr[0]+'" placeholder="">';
						allTimeHtml+='</label>';
						allTimeHtml+='</li>';
					}
					
					
				});
				allDotNameHtml +='</select>';
	            allDotNameHtml +='</div>';
				
				$("#allDotName").html(allDotNameHtml);
				$("#allTime").html(allTimeHtml);
			}
		});
	});
</script>

</head>

<body>
    <header>
    <i class="icon-angle-left"></i>
      <h1>预约办证大厅</h1> 
    </header><!-- /header -->
    <div class="img">
      <img src="images/tu_02.jpg" alt="">
    </div>
    <div class="office">
      <form action="/wx-war/webapp/appoint/appoint-info/sendAppoint" method="post">
	      <div class="office_1" id="allDotName">
	        
	      </div>
	      <h3>时间选择</h3>
	      <div class="office_2">
	        <ul class="clearfix" id="allTime">
	          
	        </ul>
	      </div>
	        <div class="office_foot">
	          <input type="submit" name="submitbtn" value="确定">
	          <p>江苏省公安局入境管理处 版本所有</p>
	        </div>
      </form>
    </div>
    
</body>
</html>