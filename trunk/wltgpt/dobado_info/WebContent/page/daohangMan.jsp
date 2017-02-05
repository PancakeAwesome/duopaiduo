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
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
 
 var url;

 //搜索
 /* function searchDataDic(){
	 $("#dg").datagrid('load',{
		"dataDicName":$("#s_dataDicName").combobox("getValue"),
		"dataDicValue":$("#s_dataDicValue").val() 
	 });
 } */

 //添加
 function openDataDicAddDialog(){
	 $("#dlg").dialog("open").dialog("setTitle","添加广告");
	 resetValue();
	 url="/admin/saveDaohang";
 }
 
//删除
 function deleteDataDic(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].navigation_id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("/admin/daohangBDelete",{ids:ids},function(result){
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
 
 //修改
 function openDataDicModifyDialog(){
	 var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","编辑数据");
	 $("#fm").form("load",row);
	 url="/admin/daohangUpdate?menu.navigation_id="+row.navigation_id;
 }
 
 //保存
 function saveDataDic(){
     var navigation_name=$("#navigation_name").val();   
	 var navigation_url=$("#navigation_url").val();			
	 var orderno=$("#orderno").val();      
	 var param = {
	            "menu.navigation_name":navigation_name,
	            "menu.navigation_url":navigation_url,
	            "menu.orderno":orderno,
	    };
	 console.log(param);   
	  $.post(url,param,function(result){
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
 
 //关闭清空
 function resetValue(){
	 $("#navigation_name").val("");
	 $("#navigation_url").val("");
	 $("#orderno").val("");
 }
 
 //关闭弹窗
 function closeDataDicDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 
</script>
<title>导航管理</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="导航管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="/admin/daohangData" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="navigation_id" width="50" align="center">编号</th>
   		<th field="navigation_name" width="100" align="center">标题</th>
   		<th field="navigation_url" width="100" align="center">链接地址</th>
   		<th field="orderno" width="100" align="center">排序</th>
   		<th field="add_time" width="100" align="center">创建时间</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:openDataDicAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openDataDicModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteDataDic()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>
 </div>
 
  <div id="dlg" class="easyui-dialog" style="width:800px;height:300px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" method="post">
   	<table cellspacing="8px">
   		 
   		 <tr>
   			<td>标题：</td>
   			<td>
   				<input type="text" id="navigation_name" name="navigation_name" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
 			</td>
   			
   		</tr>
   		<tr>
   			<td>链接地址：</td>
   			<td>
   			<input type="text" id="navigation_url" name="navigation_url" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
   			</td>
   			
   		</tr>
   		<tr>
   			<td>排序：</td>
   			<td>
   			<input type="text" id="orderno" name="orderno" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
   			</td>
   			
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:saveDataDic()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeDataDicDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 <script type="text/javascript">
 
 //图片上传
	function btnUpload(){  
		var elementIds=["uri"]; //flag为id、name属性名
		 $.ajaxFileUpload({
	          url: '/admin/upload', 
	          type: 'post',
	          secureuri: false, //
	          fileElementId: 'imgfile', // 
	          dataType: 'application/json', //
	          elementIds: elementIds, //
	          success: function(data){ 
	        	 console.log(data);
	        	 var dat = JSON.parse(data.substring(5,data.indexOf("</pre>")));
	        	 if(dat.data){
	        		 alert("成功");
		          	 $('#uri').val(dat.re);
		          	 //alert($('#imgUri').val());
		          	aaa("uri");
	        	 }else{
	        		 alert("失败");
	        	 }
	          }
		})
	} 
 function aaa(a){
		 //alert(a);
			/* if(!$("#"+a).val()){
				 alert(11);
				$('#showImg').hide();
			}else{
				 alert(2); */
				$('#showImg').attr('src',"/"+$("#"+a).val()).show();
			//}
	}	     
	</script>
</body>
</html>