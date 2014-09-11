<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${contextPath}/resources/js/smartAD_main_init.js"></script>
<!-- <script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=f6d31ce226a42277db9aaa2fe959cf32282d72b0"></script> --> 

<script type="text/javascript">
 
var map;

$(document).ready(function(){
	//init();
	rightNoticeCall();
});

function init() {
	map = new daum.maps.Map(document.getElementById('map'), {
   		center: new daum.maps.LatLng(37.515958745703564, 127.099594336588),
   		level: 4
  	});
	
  	var points = [
      	new daum.maps.LatLng(37.515958745703564,127.099594336588)
    ];
  
  	var icon = new daum.maps.MarkerImage(
   		'http://www.samchuly.co.kr/image/marker.png',
   		new daum.maps.Size(36, 36),
   		new daum.maps.Point(16,34),
   		"poly",
   		"1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33"
  	);
  
  	for(var i = 0; i < points.length; i++)	{
   		new daum.maps.Marker({
    	position: points[i]
    	//image: icon
   		}).setMap(map);
  	}         
}

function rightNoticeCall() {
	var url = uri.serverUrl + uri.noticeListUrl;
	sendRequestJson(url,{currentPage : 1, unitPerPage : 4}, rightNoticeList, rightNoticeerror);
}

function rightNoticeList(res) {
	log(res);
	
	if(res.result.resultCode == "0000"){
		
		var $table = $("#rigtBoxNoticeList");
		$table.empty();
		
		$(res.list).each(function(){
			var tag = '<li>';
			tag += '<a class="textover" href="${contextPath}/guide/noticeDetail.do?no='+this.notiNo+'" title="'+this.notiSubject+'"> - '+this.notiSubject+'</a>';
    		tag += '</li>';
    		
    		$table.append(tag);
		});
			
	} else {
		rightNoticeerror();
	}
}

function rightNoticeerror() {
	alert(msg.noticeListFail);
}

</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <div id="mainFrame">
    	<div id="mainImage">
        	<ul>
            	<li><img src="${contextPath}/resources/images/main/main_slide_01.jpg" /></li>
                <li><img src="${contextPath}/resources/images/main/main_slide_02.jpg" /></li>
                <li><img src="${contextPath}/resources/images/main/main_slide_03.jpg" /></li>
                <li><img src="${contextPath}/resources/images/main/main_slide_04.jpg" /></li>
                <li><img src="${contextPath}/resources/images/main/main_slide_05.jpg" /></li> 
                <!-- <li><div id="map"></div></li>-->
            </ul>
        </div>
        <div id="mainButton">
        	<span id="mainButtonLeft"><img src="${contextPath}/resources/images/main/left_btn.png" /></span>
            <span id="mainButtonRight"><img src="${contextPath}/resources/images/main/right_btn.png" /></span>
        </div>
        <div id="mainIcon">
        	<ul>
            	<li id="mainIcon_1" data-ponum="0" class="mainIconBtn active"></li>
                <li id="mainIcon_2" data-ponum="1" class="mainIconBtn"></li>
                <li id="mainIcon_3" data-ponum="2" class="mainIconBtn"></li>
                <li id="mainIcon_4" data-ponum="3" class="mainIconBtn"></li>
                <li id="mainIcon_5" data-ponum="4" class="mainIconBtn"></li>
            </ul>
        </div>
	</div>
	<div class="mainRightBox">
		<div id="notice">
	    	<div class="subCont">
	            <div class="rightTitle">
	                <h2>공지사항</h2>
	                <span><a href="${contextPath}/guide/noticeList.do">more</a></span>
	            </div>
	            <ul id="rigtBoxNoticeList" class="cl">
	            </ul>
	        </div>
	    </div>
	    <div id="guide">
	    	<div class="subCont">
	            <div id="playBtn" class="cl">
	                <img src="${contextPath}/resources/images/main/right_btn.png" />
	            </div>
	        </div>
	        <div id="movieContainer">
	        </div>
	    </div>
	    <div id="reserve">
	    	<div class="subCont">
	            <div class="rightTitle">
	                <h2>예약 및 취소안내</h2>
	            </div>
	            <ul class="cl">
	            	<li><a href="${contextPath}/reserve/situation.do?id=c">창작지원실</a></li>
	                <li><a href="${contextPath}/reserve/situation.do?id=r1">녹음스튜디오</a></li>
	                <li><a href="${contextPath}/reserve/situation.do?id=t1">매체적합성테스트실</a></li>
	            </ul>
	            
	        </div>
	    </div>
	    <div id="contact">
	    	<div class="subCont">
	            <div class="rightTitle">
	                <h2>찾아오시는길</h2>
	            </div>
	            <div class="cl">
	                <a href="${contextPath}/guide/location.do"><img src="${contextPath}/resources/images/common/right_menu_map.png" /></a>
	         </div>
	            <div>
	            	<select onchange="goNewSite(this)">
	                	<option value='none'>관련사이트</option>
	                	<option value='http://www.msip.go.kr'>미래창조과학부</option>
	                	<option value='https://www.kobaco.co.kr/'>한국방송광고진흥공사</option>
	                	<option value='http://edu.kobaco.co.kr/'>kobaco광고교육원</option>
	                	<option value='http://adlib.kobaco.co.kr/'>kobaco광고도서관</option>
	                	<option value='http://hrd.kobaco.co.kr '>kobaco연수원</option>
	                	<option value='http://admuseum.kobaco.co.kr '>광고박물관</option>
	                </select>
	            </div>
	        </div>
	    </div>	    
	</div>
</div>
</body>
</html>