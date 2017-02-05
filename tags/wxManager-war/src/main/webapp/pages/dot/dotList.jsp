<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网点管理</title>
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/from.css">
<script src="../../js/jquery-1.10.2.min.js"></script>
<script src="../../js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script src="../../js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script src="../../js/common/common.js"></script>
<script src="../../js/common/linkageSelect.js"></script>
<script src="../../js/common/datagrid.js"></script>
<script type="text/javascript">
$(function() {
	var $domList = $('#dotList');
	var commonDataGrid = new CommonDataGrid($domList, {
		url : '/wxManager-war/webapp/utils/searchDot',
		deleteObj : {   //删除按钮参数对象
			deleteUrl:'',  //删除url
			type : 'get',        //删除的请求方式 可不写  默认是post
		},
		detailObj : {   //查询/修改按钮参数对象
			detailUrl:'/wxManager-war/webapp/utils/searchDot',  //查询url
			type : 'get',        //查询的请求方式 可不写  默认是post
			//isNeedSign : true, //是否需要token验证  可不写  默认是false
		},
		saveObj : {   //新增/修改保存按钮参数对象
			insUrl:'/wxManager-war/webapp/utils/saveDot',  //保存url
			type : 'post',        //查询的请求方式 可不写  默认是post
			//isNeedSign : true, //是否需要token验证  可不写  默认是false
		},
		method : 'GET',
		idField : 'id',
		width : 'auto',
		columns : [[
		            {field:'cityName',title:'城市名称',width:40,align:'center'},
		            {field:'dotName',title:'网点名称',width:80,align:'center'},
		            {field:'address',title:'所在地',width:80,align:'center'},
		            {field:'telephone',title:'电话',width:40,align:'center'},
					{field:'time',title:'时间段',width:100,align:'center'}
		]], 
		// 设置true将在数据表格底部显示分页工具栏
		pagination : true,
		// 设置为true将显示行数
		rownumbers : true,
		// 设置为true将交替显示行背景
		striped : true,
		// 设置为true将自动使列适应表格宽度以防止出现水平滚动
		fitColumns : true,
		// 设置为true将只允许选择一行
		singleSelect : true,
		showToolbar : true,
		addDiv : $('#dot'),
	});
	commonDataGrid.show();
	
	$("#queryDIV").find("[name=seachBtn]").on("click",function(){
		var searchDotName = $('#searchDotName').val();
		commonDataGrid.load($domList,{"dotName" : searchDotName});
	});
})

 function check(id){
	var reg = new RegExp("^[0-9]*$");
	
	if (!reg.test($("#hour"+id).val())){
	    $("#hour"+id).val("");
	}else{
		if (parseInt($("#hour"+id).val()) == 0 || parseInt($("#hour"+id).val()) >= 24){
			$("#hour"+id).val("");
		}
		
		if(id >= 2){
			for (var i = 1;i <= id; i++){
				if (parseInt($("#hour"+id).val()) < parseInt($("#hour"+i).val())){
					$("#hour"+id).val("");
					alert("时间段不能小于前面时间！");
				}
			}
		}
	}

	if (!reg.test($("#min"+id).val())){
	    $("#min"+id).val("");
	}else{
		if (parseInt($("#min"+id).val()) >= 60){
			 $("#min"+id).val("");
		}
	}
	
	
	
 }
 
</script>
</head>
<body>
	<div style="width: 100%; background-color: -moz-buttonhoverface;
    	font-size: 12px; height: 30px; line-height: 30px; width: 100%;" id="queryDIV">
		<span style="margin-left: 55%;">
			网点名：
			<input id="searchDotName" name="searchDotName" type="text"  class="form-css" style="width:15%;height:22px;"> 
			<a class="easyui-linkbutton" iconcls="icon-search" name="seachBtn"> 搜索</a> 
	    </span>
	</div>

	<table id="dotList"></table>
	<div id="dot" class="easyui-dialog"
		style="width: 1000px; height: 430px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form>
			<div class="form-horizontal form-widgets col-sm-6 v_form">
				<div class="form-group">
					<label class="control-label col-sm-4">城市名称：</label>
					<div class="col-sm-8 col-md-6">
						<input name="cityName" type="text" class="form-css easyui-validatebox" required="true" /><font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">网点名称：</label>
					<div class="col-sm-8 col-md-6">
						<input name="dotName" type="text" class="form-css easyui-validatebox" required="true" /><font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">所在地：</label>
					<div class="col-sm-8 col-md-6" id="areaSel">
						<input name="address" type="text" class="form-css easyui-validatebox" required="true" /><font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">电话：</label>
					<div class="col-sm-8 col-md-6">
						<input name="telephone" type="text" class="form-css easyui-validatebox" required="true" /><font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">时间段一：</label>
					<div class="col-sm-8 col-md-6">
						<input id="hour1" name="hour1" type="text" class="form-css1" required="true" maxlength="2" onblur="check(1)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min1" name="min1" type="text" class="form-css1" required="true" maxlength="2" onblur="check(1)"/>
						<lable style="font-size:14px;">-</lable>
						<input id="hour2" name="hour2" type="text" class="form-css1" required="true" maxlength="2" onblur="check(2)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min2" name="min2" type="text" class="form-css1" required="true" maxlength="2" onblur="check(2)"/>
						<font color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">时间段二：</label>
					<div class="col-sm-8 col-md-6">
						<input id="hour3" name="hour3" type="text" class="form-css1" required="true" maxlength="2" onblur="check(3)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min3" name="min3" type="text" class="form-css1" required="true" maxlength="2" onblur="check(3)"/>
						<lable style="font-size:14px;">-</lable>
						<input id="hour4" name="hour4" type="text" class="form-css1" required="true" maxlength="2" onblur="check(4)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min4" name="min4" type="text" class="form-css1" required="true" maxlength="2" onblur="check(4)"/>
						<font color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">时间段三：</label>
					<div class="col-sm-8 col-md-6">
						<input id="hour5" name="hour5" type="text" class="form-css1" required="true" maxlength="2" onblur="check(5)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min5" name="min5" type="text" class="form-css1" required="true" maxlength="2" onblur="check(5)"/>
						<lable style="font-size:14px;">-</lable>
						<input id="hour6" name="hour6" type="text" class="form-css1" required="true" maxlength="2" onblur="check(6)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min6" name="min6" type="text" class="form-css1" required="true" maxlength="2" onblur="check(6)"/>
						<font color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">时间段四：</label>
					<div class="col-sm-8 col-md-6">
						<input id="hour7" name="hour7" type="text" class="form-css1" required="true" maxlength="2" onblur="check(7)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min7" name="min7" type="text" class="form-css1" required="true" maxlength="2" onblur="check(7)"/>
						<lable style="font-size:14px;">-</lable>
						<input id="hour8" name="hour8" type="text" class="form-css1" required="true" maxlength="2" onblur="check(8)"/>
						<lable style="font-size:14px;">:</lable>
						<input id="min8" name="min8" type="text" class="form-css1" required="true" maxlength="2" onblur="check(8)"/>
						<font color="red">*</font>
					</div>
				</div>
			</div>
		</form>
		
		<div style="margin-top:180px;margin-left:710px;">
			<a class="easyui-linkbutton" iconcls="icon-ok" name="save"> 保存</a> 
			<a class="easyui-linkbutton" iconcls="icon-cancel" name="canl"> 取消</a>
		</div>
	</div>
</body>
</html>