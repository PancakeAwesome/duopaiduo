<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>文章管理</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/public/font-awesome.min.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/public/laydate/laydate.js"></script>
<script type="text/javascript" src="js/public/laypage/laypage.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->
<script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="../ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        #ueditors{
            width:100%;
        }
    </style>
<script type="text/javascript">

	$(function(){
		if($.cookie('name') != null && $.cookie('name') != ""){
			 $("#sp").html( $.cookie('name'));
		 }else{
			 layer.msg("登录超时！");
			window.location.href="login.jsp";
		 }
		$.ajax({
			url : "wzfenleiData",
			type : 'POST',
			dataType : 'JSON',
			async:false,
			success : function(data) {
				//var datas=JSON.parse(data.list);
				var datas=eval(data);
				//console.log(datas);
				var htm = ' <option value=0 >所属文章分类</option>';
				$.each(datas.list,function(i,obj){
					htm +=' <option value='+obj.article_category_id+'>'+obj.article_category_name+'</option>';
				});
				$("#xiala").html(htm);
				
				$("#xiala").val($("#categoryId").val());
				
				if($("#fpId").val()>1){
					erjigaibian('xiala');
				}
			},
			error : function() {
				alert("网络异常！");
			}
		});
	});
	function addWenz(){
		var fpid=0;
		if(null == $("#erjifl").val() || "" == $("#erjifl").val()){
			 fpid=0;
		 }else{
			 fpid=$("#erjifl").val();
		 }
		 var article_category_id=$("#xiala").val();  
		 var article_id=$("#articleId").val();  
		 var title=$("#titles").val();		 
		// var imageText=$("#imageTexts").val();      
		 var content_summary=$("#content_summarys").val(); 
		 var content = UE.getEditor('editor').getContent(); 
		 var imgUri=$("#imgUri").val();
		 var source=$("#source").val();
		 var source_value=$("#source_value").val();
		 var respon =$("#respon").val();
		 var seo_key =$("#seo_key").val();
		 var sort =$("#sort").val();
		 if(article_category_id == 0 || article_category_id == ""){
			 layer.msg("分类必填!");
			 return;
		 }
		 if(title == null || title == ""){
			 layer.msg("标题必填!");
			 return;
		 }
		 if(content_summary == null || content_summary == ""){
			 layer.msg("摘要必填!");
			 return;
		 }
		 if(content == null || content == ""){
			 layer.msg("内容必填!");
			 return;
		 }
		 if(imgUri == null || imgUri == ""){
			 layer.msg("请上传图片!");
			 return;
		 }
		 if(source == null || source == ""){
			 layer.msg("来源必填!");
			 return;
		 }
		 if(source_value == null || source_value == ""){
			 layer.msg("作者必填!");
			 return;
		 }
		 if(respon == null || respon == ""){
			 layer.msg("责任编辑!");
			 return;
		 }
		 if(seo_key == null || seo_key == ""){
			 layer.msg("标签必填!");
			 return;
		 }
		 if(sort == null || sort == ""){
			 
		 }
		 var param = {
				 	"menu.article_id":article_id,
		            "menu.article_category_id":article_category_id,
		            "menu.fpid":fpid,
		            "menu.title":title,
		          //"menu.imageText":imageText,
		            "menu.content_summary":content_summary,
		            "menu.content":content,
		            "menu.img_uri":imgUri,
		            "menu.source":source,
		            "menu.source_value":source_value,
		            "menu.respon":respon,
		            "menu.sort":sort,
		            "menu.seo_key":seo_key
		    };
		// console.log(param);
		 $.ajax({
				url : "articleUpdate",
				type : 'POST',
				dataType : 'JSON',
				data : param,
				async:false,
				success : function(data) {
					if(data){
						alert("保存成功!");
						window.location.href="wenz.jsp";
					}else{
						alert("失败!");
					}
				},
				error : function() {
					alert("网络异常！");
				}
		 });
	};
	function empty(){
		 $("#xiala").val(0);  
		 $("#titles").val("");		 
		 //$("#imageTexts").val("");      
		 $("#content_summarys").val(""); 
		 UE.getEditor('editor').setContent(""); 
		 $("#imgUri").val("");
		 $("#source").val("");
		 $("#source_value").val("");
		 $("#respon").val("");
		 $("#seo_key").val("");
		 $("#showImg").attr('src',"");
		 $("#imgfile").val("");
		 $("#sort").val("");
		 $("#erjifl").addClass('none');
	}
	function erjigaibian(s){
		
		 var fid=$('#'+s).val();
		
		 $.ajax({
				url : "wzerjiData",
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
					$("#erjifl").html(htm);
					if(fid == $("#categoryId").val()){
						$("#erjifl").val($("#fpId").val());
					}
					if(datas.length>0){
						$("#erjifl").removeClass('none');
					}else{
						$("#erjifl").addClass('none');
					}
				}
		 });
	 }
</script>
</head>

<body>

<div id="container" class="thewidth100">

<!--header-->
    <div class="header_bg clearfix thewidth100"> 
        <div class="header_top clearfix">
            <div class="f_l">多拍多资讯站后台管理</div>
            <div class="f_r">欢迎<span id="sp"></div>
        </div>
        <div class="header">
            <nav>
               <ul>
                    <li><a href="">管理首页</a></li>
                    <li><a href="wenz.jsp"  class="choice">文章管理</a></li>
                    <li><a href="advert.jsp">广告管理</a></li>
                    <li><a href="">用户管理</a></li>
                    <li><a href="">评论管理</a></li>
                    <li><a href="system.jsp">系统管理</a></li>
                </ul>
            </nav>
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->


<!--wrap-->
    <div class="wrap_bg thewidth100">    
        <div class="wrap clearfix bghui">

            <div class="wrap_l">
            <h2>文章管理</h2>
                <ul>
                    <li><a href="wenz.jsp">多拍多资讯文章列表</a></li>
                    <li><a href="addWenz.jsp" class="choice">添加资讯文章</a></li>
                    <li><a href="shWenz.jsp">审核文章列表</a></li>
                </ul>
            </div>
            <div class="wrap_r">
                <div class="clearfix SearchOperation">
                    <h4>添加资讯文章列表</h4>
                </div>
                <div class="modify">
                		<input type="hidden" id="articleId" value="${article.articleId }"/>
                		<input type="hidden" id="categoryId" value="${article.articleCategoryId }"/>
                		<input type="hidden" id="fpId" value="${article.fpid }"/>
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td>所属文章分类:</td>
                                <td colspan="3" align="left">
                                	
                                    <select onchange="erjigaibian('xiala')"  id="xiala" class="form-control f_l wn250">
                                        
                                    </select>
                                    
                                    <select  id="erjifl" class="form-control f_l none wn250">
                                          
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>文章标题：</td>
                                <td><input type="text" class="form-control f_l wn250" id="titles" value="${article.title }" placeholder="请填写文章标题"></td>
                                <td>排序：</td>
                                <td><input type="text" class="form-control f_l wn250" id="sort" value="${article.sort }" placeholder=""></td>
                            
                            </tr>
                            <tr>
                                <td>文章来源</td>
                                <td><input type="text" class="form-control f_l wn250" id="source" value="${article.source }" placeholder=""></td>
                                <td>作者：</td>
                                <td><input type="text" class="form-control f_l wn250" id="source_value" value="${article.sourceValue }" placeholder=""></td>
                            </tr>
                            <tr>
                                <td>责任编辑</td>
                                <td><input type="text" class="form-control f_l wn250" id="respon" value="${article.respon }" placeholder=""></td>
                                <td>标签：</td>
                                <td><input type="text" class="form-control f_l wn250" id="seo_key" value="${article.seoKey }"  placeholder=""></td>
                            </tr>
                            <tr>
                                <td>上传图片</td>
                                <td colspan="3">
	                                <div class="clearfix">
	                                	<input id="imgfile" class="f_l" type="file" name="imgfile" />
										<input type="hidden" id="imgUri"  value="${article.imgUri }" />
									    <input type="button" class="f_l ma_l10" value="上传" id="imgUpload" onclick="btnUpload()">
	                                </div>
								    <img alt="" src="${pageContext.request.contextPath}/${article.imgUri }"  float:left; height:100px; width: 100px;" id="showImg" >
					    		</td>
                            </tr>
                            <tr>
                                <td>摘要</td>
                                <td colspan="3">
                                	<textarea style="width:740px;" class="f_l" id="content_summarys"  rows="" cols="">${article.contentSummary }</textarea>
					    		</td>
                            </tr>
                            <tr>
                                <td>文章内容</td>
                                <td colspan="3">
                                <div class="f_l">
                                    <div id="ueditors">
								    	<script name='content' id="editor" type="text/plain" style="width:740px;height:500px;">
											${article.content}
										</script>
									</div>
                                </div>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="3">
                                    <button type="button" onclick="addWenz()" class="btn btn-default f_l">保存</button>
                                    <button type="button" onclick="empty()" class="btn btn-default ma_l20 f_l">置空</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br><br><br>
                </div> 
            </div>
        </div>    
    </div>
<!--/wrap-->
<script type="text/javascript">
function btnUpload(){  
	var elementIds=["imgUri"]; //flag为id、name属性名
	 $.ajaxFileUpload({
          url: 'upload', 
          type: 'post',
          secureuri: false, //
          fileElementId: 'imgfile', // 
          dataType: 'application/json', //
          elementIds: elementIds, //
          success: function(data){ 
        	// console.log(data);
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
	})
} 
function aaa(a){
	 $('#showImg').attr('src',"/dobado_info/"+$("#"+a).val()).show();
}
</script>

<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_bg thewidth100">
        <div class="footer">
        
        
        
        </div>
    </div>
<!--/footer-->

</div>

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