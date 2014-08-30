function goPopup(url) {
	var pop = window.open(url, "popup", "width=800, height=820, scrollbars=no, menubar=no, titlebar=no");
}

function goClose(url) {
	window.opener.location.href = url;
	self.close();
}

function goPage(url) {
	location.href = url;
}

function goBack() {
	window.history.back();
}

function goNewSite(obj) {
	var url = $(obj).val();
	if(url != 'none') {
		window.open(url, '_blank');
	}
}

(function(){
	$.ajax({
		type : "GET",
		url : uri.serverUrl + uri.sessionCheck,
		cache : false,
		async : false,
		success : function(data){
			log(data);
			if(data.result.resultCode == "0000" && data.info.userNo != "") {
				user.login = true;
				user.userNo = data.info.userNo;
				user.userId = data.info.userId;
				user.userName = data.info.userName;
				user.userEmail = data.info.userEmail;
				user.enterpriseName = data.info.enterpriseName;
				user.userCellPhone = data.info.userCellPhone;
				user.consentReceiveInfoYn = data.info.consentReceiveInfoYn;				
			} 
		},
		error : function(xhr, textStatus, errorThrown){
			log(xhr);
		}
	});
	/*
	$.get( uri.serverUrl + uri.sessionCheck, function( data ) {
		log(data);
		if(data.result.resultCode == "0000" && data.info.userNo != "") {
			user.login = true;
			user.userNo = data.info.userNo;
			user.userId = data.info.userId;
			user.userName = data.info.userName;
			user.userEmail = data.info.userEmail;
			user.enterpriseName = data.info.enterpriseName;
		} 
	});
	$.get( contextPath + "/member/getSession.do", function( data ) {
		log(data);
		if(data.result == "0000" && data.login) {
			user.login = true;
			user.userNo = data.userNo;
			user.userName = data.userName;
			user.userEmail = data.userEmail;
		} 
	});*/
})();

$(document).ready(function() {
	$("#playBtn").on('click', function() {
		$(".mainRightBox #guide .subCont").hide();
		
		var tag = '<iframe width="100%" height="100%" src="http://www.youtube.com/embed/9keAjMFdN-I?feature=player_detailpage" frameborder="0" allowfullscreen></iframe>';
		
		$("#movieContainer").html(tag);
		
		/*var xpos = ((($(document).width() - 640)/2)/$(document).width())*100 ;
		var ypos = ((($(document).height() - 360)/2)/$(document).height())*100;
		
		$.blockUI({ 
			message: $("#moviePlayer"),
			css: { 
				left : xpos + '%',
				top : ypos + '%',
	            border: 'none', 
	            backgroundColor: '#fff', 
	            opacity: 1, 
	            color: '#fff' 
	        },
			overlayCSS:  { 
		        backgroundColor: '#fff', 
		        opacity:         0.6
		    }
		});
		$('.blockOverlay').click($.unblockUI); */
	});
	
});

function setReserveCall(y, m, f){
	reserve.fac = f;
	reserve.year = y;
	reserve.month = m;
	
	reserveCall();
}

function makeCalendar(y, m, data) {
	
	var date = new Date(m+"/1/"+y);
	var monthName = date.toLocaleString("en-us", {month:"long"});
	
	var title = "<div class='cal_title'>";
	title += "<input type='button' class='btn_6' value='<' onclick='setReserveCall("+(m==1?(y-1)+","+12:y+","+(m-1))+",\""+reserve.fac+"\")' />";
	title += "<p>" + y + "." + ((m < 10) ? ("0" + m) : m) + " <span>" + monthName + "<span></p>";
	title += "<input type='button' class='btn_6' value='>' onclick='setReserveCall("+(m==12?(y+1)+","+1:y+","+(m+1))+",\""+reserve.fac+"\")' />";
	title += "</div>";
	
	var text = "<table>\n<tr>"; 
	text += "<th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th>";
	text += "</tr>";
	
    var d1 = (y+(y-y%4)/4-(y-y%100)/100+(y-y%400)/400 
          +m*2+(m*5-m*5%9)/9-(m<3?y%4||y%100==0&&y%400?2:3:4))%7;
    var d2 = d1+(m*9-m*9%8) / 8%2+( m == 2?y%4 || y%100 == 0 && y%400 ? 28:29:30);
    log("d1 : " + d1 + ", d2 : " + d2);
    
    var isBlank = false;
    for (var i = 0; i < 42; i++) {    	
    	 
    	if(d2 % 7 != 0) {    		
    		if(Math.floor(i / 7) <= Math.floor(d2 / 7)){
    			isBlank = false;
        	} else {
        		isBlank = true;
        	}
    	} else {
    		if(((i+1) / 7) <= (d2 / 7)){
    			isBlank = false;
        	} else {
        		isBlank = true;
        	}
    	} 
    	
    	if(!isBlank){
    		var holiday = "";
    		log("cal i value : " + i);
    		if (i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41) {
    			holiday = "holiday";	
    		}
    		if (i % 7 == 0) text += "</tr>\n<tr>";
            if (i < d1 || i >= d2) { 
                text += '<td> </td>';
            } else { 
            	var dayId = (i+1-d1); 
                text += '<td id="' + y + '-' + ((m < 10) ? ("0" + m) : m) + '-' + ((dayId < 10) ? ("0" + dayId) : dayId) +'" class="cal_day ' + holiday + '">';
            	text += '<p>' + (i+1-d1) + '</p></td>';
            }
    	}
    } 
    
    $("#calendarDiv").html(title + text + '</tr>\n</table>');     
    
    setReserve(data);
}

function setReserve(data){
	
	var date = new Date();
	var toY = date.getFullYear();
	var toM = date.getMonth() + 1;
	var toD = date.getDate();  
	
	$(".cal_day").each(function() {
		if(!$(this).hasClass("holiday")){
		
			var $date = $(this).attr("id");
			var tag = "";
			
			var dateArr = $date.split("-");
			
			if(toY > Number(dateArr[0])){
				tag += "<input type='button' class='btn_9' disabled value='예약마감'>";
				tag += "<input type='button' class='btn_9' disabled value='예약마감'>";
			} else if(toY < Number(dateArr[0])) {
				tag += reserveDate(data, $date);
			} else {
				if(toM > Number(dateArr[1])){
					tag += "<input type='button' class='btn_9' disabled value='예약마감'>";
					tag += "<input type='button' class='btn_9' disabled value='예약마감'>";
				} else if(toM < Number(dateArr[1])){
					tag += reserveDate(data, $date);
				} else {
					if((toD+1) > Number(dateArr[2])){
						tag += "<input type='button' class='btn_9' disabled value='예약마감'>";
						tag += "<input type='button' class='btn_9' disabled value='예약마감'>";
					} else {
						tag += reserveDate(data, $date);
					}
				}
			}
			
			$(this).append(tag);
		}
	});
}

function reserveDate(data, date) {
	var returnValue = "";
	var tagArr = [];
	$(data).each(function() {
		var $obj = $("#" + this.date);
		
		if(date == this.date) {
			tagArr[this.state] = "<input type='button' class='btn_9' disabled value='예약마감'>";
		}
	});
	
	if(tagArr["1"] == "undefined" || tagArr["1"] == undefined ) {
		tagArr["1"] = "<input type='button' class='btn_10 clickable' data-time='1' value='오전가능'>";
	}
	
	if(tagArr["2"] == "undefined" || tagArr["2"] == undefined ) {
		tagArr["2"] = "<input type='button' class='btn_10 clickable' data-time='2' value='오후가능'>";
	}
	
	returnValue = tagArr["1"] + tagArr["2"];
	
	return returnValue; 
	
}

/**
 * Page Set
 * @param curPage
 * @param totalPage
 */
function page(curPage, totalPage) {
	$("#totalPage").val(totalPage);
	var $page = $(".page");
	
	var tag = "<li class='btn_6 prePage' data-page='pre' onclick='pageGo(this)'><</li>";
	
	for(var i=1; i <= totalPage; i++) {
		if(i == curPage) {
			tag += "<li class='btn_6 on' data-page='"+i+"'>"+i+"</li>";	
		} else {
			tag += "<li class='btn_6' data-page='"+i+"' onclick='pageGo(this)'>"+i+"</li>";
		}
	}
	
	tag += "<li class='btn_6 nextPage' data-page='next' onclick='pageGo(this)'>></li>"
	
	$page.empty();
	$page.html(tag);
}

/**
 * go to Page
 * @param obj
 */
function pageGo(obj) {
	var $obj = $(obj);
	var curPage = Number($("#currentPage").val())
	var goPage = curPage;
	if($obj.data("page") == "pre") {
		if(curPage > 1) {
			goPage = curPage - 1;
		}
	} else if($obj.data("page") == "next") {
		if(curPage < $("#totalPage").val()) {
			goPage = curPage + 1;	
		}
	} else {
		goPage = Number($obj.data("page"));
	}
	
	if(goPage != $("#currentPage").val()){
		$("#currentPage").val(goPage);	
		listCall();
	}
}
