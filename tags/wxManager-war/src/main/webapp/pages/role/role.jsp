<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/from.css">
<style type="text/css">
.roleclass div{
float:left;
min-width:150px;
}
</style>
<script src="../../js/jquery-1.10.2.min.js"></script>
<script src="../../js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script src="../../js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script src="../../js/common/common.js"></script>
<script src="../../js/common/linkageSelect.js"></script>
<script src="../../js/common/datagrid.js"></script>
<script type="text/javascript">
$(function(){
	var $dom = $('#dg');
	var commonDataGrid = new CommonDataGrid($dom, {
		url : '/wxManager-war/webapp/utils/getRoles',
		method : 'POST',
		deleteObj : {  
			deleteUrl:'/wx-war/webapp/user/user-info/delRole',  //删除url
			type : 'get',        
		},
		detailObj : {  
			detailUrl:'/wx-war/webapp/user/user-info/getRole',  //查询url
			type : 'get'       
		},
		saveObj : {   
			insUrl:'/wx-war/webapp/user/user-info/saveRole',  //保存url
			type : 'post',        
		},
		idField : 'roleId',
		width : 'auto',
		columns : [[
		            {field:'roleId',title:'角色ID',width:80,align:'center'},
		            {field:'roleName',title:'角色名称',width:80,align:'center'},
		            {field:'actionName',title:'角色权限',width:80,align:'center'},
		            
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
		addDiv : $('#logistics'),
	});
	commonDataGrid.show();
});
</script>

</head>
<body>
	<table id="dg"></table>
	<div id="logistics" class="easyui-dialog"
		style="width: 800px; height: 430px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form>
			<div class="form-horizontal form-widgets col-sm-12 v_form">
				<div class="form-group">
					<label class="control-label col-sm-2">角色名称：</label>
					<div class="col-sm-8 col-md-6">
						<input name="roleName" type="text" class="form-css easyui-validatebox" required="true" />&nbsp;<font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">角色权限：</label>
					<div class="col-sm-8 col-md-6 roleclass">
						<div>
							<input name="actionIds" type="checkbox" class="" value="1" />网点管理
						</div>
						<div>
							<input name="actionIds" type="checkbox" class="" value="2" />预约审核
						</div>
						<div>
							<input name="actionIds" type="checkbox" class="" value="3" />权限管理
						</div>
					</div>
				</div>
		</form>
		
		<div style="margin-top:110px;margin-left:500px;">
			<a class="easyui-linkbutton" iconcls="icon-ok" name="save"> 保存</a> 
			<a class="easyui-linkbutton" iconcls="icon-cancel" name="canl"> 取消</a>
		</div>
	</div>
</body>
</html>