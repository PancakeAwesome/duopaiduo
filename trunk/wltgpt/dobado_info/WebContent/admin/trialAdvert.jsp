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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function(){
		if($.cookie('name') != null && $.cookie('name') != ""){
			 $("#sp").html( $.cookie('name'));
			 $.ajax({
					url : "bannflData",
					type : 'POST',
					dataType : 'JSON',
					async:false,
					success : function(data) {
						//var datas=JSON.parse(data.list);
						var datas=eval(data);
						//console.log(datas);
						var htm = ' <option value=0 >广告分类</option>';
						$.each(datas,function(i,obj){
							htm +=' <option value='+obj.banner_id+'>'+obj.banner_name+'</option>';
						});
						$("#xiala").html(htm);
					},
					error : function() {
						alert("网络异常！");
					}
				});
				erjigaibian(1);
		 }else{
			 layer.msg("登录超时！");
			window.location.href="login.jsp";
		 }
		
	});
	
	function erjigaibian(curr){
		 var bannerId=$('#xiala').val();
		 var isShow=$('#isShow').val();
		 var adName=$("#adName").val();
		 
		 $.ajax({
				url : "adData1",
				type : 'POST',
				dataType : 'JSON',
				data : {
					bannerId : bannerId,
					isShow : isShow,
					adName : adName,
					page: curr,
					rows : 10
					},
				//async:false,
				success : function(data) {
					//var datas=JSON.parse(data);
					var datas = eval(data);
					//console.log(datas);
					var neirhtm = '';
					$.each(datas.list,function(i,obj){
						neirhtm +='<tr class="lh_100">';
						neirhtm +='<td><input type="checkbox" name="" value="" placeholder=""></td>';
						neirhtm +='<td><img src="/dobado_info/'+obj.uri+'" width="100px" height="100px" alt=""></td>';
						neirhtm +='<td>'+obj.title+'</td>';
						neirhtm +='<td>'+obj.banner_name+'</td>';
						if(obj.isShow == 1){
							neirhtm +='<td><i class="icon-ok green"></i></td>';
						}else{
							neirhtm +='<td><i class="icon-remove red"></i><button class="btn btn-default ma_l10">显示</button></td>';
						}
						neirhtm +='<td>'+(obj.sort==null?' ':obj.sort)+'</td>';
						neirhtm +='<td>'+obj.start_time+'</td>';
						neirhtm +='<td>'+obj.end_time+'</td>';
						if(obj.status==0 || obj.status==null)
							neirhtm +='<td>待审核</td>';
						if(obj.status==1)
							neirhtm +='<td>已通过</td>';
						if(obj.status==2)
							neirhtm +='<td>未通过</td>';
						neirhtm +='<td>';
							 neirhtm +='<a href="adupsh?adId='+obj.id+'" ><button type="button" class="btn btn-default ">去审核</button></a>';
						neirhtm +='</td>';
						neirhtm +='<tr>';
					});
					
					$("#neirhtm").html(neirhtm);
                   
					laypage({
					    cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
					    pages: (datas.totalPage),   //通过后台拿到的总页数
					    curr: curr, //当前页
					    jump: function(obj, first){ //触发分页后的回调
					        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
					        	$("#pages").val(obj.curr);
					       	erjigaibian(obj.curr)
					        }
					    }
					});
				},
				error : function() {
					alert("网络异常！");
				}
		 });
	 }
	function updateXians(e){
		$.ajax({
			url : "",
			type : 'POST',
			dataType : 'JSON',
			data:{"menu.article_id":e,"menu.is_show":0},
			async:false,
			success : function(data) {
				if(data>0){
				layer.msg('显示成功！');
				erjigaibian($("#pages").val());
				}else{
					layer.msg('显示失败！请联系管理员');
				}
			},
			error : function() {
				alert("网络异常！");
			}
		});
	}
	function deleteid(ids){
		layer.confirm('确定删除',function(){ 
			$.ajax({
				url : "adDelete",
				type : 'POST',
				dataType : 'JSON',
				data:{ids:ids},
				async:false,
				success : function(data) {
					if(data>0){
					layer.msg('删除成功！');
					erjigaibian($("#pages").val());
					}else{
						layer.msg('删除失败！请联系管理员');
					}
				},
				error : function() {
					alert("网络异常！");
				}
			});
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
            <div class="f_r">欢迎 <span id="sp"></div>
        </div>
        <div class="header">
        
            <nav>
               <ul>
                    <li><a href="">管理首页</a></li>
                    <li><a href="wenz.jsp" >文章管理</a></li>
                    <li><a href="advert.jsp"  class="choice">广告管理</a></li>
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
            <h2>广告管理</h2>
                <ul>
                    <li><a href="advert.jsp">广告列表</a></li>
                    <li><a href="addAdvert.jsp">添加广告</a></li>
                    <li><a href="trialAdvert.jsp"  class="choice">审核广告列表</a></li>
                </ul>
            </div>
            <div class="wrap_r">
                <div class="clearfix SearchOperation">
                <form class="form-inline f_l">
                      <div class="form-group">
                            <label for="">广告名称</label>
                            <input type="email" class="form-control" id="adName" placeholder="输入搜索广告名称">
                      </div>
                      <button type="submit" class="btn btn-default ma_l20">搜索</button>
                </form>
                <a href="addAdvert.jsp"><button type="submit" class="btn btn-default f_r">添加</button></a>
                </div>
                <div class="table">
                    <table  class="table table-bordered">
                        <thead>
                            <tr>
                                <th><input type="checkbox" name="" value="" placeholder=""></th>
                                <th>广告图片</th>
                                <th>广告标题</th>
                                <th>
                                    <select id="xiala" onchange="erjigaibian(1)" class="form-control flei">
                                         
                                    </select>
                                </th>
                                <th>
                                	<select id="isShow" onchange="erjigaibian(1)" class="form-control flei">
                                         <option value=2>全部</option>
                                         <option value=1>显示</option>
                                         <option value=0>不显示</option>
                                    </select>
								</th>
                                <th>排序</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>审核</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody  id="neirhtm" >
                            <tr class="lh_100">
                                <td><input type="checkbox" name="" value="" placeholder=""></td>
                                <td><img src="images/u452.png" width="100px" height="100px" alt=""></td>
                                <td>德法意葡英 欧洲杯各国战队葡萄酒实力分析</td>
                                <td>酒业资讯</td>
                                <td><i class="icon-ok green"></i></td>
                                <td>1</td>
                                <td>2016-07-08 00：00：00</td>
                                <td>2016-07-08 00：00：00</td>
                                <td>未审核</td>
                                <td>
                                    <button type="submit" class="btn btn-default ">修改</button>
                                    <button type="submit" class="btn btn-default ma_l20">删除</button>
                                </td>
                            </tr>
                            <tr  class="lh_100">
                                <td><input type="checkbox" name="" value="" placeholder=""></td>
                                <td><img src="images/u452.png" width="100px" height="100px" alt=""></td>
                                <td>德法意葡英 欧洲杯各国战队葡萄酒实力分析</td>
                                <td>酒业资讯</td>
                                <td><i class="icon-remove red"></i><button type="submit" class="btn btn-default ma_l10">显示</button></td>
                                <td>1</td>
                                <td>2016-07-08 00：00：00</td>
                                <td>2016-07-08 00：00：00</td>
                                <td>已通过</td>
                                <td>
                                    <button type="submit" class="btn btn-default ">修改</button>
                                    <button type="submit" class="btn btn-default ma_l20">删除</button>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                <div id="page"></div>
            </div>
        </div>    
    </div>
<!--/wrap-->

<!--------------------------------------[ footer ]-------------------------------------->
    <div class="footer_bg thewidth100">
        <div class="footer">
        
        
        
        </div>
    </div>
<!--/footer-->

</div>


</body>
</html>