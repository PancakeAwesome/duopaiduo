<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>三级联动</title>
<link rel="stylesheet" href="css/public/reset.css" type="text/css" media="screen" />
<link rel="Stylesheet" href="css/public/bootstrap.min.css" type="text/css" />
<link rel="Stylesheet" href="css/public/font-awesome.min.css" type="text/css" />
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/common/linkageSelect.js"></script>
<script type="text/javascript">
	$(function(){
		var $areaDom = $('#areaSel');
		// 省市区
		var areaSelect = new LinkageSelect($areaDom, {
			url: '/wx-war/webapp/common/common-info/listArea',  //数据源
			id: 'fId',   //key属性名
			name: 'fName', //value属性名
			parentNameDomName: 'province', //第一层name （父节点name）
			childrenDomName : 'city', // //第二层name （子节点name）
			nextChildrenDomName : 'county', //第三层name （第三层节点name）
			isArea:true,
		});
		areaSelect.getParentValue();  //初始化父节点数据
		$areaDom.find('[name="province"]').on("change",function(){  //改变第二层数据
			$("#city  option:selected").text("请选择");
			areaSelect.getChildrenValue();
		})
		
		$areaDom.find('[name="city"]').on("change",function(){//改变第三层数据
			areaSelect.getNextChildrenValue();
		})
	});
</script>
</head>
<body style="overflow: scroll;">
	<div class="sudi clearfix  col-xs-10 f_r row">
		<div class="sudi2 col-xs-9">
			<div id="areaSel"
				style="float: left; width: 45%; margin-top: 1px;">
				<select style='width: 31%; height: 30px;' class="form-css"
					id="province" name="province">
					<option value="0">选择省</option>
				</select> <select style='width: 31%; height: 30px;' class="form-css"
					id="city" name="city">
					<option value="0">选择市</option>
				</select> <select style='width: 31%; height: 30px;' class="form-css"
					id="county" name="county">
					<option value="0">县区</option>
				</select>
			</div>
		</div>
	</div>
</body>
</html>