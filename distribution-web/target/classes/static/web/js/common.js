function jiliang(e) {

$('.jiliang span.f_l').click(function(){
	var nam = parseInt($(this).siblings('.jl').val());
	if(nam > 0){
		nam = nam-1;
	}
	$(this).siblings('.jl').val(nam);
})
$('.jiliang span.f_r').click(function(){
	var nam = parseInt($(this).siblings('.jl').val());
	var max = parseInt($(this).siblings('.jl').attr('data-max'));
	if(nam < max){
		nam = nam + 1;
	}else{
		alert('已超出库存！')
	}
	$(this).siblings('.jl').val(nam);
})
}


function Return(){
    window.history.back(-1); 
}

































function getvl(name) {
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " "));
	return "";
};

(function($){  
        $.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();  
            var str=this.serialize();  
            var checkboxvalue='';
            
            $(array).each(function(){  
            	
                if(serializeObj[this.name]){
                	serializeObj[this.name] = serializeObj[this.name] +"," + this.value;
                    /*if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  */
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        };  
    })(jQuery)
    
/**
 * form
 */    
; $(function ($, window, document, undefined) {
	Form = function($dom, options){
		"use strict"; // stirct mode not support by IE9-
		if (!$dom)
			return;
		var $options = options || {},
		$data = $options.data;
		this.formEdit = function($options){
			return $dom.each(function(){
	            var input, name;
	            if($data == null){
	            	this.reset(); 
	            	$(this).find('input').change();
	            	return; 
	            }
	            for(var i = 0; i < this.length; i++){  
	                input = this.elements[i];
	            
	                //checkbox的name可能是name[]数组形式
	                name = (input.type == "checkbox")? input.name.replace(/(.+)\[\]$/, "$1") : input.name;
	                if($data[name] == undefined) continue;
	                
	                switch(input.type){
	                    case "checkbox":
	                        if($data[name] == ""){
	                            input.checked = false;
	                        }else{
	                            //数组查找元素
	                        
	                            if($data[name].indexOf(input.value) > -1){
	                                input.checked = true;
	                            }else{
	                                input.checked = false;
	                            }
	                        }
	                    break;
	                    case "radio":
	                        if($data[name] == ""){
	                            input.checked = false;
	                        }else if(input.value == $data[name]){
	                            input.checked = true;
	                        }
	                    break;
	                    case "select-one":
	                    	//alert("待解决");
	                    	//var opt = $('<option value="0">0</option>');
	                    	/*$(input).find('option').remove();
	                    	var opt = $('<option value="'+$data[name]+'">'+$data[name+"Name"]+'</option>');
	                    	$(input).append(opt);*/
	                    	
	                    	
	                    	//var a=$(input).find('option[value='+$data[name]+']').eq(0);
	                    	var isfind=false;
	                    	$(input).find('option[value='+$data[name]+']').each(function(){
	                    		isfind=true;
	                    	});

	                    	if(!isfind){
	                    		var opt = $('<option value="'+$data[name]+'">'+$data[name+"Name"]+'</option>');
		                    	$(input).append(opt);
	                    	}
	                    	input.value = $data[name];
	                    	$(input).change();
	                    	
	                    	
	                    	
	                    	/*var opt = $('<option value="'+$data[name]+'">'+$data[name+"Name"]+'</option>');
	                    	$(input).append(opt);
	                    	input.value = $data[name];
	                    	$(input).change();*/
	                    	
	                    break;
	                    case "button": break;
	                    case "file":
	                    	alert($data[name]);
	                    	break;
	                    default:
	                    	input.value = $data[name];
	                    	if(input.onchange){
	                    		input.onchange();
	                    	}
	                }
	            }
	           
	        });
		}
	};
}(jQuery, window, document));
    
    /**
 * ajax
 */
; $(function ($, window, document, undefined) {
    
    Ajax = function ($dom, options) {
    	var self =this;
        "use strict"; //stirct mode not support by IE9-

        var $options = options || {},
        $url = $options.url,
        $param = $options.param,
        $type = $options.type,
        $callback= $options.callback,
        $isNeedSign = $options.isNeedSign,
        $async = $options.async == null? true: $options.async;
        
        if('' != $param && null != $param){
        	
        	if($isNeedSign){
        		$param.fId=USER.userId;
            	$param.nonceStr='84584784';
            	$param.timeStamp='1347656786';
            	$param.token = USER.token;
        	}
        	/*$param = JSON.stringify($param);
    		var base64 = new Base64('', {
    			param : $param
    		});
    		$param=base64.doit();*/
        }
        /*var sign =null;
        if($isNeedSign){
           	
            var token = USER.token;
            var keyBody = $param + "key="+token;
    		var md5Sign =$.md5(keyBody).toUpperCase();
    		sign = $.sha1($param + md5Sign);
        }*/
		
        $.ajax({
			type :$type,
			url:  $url,  //数据源,
			dataType :'json',
			data: $param,
			//headers: {'Content-Type': 'application/json'},
			contentType:"application/json;charset=utf-8",
			async:$async,
			/*headers: {
	            	"Content-Type":'application/json; charset=utf-8',
                    "Accept-Language": "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3",
                    sign : sign
	        },*/
			error : function(_xhr, _errormsg, _e) {
				// console.error("ajax request url of " + _conf.url + " error for " + _errormsg);
				if (_xhr.status === 0 || _xhr.status === 503) {
					alert('网络异常,请检查网络是否通畅!');
				}
			},
			success : function(_resp) {
				/*if(JSON.parse(_resp.data).code != 0){
					alert(JSON.parse(_resp.data).msg);
				}else{
					$callback && $callback(_resp);
				}*/
				$callback && $callback(_resp);
			}
		});
    };
}(jQuery, window, document));

/**
 * jQuery MD5 hash algorithm function
 * 
 * 	<code>
 * 		Calculate the md5 hash of a String 
 * 		String $.md5 ( String str )
 * 	</code>
 * 
 * Calculates the MD5 hash of str using the 禄 RSA Data Security, Inc. MD5 Message-Digest Algorithm, and returns that hash. 
 * MD5 (Message-Digest algorithm 5) is a widely-used cryptographic hash function with a 128-bit hash value. MD5 has been employed in a wide variety of security applications, and is also commonly used to check the integrity of data. The generated hash is also non-reversable. Data cannot be retrieved from the message digest, the digest uniquely identifies the data.
 * MD5 was developed by Professor Ronald L. Rivest in 1994. Its 128 bit (16 byte) message digest makes it a faster implementation than SHA-1.
 * This script is used to process a variable length message into a fixed-length output of 128 bits using the MD5 algorithm. It is fully compatible with UTF-8 encoding. It is very useful when u want to transfer encrypted passwords over the internet. If you plan using UTF-8 encoding in your project don't forget to set the page encoding to UTF-8 (Content-Type meta tag). 
 * This function orginally get from the WebToolkit and rewrite for using as the jQuery plugin.
 * 
 * Example
 * 	Code
 * 		<code>
 * 			$.md5("I'm Persian."); 
 * 		</code>
 * 	Result
 * 		<code>
 * 			"b8c901d0f02223f9761016cfff9d68df"
 * 		</code>
 * 
 * @alias Muhammad Hussein Fattahizadeh < muhammad [AT] semnanweb [DOT] com >
 * @link http://www.semnanweb.com/jquery-plugin/md5.html
 * @see http://www.webtoolkit.info/
 * @license http://www.gnu.org/licenses/gpl.html [GNU General Public License]
 * @param {jQuery} {md5:function(string))
 * @return string
 */
(function($){
	var rotateLeft = function(lValue, iShiftBits) {
		return (lValue << iShiftBits) | (lValue >>> (32 - iShiftBits));
	}
	var addUnsigned = function(lX, lY) {
		var lX4, lY4, lX8, lY8, lResult;
		lX8 = (lX & 0x80000000);
		lY8 = (lY & 0x80000000);
		lX4 = (lX & 0x40000000);
		lY4 = (lY & 0x40000000);
		lResult = (lX & 0x3FFFFFFF) + (lY & 0x3FFFFFFF);
		if (lX4 & lY4) return (lResult ^ 0x80000000 ^ lX8 ^ lY8);
		if (lX4 | lY4) {
			if (lResult & 0x40000000) return (lResult ^ 0xC0000000 ^ lX8 ^ lY8);
			else return (lResult ^ 0x40000000 ^ lX8 ^ lY8);
		} else {
			return (lResult ^ lX8 ^ lY8);
		}
	}
	var F = function(x, y, z) {
		return (x & y) | ((~ x) & z);
	}
	var G = function(x, y, z) {
		return (x & z) | (y & (~ z));
	}
	var H = function(x, y, z) {
		return (x ^ y ^ z);
	}
	var I = function(x, y, z) {
		return (y ^ (x | (~ z)));
	}
	var FF = function(a, b, c, d, x, s, ac) {
		a = addUnsigned(a, addUnsigned(addUnsigned(F(b, c, d), x), ac));
		return addUnsigned(rotateLeft(a, s), b);
	};
	var GG = function(a, b, c, d, x, s, ac) {
		a = addUnsigned(a, addUnsigned(addUnsigned(G(b, c, d), x), ac));
		return addUnsigned(rotateLeft(a, s), b);
	};
	var HH = function(a, b, c, d, x, s, ac) {
		a = addUnsigned(a, addUnsigned(addUnsigned(H(b, c, d), x), ac));
		return addUnsigned(rotateLeft(a, s), b);
	};
	var II = function(a, b, c, d, x, s, ac) {
		a = addUnsigned(a, addUnsigned(addUnsigned(I(b, c, d), x), ac));
		return addUnsigned(rotateLeft(a, s), b);
	};
	var convertToWordArray = function(string) {
		var lWordCount;
		var lMessageLength = string.length;
		var lNumberOfWordsTempOne = lMessageLength + 8;
		var lNumberOfWordsTempTwo = (lNumberOfWordsTempOne - (lNumberOfWordsTempOne % 64)) / 64;
		var lNumberOfWords = (lNumberOfWordsTempTwo + 1) * 16;
		var lWordArray = Array(lNumberOfWords - 1);
		var lBytePosition = 0;
		var lByteCount = 0;
		while (lByteCount < lMessageLength) {
			lWordCount = (lByteCount - (lByteCount % 4)) / 4;
			lBytePosition = (lByteCount % 4) * 8;
			lWordArray[lWordCount] = (lWordArray[lWordCount] | (string.charCodeAt(lByteCount) << lBytePosition));
			lByteCount++;
		}
		lWordCount = (lByteCount - (lByteCount % 4)) / 4;
		lBytePosition = (lByteCount % 4) * 8;
		lWordArray[lWordCount] = lWordArray[lWordCount] | (0x80 << lBytePosition);
		lWordArray[lNumberOfWords - 2] = lMessageLength << 3;
		lWordArray[lNumberOfWords - 1] = lMessageLength >>> 29;
		return lWordArray;
	};
	var wordToHex = function(lValue) {
		var WordToHexValue = "", WordToHexValueTemp = "", lByte, lCount;
		for (lCount = 0; lCount <= 3; lCount++) {
			lByte = (lValue >>> (lCount * 8)) & 255;
			WordToHexValueTemp = "0" + lByte.toString(16);
			WordToHexValue = WordToHexValue + WordToHexValueTemp.substr(WordToHexValueTemp.length - 2, 2);
		}
		return WordToHexValue;
	};
	var uTF8Encode = function(string) {
		string = string.replace(/\x0d\x0a/g, "\x0a");
		var output = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				output += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				output += String.fromCharCode((c >> 6) | 192);
				output += String.fromCharCode((c & 63) | 128);
			} else {
				output += String.fromCharCode((c >> 12) | 224);
				output += String.fromCharCode(((c >> 6) & 63) | 128);
				output += String.fromCharCode((c & 63) | 128);
			}
		}
		return output;
	};
	$.extend({
		md5: function(string) {
			var x = Array();
			var k, AA, BB, CC, DD, a, b, c, d;
			var S11=7, S12=12, S13=17, S14=22;
			var S21=5, S22=9 , S23=14, S24=20;
			var S31=4, S32=11, S33=16, S34=23;
			var S41=6, S42=10, S43=15, S44=21;
			string = uTF8Encode(string);
			x = convertToWordArray(string);
			a = 0x67452301; b = 0xEFCDAB89; c = 0x98BADCFE; d = 0x10325476;
			for (k = 0; k < x.length; k += 16) {
				AA = a; BB = b; CC = c; DD = d;
				a = FF(a, b, c, d, x[k+0],  S11, 0xD76AA478);
				d = FF(d, a, b, c, x[k+1],  S12, 0xE8C7B756);
				c = FF(c, d, a, b, x[k+2],  S13, 0x242070DB);
				b = FF(b, c, d, a, x[k+3],  S14, 0xC1BDCEEE);
				a = FF(a, b, c, d, x[k+4],  S11, 0xF57C0FAF);
				d = FF(d, a, b, c, x[k+5],  S12, 0x4787C62A);
				c = FF(c, d, a, b, x[k+6],  S13, 0xA8304613);
				b = FF(b, c, d, a, x[k+7],  S14, 0xFD469501);
				a = FF(a, b, c, d, x[k+8],  S11, 0x698098D8);
				d = FF(d, a, b, c, x[k+9],  S12, 0x8B44F7AF);
				c = FF(c, d, a, b, x[k+10], S13, 0xFFFF5BB1);
				b = FF(b, c, d, a, x[k+11], S14, 0x895CD7BE);
				a = FF(a, b, c, d, x[k+12], S11, 0x6B901122);
				d = FF(d, a, b, c, x[k+13], S12, 0xFD987193);
				c = FF(c, d, a, b, x[k+14], S13, 0xA679438E);
				b = FF(b, c, d, a, x[k+15], S14, 0x49B40821);
				a = GG(a, b, c, d, x[k+1],  S21, 0xF61E2562);
				d = GG(d, a, b, c, x[k+6],  S22, 0xC040B340);
				c = GG(c, d, a, b, x[k+11], S23, 0x265E5A51);
				b = GG(b, c, d, a, x[k+0],  S24, 0xE9B6C7AA);
				a = GG(a, b, c, d, x[k+5],  S21, 0xD62F105D);
				d = GG(d, a, b, c, x[k+10], S22, 0x2441453);
				c = GG(c, d, a, b, x[k+15], S23, 0xD8A1E681);
				b = GG(b, c, d, a, x[k+4],  S24, 0xE7D3FBC8);
				a = GG(a, b, c, d, x[k+9],  S21, 0x21E1CDE6);
				d = GG(d, a, b, c, x[k+14], S22, 0xC33707D6);
				c = GG(c, d, a, b, x[k+3],  S23, 0xF4D50D87);
				b = GG(b, c, d, a, x[k+8],  S24, 0x455A14ED);
				a = GG(a, b, c, d, x[k+13], S21, 0xA9E3E905);
				d = GG(d, a, b, c, x[k+2],  S22, 0xFCEFA3F8);
				c = GG(c, d, a, b, x[k+7],  S23, 0x676F02D9);
				b = GG(b, c, d, a, x[k+12], S24, 0x8D2A4C8A);
				a = HH(a, b, c, d, x[k+5],  S31, 0xFFFA3942);
				d = HH(d, a, b, c, x[k+8],  S32, 0x8771F681);
				c = HH(c, d, a, b, x[k+11], S33, 0x6D9D6122);
				b = HH(b, c, d, a, x[k+14], S34, 0xFDE5380C);
				a = HH(a, b, c, d, x[k+1],  S31, 0xA4BEEA44);
				d = HH(d, a, b, c, x[k+4],  S32, 0x4BDECFA9);
				c = HH(c, d, a, b, x[k+7],  S33, 0xF6BB4B60);
				b = HH(b, c, d, a, x[k+10], S34, 0xBEBFBC70);
				a = HH(a, b, c, d, x[k+13], S31, 0x289B7EC6);
				d = HH(d, a, b, c, x[k+0],  S32, 0xEAA127FA);
				c = HH(c, d, a, b, x[k+3],  S33, 0xD4EF3085);
				b = HH(b, c, d, a, x[k+6],  S34, 0x4881D05);
				a = HH(a, b, c, d, x[k+9],  S31, 0xD9D4D039);
				d = HH(d, a, b, c, x[k+12], S32, 0xE6DB99E5);
				c = HH(c, d, a, b, x[k+15], S33, 0x1FA27CF8);
				b = HH(b, c, d, a, x[k+2],  S34, 0xC4AC5665);
				a = II(a, b, c, d, x[k+0],  S41, 0xF4292244);
				d = II(d, a, b, c, x[k+7],  S42, 0x432AFF97);
				c = II(c, d, a, b, x[k+14], S43, 0xAB9423A7);
				b = II(b, c, d, a, x[k+5],  S44, 0xFC93A039);
				a = II(a, b, c, d, x[k+12], S41, 0x655B59C3);
				d = II(d, a, b, c, x[k+3],  S42, 0x8F0CCC92);
				c = II(c, d, a, b, x[k+10], S43, 0xFFEFF47D);
				b = II(b, c, d, a, x[k+1],  S44, 0x85845DD1);
				a = II(a, b, c, d, x[k+8],  S41, 0x6FA87E4F);
				d = II(d, a, b, c, x[k+15], S42, 0xFE2CE6E0);
				c = II(c, d, a, b, x[k+6],  S43, 0xA3014314);
				b = II(b, c, d, a, x[k+13], S44, 0x4E0811A1);
				a = II(a, b, c, d, x[k+4],  S41, 0xF7537E82);
				d = II(d, a, b, c, x[k+11], S42, 0xBD3AF235);
				c = II(c, d, a, b, x[k+2],  S43, 0x2AD7D2BB);
				b = II(b, c, d, a, x[k+9],  S44, 0xEB86D391);
				a = addUnsigned(a, AA);
				b = addUnsigned(b, BB);
				c = addUnsigned(c, CC);
				d = addUnsigned(d, DD);
			}
			var tempValue = wordToHex(a) + wordToHex(b) + wordToHex(c) + wordToHex(d);
			return tempValue.toLowerCase();
		}
	});
})(jQuery);




(function($){
    
    var rotateLeft = function(lValue, iShiftBits) {
        return (lValue << iShiftBits) | (lValue >>> (32 - iShiftBits));
    }
    
    var lsbHex = function(value) {
        var string = "";
        var i;
        var vh;
        var vl;
        for(i = 0;i <= 6;i += 2) {
            vh = (value>>>(i * 4 + 4))&0x0f;
            vl = (value>>>(i*4))&0x0f;
            string += vh.toString(16) + vl.toString(16);
        }
        return string;
    };
    
    var cvtHex = function(value) {
        var string = "";
        var i;
        var v;
        for(i = 7;i >= 0;i--) {
            v = (value>>>(i * 4))&0x0f;
            string += v.toString(16);
        }
        return string;
    };
    
    var uTF8Encode = function(string) {
        string = string.replace(/\x0d\x0a/g, "\x0a");
        var output = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                output += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                output += String.fromCharCode((c >> 6) | 192);
                output += String.fromCharCode((c & 63) | 128);
            } else {
                output += String.fromCharCode((c >> 12) | 224);
                output += String.fromCharCode(((c >> 6) & 63) | 128);
                output += String.fromCharCode((c & 63) | 128);
            }
        }
        return output;
    };
    
    $.extend({
        sha1: function(string) {
            var blockstart;
            var i, j;
            var W = new Array(80);
            var H0 = 0x67452301;
            var H1 = 0xEFCDAB89;
            var H2 = 0x98BADCFE;
            var H3 = 0x10325476;
            var H4 = 0xC3D2E1F0;
            var A, B, C, D, E;
            var tempValue;
            string = uTF8Encode(string);
            var stringLength = string.length;
            var wordArray = new Array();
            for(i = 0;i < stringLength - 3;i += 4) {
                j = string.charCodeAt(i)<<24 | string.charCodeAt(i + 1)<<16 | string.charCodeAt(i + 2)<<8 | string.charCodeAt(i + 3);
                wordArray.push(j);
            }
            switch(stringLength % 4) {
                case 0:
                    i = 0x080000000;
                break;
                case 1:
                    i = string.charCodeAt(stringLength - 1)<<24 | 0x0800000;
                break;
                case 2:
                    i = string.charCodeAt(stringLength - 2)<<24 | string.charCodeAt(stringLength - 1)<<16 | 0x08000;
                break;
                case 3:
                    i = string.charCodeAt(stringLength - 3)<<24 | string.charCodeAt(stringLength - 2)<<16 | string.charCodeAt(stringLength - 1)<<8 | 0x80;
                break;
            }
            wordArray.push(i);
            while((wordArray.length % 16) != 14 ) wordArray.push(0);
            wordArray.push(stringLength>>>29);
            wordArray.push((stringLength<<3)&0x0ffffffff);
            for(blockstart = 0;blockstart < wordArray.length;blockstart += 16) {
                for(i = 0;i < 16;i++) W[i] = wordArray[blockstart+i];
                for(i = 16;i <= 79;i++) W[i] = rotateLeft(W[i-3] ^ W[i-8] ^ W[i-14] ^ W[i-16], 1);
                A = H0;
                B = H1;
                C = H2;
                D = H3;
                E = H4;
                for(i = 0;i <= 19;i++) {
                    tempValue = (rotateLeft(A, 5) + ((B&C) | (~B&D)) + E + W[i] + 0x5A827999) & 0x0ffffffff;
                    E = D;
                    D = C;
                    C = rotateLeft(B, 30);
                    B = A;
                    A = tempValue;
                }
                for(i = 20;i <= 39;i++) {
                    tempValue = (rotateLeft(A, 5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & 0x0ffffffff;
                    E = D;
                    D = C;
                    C = rotateLeft(B, 30);
                    B = A;
                    A = tempValue;
                }
                for(i = 40;i <= 59;i++) {
                    tempValue = (rotateLeft(A, 5) + ((B&C) | (B&D) | (C&D)) + E + W[i] + 0x8F1BBCDC) & 0x0ffffffff;
                    E = D;
                    D = C;
                    C = rotateLeft(B, 30);
                    B = A;
                    A = tempValue;
                }
                for(i = 60;i <= 79;i++) {
                    tempValue = (rotateLeft(A, 5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & 0x0ffffffff;
                    E = D;
                    D = C;
                    C = rotateLeft(B, 30);
                    B = A;
                    A = tempValue;
                }
                H0 = (H0 + A) & 0x0ffffffff;
                H1 = (H1 + B) & 0x0ffffffff;
                H2 = (H2 + C) & 0x0ffffffff;
                H3 = (H3 + D) & 0x0ffffffff;
                H4 = (H4 + E) & 0x0ffffffff;
            }
            var tempValue = cvtHex(H0) + cvtHex(H1) + cvtHex(H2) + cvtHex(H3) + cvtHex(H4);
            return tempValue.toLowerCase();
        }
    });
})(jQuery);


jQuery.extend({
    createUploadIframe: function(id, uri)
	{
		//create frame
		var frameId = 'jUploadFrame' + id;
		var iframeHtml = '<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute; top:-9999px; left:-9999px"';
		if(window.ActiveXObject)
		{
			if(typeof uri== 'boolean'){
				iframeHtml += ' src="' + 'javascript:false' + '"';

			}
			else if(typeof uri== 'string'){
				iframeHtml += ' src="' + uri + '"';

			}	
		}
		iframeHtml += ' />';
		jQuery(iframeHtml).appendTo(document.body);

		return jQuery('#' + frameId).get(0);			
    },
    createUploadForm: function(id,fileElementId,data,fileElement)
	{
		//create form	
		var formId = 'jUploadForm' + id;
		var fileId = 'jUploadFile' + id;
		var form = jQuery('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');	
		if(data)
		{
			for(var i in data)
			{
				jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
			}			
		}
		var oldElement;
		if(fileElement == null)
			oldElement = jQuery('#' + fileElementId);
		else
			oldElement = fileElement;
		
		var newElement = jQuery(oldElement).clone();
		jQuery(oldElement).attr('id', fileId);
		jQuery(oldElement).before(newElement);
		jQuery(oldElement).appendTo(form);
		
		//set attributes
		jQuery(form).css('position', 'absolute');
		jQuery(form).css('top', '-1200px');
		jQuery(form).css('left', '-1200px');
		jQuery(form).appendTo('body');		
		return form;
    },

    ajaxFileUpload: function(s) {
        // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout		
        s = jQuery.extend({}, jQuery.ajaxSettings, s);
        var id = new Date().getTime()        
		var form = jQuery.createUploadForm(id, s.fileElementId, (typeof(s.data)=='undefined'?false:s.data),s.fileElement);
		var io = jQuery.createUploadIframe(id, s.secureuri);
		var frameId = 'jUploadFrame' + id;
		var formId = 'jUploadForm' + id;		
        // Watch for a new set of requests
        if ( s.global && ! jQuery.active++ )
		{
			jQuery.event.trigger( "ajaxStart" );
		}            
        var requestDone = false;
        // Create the request object
        var xml = {}   
        if ( s.global )
            jQuery.event.trigger("ajaxSend", [xml, s]);
        // Wait for a response to come back
        var uploadCallback = function(isTimeout)
		{			
			var io = document.getElementById(frameId);
            try 
			{				
				if(io.contentWindow)
				{
					 xml.responseText = io.contentWindow.document.body?io.contentWindow.document.body.innerHTML:null;
                	 xml.responseXML = io.contentWindow.document.XMLDocument?io.contentWindow.document.XMLDocument:io.contentWindow.document;
					 
				}else if(io.contentDocument)
				{
					 xml.responseText = io.contentDocument.document.body?io.contentDocument.document.body.innerHTML:null;
                	xml.responseXML = io.contentDocument.document.XMLDocument?io.contentDocument.document.XMLDocument:io.contentDocument.document;
				}						
            }catch(e)
			{
				jQuery.handleError(s, xml, null, e);
			}
            if ( xml || isTimeout == "timeout") 
			{				
                requestDone = true;
                var status;
                try {
                    status = isTimeout != "timeout" ? "success" : "error";
                    // Make sure that the request was successful or notmodified
                    if ( status != "error" )
					{
                        // process the data (runs the xml through httpData regardless of callback)
                        var data = jQuery.uploadHttpData( xml, s.dataType );    
                        // If a local callback was specified, fire it and pass it the data
                        if ( s.success )
                            s.success( data, status );
    
                        // Fire the global callback
                        if( s.global )
                            jQuery.event.trigger( "ajaxSuccess", [xml, s] );
                    } else
                        jQuery.handleError(s, xml, status);
                } catch(e) 
				{
                    status = "error";
                    jQuery.handleError(s, xml, status, e);
                }

                // The request was completed
                if( s.global )
                    jQuery.event.trigger( "ajaxComplete", [xml, s] );

                // Handle the global AJAX counter
                if ( s.global && ! --jQuery.active )
                    jQuery.event.trigger( "ajaxStop" );

                // Process result
                if ( s.complete )
                    s.complete(xml, status);

                jQuery(io).unbind()

                setTimeout(function()
									{	try 
										{
											jQuery(io).remove();
											jQuery(form).remove();	
											
										} catch(e) 
										{
											jQuery.handleError(s, xml, null, e);
										}									

									}, 100)

                xml = null

            }
        }
        // Timeout checker
        if ( s.timeout > 0 ) 
		{
            setTimeout(function(){
                // Check to see if the request is still happening
                if( !requestDone ) uploadCallback( "timeout" );
            }, s.timeout);
        }
        try 
		{

			var form = jQuery('#' + formId);
			jQuery(form).attr('action', s.url);
			jQuery(form).attr('method', 'POST');
			jQuery(form).attr('target', frameId);
            if(form.encoding)
			{
				jQuery(form).attr('encoding', 'multipart/form-data');      			
            }
            else
			{	
				jQuery(form).attr('enctype', 'multipart/form-data');			
            }			
            jQuery(form).submit();

        } catch(e) 
		{			
            jQuery.handleError(s, xml, null, e);
        }
		
		jQuery('#' + frameId).load(uploadCallback);
        return {abort: function(){
			try
			{
				jQuery('#' + frameId).remove();
				jQuery(form).remove();
			}
			catch(e){}
		}};
    },

    uploadHttpData: function( r, type ) {
        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
		
        // If the type is "script", eval it in global context
        if ( type == "script" )
            jQuery.globalEval( data );
        // Get the JavaScript object, if JSON is used.
        if ( type == "json"){
        	//eval( "data = " + data );
        	data = jQuery.parseJSON(jQuery(data).text());
        }
        if(type == "application/json"){
        	data = '<pre>'+jQuery(data).text()+'</pre>';
        }
        // evaluate scripts within html
        if ( type == "html" )
            jQuery("<div>").html(data).evalScripts();

        return data;
    },
	
	handleError: function( s, xml, status, e ) {
		// If a local callback was specified, fire it
		if ( s.error )
			s.error( xml, status, e );

		// Fire the global callback
		if ( s.global )
			jQuery.event.trigger( "ajaxError", [xml, s, e] );
	}
});


//
//var json = '{"itemId" : <%=itemId%>,"machineId" : <%=machineId%>}';
//var jsondata = base64encode(utf16to8(json));

//var  p = $addForm.find('form').serializeJson();
//var jsondata = base64encode(utf16to8(p));

var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

function base64encode(str) {
	var out, i, len;
	var c1, c2, c3;

	len = str.length;
	i = 0;
	out = "";
	while (i < len) {
		c1 = str.charCodeAt(i++) & 0xff;
		if (i == len) {
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt((c1 & 0x3) << 4);
			out += "==";
			break;
		}
		c2 = str.charCodeAt(i++);
		if (i == len) {
			out += base64EncodeChars.charAt(c1 >> 2);
			out += base64EncodeChars.charAt(((c1 & 0x3) << 4)
					| ((c2 & 0xF0) >> 4));
			out += base64EncodeChars.charAt((c2 & 0xF) << 2);
			out += "=";
			break;
		}
		c3 = str.charCodeAt(i++);
		out += base64EncodeChars.charAt(c1 >> 2);
		out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
		out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
		out += base64EncodeChars.charAt(c3 & 0x3F);
	}
	return out;
}

function utf16to8(str) {
	var out, i, len, c;

	out = "";
	len = str.length;
	for (i = 0; i < len; i++) {
		c = str.charCodeAt(i);
		if ((c >= 0x0001) && (c <= 0x007F)) {
			out += str.charAt(i);
		} else if (c > 0x07FF) {
			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
			out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		} else {
			out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
		}
	}
	return out;
}

var globalurl="http://localhost:30000"

//登陆用户信息(id)	
var globalUserId="";
//推广人id
var promoterId="";
	
