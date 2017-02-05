
function postcheck(){
try{
		if (document.form.name.value==""){
			alert('请填写姓名！');
			document.form.name.focus();
			return false;
		}
		var name = /^[\u4e00-\u9fa5]{2,6}$/;
		if (!name.test(document.form.name.value)){
			alert('姓名格式不正确，请重新填写！');
			document.form.name.focus();
			return false;
		}
    }
    catch(ex){
    }
    try{
		if (document.form.mob.value==""){
			alert('请填写手机号码！');
			document.form.mob.focus();
			return false;
		}
		var form = /^1[3,4,5,6,7,8]\d{9}$/;
		if (!form.test(document.form.mob.value)){
			alert('手机号码格式不正确，请重新填写！');
			document.form.mob.focus();
			return false;
		}
    }
    catch(ex){
    } 	
    try{
		if (document.form.province.value==""){
			alert('请选择所在地区！');
			document.form.province.focus();
			return false;
		}
    }
    catch(ex){
    } 	
    try{
		if (document.form.address.value==""){
			alert('请填写详细地址！');
			document.form.address.focus();
			return false;
		}
    }
    catch(ex){
    } 	
   // document.form.submit.disabled = true;
   // document.form.submit.value="正在提交，请稍等 >>";
    return true;
}


function countDown(time,id){
    var day_elem = $(id).find('.day');
    var hour_elem = $(id).find('.hour');
    var minute_elem = $(id).find('.minute');
    var second_elem = $(id).find('.second');
    sys_second = (time-new Date().getTime())/1000;
    var timer = setInterval(function(){
        if (sys_second > 1) {
            sys_second -= 1;
            var day = Math.floor((sys_second / 3600) / 24);
            var hour = Math.floor((sys_second / 3600) % 24);
            var minute = Math.floor((sys_second / 60) % 60);
            var second = Math.floor(sys_second % 60);
            day_elem && $(day_elem).html(day+'<samp>天</samp>');//计算天
            $(hour_elem).html((hour<10?"0"+hour:hour)+'<samp>时</samp>');//计算小时 
            $(minute_elem).html((minute<10?"0"+minute:minute)+'<samp>分</samp>');//计算分钟
            $(second_elem).html((second<10?"0"+second:second)+'<samp>秒</samp>');//计算秒杀
        } else { 
            clearInterval(timer);
            // var times=(new Date().getTime())+(2*3600)
            // countDown(times,id);
        }
    }, 1000);
}


function pricea(e){
   $('input[name="price"]').val($(e).attr('alt'));
}

function scollDown(clas,time){
    var time=time||2500;
    setInterval(function(){
        var liHeight=$("."+clas+" .diqiehuans>div:last").height();
        $("."+clas+" .diqiehuans").prepend($("."+clas+" .diqiehuans>div:last").css("height","0px").animate({
            height:liHeight+"px"
        },"slow"));
    },time);
}
function scollDownd(clas,time){
    var time=time||2500;
    setInterval(function(){
        var liHeight=$("."+clas+" .diqiehuans>div:first-child").height();
        $("."+clas+" .diqiehuans").append($("."+clas+" .diqiehuans>div:first-child").css("height","0px").animate({
            height:(liHeight+10)+"px"
        },"slow"));
    },time);
}

function scollDowns(clas,time){
    var L = $("."+clas+" .diqiehuans div").length;
    var liWidth=$("."+clas+" .diqiehuans").width()*L;
    $("."+clas+" .diqiehuans").css('width',liWidth);
    var time=time||1;
       abc(clas,time);
}
function abc(clas,time){
 $("."+clas+" .diqiehuans").append($("."+clas+" .diqiehuans>div:first-child"));
       // $("."+clas+" .diqiehuans>div:first-child").remove();
        var liWidths=$("."+clas+" .diqiehuans>div:first-child").width()+20;
        $("."+clas+" .diqiehuans").css({'left':"0px"})
        $("."+clas+" .diqiehuans").animate({'left':'-'+liWidths+"px"},time,function(){
            abc(clas,time);
        });
}