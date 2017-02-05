<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<meta name="keywords" content="酒,酒业,白酒,葡萄酒,啤酒,果酒,酱香酒,浓香酒,米香酒,贵香坊,酒业资讯,多拍多酒业" /> 
<meta name="description" content="多拍多酒业频道，以互联网+的媒体视野，权威、深刻的独立视角，全景呈现酒类舆情走向、市场趋势、产业转型、业态创新、消费价值，融合酒业新生态，创领酒道新主流。多拍多酒业，让饮酒更更快乐。" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>多拍多酒业网络推广平台</title><link rel="Stylesheet" href="css/style.css" type="text/css" />
<link rel="Stylesheet" href="css/son.css" type="text/css" />
<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/public/laypage/laypage.js"></script>
<script type="text/javascript">
            var roll = $.noConflict(true);
        </script>
<style type="text/css">
	.content p{text-indent:2em;}
	.content h5{font-weight: 800};
</style>        
</head>

<body>

<div id="container" class="thewidth100">

<!--header-->
    <div class="header_article clearfix thewidth100"> 
        <div class="header thewidth">
            <div class="f_l clearfix ">
            <div>
              <a href="/dobado_info"><img src="images/login/login_03.png" alt=""></a>
            </div>
            
                <p class="c_lan">
                  <a href="#" onClick="javascript :history.back(-1);" >多拍多资讯</a>
                  <span>></span>
                  <a href="javascript:;">资讯正文</a>
                </p>
            </div>
            <div class="f_r clearfix">
            <div class="f_l sou" >
              <input type="text" name="" value="" placeholder="名酒/资讯关键词">
              <span></span>
            </div>
             
            <!--  <p class="f_l">
               <a href="my.html">登录</a>
                |
                <a href="login.html">注册</a>
             </p> -->
            </div>
        
        </div>
    </div>
<!--------------------------------------[ header ]-------------------------------------->
<!--wrap-->
    <div class="wrap_bg thewidth100 article">    
        <div class="wrap thewidth clearfix">
          <div class="f_l">
              <h3>${article.title}</h3>
              <div class="clearfix basic">
                  <div class="f_l">
                    <p>${article.addTime.toString().substring(0, 10)}</p>
                    <div class="clearfix">
                        <div class=" f_l wn200">来源：${article.source}</div>
                        <div class=" f_l wn200">作者：${article.sourceValue}</div>
                    </div>
                  </div>
                  <div class="f_r">
                    <div class="share clearfix bdsharebuttonbox">
                      <span class="f_l">分享到：</span>
                      <a href="#" class="ion" data-cmd="weixin" title="分享到微信" class=""></a>
                      <a href="#" class="ion ion_1" data-cmd="qzone" title="分享到QQ空间" class=""></a>
                      <a href="#" class="ion ion_2" data-cmd="tsina" title="分享到新浪微博" class=""></a>
                      <a href="#" class="ion ion_3" data-cmd="renren" title="分享到人人网" class=""></a>
                    </div>
                    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
                  </div>
              </div>
              <div class="content">
                ${article.content}
                
                <div class="a_c mt_40" style="text-align:center;" >
                    <img class="mt_40" src="images/public/Newpage_28.jpg" alt="">
                </div>
                <div style="text-align:center; ">关注多拍多酒业官方微信</div>
            
              </div>
              <!-- /content -->
              <div class="sign">
                  <p>责任编辑：${article.respon }</p>
                  <p>标签： 
                    <a class="c_lan" href="javascript:;">白酒</a> 
                    <a class="c_lan" href="javascript:;">关税</a>
                    <a class="c_lan" href="javascript:;">非洲</a>
                  </p>
                  <p>
                      <div class="share clearfix bdsharebuttonbox">
                          <span class="f_l">分享到：</span>
                          <a href="#" class="ion" data-cmd="weixin" title="分享到微信" class=""></a>
                          <a href="#" class="ion ion_1" data-cmd="qzone" title="分享到QQ空间" class=""></a>
                          <a href="#" class="ion ion_2" data-cmd="tsina" title="分享到新浪微博" class=""></a>
                          <a href="#" class="ion ion_3" data-cmd="renren" title="分享到人人网" class=""></a>
                      </div>
                  </p>
              </div> 

              <div class="comment">
                  <div class="clearfix comment_t">
                     <h4 class="f_l">网友评论</h4>
                     <p class="f_r"><span class="red">15</span> 人参与<span class="red ml_20">20</span> 条跟帖</p>
                  </div>
                  <div class="textarea">
                    <textarea name="" class="thewidth100" rows="7"></textarea>
                    <p>文明上网，不传谣言，登陆发表评论！</p>
                  </div>
                  
                  <div class="clearfix mt_15 comment_t">
                     <!--  <div class="f_l">
                        <p>一键登录: <a class="c_lan ml_20" href="javascript:;">多拍多</a>
                                     <span class="c_lan">|</span>
                                     <a class="c_lan" href="javascript:;">微信账号</a> 
                                     <span class="c_lan">|</span>
                                     <a class="c_lan" href="javascript:;">QQ账号</a>
                        </p>
                      </div>
                      <div class="f_r">
                          <a href="javascript:;">登录</a> 
                          |
                          <a href="javascript:;">注册</a>
                          <input type="submit" class="b_lan" name="" value="发表评论">
                      </div> -->
                  </div>
              </div>
              <div class="comment_li">
                  <h4>热门评论</h4>
                  <div>
                      <h5><a href="javascript:;">用户名用户名用户名</a></h5>
                      <p> 分白酒文化属于上一代人啊！ </p>
                      <div class="clearfix">
                        2016-05-05   16 : 00
                        <p class="f_r">赞成 <span class="red mr_20">9</span>回复 <span class="red ">5</span> </p>
                      </div>
                  </div>
              </div>
              <div class="next comment_li">
              <p>下一篇：</p>
                  <h4 style="text-indent: 2em"><a href="javascript:;">保健酒“亲民价”保文化塑主流 实效和实惠为主流</a></h4>
                  <p style="text-indent: 2em" class="hang3">两年前的中国白酒业可能想不到，一个政策的实行会给全行业带来大的变化。而已经在快速发展道路上疾驰多年的白酒从业人员，将面对还
                  </p>
              </div>
              <div class="contents links">
                  <div class="links_list"><a class="link_prev" href="javascript:;" hidefocus="true" title="上一组" id="link_prev">上一组</a>
                      <div id="links">
                          <ul id="slideContainer" class="slideContainer" >
                              <li><a href="#"><img src="images/public/Newpage_32.jpg"></a></li>
                              <li><a href="#"><img src="images/public/Newpage_34.jpg"></a></li>
                              <li><a href="#"><img src="images/public/Newpage_36.jpg"></a></li>
                              <li><a href="#"><img src="images/public/Newpage_38.jpg"></a></li>
                              <li><a href="#"><img src="images/public/Newpage_32.jpg"></a></li>
                              <li><a href="#"><img src="images/public/Newpage_34.jpg"></a></li>
                          </ul>
                      </div>
                      <a class="link_next" href="javascript:;" hidefocus="true" title="下一组" id="link_next">下一组</a> </div>
              </div>
              <!--links end-->
          </div>
          <div class="f_r">
              <img class="mt_15" src="images/public/Newpage_07.jpg" alt="">
              <img class="mt_15" src="images/public/Newpage_23.jpg" alt="">
              <img class="mt_15" src="images/public/Newpage_25.jpg" alt="">
          </div>
        </div>    
    </div>
<!--/wrap-->



<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_bg thewidth100 mt_40">
        <div class="footer thewidth clearfix">
          <div class="footer_l">
            <h4>友情链接</h4>
            <div class="clearfix">
              <a href="javascript:;">中国酒业协会</a>
              <a href="javascript:;">中国酒类流通协会</a>
              <a href="javascript:;">华夏酒报</a>
              <a href="javascript:;">新华食品</a>
              <a href="javascript:;">意酒网</a>
              <a href="javascript:;">佳酿网</a>
              <a href="javascript:;">酒媒网</a>
              <a href="javascript:;">90天使汇</a>
              <a href="javascript:;">凤凰周刊</a>
              <a href="javascript:;">路透</a>
              <a href="javascript:;">凤凰金融</a>
              <a href="javascript:;">全景网</a>
              <a href="javascript:;">华夏酒报</a>
              <a href="javascript:;">新华食品</a>
              <a href="javascript:;">意酒网</a>
            </div>
          </div>
          <div class="footer_z">
            <h4>优秀栏目</h4>

            <div class="clearfix">
              <ul>
                <li><a href="javascript:;">名酒库</a></li>
                <li><a href="javascript:;">酒业资讯</a></li>
                <li><a href="javascript:;">酒厂动态</a></li>
                <li><a href="javascript:;">餐厅美食</a></li>
              </ul>
              <ul>
                <li><a href="javascript:;">酒文化</a></li>
                <li><a href="javascript:;">酒旅游</a></li>
                <li><a href="javascript:;">酒趣事</a></li>
                <li><a href="javascript:;">品鉴</a></li>
              </ul>
              <ul>
                <li><a href="javascript:;">播客</a></li>
                <li><a href="javascript:;">活动</a></li>
                <li><a href="javascript:;">专区</a></li>
                <li><a href="javascript:;">商城</a></li>
              </ul>
            </div>
          </div>
          <div class="footer_r">
            <h4><img src="images/login/login_03.png" alt=""> 多拍多资讯站</h4>
            <div class="clearfix">
              <p>站点：www.dobado.cn</p>
              <p>邮箱：MD123@digirun</p>
              <p>电话：025-25463215</p>
              <p>地址：南京雨花区软件大道润</p>
            </div>
            <img src="images/public/er_03.jpg" alt="">
          </div>
        </div>
    </div>
<!--/footer-->

</div>
</body>
<!--links end-->
<script type="text/javascript" src="js/roll.js"></script>
</html>
