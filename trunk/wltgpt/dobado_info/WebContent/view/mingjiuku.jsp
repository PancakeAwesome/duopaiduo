<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = "http://jiu.dobado.cn/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="酒,酒业,白酒,葡萄酒,啤酒,果酒,酱香酒,浓香酒,米香酒,贵香坊,酒业资讯,多拍多酒业" /> 
<meta name="description" content="多拍多酒业频道，以互联网+的媒体视野，权威、深刻的独立视角，全景呈现酒类舆情走向、市场趋势、产业转型、业态创新、消费价值，融合酒业新生态，创领酒道新主流。多拍多酒业，让饮酒更更快乐。" />
<title>名酒库</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/index.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/public/laypage/laypage.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!-- <script type="text/javascript"> 
function loadHeight(){
	parent.document.getElementById('centerFrame').style.height = ($("#container").height()+50)+'px';
}
$(function(){
	info();
	pageData(1);
});
function info(){
	$.ajax({
		url : "canyinData",
		type : 'POST',
		dataType : 'JSON',
		async:false,
		success : function(data) {
			//var datas=JSON.parse(data);
			console.log(data);
			var paihangbang = '';
			$.each(data.paihang,function(i,obj){
                
                paihangbang +='<div class="article clearfix">';
                paihangbang +='<img style="width: 120px; height: 100px;" src="'+obj.img_uri+'" alt="">';
                paihangbang +='<div><h4 class="img"><a href="article?articleid='+obj.article_id+'" target="view_window">'+obj.title+'</a></h4>';
                paihangbang +='<p class="img">'+obj.content_summary+'</p>';
                paihangbang +='</div></div>';
            });
            $("#paihangbang").html(paihangbang);
            
            var xinwen = '';
            $.each(data.xinwen,function(i,obj){
                
                xinwen +='<div class="article clearfix">';
                xinwen +='<img style="width: 120px; height: 100px;" src="'+obj.img_uri+'" alt="">';
                xinwen +='<div><h4 class="img"><a href="article?articleid='+obj.article_id+'" target="view_window">'+obj.title+'</a></h4>';
                xinwen +='<p class="img">'+obj.content_summary+'</p>';
                xinwen +='</div></div>';
            });
            $("#xinwen").html(xinwen);
		},
		error : function() {
			alert("网络异常！");
		}
	});
};
function pageData(curr){
	$.ajax({
		url : "pageData",
		type : 'POST',
		dataType : 'JSON',
		data:{page: curr,rows : 7,aids : 29},
		async:false,
		success : function(data) {
			//var datas=JSON.parse(data);
			console.log(data);
			var boodhtm='';
			$.each(data.list,function(i,obj){
				
                boodhtm +='<div class="article clearfix">';
                boodhtm +='<img style="width: 120px; height: 120px;" src="'+obj.img_uri+'" alt="'+obj.title+'">';
                boodhtm +='<div><h4 class="img"><a href="article?articleid='+obj.article_id+'" target="view_window">'+obj.title+'</h4></a>';
                boodhtm +='<summary>'+obj.add_time+'</summary>';
                boodhtm +='<p class="img">'+obj.content_summary+'</p>';
                boodhtm +='</div></div>';
			});
			$("#boodhtm").html(boodhtm);
			laypage({
	             cont: 'paging', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
	             pages: (data.totalPage),   //通过后台拿到的总页数
	             curr: curr, //当前页
	             skin: 'yahei',
	             jump: function(obj, first){ //触发分页后的回调
	                 if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                	 pageData(obj.curr);
	                 }
	             }
	         });
			loadHeight();
		},
		error : function() {
			alert("网络异常！");
		}
	});
}
</script>-->
</head>

<body id="container">

<div class="thewidth100">
<p>系统正在搭建中....</p>
<!-- <!--wrap
    <div class="wrap_bg thewidth100 dongtai">    
        <div class="wrap thewidth ">
            <div  class="index3 clearfix">
            index3左
              <div class="f_l">
                  <h3 class="small">
                    <span class="c_lan">名酒库</span>
                  </h3>
                  <div id="banner" class="index1">
                    banner轮转
                  </div>
                  <div id="boodhtm" class="dongtai_1">
                       主体  
                  </div>
                  <div class="paging" id="paging" style="eight: 100px">
                    111
                  </div>
                        
              </div>
               /index3左
              index3右
              <div class="f_r">
                  <h3 class="small">
                    <span class="c_lan">名酒库</span>
                  </h3>
                  <div id="paihangbang" class="dongtai_2">
                        
                  </div>

				<div class="mt_40">
  

                  <h3 class="small">
                    <span class="c_lan">热门新闻</span>
                  </h3>
                  <div id="xinwen" class="dongtai_2">
                        
                  </div>
			</div>
              </div>
              /index3右
          </div>
          /index3
        </div>    
    </div>
/wrap -->
</div>
</body>
</html>