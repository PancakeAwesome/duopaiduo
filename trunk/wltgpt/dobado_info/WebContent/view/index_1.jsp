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
<title>首页</title>
<link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/index.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<title>首页</title>
<script type="text/javascript">
function loadHeight(){
	parent.document.getElementById('centerFrame').style.height = $("#container").height()+'px';
}
$(function(){
	infoData();
});
function infoData(){
	$.ajax({
		url : "indexData",
		type : 'POST',
		dataType : 'JSON',
		async:false,
		success : function(data) {
			//var datas=JSON.parse(data);
			console.log(data);
			
			//首页轮播图
			var active='';
			var htm='<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">'
				   +'<!-- Indicators -->'
				   +'<ol class="carousel-indicators">';
				   for(var j=0;j<data.ad.length;j++){
					   if(j==0){
							active="active";
						}else{
							active="";
						}
					   htm +='<li data-target="#carousel-example-generic" data-slide-to="'+j+'" class="'+active+'"></li>';
				   }
				   htm +='</ol>';
				   htm +='<div class="carousel-inner" role="listbox">';
			$.each(data.ad,function(i,obj){
				if(i==0){
					active="active";
				}else{
					active="";
				}
				htm +='<div class="item '+active+'">';
				htm +='<a href="http://'+obj.url+'" target="view_window">';
				htm +='<img src="'+obj.uri+'" alt="'+obj.title+'">';
				htm +='<div class="carousel-caption">';
				htm +=obj.title;
				htm +='</div></a>';
				htm +=' </div>';
			});
			htm +='</div></div>';
			$("#banner").html(htm);
			
			
			//酒趣事（轮播图）
			var qushihtml='<div id="carousel-example-generics" class="carousel slide" data-ride="carousel">'
				   +'<!-- Indicators -->'
				   +'<ol class="carousel-indicators">';
				   for(var j=0;j<data.qushi.length;j++){
					   if(j==0){
							active="active";
						}else{
							active="";
						}
					   qushihtml +='<li data-target="#carousel-example-generics" data-slide-to="'+j+'" class="'+active+'"></li>';
				   }
				   qushihtml +='</ol>';
				   qushihtml +='<div class="carousel-inner" role="listbox">';
			$.each(data.qushi,function(i,obj){
				if(i==0){
					active="active";
				}else{
					active="";
				}
				qushihtml +='<div class="item '+active+'">';
				qushihtml +='<a href="http://'+obj.url+'" >';
				qushihtml +='<img src="'+obj.uri+'" alt="'+obj.title+'">';
				qushihtml +='<div class="carousel-caption">';
				qushihtml +=obj.title;
				qushihtml +='</div></a>';
				qushihtml +=' </div>';
			});
			htm +='</div></div>';
			$("#carousel-example-generica").html(qushihtml);
			
			//轮播右边 定义头条
			var toutiaohtm='<ul>';
			
			$.each(data.toutiao,function(i,obj){
				toutiaohtm +='<li class="bi_b_hui">';
				/* toutiaohtm +='<h4><a href="javascript:;">'+obj.title+'</a></h4>'; */
				var ss=1;
				var aa=1;
				$.each(data.toutiaos,function(j,ob){
					
					if(ob.fpid == obj.article_id){
						if(i==0){
							if(ss==1){
								ss=2;
								toutiaohtm +='<h4><a href="article?articleid='+ob.article_id+'" target="view_window">'+ob.title+'</a></h4>';
							}else{
								toutiaohtm +='<p><a href="article?articleid='+ob.article_id+'" target="view_window">'+ob.title+'</a></p>';
							}
						}
						if(i==1){
							if(aa==1){
								aa=2;
								toutiaohtm +='<h4><a href="article?articleid='+ob.article_id+'" target="view_window">'+ob.title+'</a></h4>';
							}else{
								toutiaohtm +='<p><a href="article?articleid='+ob.article_id+'" target="view_window">'+ob.title+'</a></p>';
							}
						}
					}
				});
				toutiaohtm +='</li>';
				
			});
			toutiaohtm +='</ul>';
			$("#toutiaohtm").html(toutiaohtm);
			
			//酒业资讯
			var jiuzxunhtm='';
			$.each(data.zxun,function(i,obj){
				jiuzxunhtm +='<div class="article clearfix">';
				if(i==0){
					jiuzxunhtm +='<img style="width: 120px; height: 100px;" src="'+obj.img_uri+'" alt="">';
					jiuzxunhtm +='<a href="article?articleid='+obj.article_id+'" target="view_window">';
					jiuzxunhtm +='<h4 class="img">'+(obj.title).substr(0,13)+'</h4>';
					jiuzxunhtm +='</a>';
					jiuzxunhtm +='<p class="img">'+obj.content_summary+'</p>';
				}else{
					jiuzxunhtm +='<a href="article?articleid='+obj.article_id+'" target="view_window">';
					jiuzxunhtm +='<h4>'+(obj.title).substr(0,21)+'</h4>';
					jiuzxunhtm +='</a>';
					
				}
				jiuzxunhtm +='</div>';
			});
			$("#jiuzxunhtm").html(jiuzxunhtm);
			
			//酒文化
			var jiuwenhuahtm='';
			$.each(data.wenhua,function(i,obj){
				jiuwenhuahtm +='<div class="article clearfix">';
				if(i==0){
					jiuwenhuahtm +='<img style="width: 120px; height: 100px;" src="'+obj.img_uri+'" alt="">';
					jiuwenhuahtm +='<a href="article?articleid='+obj.article_id+'" target="view_window">';
					jiuwenhuahtm +='<h4 class="img">'+(obj.title).substr(0,13)+'</h4>';
					jiuwenhuahtm +='</a>';
					jiuwenhuahtm +='<p class="img">'+obj.content_summary+'</p>';
				}else{
					jiuwenhuahtm +='<a href="article?articleid='+obj.article_id+'" target="view_window">';
					jiuwenhuahtm +='<h4>'+(obj.title).substr(0,21)+'</h4>';
					jiuwenhuahtm +='</a>';
				}
				jiuwenhuahtm +='</div>';
			});
			$("#jiuwenhuahtm").html(jiuwenhuahtm);
			
			//品牌
			var pinpaihtm='<td class="b_lan"><span class="mt_30">品牌</span></td>';
			$.each(data.brand,function(i,obj){
				pinpaihtm +='<td><img style="width: 60px; height: 60px;" src="'+obj.brandImg+'" alt=""><summary>'+obj.brandName+'</summary></td>';
			});
			$("#pinpaihtm").html(pinpaihtm);
			
			//价格
			var i1=0;
			var jiagehtm='<td class="b_lan"><span class="mt_15">价格</span></td>';
			$.each(data.price,function(i,obj){
				if(i1%2==0)
				  	jiagehtm +='<td><p>'+obj.priceName+'元</p>';
				if(i1%2==1)
					jiagehtm +='<summary>'+obj.priceName+'元</summary></td>';
				i1 ++;
			});
			$("#jiagehtm").html(jiagehtm);
			
			//度数
			var dushuhtm='<td class="b_lan"><span>度数</span></td>';
			$.each(data.degrees,function(i,obj){
				dushuhtm +='<td><a href="javascript:;">'+obj.degreesName+'</a></td>';
			});
			$("#dushuhtm").html(dushuhtm);
			
			//香型
			var flavorhtm='<td class="b_lan"><span>度数</span></td>';
			$.each(data.flavor,function(i,obj){
				flavorhtm +='<td><a href="javascript:;">'+obj.flavorName+'</a></td>';
			});
			$("#flavorhtm").html(flavorhtm);
			
			//酒业动态 -2级类
			var dongtaihtm='';
			$.each(data.dong,function(i,obj){
				dongtaihtm +=' <a href="jiuchangdongtai" target="centerFrame">'+obj.title+'</a>';
			});
			$("#dongtaihtm").html(dongtaihtm);
			//酒业动态 -2级类-图片
			var dongtaiimg='';
			$.each(data.dong,function(i,obj){
				if(i==0){
					dongtaiimg +='<div class="picture first">';
					dongtaiimg +='<a href="jiuchangdongtai" target="centerFrame">';
					dongtaiimg +='<img style="width: 375px; height: 227px;" src="'+obj.img_uri+'" alt="">';
					dongtaiimg +='<div>'+obj.imageText+'</div>';
					dongtaiimg +='</a>';
					dongtaiimg +='</div>';
				}else if(i==1){
					dongtaiimg +='<div class="clearfix">';
					dongtaiimg +='<div class="picture f_l wd48">';
					dongtaiimg +='<a href="jiuchangdongtai" target="centerFrame">';
					dongtaiimg +='<img style="width: 180px; height: 133px;" src="'+obj.img_uri+'" alt="">';
					dongtaiimg +='<div>'+obj.imageText+'</div>';
					dongtaiimg +=' </a></div>';
				}else if(i==2){
					dongtaiimg +='<div class="picture f_r wd48">';
					dongtaiimg +='<a href="jiuchangdongtai" target="centerFrame">';
					dongtaiimg +='<img style="width: 180px; height: 133px;" src="'+obj.img_uri+'" alt="">';
					dongtaiimg +='<div>'+obj.imageText+'</div>';
					dongtaiimg +='</a></div>';
					dongtaiimg +='</div>';
				}
			});
			$("#dongtaiimg").html(dongtaiimg);
			
			
			//酒业动态 2-3级标题 
			var donghtm='';
			$.each(data.dong,function(i,obj){
				donghtm +='<div class="cla">';
				donghtm +='<h5><span>'+obj.title+'</span></h5>';
				donghtm +='<ul>';
				$.each(data.dongs,function(j,ob){
					if(ob.fpid == obj.article_id){
						donghtm +='<li><a href="article?articleid='+ob.article_id+'" target="view_window">'+ob.title+'</a></li>';
					}
				});
				donghtm +='<ul>';
				donghtm +='</div>';
			});
			$("#donghtm").html(donghtm);
			

			//餐厅美食 -2级类
			var cantinghtm='';
			$.each(data.can,function(i,obj){
				cantinghtm +=' <a href="canyinmeishi" target="centerFrame">'+obj.title+'</a>';
			});
			$("#cantinghtm").html(cantinghtm);
			
			//餐厅美食-2级类-图片
			var cantingimg='';
			$.each(data.can,function(i,obj){
				if(i==0){
					cantingimg +='<div class="picture first">';
					cantingimg +='<a href="canyinmeishi" target="centerFrame">';
					cantingimg +='<img style="width: 375px; height: 227px;" src="'+obj.img_uri+'" alt="">';
					cantingimg +='<div>'+obj.imageText+'</div>';
					cantingimg +='</a>';
					cantingimg +='</div>';
				}else if(i==1){
					cantingimg +='<div class="clearfix">';
					cantingimg +='<div class="picture f_l wd48">';
					cantingimg +='<a href="canyinmeishi" target="centerFrame">';
					cantingimg +='<img style="width: 180px; height: 133px;" src="'+obj.img_uri+'" alt="">';
					cantingimg +='<div>'+obj.imageText+'</div>';
					cantingimg +=' </a></div>';
				}else if(i==2){
					cantingimg +='<div class="picture f_r wd48">';
					cantingimg +='<a href="canyinmeishi" target="centerFrame">';
					cantingimg +='<img style="width: 180px; height: 133px;" src="'+obj.img_uri+'" alt="">';
					cantingimg +='<div>'+obj.imageText+'</div>';
					cantingimg +='</a></div>';
					cantingimg +='</div>';
				}
			});
			$("#cantingimg").html(cantingimg);
			
			//餐厅美食 2-3级标题 
			var canhtm='';
			$.each(data.can,function(i,obj){
				canhtm +='<div class="cla">';
				canhtm +='<h5><span>'+obj.title+'</span></h5>';
				canhtm +='<ul>';
				$.each(data.cans,function(j,ob){
					if(ob.fpid == obj.article_id){
						canhtm +='<li><a href="article?articleid='+ob.article_id+'" target="view_window">'+ob.title+'</a></li>';
					}
				});
				canhtm +='<ul>';
				canhtm +='</div>';
			});
			$("#canhtm").html(canhtm);
			
			//活动
			var huodonghtm='';
			$.each(data.huodong,function(i,obj){
				huodonghtm +='<div class="article clearfix">';
				huodonghtm +='<img  style="width: 120px; height: 115px;" src="'+obj.img_uri+'" alt="">';
				huodonghtm +='<a href="article?articleid='+obj.article_id+'" target="view_window">';
				huodonghtm +='<h4 class="img">'+obj.title+'</h4></a>';
				huodonghtm +='<p class="img">'+obj.content_summary+'</p>';
				huodonghtm +='</div>';
			});
			$("#huodonghtm").html(huodonghtm);
			//品鉴
			var pinjianhtm='';
			$.each(data.pinjian,function(i,obj){
				pinjianhtm +='<div class="article clearfix">';
				pinjianhtm +='<img  style="width: 120px; height: 115px;" src="'+obj.img_uri+'" alt="">';
				pinjianhtm +='<a href="article?articleid='+obj.article_id+'" target="view_window">';
				pinjianhtm +='<h4 class="img">'+obj.title+'</h4>';
				pinjianhtm +='</a>';
				pinjianhtm +='<p class="img">'+obj.content_summary+'</p></div>';
			});
			$("#pinjian").html(pinjianhtm);
			
			//播客
			var bokehtm='';
			$.each(data.boke,function(i,obj){
				bokehtm +='<div class="picture">';
				bokehtm +='<a href="javascript:;">';
				bokehtm +='<img src="'+obj.screenImgUrl+'" alt="">';
				bokehtm +='<div>'+obj.imgText+'</div>';
				bokehtm +='<img class="play" src="images/index/play.png" alt="">';
				bokehtm +='</a></div>';
			});
			$("#bokehtm").html(bokehtm);
			 
			//美图
			var meituhtm='';
			var width = 0;
			var height =0;
			$.each(data.meitu,function(i,obj){
				if(i==0){
					width = 380;
					height =355;
				}else if(i==5){
					width = 333;
					height =175;
				}else if(i%2==1){
					width = 284;
					height =175;
				}else{
					width = 235;
					height =175;
				}
				meituhtm +='<div class="clearfix f_l">';
				meituhtm +='<div class="picture">';
				meituhtm +='<a href="javascript:;">';
				meituhtm +='<img style="width:'+width+'px; height:'+height+'px;" src="'+obj.screenImgUrl+'" alt="">';
				meituhtm +='<div>'+obj.imgText+'</div>';
				meituhtm +='</a></div></div>';
			});
			$("#meituhtm").html(meituhtm);
			
			$('.podcast').find('.picture').hover(
				      function(){
				         $(this).find('.play').css('display','block');
				      },function(){
				         $(this).find('.play').css('display','none');
				      }
				    );
		},
		error : function() {
			alert("网络异常！");
		}
	});
}
</script>
</head>
<body id="container" onload="loadHeight()">
   <div class="wrap_bg thewidth100">    
        <div class="wrap thewidth">
        <!-- index1 -->
          <div class="clearfix index1">
              <div id="banner"  class="f_l">
                  
              </div>
              <div id="toutiaohtm" class="f_r">
                
              </div>
          </div>
          <!-- /index1 -->

          <div class="index2 clearfix">
              <div class="index2_l">
                  <h3 class="small">
                    <span class="c_lan">酒业资讯</span>
                    <a class="em" href="jiuyezixun">+ 更多</a>
                  </h3>
                  <div id="jiuzxunhtm"></div>
              </div>
              <!-- /index2_l -->
              <div class="index2_l">
                  <h3 class="small">
                    <span class="c_lan">酒文化</span>
                    <a class="em" href="jiuwenhua">+ 更多</a>
                  </h3>
                  <div id="jiuwenhuahtm"></div>
              </div>
              <!-- /index2_l -->
              <div class="index2_r">
                  <h3 class="small">
                    <span class="c_lan">名酒库</span>
                    <a class="em" href="mingjiuku">+ 更多</a>
                  </h3>
                  <table class="table a_c">
                    <tbody>
                      <tr id="pinpaihtm">
                      </tr>
                      <tr id="jiagehtm">
                      </tr>
                      <tr id="dushuhtm">
                      </tr>
                      <tr id="flavorhtm">
                      </tr>
                    </tbody>
                  </table>
              </div>
              <!-- /index2_r -->
          </div>
          <!-- /index2 -->
          <div class="index3 clearfix">
           <!-- index3左 -->
              <div class="f_l">
                  <h3 class="small">
                    <span class="c_lan">酒厂动态</span>
                    <a class="em" href="jiuchangdongtai" target="centerFrame">+ 更多</a>
                    <div id="dongtaihtm" class="small_l">
                        
                    </div>
                  </h3>
                  <div class="index_con clearfix ">
                      <div id="dongtaiimg" class="f_l">
                       

                      </div>
                      <!-- /index_con f_l -->
                      <div id="donghtm" class="f_r c_lan">
                       
                      </div>
                      <!-- /index_con f_r -->
                  </div>
              </div>
               <!-- /index3左 -->
              <!-- index3右 -->
              <div class="f_r">
                  <h3 class="small">
                    <span class="c_lan">活动</span>
                    <a class="em" href="huodong">+ 更多</a>
                  </h3>
                  <div id="huodonghtm" class="index3_r">
                        
                  </div>
                        
              </div>
              <!-- /index3右 -->
          </div>
          <!-- /index3 -->
          <div class="index3 clearfix">
          <!-- index3左 -->
              <div class="f_l">
                  <h3 class="small">
                    <span class="c_lan">餐厅美食</span>
                    <a class="em" href="canyinmeishi">+ 更多</a>
                    <div id="cantinghtm" class="small_l">
                        <a href="canyinmeishi">餐酒搭配</a>
                        <a href="canyinmeishi">餐厅丰尚</a>
                        <a href="canyinmeishi">美酒探密</a>
                    </div>
                  </h3>
                  <div class="index_con clearfix ">
                      <div id="cantingimg" class="f_l">
                      </div>
                      <!-- /index_con f_l -->

                      <div id="canhtm" class="f_r c_lan">
                        
                      </div>
                      <!-- /index_con f_r -->
                  </div>
                  <!-- /index_con -->
              </div>
              <!-- /index3左 -->
              <!-- index3右 -->
              <div class="f_r">
                  <h3 class="small">
                    <span class="c_lan">品鉴</span>
                    <a class="em" href="jiupinjia">+ 更多</a>
                  </h3>
                  <div id="pinjian" class="index3_r">
                        
                  </div>
              </div>
              <!-- /index3右 -->
          </div>
          <!-- /index3 -->
          <div class="index2 clearfix mt_40">
              <div class="index2_l">
                  <h3 class="small">
                    <span class="c_lan">酒趣事</span>
                    <a class="em" href="jiuqushi">+ 更多</a>
                  </h3>
                  <div id="carousel-example-generica" class="carousel slide" data-ride="carousel">
                      
                    </div>
              </div>
              <div class="index2_l">
                  <h3 class="small">
                    <span class="c_lan">播客</span>
                    <a class="em" href="boke">+ 更多</a>
                  </h3>
                  <div id="bokehtm" class="clearfix podcast">
                      <div class="picture f_l wd48">
                        <a href="javascript:;">
                          <img src="images/index/index_33.jpg" alt="">
                          <div>
                            中国白酒成功拓展
                          </div>
                          <img class="play" src="images/index/play.png" alt="">
                        </a>
                      </div>
                      <div class="picture f_r wd48">
                        <a href="javascript:;">
                        <img src="images/index/index_33.jpg" alt="">
                        <div>
                          中国白酒成功拓展
                        </div>
                        <img class="play" src="images/index/play.png" alt="">
                      </a>
                      </div>
                      <div class="picture f_l wd48">
                        <a href="javascript:;">
                          <img src="images/index/index_33.jpg" alt="">
                          <div>
                            中国白酒成功拓展
                          </div>
                          <img class="play" src="images/index/play.png" alt="">
                        </a>
                      </div>
                      <div class="picture f_r wd48">
                        <a href="javascript:;">
                        <img src="images/index/index_33.jpg" alt="">
                        <div>
                          中国白酒成功拓展
                        </div>
                        <img class="play" src="images/index/play.png" alt="">
                      </a>
                      </div>
                      <div class="picture f_l wd48">
                        <a href="javascript:;">
                          <img src="images/index/index_34.jpg" alt="">
                          <div>
                            中国白酒成功拓展
                          </div>
                          <img class="play" src="images/index/play.png" alt="">
                        </a>
                      </div>
                      <div class="picture f_r wd48">
                        <a href="javascript:;">
                        <img src="images/index/index_34.jpg" alt="">
                        <div>
                          中国白酒成功拓展
                        </div>
                        <img class="play" src="images/index/play.png" alt="">
                      </a>
                      </div>
                  </div>
              </div>
              <div class="index2_r">
                  <h3 class="small">
                    <span class="c_lan">专题回顾</span>
                    <a class="em" href="zhuanti">+ 更多</a>
                  </h3>
                   <div class="cla">
                    <div class="picture">
                        <a href="javascript:;">
                        <img src="images/index/index_05.jpg" alt="">
                        <div>
                          中国白酒成功拓展
                        </div>
                      </a>
                    </div>
                    <h5><span class="spanbei">酒市财经</span></h5>
                    <ul>
                      <li><a href="javascript:;">川酒1-5月主营业务收入791亿5月主营业务收入791亿5月主营业务收入791亿5月主营业务收入791亿</a></li>
                      <li><a href="javascript:;">上市酒企前三季仅剩一家亏损</a></li>
                    </ul>
                  </div>
                   <div class="cla">
                    <h5><span class="spanbei">酒市财经</span></h5>
                    <ul>
                      <li><a href="javascript:;">川酒1-5月主营业务收入791亿5月主营业务收入791亿5月主营业务收入791亿5月主营业务收入791亿</a></li>
                      <li><a href="javascript:;">上市酒企前三季仅剩一家亏损</a></li>
                    </ul>
                  </div>
              </div>
          </div>
          <!-- /index2 -->
          <div class="index_chart clearfix mt_40">
            <h3 class="small">
              <span class="c_lan">旅游美图</span>
              <a class="em" href="javascript:;">+ 更多</a>
            </h3>
            <div id="meituhtm" class="chart clearfix">
             </div>        
          </div>
        <!-- / index_chart-->


        </div>    
    </div>
</body>
</html>