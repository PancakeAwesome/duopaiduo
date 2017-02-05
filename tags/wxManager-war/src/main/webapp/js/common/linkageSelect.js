/**
 * LinkageSelect联动下拉框
 */
; $(function ($, window, document, undefined) {
    
    LinkageSelect = function ($dom, options) {

        "use strict"; //stirct mode not support by IE9-

        if (!$dom) return;

        var $options = options || {},
        $url = $options.url,
        $id = $options.id,
        $name = $options.name,
        $parentDomName= $options.parentNameDomName,
		$childrenDomName = $options.childrenDomName,
		$nextChildrenDomName = $options.nextChildrenDomName,
		// 请求类型
		$requestType = $options.requestType==null?'get':$options.requestType,
		// 请求参数
		$queryParam = $options.queryParam,
		// 分页属性
        $isPage = $options.isPage,
        $isArea = $options.isArea
        this.getParentValue=function() {
        	$.ajax( {
        		type :$requestType==null?'get':$requestType,
        		url :$queryParam==null?$url+'/0':$url,
        		data:$queryParam,
        		dataType :'json',
        		success : function(result) {
        			if($isArea != null){
        				result = result.data;
        			}else{
        				result = $isPage == false ? JSON.parse(result.data).data : JSON.parse(result.data).data.rows;
        			}
        			$.each(result, function(entryIndex, entry) {
        				
        				var html = "<option value='" + entry[$id] + "'>"
        						+ entry[$name] + "</option>";
        				$dom.find('[name="'+$parentDomName+'"]').append(html);
        				;
        			});
        		}
        	});
        }
        
        this.getChildrenValue = function() {
        	
//        	$("#selcity option[value!=0]").remove();
//        	$("#selarea option[value!=0]").remove();
        	$dom.find('[name="'+$childrenDomName+'"] option[value!=0]').remove();
        	$dom.find('[name="'+$nextChildrenDomName+'"] option[value!=0]').remove();
        	var object =$dom.find('[name="'+$parentDomName+'"]');
        	if (object.val() != 0) {
        		$.ajax( {
        			type :'get',
        			url :$url+'/'+object.val(),
        			
        			dataType :'json',
        			success : function(result) {
        				result = result.data;
        				$.each(result, function(entryIndex, entry) {
        					var html = "<option value='" + entry[$id] + "'>"
        							+ entry[$name] + "</option>";
        					$dom.find('[name="'+$childrenDomName+'"]').append(html);
        				});
        			}
        		});
        	}

        }
        
        this.getNextChildrenValue=function() {
        	
        	//$("#selarea option[value!=0]").remove();
        	$dom.find('[name="'+$nextChildrenDomName+'"] option[value!=0]').remove();
        	var object = $dom.find('[name="'+$childrenDomName+'"]');
        	if (object.val() != 0) {
        		$.ajax( {
        			type :'get',
        			url :$url+'/'+object.val(),
        			
        			dataType :'json',
        			success : function(result) {
        				result = result.data;
        				$.each(result, function(entryIndex, entry) {
        					var html = "<option value='" + entry[$id] + "'>"
        							+ entry[$name] + "</option>";
        					$dom.find('[name="'+$nextChildrenDomName+'"]').append(html);
        				});
        			}
        		});
        	}
        }

    };

}(jQuery, window, document));