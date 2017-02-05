<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>江苏省厅出入境微信警务平台</title>

<%
	HttpSession sessions=request.getSession();
	String userId = (null==sessions.getAttribute("userId")?null:sessions.getAttribute("userId").toString());
	String userName = (null==sessions.getAttribute("userName")?null:sessions.getAttribute("userName").toString());
	//String roles = (null==sessions.getAttribute("roles")?null:sessions.getAttribute("roles").toString());
	String roles = ",1,2,3,";
	String position = (null==sessions.getAttribute("position")?null:sessions.getAttribute("position").toString());
%>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="js/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common/common.js"></script>
<script type="text/javascript">
	
$(function(){
	USER.userId = <%=userId %>;
	USER.userName = '<%=userName %>';
	USER.roles = '<%=roles %>';
	USER.position = '<%=position %>';
	if(null != USER.userId){
		openTab('控制台','../pages/admin/controlPlat.jsp','icon-khxxgl');
	}
});
	
var url;

function openTab(text,url,iconCls){
	if($("#tabs").tabs("exists",text)){
		$("#tabs").tabs("close",text);
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/page/"+url+"'></iframe>";
		$("#tabs").tabs("add",{
			title:text,
			iconCls:iconCls,
			closable:true,
			content:content
		});
	}else{
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/page/"+url+"'></iframe>";
		$("#tabs").tabs("add",{
			title:text,
			iconCls:iconCls,
			closable:true,
			content:content
		});
		$('#logout').on("click", function() {
			
			var aj = $.ajax({
				url : "/wxManager-war/webapp/utils/logout",
				type : 'POST',
				dataType : 'text',
				success : function(data) {
					window.location.replace("/wxManager-war/login.jsp");
				},
				error : function() {
					alert("请稍候再试!");
				}
			});
		});
	}
};
</script>
</head>
<body class="easyui-layout">
<div region="north" style="background-color: #E0ECFF">
	<table style="padding: 5px" width="100%">
		<tr>
			
			<td valign="bottom" align="right" width="45%">
				<%=position %>:<%=userName %>
			</td>
			<td valign="bottom" align="right" width="5%">
				<a id="logout" href="#">退出</a>
			</td>
		</tr>
	</table>
</div>
<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		 <%-- <div title=".." data-options="iconCls:'icon-home'">
			<div align="center" style="margin-top: 20px;">
				<font color="red" size="10">
					<img alt="首页" src="${pageContext.request.contextPath}/images/shouye.jpg">
				</font>
			</div>
		</div>  --%>
	</div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
	
		<% if(null != roles){%>
			<% if(  null != roles && roles.indexOf(",100000,")>=0){%>	
		<div title="测试dome" data-options="selected:true,iconCls:'icon-yxgl'" style="padding: 10px">
			<a href="javascript:openTab('ajax请求','../pages/dome/ajax.html','icon-yxjhgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-yxjhgl'" style="width: 150px">ajax请求</a>
			<a href="javascript:openTab('下拉框联动','../pages/dome/area.html','icon-khkfjh')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">下拉框联动</a>
			<a href="javascript:openTab('表格','../pages/dome/grid.html','icon-bg')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">表格</a>
		</div>
		<%} %>
		
		<% if(roles.indexOf(",1,")>=0){ %>	
		<div title="网点管理"  data-options="iconCls:'icon-khgl'" style="padding:10px;">
			<a href="javascript:openTab('网点列表','../pages/dot/dotList.jsp','icon-khlsgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khlsgl'" style="width: 150px;">网点列表</a>
		</div>
		<% } %>
		
		<% if(roles.indexOf(",2,")>=0){ %>	
		<div title="预约管理"  data-options="iconCls:'icon-khgl'" style="padding:10px;">
			<a href="javascript:openTab('预约列表','../pages/appointment/appointmentList.jsp','icon-khlsgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khlsgl'" style="width: 150px;">预约列表</a>
		</div>
		<% } %>
		
		<% if(roles.indexOf(",3,")>=0){
			%>
		<div title="权限管理"  data-options="iconCls:'icon-khgl'" style="padding:10px;">
			<a href="javascript:openTab('管理员列表','../pages/role/admin.jsp','icon-khxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">管理员列表</a>
			<a href="javascript:openTab('角色管理','../pages/role/role.jsp','icon-khxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">角色管理</a>
			<a href="javascript:openTab('工作日志','../pages/admin/adminLogs.jsp','icon-khxxgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">工作日志</a>
		</div>
		<%}%>
		<%} else {%>
			<br><a href="login.jsp">点击这里，重新登录</a>
		<%} %>
	</div>
</div>
<div region="south" style="height: 25px;padding: 5px" align="center">
	江苏省厅出入境微信警务平台
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:250px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>用户名：</td>
   			<td><input type="text" id="userName" name="userName" readonly="readonly" value="${currentUser.userName }" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>原密码：</td>
   			<td><input type="password" id="oldPassword" name="oldPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>新密码：</td>
   			<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>确认新密码：</td>
   			<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   	</table>
   </form>
 </div>
 
	 <div id="dlg-buttons">
	 	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	 	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	 </div>
	 
</body>
</html>