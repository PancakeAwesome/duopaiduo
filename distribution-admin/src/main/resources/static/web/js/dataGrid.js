/**
 * DataGrid数据列表
 */

var allObj;
var dataGird='';
;
$(function($, window, document, undefined) {

	CommonDataGrid = function($dom, options) {
		"use strict"; // stirct mode not support by IE9-
		if (!$dom)
			return;
		
		allObj =  options;
		var $options = options || {},
		$idKey = $options.idKey,
		$url = $options.url,
		$type = $options.type,
		$data = $options.data,
		
		$updBtn = $options.updBtn == null ? true:$options.updBtn ,
		$delBtn = $options.delBtn == null ? true: $options.delBtn,
		$selBtn =$options.selBtn == null ? true: $options.selBtn,
		$fromDom = $options.fromDom,
		// 添加数据url
		$saveObj = $options.saveObj,
		// 详情数据url
		$detailObj = $options.detailObj,
		// 删除数据url
		$deleteObj = $options.deleteObj,
		$paging = $options.paging == null?true:$options.paging,
		$ordering = $options.ordering == null?true:$options.ordering,
        $info = $options.info == null?true:$options.info,
        		
		$columnDefs = $options.columnDefs;
		
		
		var cloLen = $columnDefs.length;
		$columnDefs.push({
			"data":"handle",
			"targets":cloLen,
			"render":function(data,type,row){
				
				var _html = "";
				if($selBtn){
					_html+="<a title='查看' onclick=\"showFrom('"+row.id+"','query')\" class='btn btn-default btn-xs'><i class='fa fa-search'></i></a>&nbsp;";
				}
				if($updBtn){
					_html +="<a title='编辑' onclick=\"showFrom('"+row.id+"')\" class='btn btn-default btn-xs'><i class='fa fa-edit'></i></a>&nbsp;"
				}
				if($delBtn){
					_html +="<a title='删除' onclick=\"delData('"+row.id+"')\" class='btn btn-default btn-xs'><i class='fa fa-remove'></i></a>";
				}
				
				return _html;
			}
		});
		
		this.show = function(){
			var self = this;
			
			dataGird = $dom.DataTable({
					"processing" : true,
					"serverSide" : true,
					"ajax" : {
						"url" : $url,
						"contentType": "application/json",
					    "type": $type,
					    "data": $data
					},
					"dom":
						"<'row'<'col-sm-12'<'toolbar-rgt'>>>" +
						"<'row'<'col-sm-12'tr>>" +
						"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					"orderMulti":false,
					"order":[[1,"asc"]],
					"columnDefs" : $columnDefs,
					"paging":   $paging,
				    "ordering": $ordering,
				    "info":     $info,
				    "searching":false,
					
					"language" : {
						"sProcessing" : "处理中...",
						"sLengthMenu" : "显示 _MENU_ 项结果",
						"sZeroRecords" : "没有匹配结果",
						"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
						"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
						"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
						"sInfoPostFix" : "",
						"sSearch" : "搜索:",
						"sUrl" : '',
						"sEmptyTable" : "表中数据为空",
						"sLoadingRecords" : "载入中...",
						"sInfoThousands" : ",",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "上页",
							"sNext" : "下页",
							"sLast" : "末页"
						},
						"oAria" : {
							"sSortAscending" : ": 以升序排列此列",
							"sSortDescending" : ": 以降序排列此列"
						}
					},
				});
			 
			 $("#DataTables_Table_0_length").remove();
			 $(".toolbar-rgt").html('<div align="left"><a onclick="showFrom(0);" class="btn btn-primary">新增 </a> ');
				
				
				var formBtn = $('<div class="box-footer"></div>');
				var cancelBtn = $('<button   class="btn btn-default">取消</button>');
				var subBtn = $('<input type="button" name="queren" class="btn btn-info pull-right" value="确认"></input>');
				
				subBtn.on("click",function(){
					var  p = $fromDom.find('form').serializeJson();
					if(p.id==""){
						p.id=null;
					}
					new Ajax('',{
						url: $saveObj.url,
						type:$saveObj.type,
						param:base64encode(utf16to8(JSON.stringify(p))),
						callback : function(rsp) {
							layer.closeAll();
							self.reloadTable($dom);
						}
				 	}); 
					
				})
				$fromDom.find('form').append(formBtn.append(cancelBtn).append(subBtn));
		}
		
		this.reloadTable = function($dom){
			var self =this;
			dataGird.ajax.reload();
		}
		
		
		
		
		
		
	};
}(jQuery, window, document));



function showFrom(id,type){
	
	if("query"==type){
		allObj.fromDom.find('[name="queren"]').css("display","none");
	}else{
		allObj.fromDom.find('[name="queren"]').css("display","block");
	}
	var index = layer.open({
        type: 1,
        title: "编辑",
        shadeClose: true,
        maxmin:true,
        area:['800px','550px'],
        content: allObj.fromDom,
    });
	
	var cartPost = new Ajax('',{
		url: allObj.detailObj.url+"?"+allObj.idKey +"="+id,
		type:allObj.detailObj.type,
		callback : function(rsp) {
			var form = new Form(allObj.fromDom.find('form'),{
        		data : rsp.data
        	});
        	form.formEdit();
		}
 });
	layer.full(index);
}


function delData(id){
	
	var ly =layer.confirm('确定删除该条数据吗？', {
	    btn: ['确定','取消']
	}, function(){
		 var cartPost = new Ajax('',{
			url: allObj.deleteObj.url+"?"+allObj.idKey+"="+id,
			type:allObj.deleteObj.type,
			callback : function(rsp) {
				layer.closeAll();
				dataGird.ajax.reload();
			}
	 	}); 
	});
		
		
		
		
	}
