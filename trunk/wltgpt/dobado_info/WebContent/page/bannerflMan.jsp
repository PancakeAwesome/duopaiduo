<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
 
 var url;

 function searchDataDic(){
	 $("#dg").datagrid('load',{
		"dataDicName":$("#s_dataDicName").combobox("getValue"),
		"dataDicValue":$("#s_dataDicValue").val() 
	 });
 }

 function openDataDicAddDialog(){
	 $("#dlg").dialog("open").dialog("setTitle","添加文章分类");
	 url="/admin/flsave";
 }
 
 function openDataDicModifyDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","编辑数据");
	 $("#fm").form("load",row);
	 url="/admin/flupdate?ZxarticleCategory.article_category_id="+row.article_category_id;
 }
 
 function saveDataDic(){
	 //var dataDicName=$("#dataDicName").combobox("getText");
	 var dataDicValue=$("#dataDicValue").val();
	 /* if(dataDicName==""){
		 $.messager.alert("系统提示","分类名称不能为空！");
		 return;
	 } */
	 if(dataDicValue==""){
		 $.messager.alert("系统提示","分类名称不能为空！");
		 return;
	 }                              
	 $.post(url,{"ZxarticleCategory.article_category_name":dataDicValue},function(result){
			if(result){
				 $.messager.alert("系统提示","保存成功！");
				 $("#dlg").dialog("close");
				 $("#dg").datagrid("reload");
				 resetValue();
			}else{
				$.messager.alert("系统提示","保存失败！");
			}
		},"json");
 }
 
 function resetValue(){
	 $("#dataDicName").combobox("setValue","");
	 $("#dataDicValue").val("");
 }
 
 function closeDataDicDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
 function deleteDataDic(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].article_category_id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("/admin/delete",{ids:ids},function(result){
				if(result > 0){
					 $.messager.alert("系统提示","数据已成功删除！");
					 $("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","数据删除失败，请联系系统管理员！");
				}
			},"json");
		} 
	 });
 }
 
</script>
<title>广告分类管理</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="广告分类管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="/admin/bannerflData" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<!-- <th field="cb" checkbox="true" align="center"></th> -->
   		<th field=banner_id width="50" align="center">编号</th>
   		<th field="banner_name" width="100" align="center">分类名称</th>
   		<th field="add_Time" width="100" align="center">创建时间</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<!-- <div>
 		<a href="javascript:openDataDicAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openDataDicModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteDataDic()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div> -->
 	<%-- <div>
 		&nbsp;数据字典名：&nbsp;<input class="easyui-combobox" id="s_dataDicName" data-options="panelHeight:'auto',editable:false,valueField:'dataDicName',textField:'dataDicName',url:'/dataDic/findDataDicName.do'"/>
 		&nbsp;数据字典值：&nbsp;<input type="text" id="s_dataDicValue" size="20" onkeydown="if(event.keyCode==13) searchDataDic()"/>
 		<a href="javascript:searchDataDic()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div> --%>
 </div>
 
  <div id="dlg" class="easyui-dialog" style="width:620px;height:150px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		 <tr>
   			<%-- <td>数据字典名：</td>
   			<td><input class="easyui-combobox" id="dataDicName" name="dataDicName" data-options="panelHeight:'auto',valueField:'dataDicName',textField:'dataDicName',url:'/dataDic/findDataDicName.do'"/>&nbsp;<font color="red">*</font></td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td> --%>
   			<td>文章分类名称：</td>
   			<td><input type="text" id="dataDicValue" name="dataDicValue" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:saveDataDic()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeDataDicDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
</body>
</html>