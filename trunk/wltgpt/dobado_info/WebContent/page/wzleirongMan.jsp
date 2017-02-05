<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = "http://jiu.dobado.cn/";
%>
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
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        #ueditors{
            width:100%;
        }
    </style>
<script type="text/javascript">
 
 var url;

 function searchDataDic(){
	 $("#dg").datagrid('load',{
		"dataDicName":$("#ayijifl").val(),
		"dataDicValue":$("#aerjifl").val() 
	 });
 }

 function openDataDicAddDialog(){
	 resetValue();
	 $("#dlg").dialog("open").dialog("setTitle","添加文章分类");
	 url="/admin/saveArticle";
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
	 UE.getEditor('editor').setContent(row.content);
	 $("#imgUri").val(row.img_uri);
	 $('#showImg').attr('src',"/"+row.img_uri).show();
	 url="/admin/articleUpdate?menu.article_id="+row.article_id;
	 erjigaibian('byijifl'); 
	 setTimeout(function () {$('#fpids').val(row.fpid);}, 500);
 }
 
 function saveDataDic(){
	 
     var article_category_id=$("#byijifl").val();   //一级分类
	 var fpid;
	 if($('#radioName input[name="radioName"]:checked ').val()==1){
		 fpid=1;  
	 }else if($('#radioName input[name="radioName"]:checked ').val()==0){
		 if(null == $("#fpids").val() || "" == $("#fpids").val()){
			 fpid=0;
		 }else{
			 fpid=$("#fpids").val();
		 }  
	 }else{
		 if(null == $("#fpids").val() || "" == $("#fpids").val()){
			 fpid=0;
		 }else{
			 fpid=$("#fpids").val();
		 } 
	 }				//二级分类
	 var title=$("#titles").val();			//biaoti 
	 var imageText=$("#imageTexts").val();      //图片文字
	 var content_summary=$("#content_summarys").val(); 
	 //摘要
	 var content = UE.getEditor('editor').getContent(); //正文内容
	 var imgUri=$("#imgUri").val();
	 var source=$("#source").val();
	 var source_value=$("#source_value").val();
	 if (article_category_id == 0 || title == '' || content_summary == '' || source == '' || source_value == ''){
		 alert("填写完整信息！");
		 return;
	 }else{
	 var param = {
	            "menu.article_category_id":article_category_id,
	            "menu.fpid":fpid,
	            "menu.title":title,
	            "menu.imageText":imageText,
	            "menu.content_summary":content_summary,
	            "menu.content":content,
	            "menu.img_uri":imgUri,
	            "menu.source":source,
	            "menu.source_value":source_value
	    };
	  
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
 }
 
 function resetValue(){
	 $("#byijifl").val("");
	 $("#fpids").val("");
	 $("#titles").val("");			//biaoti 
	 $("#imageTexts").val("");      //图片文字
	 $("#content_summarys").val(""); //摘要
	 $("#editor").val("");           //正文内容
	 $("#imgUri").val("");
	 $("#source").val("");
	 $("#source_value").val("");
	 $('#showImg').attr('src',"");
	 $("#imgfile").val("");
	 UE.getEditor('editor').setContent('');
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
		 strIds.push(selectedRows[i].article_id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("/admin/wzdelete",{ids:ids},function(result){
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
 $(function(){
	 $.ajax({
			url : "/admin/wzflData",
			type : 'POST',
			dataType : 'JSON',
			//async:false,
			success : function(data) {
				//var datas=JSON.parse(data);
				var datas = eval(data);
				//console.log(datas);
				var htm='<option value=0 > 请选择 </option>';
				$.each(datas,function(i,obj){
				    htm +='<option value='+obj.article_category_id+'>'+obj.article_category_name+'</option>'
				});
				$(".yijifl").html(htm);
			}
	 });
	 

 });
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
				var htm='<option value=0 > 请选择 </option>';
				$.each(datas,function(i,obj){
				    htm +='<option value='+obj.article_id+'>'+obj.title+'</option>'
				});
				$(".erjifl").html(htm);
			}
	 });
 }
 
</script>
<title>文章内容管理</title>
</head>
<body style="margin: 1px">
 <table id="dg" title="章分类管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="/admin/wzleirongData" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="article_id" width="50" align="center">编号</th>
   		<th field="title" width="100" align="center">标题</th>
   		<th field="imageText" width="100" align="center">图片文字</th>
   		<th field="content_summary" width="100" align="center">摘要</th>
   		<th field="add_time" width="100" align="center">创建时间</th>
   		<th field="img_uri" width="100" align="center">创建时间</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:openDataDicAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openDataDicModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteDataDic()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>
 	<div>
 		&nbsp;文章一级分类：&nbsp;
 		<select onchange="erjigaibian('ayijifl')" style="width: 200px" id="ayijifl" class="yijifl">
 		</select> 
 		&nbsp;文章二级分类：&nbsp;
 		<select style="width: 200px" id="aerjifl" class="erjifl">
 		</select>
 		<a href="javascript:searchDataDic()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	</div> 
 </div>
 
  <div id="dlg" class="easyui-dialog" style="width:1000px;height:500px;padding: 5px 10px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="fm" action="/admin/saveArticle" method="post">
   	<table cellspacing="8px">
   		 <tr>
   			<td>一级分类：</td>
   			<td>
	   			<select onchange="erjigaibian('byijifl')" name="article_category_id" id="byijifl" style="width: 200px" class="yijifl">
	 			</select> 
 			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>二级分类：</td>
   			<td>
	   			<select id="fpids" name="fpid"  style="width: 200px" class="erjifl">
	 			</select>
   			</td>
   		</tr>
   		 <tr>
   			<td>标题：</td>
   			<td>
   				<input type="text" id="titles" name="title" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
 			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>是否二级分类：</td>
   			<td>
   				<span id="radioName" class="radioSpan">
                <input type="radio"  name="radioName" value="0">否</input>
                <input type="radio"  name="radioName" value="1">是</input>
            	</span>
   			</td>
   		</tr>
   		<tr>
   			<td>来源：</td>
   			<td>
   				<input type="text" id="source" name="source" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
 			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>作者：</td>
   			<td>
   				<input type="text" id="source_value" name="source_value" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font>
   			</td>
   		</tr>
   		<tr>
   			<td>上传图片：</td>
   			<td>
						<input id="imgfile" type="file" name="imgfile" />
						<input type="text" style="display: none;" id="imgUri" name="img_uri" onchange="aaa(this)">
					    <input type="button" value="上传" id="imgUpload" onclick="btnUpload()">
					    <br>
					    <img alt="" src="" style="display: none; height:100px; width: 100px;" id="showImg" >
<!--    			
   			
   				<input  name="imguil" type="file" id="imguil" />
   				<p>
					    <input id="imageInput" type="file" name="imageInput" onchange="loadImageFile();" />
					    <input type="button" value="上传" onclick="btnUpload()">
					    <input type="hidden" id="filePath" name="uri"><br />
					    <input id="imgfile" type="file" name="imgfile" />
					    <input type="text" style="display: none;" id="uri" name="uri" onchange="aaa(this)">
					    <input type="button" value="上传" id="imgUpload" onclick="btnUpload()">
					    <br>
					    <img alt="" src="" style="display: none; width: 250px;" id="showImg" >
  						</p> -->
 			</td>
   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
   			<td>图片文字：</td>
   			<td>
   				<input type="text" id="imageTexts" name="imageText" class="easyui-validatebox" />
   			</td>
   		</tr>
   		<tr>
   			<td>摘要：</td>
   			<td colspan="4">
   				<textarea id="content_summarys" name="content_summary" style="width: 565px; height: 20px;"></textarea>
 			</td>
   		</tr>
   		<tr>
   			<td>内容：</td>
   			<td colspan="4">
   				<!-- <textarea id="contents" name="content" style="width: 565px; height: 80px;"></textarea> -->
   				<div id="ueditors">
			    	<script name='content' id="editor" type="text/plain" style="width:740px;height:500px;"></script>
				</div>
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
	function btnUpload(){  
		var elementIds=["imgUri"]; //flag为id、name属性名
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
		          	 $('#imgUri').val(dat.re);
		          	 //alert($('#imgUri').val());
		          	aaa("imgUri");
	        	 }else{
	        		 alert("失败");
	        	 }
	          	 
	          }
		
		/* alert(111)
		 var elementIds=["uri"]; //flag为id、name属性名
		 $.ajaxFileUpload({
	            url: '/admin/upload', 
	            type: 'post',
	            secureuri: false, //
	            fileElementId: 'imgfile', // 
	            dataType: 'application/json', //
	            elementIds: elementIds, //
	            success: function(data, status){  
	            	 alert("成功");
	            	 console.log(data);
	            	 var dat = JSON.parse(data.substring(5,data.indexOf("</pre>")));
	            	 $('#uri').val(dat.data); 
	            },
	            error: function(data, status, e){ 
	            	alert("失败");
	                alert(e);
	            } */
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
	<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>
</body>
</html>