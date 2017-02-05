<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String appointAddress = null == request.getSession().getAttribute("appointAddress") ? "" : request.getSession().getAttribute("appointAddress").toString();
	String appointDate = null == request.getSession().getAttribute("appointDate") ? "" : request.getSession().getAttribute("appointDate").toString();
	String appointTime = null == request.getSession().getAttribute("appointTime") ? "" : request.getSession().getAttribute("appointTime").toString();
	String sendAppointTime = appointDate+"    "+appointTime;
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>预约办证大厅</title>
<link rel="stylesheet" href="css/public/reset.css" type="text/css" media="screen" />
<link rel="Stylesheet" href="css/public/bootstrap.min.css" type="text/css" />
<link rel="Stylesheet" href="css/public/font-awesome.min.css" type="text/css" />
<link rel="Stylesheet" href="css/style.css" type="text/css" />

<!-- jQuery -->
<script type="text/javascript" src="js/public/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/public/bootstrap.min.js"></script>
<script type="text/javascript" src="js/public/layer/layer.js"></script>
<script type="text/javascript" src="js/common/linkageSelect.js"></script>
<script type="text/javascript">
	$(function(){
		$("#sendDotName").text('<%=appointAddress%>');
		$("#sendAppointDate").text('<%=sendAppointTime%>');
		
		var $areaDom = $('#areaSel');
		// 省市区
		var areaSelect = new LinkageSelect($areaDom, {
			url: '/wx-war/webapp/common/common-info/listArea',  //数据源
			id: 'fId',   //key属性名
			name: 'fName', //value属性名
			parentNameDomName: 'birth', //第一层name （父节点name）
			isArea:true,
		});
		areaSelect.getParentValue();  //初始化父节点数据
		
		var $areaDom1 = $('#regResidence');
		// 省市区
		var areaSelect1 = new LinkageSelect($areaDom1, {
			url: '/wx-war/webapp/common/common-info/listArea',  //数据源
			id: 'fId',   //key属性名
			name: 'fName', //value属性名
			parentNameDomName: 'province', //第一层name （父节点name）
			childrenDomName : 'city', // //第二层name （子节点name）
			nextChildrenDomName : 'county', //第三层name （第三层节点name）
			isArea:true,
		});
		areaSelect1.getParentValue();  //初始化父节点数据
		$areaDom1.find('[name="province"]').on("change",function(){  //改变第二层数据
			$("#city  option:selected").text("请选择");
			areaSelect1.getChildrenValue();
		})
		
		$areaDom1.find('[name="city"]').on("change",function(){//改变第三层数据
			areaSelect1.getNextChildrenValue();
		})
		
		$.ajax({
			url : "/wx-war/webapp/common/common-info/searchMz",
			type : 'GET',
			dataType : 'JSON',
			async:false,
			success : function(data) {
		        var datas = eval(data);
		        var allNationHtml='';
                allNationHtml+='<select>';
				$.each(datas.data,function(i,obj){
					 allNationHtml+='<option value="'+obj.mzmc+'">'+obj.mzmc+'</option>';
				});
				allNationHtml+='</select>';
				$("#allNation").html(allNationHtml);
			}
		});
	});
	
	
	/*
	js自身没有trim()函数取消字符串中的空白字符
	自定义函数：用正则替换掉空白字符
	 */
	function trim(s) {
		return s.replace(/^\s+|\s+$/g, "");
	};

	//验证身份证号并获取出生日期
	function getBirthdatByIdNo() {
		var iIdNo =$("#idCard").val();
		var tmpStr = "";
		var idDate = "";
		var tmpInt = 0;
		var strReturn = "";

		iIdNo = trim(iIdNo);

		if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
			strReturn = "输入的身份证号位数错误";
			return strReturn;
		}

		if (iIdNo.length == 15) {
			tmpStr = iIdNo.substring(6, 12);
			tmpStr = "19" + tmpStr;
			tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6)
					+ "-" + tmpStr.substring(6)
		} else {
			tmpStr = iIdNo.substring(6, 14);
			tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6)
					+ "-" + tmpStr.substring(6)
		}
		$("#birthday").val(tmpStr);
	}
	
	function check(){
		if ($('#commonPassport').is(':checked')){
			$("#firstApply").attr("disabled",false);
			$("#lost").attr("disabled",false);
			$("#stolen").attr("disabled",false);
			$("#renew").attr("disabled",false);
			$("#invalid").attr("disabled",false);
			$("#filling").attr("disabled",false);
		}else{
			$("#firstApply").attr("disabled",true);
			$("#lost").attr("disabled",true);
			$("#stolen").attr("disabled",true);
			$("#renew").attr("disabled",true);
			$("#invalid").attr("disabled",true);
			$("#filling").attr("disabled",true);
		}
		
		if($('#hmvisa').is(':checked')){
			$("#onlypass").attr("disabled",false);
			$("#passportvisa").attr("disabled",false);
			$("#applyvisa").attr("disabled",false);
		}else{
			$("#onlypass").attr("disabled",true);
			$("#passportvisa").attr("disabled",true);
			$("#applyvisa").attr("disabled",true);
		}
	}
</script>
	
</head>

<body class="huis">
    <header>
    <i class="icon-angle-left"></i>
      <h1>预约办证大厅</h1> 
    </header><!-- /header -->
      <form action="success.jsp">
      <div class="table">
        <!-- 基本信息 -->
        <h3>基本信息</h3>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>服务大厅选择</div>
            <div class="f_r" id="sendDotName">南京市鼓楼区出入境办证大厅</div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>预约办证日期</div>
            <div class="f_r" id="sendAppointDate">2016-03-20   13:00-14:00</div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>身份证号</div>
            <div class="f_r"><input type="text" id="idCard" name="idCard" value="" placeholder="输入身份证号码" onblur="getBirthdatByIdNo()"></div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>出生地</div>
            <div class="f_r" id="areaSel">
              <select id="birth" name="birth">
					<option value="0">选择省</option>
			  </select>
            </div>
          </div>
          <div class="clearfix xm">
            <div class="f_l ta_r">
              <span>*</span>姓<input type="text" class="" name="" value="" placeholder="输入姓">
            </div>
            <div class="f_r ta_l">
              <span>*</span>拼音姓<input type="text" class="" name="" value="" placeholder="输入拼音姓">
            </div>
          </div>
          <div class="clearfix xm">
            <div class="f_l ta_r">
              <span>*</span>名<input type="text" class="" name="" value="" placeholder="输入名">
            </div>
            <div class="f_r ta_l">
              <span>*</span>拼音名<input type="text" class="" name="" value="" placeholder="输入拼音名">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>出生日期</div>
            <div class="f_r">
            	<input type="text" id="birthday" name="birthday" value="" readonly="readonly">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>性别</div>
            <div class="f_r">
              <select name="sex">
                <option value="1">男</option>
                <option value="2">女</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>民族</div>
            <div class="f_r" id="allNation">
              
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>户口所在地</div>
            <div class="f_r1" id="regResidence">
               <select id="province" name="province" style="width:20%;">
					<option value="0">选择省</option>
				</select> 
				<select id="city" name="city" style="width:20%;">
					<option value="0">选择市</option>
				</select> 
				<select id="county" name="county" style="width:20%;">
					<option value="0">县区</option>
				</select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>本人联系电话</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="输入联系电话">
            </div>
          </div>
      <!-- /基本信息 -->
      <!-- 紧急联系人 -->
        <h3>紧急联系人</h3>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>紧急联系人姓名</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="输入紧急联系人姓名">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>紧急联系人电话</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="输入紧急联系人电话">
            </div>
          </div>
      <!-- /紧急联系人 -->

      <!-- 办证类型(可多选) -->
        <h3>办证类型(可多选)</h3>
          <div class="clearfix bzlx">
            <label class="thewidth100"><input id="commonPassport" type="checkbox" name="" value="" placeholder="" onclick="check()">普通护照</label>
            <table>
              <tr>
                <td><label><input id="firstApply" type="radio" name="commonType" value="11" placeholder="" disabled="disabled">首次申领</label></td>
                <td><label><input id="lost" type="radio" name="commonType" value="21" placeholder="" disabled="disabled">补发(丢失)</label></td>
                <td><label><input id="stolen" type="radio" name="commonType" value="32" placeholder="" disabled="disabled">补发(损毁被盗)</label></td>
              </tr>
              <tr>
                <td><label><input id="renew" type="radio" name="commonType" value="31" placeholder="" disabled="disabled">换发</label></td>
                <td><label><input id="invalid" type="radio" name="commonType" value="13" placeholder="" disabled="disabled">失效重新申领</label></td>
                <td><label><input id="filling" type="radio" name="commonType" value="71" placeholder="" disabled="disabled">加注</label></td>
              </tr>
            </table>
        </div>

      <!-- 往来港澳通行证和签注 -->
          <div class="clearfix bzlx m_t9">
            <label class="thewidth100"><input id="hmvisa" type="checkbox" name="" value="" placeholder="" onclick="check()">往来港澳通行证和签注</label>
            <table>
              <tr>
                <td><label><input id="onlypass" type="radio" name="hmVisaType" value="" placeholder="" disabled="disabled">仅通行证</label></td>
                <td><label><input id="passportvisa" type="radio" name="hmVisaType" value="" placeholder="" disabled="disabled">通行证和签注</label></td>
                <td><label><input id="applyvisa" type="radio" name="hmVisaType" value="" placeholder="" disabled="disabled">仅申请签注</label></td>
              </tr>
            </table>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>办证类别</div>
            <div class="f_r">
              <select>
                <option>请选择办证类别</option>
                <option>首次申领</option>
                <option>补发</option>
                <option>换发</option>
                <option>失效重新申领</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>签注类型</div>
            <div class="f_r">
              <select>
                <option>请选择签注类型</option>
                <option>团队旅游(L)</option>
                <option>个人旅游(G)</option>
                <option>商务(S)</option>
                <option>探亲(T)</option>
                <option>其他(Q)</option>
                <option>逗留(D)</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>签注前往地</div>
            <div class="f_r">
              <select>
                <option>请选择签注前往地</option>
                <option>澳门</option>
                <option>澳门+香港</option>
                <option>香港</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>签注种类</div>
            <div class="f_r" style="height:4.2em">
              <select>
                <option>请选择澳门签注种类</option>
                <option>澳门3个月一次</option>
                <option>澳门一年一次</option>
              </select>
              <select style=" top: 2.3em;">
                <option>请选择香港签注种类</option>
                <option>香港3个月一次</option>
                <option>香港一年一次</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>港澳亲属姓名</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="输入姓名">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>性别</div>
            <div class="f_r">
              <select>
                <option>请选择性别</option>
                <option>男</option>
                <option>女</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>港澳身份证号码</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="输入身份证号码">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>旅行证件号码</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="输入旅行证件号码">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>与申请人关系</div>
            <div class="f_r">
              <select>
                <option>请选择与申请人关系</option>
                <option>配偶</option>
                <option>父母</option>
                <option>子</option>
                <option>女</option>
                <option>兄弟姐妹</option>
                <option>其他亲属</option>
              </select>
            </div>
          </div>
      <!-- /往来港澳通行证和签注 -->

      <!-- 往来台湾通行证和签注 -->
          <div class="clearfix bzlx m_t9">
            <label class="thewidth100"><input type="checkbox" name="" value="" placeholder="">往来台湾通行证和签注</label>
            <table>
              <tr>
                <td><label><input type="radio" name="1" value="" placeholder="">仅通行证</label></td>
                <td><label><input type="radio" name="1" value="" placeholder="">通行证和签注</label></td>
                <td><label><input type="radio" name="1" value="" placeholder="">仅申请签注</label></td>
              </tr>
            </table>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>现持证件号码</div>
            <div class="f_r">
              <input type="text" class="" name="" value="" placeholder="">
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>有效期至</div>
            <div class="f_r">
              <select>
                <option>请选择有效期至</option>
                <option>2016-05-31</option>
                <option>2016-07-31</option>
                <option>2016-12-31</option>
                <option>2017-05-31</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>办证类别</div>
            <div class="f_r">
              <select>
                <option>请选择办证类别</option>
                <option>首次申领</option>
                <option>补发</option>
                <option>换发</option>
                <option>失效重新申领</option>
              </select>
            </div>
          </div>
          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>签注类型</div>
            <div class="f_r">
              <select>
                <option>请选择签注类型</option>
                <option>团队旅游</option>
                <option>个人旅游</option>
                <option>经贸交流</option>
                <option>探亲</option>
                <option>定居</option>
                <option>居留</option>
                <option>就学</option>
                <option>乘务</option>
                <option>其他</option>
              </select>
            </div>
          </div>

          <div class="clearfix">
            <div class="f_l ta_r"><span>*</span>签注种类</div>
            <div class="f_r">
              <select>
                <option>请选择签注种类</option>
                <option>3个月一次</option>
                <option>一年一次</option>
              </select>
            </div>
          </div>
      <!-- /往来台湾通行证和签注 -->
      <!-- /办证类型(可多选) -->
      <!-- 领取方式 -->
      <h3>领取方式</h3>
          <div class="clearfix bzlx">
            <table>
              <tr>
                <td><label><input type="radio" name="1" value="" placeholder="">到公安机关领取</label></td>
                <td><label><input type="radio" name="1" value="" placeholder="">邮寄送达</label></td>
              </tr>
            </table>
        </div>
        <!-- /领取方式 -->
      <label class="tiaoy m_t9">
      <input type="checkbox" name="" value="" placeholder="">同意并遵守
      <a href="javascript:;">《服务条款》</a>
      </label>

      <div class="office_foot">
          <input type="submit" name="" value="确定">
          <p>江苏省公安局入境管理处 版本所有</p>
      </div>
    </div>
    <!-- /table -->
   </form>
</body>
</html>