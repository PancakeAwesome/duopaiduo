/**
 * DataGrid数据列表
 */
;
$(function($, window, document, undefined) {

	CommonDataGrid = function($dom, options) {
		"use strict"; // stirct mode not support by IE9-
		if (!$dom)
			return;
		var $options = options || {}, 
		$url = $options.url, 
		$columns = $options.columns, 
		$method = $options.method, 
		$idField = $options.idField,
		$width = $options.width == null?'auto':$options.width,
		$queryParams = $options.queryParams,		
		// 设置true将在数据表格底部显示分页工具栏
		$pagination = $options.pagination,
		// 设置为true将显示行数
		$rownumbers = $options.rownumbers,
		// 设置为true将交替显示行背景
		$striped = $options.striped,
		// toolbar 设置为true将显示toolbar
		$showToolbar = $options.showToolbar,
		// 需要渲染的form
		$addForm = $options.addDiv,
		// 重置按钮
		isBack=$options.isBack,
		// 添加数据url
		$saveObj = $options.saveObj,
		// 详情数据url
		$detailObj = $options.detailObj,
		// 删除数据url
		$deleteObj = $options.deleteObj,
		// 是否需要查看
		$isNeedDetail = $options.isNeedDetail,
		
		$fromSaveObj = $options.fromSaveObj,
		
		
		
		// excel导入数据url
		$exportObj = $options.exportObj,
		$exportDiv = $options.exportDiv,
		
		$singleSelect = $options.singleSelect,
		$onLoadSuccess =$options.onLoadSuccess;
		
		var torb =[];
		var detailurl;
		var deleteurl;
		if($detailObj!=null){
			detailurl = $detailObj.detailUrl;
		}
		
		if($isNeedDetail){
			var self = this;
			torb.push({
				iconCls : 'icon-reload',
				stype : 'width:100px;',
				text : '刷新',
				handler : function() {
					self.load($dom,$queryParams);
				}
			});
			torb.push({
				iconCls : 'icon-search',
				text : '查看',
				handler : function() {
					
					$addForm.find('div').last().css("display", "none");
					
					$.each($addForm.find('input'),function(i,fr){
	            		$(fr).attr("disabled","disabled");
	            		$(fr).removeClass("validatebox-invalid");
					});
					
					$.each($addForm.find('select'),function(i,fr){
	            		$(fr).attr("disabled","disabled");
					});
					
					$.each($addForm.find('textarea'),function(i,fr){
	            		$(fr).attr("disabled","disabled");
					});
					var row = self.getRowData($dom);
		              if($(row).length < 1 || $(row).length > 1)
		              {
		            	  $.messager.alert("系统提示","请选择记录");
		                  return ;
		              }else{
		            	    $detailObj.param={};
		            	  	$detailObj.param[$idField]=row[0][$idField];
		            	  	if($detailObj.type == "get"){
		            	  		 $detailObj.detailUrl = detailurl + "/"+ row[0][$idField];
		            	  		 $detailObj.param={};
		            	  	}
		            	  	var AJAXPost = new Ajax('',{
								url: $detailObj.detailUrl,
								param: $detailObj.param,
								type:$detailObj.type  == null ? 'post' : $detailObj.type,
								isNeedSign:$detailObj.isNeedSign  == null ? false : $detailObj.isNeedSign,  //token验证
								callback : function(rsp) {
									$addForm.dialog("open").dialog("setTitle", "查看信息");
									
									var form = new Form($addForm.find('form'),{
					            		data : rsp.data
					            	});
					            	form.formEdit();
									
									$addForm.find('[name=canl]').on('click', function() {
										$addForm.dialog('close');
									});
								}
							});
									 
		              }
				}
			});
		}
		
		if($saveObj!=null){
			var self = this;
			torb.push({
				iconCls : 'icon-reload',
				stype : 'width:100px;',
				text : '刷新',
				handler : function() {
					self.load($dom,$queryParams);
				}
			});
			
			
			torb.push({
				iconCls : 'icon-add',
				text : '新增',
				handler : function() {
					
	            	$addForm.find('[name=save]').unbind();
	            	
	            	$addForm.find('div').last().css("display", "");
	            	
	            	$.each($addForm.find('input'),function(i,fr){
	            		$(fr).removeAttr("disabled");
	            		$(fr).removeClass("validatebox-invalid");
					});
	            	
	            	$.each($addForm.find('select'),function(i,fr){
	            		$(fr).removeAttr("disabled");
					});
					
					$.each($addForm.find('textarea'),function(i,fr){
						$(fr).removeAttr("disabled");
					});
	            	
					$addForm.dialog("open").dialog("setTitle", "新增信息");
					
					var form = new Form($addForm.find('form'),{
	            		data : null
	            	});
	            	form.formEdit();
	            	
					$addForm.find('[name=save]').on('click', function() {
						var flag = true;
						$.each($addForm.find('input'),function(i,fr){
							
							if(($(fr).attr('required')=='required' && ('' == $(fr).val() || null == $(fr).val()))||$(fr).attr('isExist')=='true'){
							
								flag = false;
								$(fr).addClass("validatebox-invalid");
								return false;
							}
						});
					    if(flag){
					    	new Ajax('',{
								url: $saveObj.insUrl,
								param:  $addForm.find('form').serializeJson(),
								type:$saveObj.type  == null ? 'post' : $saveObj.type,
								isNeedSign:$saveObj.isNeedSign  == null ? false : $saveObj.isNeedSign,  //需要token验证
								callback : function(d) {
									if(JSON.parse(d.data).hasOwnProperty('code') && JSON.parse(d.data).code!=0){
										$.messager.alert("系统提示",JSON.parse(d.data).msg);
										return;
									}
									if(!JSON.parse(d.data).hasOwnProperty('code') && !d.data){
										$.messager.alert("系统提示",d.msg);
										return;
									}
									$addForm.dialog('close');
									$.messager.alert("系统提示","操作成功!");
									self.load($dom,$queryParams);
								}
							});
					    }
				});
				$addForm.find('[name=canl]').on('click', function() {
					$addForm.dialog('close');
				});
				
				}
			});
			
			torb.push({
				iconCls : 'icon-search',
				text : '查看',
				handler : function() {
					
					$addForm.find('div').last().css("display", "none");
					
					$.each($addForm.find('input'),function(i,fr){
	            		$(fr).attr("disabled","disabled");
	            		$(fr).removeClass("validatebox-invalid");
					});
					
					$.each($addForm.find('select'),function(i,fr){
	            		$(fr).attr("disabled","disabled");
					});
					
					$.each($addForm.find('textarea'),function(i,fr){
	            		$(fr).attr("disabled","disabled");
					});
					var row = self.getRowData($dom);
		              if($(row).length < 1 || $(row).length > 1)
		              {
		            	  $.messager.alert("系统提示","请选择记录");
		                  return ;
		              }else{
		            	    $detailObj.param={};
		            	  	$detailObj.param[$idField]=row[0][$idField];
		            	  	if($detailObj.type == "get"){
		            	  		 $detailObj.detailUrl = detailurl + "/"+ row[0][$idField];
		            	  		 $detailObj.param={};
		            	  	}
		            	  	var AJAXPost = new Ajax('',{
								url: $detailObj.detailUrl,
								param: $detailObj.param,
								type:$detailObj.type  == null ? 'post' : $detailObj.type,
								isNeedSign:$detailObj.isNeedSign  == null ? false : $detailObj.isNeedSign,  //token验证
								callback : function(rsp) {
									$addForm.dialog("open").dialog("setTitle", "查看信息");
									
									var form = new Form($addForm.find('form'),{
					            		data : rsp.data
					            	});
					            	form.formEdit();
									
									$addForm.find('[name=canl]').on('click', function() {
										$addForm.dialog('close');
									});
								}
							});
									 
		              }
				}
			});
			
			torb.push({
				iconCls : 'icon-edit',
				text : '修改',
				handler : function() {
	            	$addForm.find('[name=save]').unbind();
	            	$addForm.find('div').last().css("display", "");
	            	$.each($addForm.find('input'),function(i,fr){
	            		
	            		$(fr).removeAttr("disabled");
	            		
	            		if($(fr).attr("editstatus")=='false'){
	            			$(fr).attr("disabled","disabled");
	            		}
	            		$(fr).removeClass("validatebox-invalid");
					});
	            	
	            	
	            	$.each($addForm.find('select'),function(i,fr){
	            		$(fr).removeAttr("disabled");
					});
					
					$.each($addForm.find('textarea'),function(i,fr){
						$(fr).removeAttr("disabled");
					});
					
					var row = self.getRowData($dom);
		              if($(row).length < 1 || $(row).length > 1)
		              {
		            	  $.messager.alert("系统提示","请选择记录");
		                  return ;
		              }else{
		            	    $detailObj.param={};
		            	  	$detailObj.param[$idField]=row[0][$idField];
		            	  	if($detailObj.type == "get"){
		            	  		 $detailObj.detailUrl = detailurl + "/"+ row[0][$idField];
		            	  		 $detailObj.param={};
		            	  	}
		            	  	var AJAXPost = new Ajax('',{
								url: $detailObj.detailUrl,
								param: $detailObj.param,
								type:$detailObj.type  == null ? 'post' : $detailObj.type,
								isNeedSign:$detailObj.isNeedSign  == null ? false : $detailObj.isNeedSign,  //token验证
								callback : function(rsp) {
									if(isBack){
										isBack(rsp.data);
									}
									$addForm.dialog("open").dialog("setTitle", "修改信息");
									
									var form = new Form($addForm.find('form'),{
					            		data : rsp.data
					            	});
					            	form.formEdit();
					            	
									$addForm.find('[name=save]').on('click', function() {
										
										var flag = true;
										$.each($addForm.find('input'),function(i,fr){
											
											if($(fr).attr('required')=='required' && ('' == $(fr).val() || null == $(fr).val() )){
												flag = false;
												$(fr).addClass("validatebox-invalid");
												return false;
											}
										});
										if(flag){
											var  p = $addForm.find('form').serializeJson();
											p[$idField]=row[0][$idField];
										    new Ajax('',{
												url: $saveObj.updUrl==null? $saveObj.insUrl:  $saveObj.updUrl ,
												param:  p,
												type:$saveObj.type  == null ? 'post' : $saveObj.type,
												isNeedSign:$saveObj.isNeedSign  == null ? false : $saveObj.isNeedSign,  //需要token验证
												callback : function(d) {
													if(JSON.parse(d.data).hasOwnProperty('code') && JSON.parse(d.data).code!=0){
														$.messager.alert("系统提示",JSON.parse(d.data).msg);
														return;
													}
													if(!JSON.parse(d.data).hasOwnProperty('code') && !d.data){
														$.messager.alert("系统提示",d.msg);
														return;
													}
													$addForm.dialog('close');
													$.messager.alert("系统提示","操作成功!");
													self.load($dom,$queryParams);
												}
										    });
										}
											
										
										
									});
									$addForm.find('[name=canl]').on('click', function() {
										$addForm.dialog('close');
									});
								}
							});
									 
		              }
				}
			});
		}
		
		
		
		if($fromSaveObj!=null){
			var self = this;
			torb.push({
				iconCls : 'icon-edit',
				text : '提交',
				handler : function() {
	            	var $tr = $dom.parent().find(".datagrid-view2").find(".datagrid-body").find('tr');
	            	console.log($tr);
	            	var id;
	            	var value;
	            	var rowsValue;
	            	$.each($tr,function(i,obj){
	            		var key = $(this).find('td').eq(0).find('div').text();
	            		var inputValue =  $(this).find('td').find('input').val();
		            	console.log(key);
		            	if(rowsValue!=null){
		            		rowsValue +=","+key;
		            	}else{
		            		rowsValue=key;
		            	}
		            	/*if(rowsValue!=null){
		            		rowsValue +=","+key+":"+inputValue;
		            	}else{
		            		rowsValue=key+":"+inputValue;
		            	}*/
	            	});
	            	
	            	new Ajax('',{
						url: $fromSaveObj.saveUrl,
						param:  {"cartIds":rowsValue,"userId":$fromSaveObj.userId},
						type:$fromSaveObj.type  == null ? 'post' : $fromSaveObj.type,
						callback : function(d) {
//							$addForm.dialog('close');
							$.messager.alert("系统提示","操作成功！");
							self.load($dom,$queryParams);
						}
				    });
	            	
				}
			
			});
		}
		var detailurl;
		if($deleteObj!=null){
			var self = this;
			deleteurl = $deleteObj.deleteUrl;
			torb.push({
				iconCls: 'icon-remove',
				text:'删除',
				handler: function(){
					var row = self.getRowData($dom);
		              if($(row).length < 1)
		              {
		            	  $.messager.alert("系统提示","请选择记录");
		                  return ;
		              }else{
			              $.messager.confirm("系统提示","确认删除？",function(r){
								if(r){
									$deleteObj.param={};
									var ids="";
									$.each(row,function(i,obj){
										if("" ==ids){
											ids=obj[$idField];;
										}else{
											ids=ids+","+obj[$idField];
										}
										
									});
									$deleteObj.param[$idField]=ids+"";
									
									if($deleteObj.type == "get"){
										$deleteObj.deleteUrl  =  deleteurl + '/'+row[0][$idField];
										$deleteObj.param={};
				            	  	}
									
									var AJAXPost = new Ajax('',{
										url: $deleteObj.deleteUrl,
										param: $deleteObj.param,
										type:$deleteObj.type  == null ? 'post' : $deleteObj.type,
										isNeedSign:$deleteObj.isNeedSign  == null ? false : $deleteObj.isNeedSign,  //需要token验证
										callback : function(rsp) {
											$.messager.alert("系统提示","成功删除！");
											 self.load($dom,$queryParams);
										}
									});
									 
								}
						 	});
		              }
				}
			});
		}
	    
		if($exportObj!=null){
			var self = this;
			torb.push({
				iconCls: 'icon-yxjhgl',
				text:'excel导入',
				handler: function(){
				
					$exportDiv.dialog("open").dialog("setTitle", "上传");
					$exportDiv.find('[name=btnUpload]').unbind();
					$exportDiv.find('[name=btnUpload]').on("click",function(){
						
						 var elementIds=["flag"]; //flag为id、name属性名
						 $.ajaxFileUpload({
					            url: 'http://localhost:8080/wfbManager-war/webapp/utils/exportUpload', 
					            type: 'post',
					            secureuri: false, //
					            fileElementId: 'file', // 
					            dataType: 'application/json', //
					            elementIds: elementIds, //
					            success: function(data, status){  
					            	 var dat = JSON.parse(data.substring(5,data.indexOf("</pre>")));
					            	 var AJAXPost = new Ajax('',{
											url: $exportObj.exportUrl,
											param: {"filePath":dat.data},
											type:$exportObj.type  == null ? 'post' : $exportObj.type,
											isNeedSign:$exportObj.isNeedSign  == null ? false : $exportObj.isNeedSign,  //需要token验证
											callback : function(rsp) {
												$.messager.alert("系统提示","导入成功！");
												$exportDiv.dialog('close');
												 self.load($dom,$queryParams);
											}
										});
					               
					            },
					            error: function(data, status, e){ 
					            	alert("失败");
					                alert(e);
					            }
						})
					});
				}
			})
		}
		
		this.show = function(){
			var self = this;
			$dom.datagrid({
				url : $url,
				iconCls : 'icon-save',
				width : $width,
				height : 'auto',
				fitColumns : true,
				singleSelect : $singleSelect,
				method : $method,
				idField : $idField,
				columns : $columns,

				toolbar :torb,
				showToolbar:$showToolbar,
				
				queryParams : $queryParams,
				pagination : $pagination,
				rownumbers : $rownumbers,
				striped : $striped,
				onLoadSuccess:function(data){
					$onLoadSuccess && $onLoadSuccess(data)
				}
			})
		}
		
		//获取选择行数据 return array
		this.getRowData = function(dom){
			return dom.datagrid("getSelections");
		}
		
		
		//表格刷新
		this.load = function(dom,obj){
			for(var i in $queryParams){
				obj[i]=$queryParams[i];
			}
			dom.datagrid('reload',obj);
			
		}
		
		this.onLoadSuccess = function(dom){
			dom.datagrid({onLoadSuccess : $onLoadSuccess && $onLoadSuccess(data)});
		}
	};
}(jQuery, window, document));