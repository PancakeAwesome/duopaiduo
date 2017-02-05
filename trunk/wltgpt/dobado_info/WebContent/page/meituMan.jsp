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
	 url="/admin/saveBoke";
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
		 strIds.push(selectedRows[i].id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("/admin/bokeBDelete",{ids:ids},function(result){
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
	 url="/admin/bokeUpdate?menu.screenId="+row.screenId;
 }
 
 //保存
 function saveDataDic(){
     var imgText=$("#imgText").val();   
	 var screenActionUrl=$("#screenActionUrl").val();			
	 var screenImgUrl=$("#screenImgUrl").val();      
	 var param = {
	            "menu.imgText":imgText,
	            "menu.screenActionUrl":screenActionUrl,
	            "menu.screenImgUrl":screenImgUrl,
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
	 $("#imgText").val("");
	 $("#screenActionUrl").val("");
	 $("#screenImgUrl").val("");
	 $("#showImg").val("");
 }
 
 //关闭弹窗
 function closeDataDicDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }
 /*
 //初始加载下拉框
 $(function(){
	 $.ajax({
			url : "/admin/bannflData",
			type : 'POST',
			dataType : 'JSON',
			//async:false,
			success : function(data) {
				//var datas=JSON.parse(data);
				var datas = eval(data);
				//console.log(datas);
				var htm='';
				$.each(datas,function(i,obj){
				    htm +='<option value='+obj.banner_id+'>'+obj.banner_name+'</option>'
				});
				$(".yijifl").html(htm);
			}
	 });
	 

 });
 
 //下拉框变动事件
  function erjigaibian(s){
	
	 var fid=$('#'+s).val();
	
	 $.ajax({
			url : "/admin/wzerjiData",
			type : 'POST',
			dataType : 'JSON',
			data : {fid:fid},
			//async:false,
			success : function(data) {
				//var datas=JSON.parse(data);
				var datas = eval(data);
				//console.log(datas);
				var htm='';
				$.each(datas,function(i,obj){
				    htm +='<option value='+obj.article_id+'>'+obj.title+'</option>'
				});
				$(".erjifl").html(htm);
			}
	 });
 } */
 
</script>
<title>播客管理</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="播客广告管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="/admin/bokeData?tpId=2" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="screenId" width="50" align="center">编号</th>
   		<th field="imgText" width="100" align="center">标题</th>
   		<th field="screenActionUrl" width="100" align="center">图片链接地址</th>
   		<th field="screenImgUrl" width="100" align="center">图片地址</th>
   		<th field="addTime" width="100" align="center">创建时间</th>
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
   				<input type="text" id="imgText" name="imgText" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
 			</td>
   			
   		</tr>
   		<tr>
   			<td>图片链接地址：</td>
   			<td>
   			<input type="text" id="screenActionUrl" name="screenActionUrl" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
   			</td>
   			
   		</tr>
   		<tr>
   			<td>上传图片：</td>
   			<td>
						<input id="imgfile" type="file" name="imgfile" />
						<input type="text" style="display: none;" id="screenImgUrl" name="screenImgUrl" onchange="aaa('screenImgUrl')">
					    <input type="button" value="上传" id="imgUpload" onclick="btnUpload()">
					    <br>
					    <img alt="" src="" style=" height:100px; width: 250px;" id="showImg" >

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
		var elementIds=["screenImgUrl"]; //flag为id、name属性名
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
		          	 $('#screenImgUrl').val(dat.re);
		          	 //alert($('#imgUri').val());
		          	 aaa("screenImgUrl");
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