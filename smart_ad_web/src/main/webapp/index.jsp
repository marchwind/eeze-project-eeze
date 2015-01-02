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
<script language="JavaScript">

 function setCookie( name, value, expiredays ) {

var todayDate = new Date();

  todayDate.setDate( todayDate.getDate() + expiredays );

     document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"

    }

 

 function closeWin(layerNum) {

  if ( document.getElementById("layer_chkbox"+layerNum).checked ){

  setCookie( "maindiv"+layerNum, "done" , 1 );

    }

    document.all['divpop'+layerNum].style.visibility = "hidden";

   }

</script> 

 


  <!--레이어팝업 시작-->   

 <div id="divpop1" style="position: absolute; left: 30%; top: 36%;margin-left:125px;;margin-top:-330px; border: 5px solid rgb(f, f, f); z-index: 201; visibility: visible;">

   <table cellpadding="0" cellspacing="0">

   <tbody><tr>

      <td>

   <a href=""><img src="http://smartad.or.kr/resources/images/main/naward2014.jpg"></a>

      </td>

   </tr>

   <tr>

    <td align="right" style="background:#000;">

   <input type="checkbox" name="layer_chkbox1" id="layer_chkbox1" value="checkbox"><span style=" font-size:12px;color:#fff;">오늘 하루 이 창을 열지 않음</span><a href="#" onclick="closeWin(1);return false" ><span style="font-size:12px;color:#fff;">[닫기]</span></a>

    </td>

   </tr>

   </tbody></table>

  </div>
 

  <script language="JavaScript">  

   cookiedata = document.cookie;

   var layerCount =1;  // 레이어팝업갯수는 꼭 조정

   for(var i=1 ; i<=layerCount ; i++) {

     if ( cookiedata.indexOf("maindiv"+i+"=done") < 0 ) {

     document.all['divpop'+i].style.visibility = "visible";

     } else {

     document.all['divpop'+i].style.visibility = "hidden";

     }

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
	                	<option value='http://hrd.kobaco.co.kr/'>kobaco연수원</option>
	                	<option value='http://admuseum.kobaco.co.kr/'>광고박물관</option>
					    <option value="http://onlinead.or.kr/">한국온라인광고협회</option>
						<option value="http://www.smicenter.or.kr/">스마트미디어이노베이션센터</option>
                        <option value="http://www.smpa.or.kr/">스마트미디어산업진흥협회</option>
                        <option value="http://www.kaaa.co.kr/">한국광고산업협회</option>
                        <option value="http://www.koreacf.or.kr/">한국광고영상제작사협회</option>
                        <option value="http://www.koeba.com/">한국전광방송협회</option>
                        <option value="http://www.dea.or.kr/">한국디지털기업협회</option>
                        <option value="http://www.kapa.or.kr/">한국광고사진가협회</option>
                        <option value="http://www.kosmedia.or.kr/">한국스마트미디어협회</option>
                       </select>
	            </div>
	        </div>
	    </div>	    
	</div>
</div>
</body>
</html>
