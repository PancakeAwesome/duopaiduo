$(document).ready(function(e) {

	
	if($(".the_btn").length!=0){
		$(".the_btn").hover(function(){$(this).stop().fadeTo(250, 0.8);},function(){$(this).stop().fadeTo(250, 1);});
	}
	if($(".the_hover").length!=0){
		$(".the_hover img").hover(function(){$(this).stop().fadeTo(250, 0.8);},function(){$(this).stop().fadeTo(250, 1);});
	}
	
	
	

	
	//TAB
	if($(".the_tab_head").length!=0){
		$('.the_tab_head a').click(function(e) {
			$('.the_tab_head li').removeClass('checked');
			$(this).parent().addClass('checked');
			$('.tab_box').addClass('none');
			$('#'+$(this).attr('rel')).removeClass('none');
			return false;
		});
	}

	
	//下拉菜单
	navDowns(".menu_cate .menu_cate_title",".menu_cate_list","hover");
	navDowns(".menu_cart",".menu_cart_list","hover");

	//返回顶部
	if($(".toolbar_top").length!=0){
		$(".toolbar_top").on("click", function() {
			$("body, html").animate({
				scrollTop: 0
			}, 500);
			return false;
		});
	}
	
	
	


})




//菜单
function navDowns(cla,clb,clc){
	$(cla).hover(function(){
		if (clb!=""||(clb!=""&&$(this).find(clb).html()!=null)){
			$(this).find(clb).first().stop(false,true).fadeIn(300);
			$(this).addClass(clc);
		}else{
			$(this).stop(false,true).fadeIn(300);
			$(this).addClass(clc+'a');
		}
	},function(){
		//$(this).find(clb).fadeOut();
		if (clb!=""||(clb!=""&&$(this).find(clb).html()!=null)){
			$(this).find(clb).stop(false,true).fadeOut(500);
			$(this).removeClass(clc);
		}else{
			$(this).stop(false,true).fadeOut(500);
			$(this).removeClass(clc+'a');
		}
	})
}



