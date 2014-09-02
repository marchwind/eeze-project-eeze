function log(msg) {
	console.log(msg);
}

function numberToDate(num){
	var d = new Date(num);
	return d.format("yyyy-MM-dd");
}

function numberToFull(num) {
	var d = new Date(num);
	return d.format("yyyy-MM-dd hh:mm:ss");
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function weekCount(year, month_number) {

    var firstOfMonth = new Date(year, month_number-1, 1);
    var lastOfMonth = new Date(year, month_number, 0);

    var used = firstOfMonth.getDay() + lastOfMonth.getDate();

    return Math.ceil( used / 7);
}

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

Array.prototype.remove = function(x) { 
    var i;
    for(i in this){
        if(this[i].toString() == x.toString()){
            this.splice(i,1)
        }
    }
}

// Date String parsing
String.prototype.StringToDate = function() {
	var yy = this.substr(0,4);
	var mm = this.substr(4,2);
	var dd = this.substr(6,2);
	var hh = this.substr(8,2);
	var mi = this.substr(10,2);
	var ss = this.substr(12,2);
	
	return yy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + ss;
};

/**
 * 문자열 왼쪽,오른쪽 공백을 제거.
 * @return {String} 공백제거된 문자열.
 */
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
};

String.prototype.isNum = function(){ // 숫자
	if(/[^0123456789]/g.test(this)) return false;
	else return true;
};

String.prototype.isEmail = function() { // 이메일
	var regex = /([a-zA-Z0-9_])@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/  ;
	if(this.search("[ㄱ-ㅎㅏ-ㅣ가-힣]") > -1) return false;
	if(this.search(/[<>()[\]\\,;:\s\-!~#%^*]/) > -1) return false;
	if(this.match(/[*\s*]/)) return false;
	return this.match(regex);
};

/**
 * 해당문자열의 특정문자(findStr)를 특정문자(newStr)로 전체치환.
 * 
 * @param {Object} findStr 찾을문자
 * @param {Object} newStr 대체문자
 * @return {String} 치환된문자열
 */
String.prototype.replaceAll = function(findStr, newStr) {
	return this.replace(new RegExp(findStr, "gi"), newStr);
};

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function sendRequest(form, url, successCall, errorCall) {
	
	var $form = $("#"+form);
	//var url = form.attr("action");
	var paramBody = $form.serializeObject();
	paramBody["managerMode"] = user.managerMode;
	
	log(paramBody);
	$.ajax({
		url: url,
		type: "POST",
		data: paramBody,
		cache : false,	
		success : function(res){
			log(res);
			if(res.result.resultCode == "0000"){
				successCall(res);	
			} else if(res.result.resultCode == "0400") {
				alert(msg.noData);
			} else {
				errorCall(res);
			}
		},
		error : function(xhr, textStatus, errorThrown){
			log(xhr);
			errorCall(xhr);
		}			
	});
};

function sendRequestFile(form, url, successCall, errorCall) {
	
	var $form = $("#"+form);
	//var url = form.attr("action");
	var paramBody = new FormData($form[0]);
	paramBody.append("managerMode", user.managerMode);
	
	log(paramBody);
	$.ajax({
		url: url,
		type: "POST",
		data: paramBody,
		mimeType:"multipart/form-data",
		cache : false,	
		processData: false,
        contentType: false,
        dataType: "json",
		success : function(res){
			log(res);
			successCall(res);
		},
		error : function(xhr, textStatus, errorThrown){
			log(xhr);
			errorCall(xhr);
		}			
	});
};

function sendRequestJson(url, paraData, successCall, errorCall) {
	
	var paramBody = paraData;
	paramBody["managerMode"] = user.managerMode;
	
	log(paramBody);
	$.ajax({
		url: url,
		type: "POST",
		data: paramBody,
		cache : false,	
		success : function(res){
			log(res);
			if(res.result.resultCode == "0000" || res.result.resultCode == "0400"){
				successCall(res);	
			} else {
				errorCall(res);
			}
		},
		error : function(xhr, textStatus, errorThrown){
			log(xhr);
			errorCall(xhr);
		}			
	});
};

function sendRequestHtml(url, successCall, errorCall) {
	$.ajax({
		url: url,
		type: "POST",
		data: {},
		cache : false,	
		dataType: "html",
		success : function(res){
			successCall(res);
		},
		error : function(xhr, textStatus, errorThrown){
			errorCall(xhr);
		}			
	});	
}

/**
 * Cookie
 */

function playcookie() {
	
	this.save = function(name, value, expiredays) {
		var fixExp = new Date();		
		fixExp.setDate(fixExp.getDate() + expiredays); 
		//fixExp = fixExp.setTime(fixExp.getTime() + 1000*24*60*60);
		
		var str = name + "=" + escape(value);
		str += ";expires=" + fixExp.toGMTString() + ";";		
		str += ";path=/";
		
		document.cookie = str;
		//alert(str);		
	};
	
	this.get = function(name) {		
	    var nameOfCookie = name + "=";
	    var x = 0;
	    //alert(document.cookie.length);
	    while ( x <= document.cookie.length )
	 
	    {
	            var y = (x+nameOfCookie.length);
	            if ( document.cookie.substring( x, y ) == nameOfCookie ) {
	                    if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
	                            endOfCookie = document.cookie.length;
	                    return unescape( document.cookie.substring( y, endOfCookie ) );
	            }
	            x = document.cookie.indexOf( " ", x ) + 1;
	            if ( x == 0 )
	                    break;
	    }
	    return "";
	};
	
}

var Base64 = {
		 
	    // private property
	    _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
	 
	    // public method for encoding
	    encode : function (input) {
	        var output = "";
	        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
	        var i = 0;
	 
	        input = Base64._utf8_encode(input);
	 
	        while (i < input.length) {
	 
	            chr1 = input.charCodeAt(i++);
	            chr2 = input.charCodeAt(i++);
	            chr3 = input.charCodeAt(i++);
	 
	            enc1 = chr1 >> 2;
	            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	            enc4 = chr3 & 63;
	 
	            if (isNaN(chr2)) {
	                enc3 = enc4 = 64;
	            } else if (isNaN(chr3)) {
	                enc4 = 64;
	            }
	 
	            output = output +
	            this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
	            this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
	 
	        }
	        console.log(output);
	        return output;
	    },
	 
	    // public method for decoding
	    decode : function (input) {
	        var output = "";
	        var chr1, chr2, chr3;
	        var enc1, enc2, enc3, enc4;
	        var i = 0;
	 
	        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	 
	        while (i < input.length) {
	 
	            enc1 = this._keyStr.indexOf(input.charAt(i++));
	            enc2 = this._keyStr.indexOf(input.charAt(i++));
	            enc3 = this._keyStr.indexOf(input.charAt(i++));
	            enc4 = this._keyStr.indexOf(input.charAt(i++));
	 
	            chr1 = (enc1 << 2) | (enc2 >> 4);
	            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	            chr3 = ((enc3 & 3) << 6) | enc4;
	 
	            output = output + String.fromCharCode(chr1);
	 
	            if (enc3 != 64) {
	                output = output + String.fromCharCode(chr2);
	            }
	            if (enc4 != 64) {
	                output = output + String.fromCharCode(chr3);
	            }
	 
	        }
	 
	        output = Base64._utf8_decode(output);
	 
	        return output;
	 
	    },
	 
	    // private method for UTF-8 encoding
	    _utf8_encode : function (string) {
	        string = string.replace(/\r\n/g,"\n");
	        var utftext = "";
	 
	        for (var n = 0; n < string.length; n++) {
	 
	            var c = string.charCodeAt(n);
	 
	            if (c < 128) {
	                utftext += String.fromCharCode(c);
	            }
	            else if((c > 127) && (c < 2048)) {
	                utftext += String.fromCharCode((c >> 6) | 192);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }
	            else {
	                utftext += String.fromCharCode((c >> 12) | 224);
	                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }
	 
	        }
	 
	        return utftext;
	    },
	 
	    // private method for UTF-8 decoding
	    _utf8_decode : function (utftext) {
	        var string = "";
	        var i = 0;
	        var c = c1 = c2 = 0;
	 
	        while ( i < utftext.length ) {
	 
	            c = utftext.charCodeAt(i);
	 
	            if (c < 128) {
	                string += String.fromCharCode(c);
	                i++;
	            }
	            else if((c > 191) && (c < 224)) {
	                c2 = utftext.charCodeAt(i+1);
	                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
	                i += 2;
	            }
	            else {
	                c2 = utftext.charCodeAt(i+1);
	                c3 = utftext.charCodeAt(i+2);
	                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
	                i += 3;
	            }
	 
	        }
	 
	        return string;
	    }
	 
	};
