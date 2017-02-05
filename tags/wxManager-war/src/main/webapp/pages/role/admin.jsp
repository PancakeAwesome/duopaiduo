<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理员</title>
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
	var $domList = $('#adminList');
	var $form = $('#admin');
	var $dom = $('#roleSel');
	var commonDataGrid = new CommonDataGrid($domList, {
		url : '/wxManager-war/webapp/utils/getAdmins',
		method : 'POST',
		deleteObj : {   //删除按钮参数对象
			deleteUrl:'',  //删除url
			type : 'get',        //删除的请求方式 可不写  默认是post
		},
		detailObj : {   //查询/修改按钮参数对象
			detailUrl:'',  //查询url
			type : 'get',        //查询的请求方式 可不写  默认是post
			//isNeedSign : true, //是否需要token验证  可不写  默认是false
		},
		saveObj : {   //新增/修改保存按钮参数对象
			insUrl:'',  //保存url
			type : 'post',        //查询的请求方式 可不写  默认是post
			//isNeedSign : true, //是否需要token验证  可不写  默认是false
		},
		idField : 'adminId',
		width : 'auto',
		columns : [[
		            {field:'adminId',title:'ID',width:80,align:'center'},
		            {field:'admin',title:'登录名',width:80,align:'center'},
		            {field:'adminName',title:'姓名',width:80,align:'center'},
		            {field:'roleName',title:'所属角色',width:80,align:'center'},
					{field:'addTime',title:'创建时间',width:80,align:'center'},
					/* {field:'modTime',title:'编辑时间',width:120,align:'center'}, */
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
		addDiv : $form,
	});
	commonDataGrid.show();
	
	$form.find('[name="admin"]').on("blur",function(){
		var result =$form.find('[name="admin"]').val();
	    var aj =$.ajax({ 
	        url: '/wx-war/webapp/user/user-info/getByAdmin/'+result, 
	        type:'GET', 
	        dataType:'JSON',  
	        success: function(data){ 
	        	if(data.code != 0){
	        		alert('此登录名已存在');
	        		$form.find('[name="admin"]').attr("isExist",true);
	        		$form.find('[name="admin"]').addClass("validatebox-invalid");
	        	}else{
	        		$form.find('[name="admin"]').attr("isExist",false);
	        		$form.find('[name="admin"]').removeClass("validatebox-invalid");
	        	}
	        	//data = JSON.parse(data.data);
	        }
	    });
		/* new Ajax('',{
			url: $saveObj.insUrl,
			param:  $addForm.find('form').serializeJson(),
			type:$saveObj.type  == null ? 'post' : $saveObj.type,
			isNeedSign:$saveObj.isNeedSign  == null ? false : $saveObj.type,  //需要token验证
			callback : function(d) {
				$addForm.dialog('close');
				$.messager.alert("系统提示","操作成功!");
				self.load($dom,$queryParams);
			}
		}); */
	});
	
	var linkageSelect = new LinkageSelect($dom, {
		url: '/wxManager-war/webapp/utils/getRoles',  //数据源
		id: 'roleId',   //key属性名
		name: 'roleName', //value属性名
		parentNameDomName: 'role', //第一层name （父节点name）
		queryParam:{"row":100,"page":1},
		requestType:'post',
		isPage:true
	});
	linkageSelect.getParentValue();  //初始化父节点数据
})
</script>
</head>
<body>
	<table id="adminList"></table>
	<div id="admin" class="easyui-dialog"
		style="width: 1000px; height: 430px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form>
			<div class="form-horizontal form-widgets col-sm-6 v_form">
				<div class="form-group">
					<label class="control-label col-sm-4">登录名：</label>
					<div class="col-sm-8 col-md-6">
						<input name="admin" type="text" class="form-css easyui-validatebox" required="true" isExist="false" editstatus="false"/>&nbsp;<font
					color="red">*</font>
					</div>
					<div id="checkout"></div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">姓名：</label>
					<div class="col-sm-8 col-md-6">
						<input name="adminName" type="text" class="form-css easyui-validatebox" required="true" />&nbsp;<font
					color="red">*</font>
					</div>
				</div>
			</div>
			<div class="form-horizontal form-widgets col-sm-6 v_form">
				<div class="form-group">
					<label class="control-label col-sm-4">密码：</label>
					<div class="col-sm-8 col-md-6">
						<input name="password" type="password" class="form-css easyui-validatebox" required="true" editstatus="false"/>&nbsp;<font
					color="red">*</font>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">角色：</label>
					<div class="col-sm-8 col-md-6" id="roleSel">
						<select  class="form-css" name="role">
							<option value="0">选择角色</option>
						</select>
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