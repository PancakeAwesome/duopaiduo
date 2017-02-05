<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作日志</title>
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/from.css">
<script src="../../js/jquery-1.10.2.min.js"></script>
<script src="../../js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script src="../../js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script src="../../js/common/common.js"></script>
<script src="../../js/common/datagrid.js"></script>
<script src="../../js/common/linkageSelect.js"></script>
<script type="text/javascript">
$(function(){

	var $domList = $('#adminLogs');
	var commonDataGrid = new CommonDataGrid($domList, {
		url : '/wxManager-war/webapp/utils/getAdminLogs',
		method : 'POST',
		idField : 'id',
		width : 'auto',
		columns : [[
			{field:'adminName',title:'姓名',width:40,align:'center'},
	        {field:'adminId',title:'工号',width:40,align:'center'},
			{field:'roleName',title:'角色',width:40,align:'center'},
			{field:'event',title:'事件',width:80,align:'center'},
	        {field:'remark',title:'备注项',width:80,align:'center'},
	        {field:'createTime',title:'评论时间',width:80,align:'center'}
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
		showToolbar : false,
		onLoadSuccess:function(){
			
		}
	});
	commonDataGrid.show();
});

</script>

</head>
<body>
	<table id="adminLogs"></table>
</body>
</html>