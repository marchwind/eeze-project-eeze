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

function goPopupSite(url){
	if(url != 'none') {
		window.open(url, '_blank');
	}
}


(function(){
	$.ajax({
		type : "GET",
		url : uri.serverUrl + uri.sessionCheck,
		data : {managerMode : "Y"},
		cache : false,
		async : false,
		success : function(data){
			log(data);
			if(data.result.resultCode == "0000" && data.info.managerNo != "") {
				user.login = true;
				user.managerNo = data.info.managerNo;
				user.managerId = data.info.managerId;
				user.managerName = data.info.managerName;
				user.managerEmail = data.info.managerEmail;
			} 
		},
		error : function(xhr, textStatus, errorThrown){
			log(xhr);
		}
	});
	
	var pageUrl = window.location.href;
	pageUrl = pageUrl.substr(0,pageUrl.lastIndexOf("/"));
	gnbPostion = pageUrl.substr(pageUrl.lastIndexOf("/") + 1, pageUrl.length);
	
})();

$(document).ready(function() {
	
	
});

function setReserveCall(y, m, f){
	reserve.facilityNo = f;
	reserve.reserveYear = y;
	reserve.reserveMonth = ((m < 10) ? ("0" + m) : m);
	reserve.month = m;
	
	reserveListCall();
}

function makeCalendar(y, m, data) {
	
	var date = new Date(m+"/1/"+y);
	var monthName = date.toLocaleString("en-us", {month:"long"});
	
	var $title = $("#cal_title");
	
	var title = "<ul>"; 
	title += "<li onclick='setReserveCall("+(m==1?(y-1)+","+12:y+","+(m-1))+",\""+reserve.facilityNo+"\")'> << <li>";
	title += "<li class='red'>" + y + "." + ((m < 10) ? ("0" + m) : m) + "</li>";
	title += "<li onclick='setReserveCall("+(m==12?(y+1)+","+1:y+","+(m+1))+",\""+reserve.facilityNo+"\")'> >> </li>";
	title += "</ul>";
	
	$title.html(title);
	
	var text = "<table id='cal_list'>\n<tr>"; 
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
    
    $("#calendarDiv").html(text + '</tr>\n</table>');     
    
    setReserve(data);
}

function setReserve(data){
	
	$(".cal_day").each(function() {
		if(!$(this).hasClass("holiday")){
		
			var $date = $(this).attr("id");
			var tag = "";
			
			tag += reserveDate(data, $date);
			
			$(this).append(tag);
		}
	});
}

function reserveDate(data, dateId) {
	var date = new Date();
	var toY = date.getFullYear();
	var toM = date.getMonth() + 1;
	var toD = date.getDate();  
	var toH = (date.getHours() < 13) ? 1 : 2;
	
	var returnValue = "";
	var dateArr = dateId.split("-");
	var tagArr = [];
	
	if(toY > Number(dateArr[0]) ||
		toY == Number(dateArr[0]) && toM > Number(dateArr[1]) ||
		toY == Number(dateArr[0]) && toM == Number(dateArr[1]) && toD >= Number(dateArr[2])) {
		tagArr["1"] = '<input type="button" value="기간종료" class="btn_over_period"/>';
		tagArr["2"] = '<input type="button" value="기간종료" class="btn_over_period"/>';
	} else {
		tagArr["1"] = '<input type="button" class="btn_possible_period clickable" data-time="1" value="오전가능">';
		tagArr["2"] = '<input type="button" class="btn_possible_period clickable" data-time="2" value="오후가능">';
	}	
		

	$(data).each(function() {
		
		if(dateId == this.date){
			if(toY > Number(dateArr[0]) || 
				toY == Number(dateArr[0]) && toM > Number(dateArr[1]) ||
				toY == Number(dateArr[0]) && toM == Number(dateArr[1]) && toD > Number(dateArr[2])){
				tagArr[this.state] = "<input type='button' class='btn_used_period' data-reserveno='"+this.reserveNo+"' data-reservedetailno='"+this.reserveDetailNo+"' onclick='usedPopup(this)' value='"+this.enterpriseName+"'>";
			} else if(toY == Number(dateArr[0]) && toM == Number(dateArr[1]) && toD == Number(dateArr[2])) {
				if(toH == this.state){
					if(this.checkInYn == "Y") {
						tagArr[this.state] = "<input type='button' class='btn_today_period' data-reserveno='"+this.reserveNo+"' data-reservedetailno='"+this.reserveDetailNo+"' onclick='checkinPopup(this)' value='"+this.enterpriseName+"'>";	
					}
					if(this.checkOutYn == "Y") {
						tagArr[this.state] = "<input type='button' class='btn_used_period' data-reserveno='"+this.reserveNo+"' data-reservedetailno='"+this.reserveDetailNo+"' onclick='usedPopup(this)' value='"+this.enterpriseName+"'>";	
					}	
				} else if(toH < this.state){
					tagArr[this.state] = "<input type='button' class='btn_reserve_period' data-reserveno='"+this.reserveNo+"' data-date='"+this.date+"' data-reservedetailno='"+this.reserveDetailNo+"' onclick='reserveCheckPopup(this)' value='"+this.enterpriseName+"'>";
				} else {
					tagArr[this.state] = "<input type='button' class='btn_used_period' data-reserveno='"+this.reserveNo+"' data-reservedetailno='"+this.reserveDetailNo+"' onclick='usedPopup(this)' value='"+this.enterpriseName+"'>";
				}
				
			} else {
				tagArr[this.state] = "<input type='button' class='btn_reserve_period' data-reserveno='"+this.reserveNo+"' data-date='"+this.date+"' data-reservedetailno='"+this.reserveDetailNo+"' onclick='reserveCheckPopup(this)' value='"+this.enterpriseName+"'>";
			}
		}
		
		
	});
	
	returnValue = tagArr["1"] + tagArr["2"];
	
	return returnValue; 
	
}

/**
 * show popup
 * @param res
 */
function showPopup(res) {	
	$('.popup').html(res);
	
	var docW = $(window).width();
	var docH = $(window).height()
	var popW = $('.popup').width();
	var popH = $('.popup').height();
	
	var cenW = docW/2 - (popW/2 + 50);
	//var cenH = docH/2 - (popH/2 + 50);
	log("docW : " + docW + ", popW : " + popW);
	log("cenW : " + cenW);
	var cenH = 50;
	
	 $.blockUI({ 
		 message: $('.popup'), 
		 css: { 
			 top : cenH + "px",
			 left : cenW + "px",
	         border: 'none', 
	         padding: '50px', 
	         backgroundColor: '#fff', 
	         color: '#464646'
		 },
		 overlayCSS: { 
			 backgroundColor: '#000000',
			 opacity : .2
		 } 
     });
	 
	 $(".blockOverlay").click(function() {
		closePopup(); 
	 });
}

function closePopup() {
	$.unblockUI();
}

/**
 * Page Set
 * @param curPage
 * @param totalPage
 */
var pageCallFn;
function page(curPage, totalPage, callFn, container) {
	pageValue.totalPage = totalPage;
	var $page;
	
	if(callFn != null){
		pageCallFn = callFn;
	} else {
		pageCallFn = pageListCall;
	}
	
	if(container != null){
		$page = $("#" + container + ".page");
	} else {
		$page = $(".page");	
	}
	
	
	var tag = "<ul><li class='btn_6 prePage' data-page='pre' onclick='pageGo(this)'> << </li>";
	
	var roopStart = Math.floor(curPage / 10) * 10 + 1;
	if((curPage % 10) == 0) {
		roopStart -= 10;
	}
	
	var endPage = roopStart + 9;
	
	if(endPage > totalPage) {
		endPage = totalPage;
	}
	
	for(var i = roopStart; i <= endPage; i++) {
		if(i == curPage) {
			tag += "<li class='btn_6 on' data-page='"+i+"'>"+i+"</li>";	
		} else {
			tag += "<li class='btn_6' data-page='"+i+"' onclick='pageGo(this)'>"+i+"</li>";
		}
	}
	
	tag += "<li class='btn_6 nextPage' data-page='next' onclick='pageGo(this)'> >> </li></ul>"
	
	$page.empty();
	$page.html(tag);
}

/**
 * go to Page
 * @param obj
 */
function pageGo(obj) {
	var $obj = $(obj);
	var curPage = Number(pageValue.currentPage)
	var goPage = curPage;
	if($obj.data("page") == "pre") {
		if(curPage > 1) {
			goPage = curPage - 1;
		}
	} else if($obj.data("page") == "next") {
		if(curPage < pageValue.totalPage) {
			goPage = curPage + 1;	
		}
	} else {
		goPage = Number($obj.data("page"));
	}
	
	if(goPage != pageValue.currentPage){
		pageValue.currentPage = goPage;	
		pageCallFn();
	}
}
