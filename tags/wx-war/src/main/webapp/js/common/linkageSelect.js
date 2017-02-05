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
		$nextChildrenDomName = $options.nextChildrenDomName
        
       
        
        this.getParentValue=function() {
        	$.ajax( {
        		type :'get',
        		url :$url+'/0',
        		dataType :'json',
        		success : function(result) {
        			result = result.data;
        			
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