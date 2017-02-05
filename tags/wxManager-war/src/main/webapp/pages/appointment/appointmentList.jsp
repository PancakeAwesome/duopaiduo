<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约管理</title>
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
	$.ajax({
		url : "/wxManager-war/webapp/utils/searchAllDot",
		type : 'GET',
		dataType : 'JSON',
		async:false,
		success : function(data) {
			var datas = JSON.parse(data.data);
			//console.log(datas);
			var dothtml = '<select id="dotId" name="dotId" class="form-css easyui-validatebox">';
			$.each(datas.data,function(i,obj){
				dothtml+='<option value="'+obj.id+'">';
				dothtml+= obj.dotName;
				dothtml+='</option>';
			});
			dothtml+='</select>';
			$("#allDotName").html(dothtml);
		}
	});
	
	var $domList = $('#appointList');
	var commonDataGrid = new CommonDataGrid($domList, {
		url : '/wxManager-war/webapp/utils/searchAppoint',
		deleteObj : {   //删除按钮参数对象
			deleteUrl:'',  //删除url
			type : 'get',        //删除的请求方式 可不写  默认是post
		},
		detailObj : {   //查询/修改按钮参数对象
			detailUrl:'/wxManager-war/webapp/utils/searchAppoint',  //查询url
			type : 'get',        //查询的请求方式 可不写  默认是post
			//isNeedSign : true, //是否需要token验证  可不写  默认是false
		},
		saveObj : {   //新增/修改保存按钮参数对象
			insUrl:'/wxManager-war/webapp/utils/saveAppoint',  //保存url
			type : 'post',        //查询的请求方式 可不写  默认是post
			//isNeedSign : true, //是否需要token验证  可不写  默认是false
		},
		method : 'GET',
		idField : 'id',
		width : 'auto',
		columns : [[
		            {field:'dotName',title:'网点名称',width:80,align:'center'},
		            {field:'personNum',title:'人数限制',width:80,align:'center'},
		            {field:'appointDay',title:'延迟时间',width:40,align:'center'},
					{field:'isCancel',title:'预约状态',width:100,align:'center'}
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
		addDiv : $('#appoint'),
	});
	commonDataGrid.show();
	
	$("#queryDIV").find("[name=seachBtn]").on("click",function(){
		var searchDotName = $('#searchDotName').val();
		commonDataGrid.load($domList,{"dotName" : searchDotName});
	});
	
	
})

 function checkIsCancel(){
	if ($('#isCancel').is(':checked')){
		$("#isCancel").val(1);
	}else{
		$("#isCancel").val(0);
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

	<table id="appointList"></table>
	<div id="appoint" class="easyui-dialog"
		style="width: 1000px; height: 430px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form>
			<div class="form-horizontal form-widgets col-sm-6 v_form">
				<div class="form-group">
					<label class="control-label col-sm-4">网点名称：</label>
					<div class="col-sm-8 col-md-6" id="allDotName">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">人数限制：</label>
					<div class="col-sm-8 col-md-6">
						<input name="personNum" type="text" class="form-css easyui-validatebox" required="true" /><font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">预约向后天数：</label>
					<div class="col-sm-8 col-md-6">
						<input name="appointDay" type="text" class="form-css easyui-validatebox" required="true" /><font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">是否取消预约：</label>
					<div class="col-sm-8 col-md-6">
						<input id="isCancel" name="isCancel" type="checkbox" value="0" onclick="checkIsCancel()" class="form-css easyui-validatebox" required="true"/>
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