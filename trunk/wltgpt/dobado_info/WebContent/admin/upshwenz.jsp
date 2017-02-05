<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>审核文章</title>
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
	function addWenz(e){
		var status;
		var opinion="";
		var is_show;
		if(e==2){
			if($("#opinion").val() == null || $("#opinion").val() == ""){
				layer.msg("请输入审批意见！");
				return;
			}else{
				opinion=$("#opinion").val();
				status=e;
				is_show=1;
			}
		}else{
			status=e;
			is_show=0;
		}
		 var article_id=$("#articleId").val();  
		 
		 var param = {
				 	"menu.article_id":article_id,
		            "menu.status":status,
		            "menu.is_show":is_show,
		            "menu.opinion":opinion
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
						alert("审核成功!");
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
            <div class="f_r">欢迎<span id="sp"> </div>
        </div>
        <div class="header">
            <nav>
                <ul>
                    <li><a href="">管理首页</a></li>
                    <li><a href="wenz.jsp" class="choice" >文章管理</a></li>
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
                    <li><a href="addWenz.jsp">添加资讯文章</a></li>
                    <li><a href="shWenz.jsp">审核文章列表</a></li>
                </ul>
            </div>
            <div class="wrap_r">
                <div class="clearfix SearchOperation">
                    <h4>审核资讯文章</h4>
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
                                	
                                    <select onchange="erjigaibian('xiala')"  id="xiala" disabled  class="form-control f_l wn250">
                                        
                                    </select>
                                    
                                    <select  id="erjifl" disabled  class="form-control f_l none wn250">
                                          
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>文章标题：</td>
                                <td><input type="text" class="form-control f_l wn250" disabled  id="titles" value="${article.title }" placeholder="请填写文章标题"></td>
                                <td>排序：</td>
                                <td><input type="text" class="form-control f_l wn250" disabled  id="sort" value="${article.sort }" placeholder=""></td>
                            
                            </tr>
                            <tr>
                                <td>文章来源</td>
                                <td><input type="text" class="form-control f_l wn250" disabled  id="source" value="${article.source }" placeholder=""></td>
                                <td>作者：</td>
                                <td><input type="text" class="form-control f_l wn250" disabled  id="source_value" value="${article.sourceValue }" placeholder=""></td>
                            </tr>
                            <tr>
                                <td>责任编辑</td>
                                <td><input type="text" class="form-control f_l wn250" disabled  id="respon" value="${article.respon }" placeholder=""></td>
                                <td>标签：</td>
                                <td><input type="text" class="form-control f_l wn250" disabled  id="seo_key" value="${article.seoKey }"  placeholder=""></td>
                            </tr>
                            <tr>
                                <td>图片</td>
                                <td colspan="3">
	                                <div class="clearfix">
										<input type="hidden" id="imgUri"  disabled  value="${article.imgUri }" />
	                                </div>
								    <img alt="" src="${pageContext.request.contextPath}/${article.imgUri }"  float:left; height:100px; width: 100px;" id="showImg" >
					    		</td>
                            </tr>
                            <tr>
                                <td>摘要</td>
                                <td colspan="3">
                                	<textarea style="width:740px;" disabled  class="f_l" id="content_summarys"  rows="" cols="">${article.contentSummary }</textarea>
					    		</td>
                            </tr>
                            <tr>
                                <td>文章内容</td>
                                <td colspan="3">
                                <div class="f_l">
                                    <div id="ueditors">
								    	<div  id="editor" type="text/plain" style="width:740px;height:500px; float: left;text-align: left; overflow: auto;">
											${article.content}
										</div>
									</div>
                                </div>
                                </td>
                            </tr>
                            <tr>
                                    <td>审批意见</td>
                                    <td colspan="3">
                                        <textarea class="form-control" id="opinion" name="" placeholder="主编：XXX   时间： 2016-07-01  "></textarea>
                                    </td>
                                </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td colspan="3">
                                    <button type="button" onclick="addWenz(1)" class="btn btn-default f_l">通过</button>
                                    <button type="button" onclick="addWenz(2)" class="btn btn-default ma_l20 f_l">拒绝</button>
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

</body>
</html>