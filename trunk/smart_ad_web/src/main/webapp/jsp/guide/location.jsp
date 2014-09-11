<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
function printLocation() {
    $("#leftNavi").hide();
    $("#subRightSlide").hide();	
    $("#mainFrame").addClass("print");
    
    window.print();

    $("#mainFrame").removeClass("print");
    $("#leftNavi").show();
    $("#subRightSlide").show();
}
</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <div id="mainFrame" class="subFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_map.png" /></span>
                    <span class="subPageTitleRight">/ 찾아오시는길</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- 찾아오시는길</span>
                </span>
            </div>
            
            <div id="subPageCont">
            	<div id="listContainer">
	               <div id="locationCont" class="guideContainer">
	               		<div id="location_img">
	               			<div class="roundBox">
	               				<img src="${contextPath}/resources/images/common/map.png" />
	               			</div>
	               			<input type="button" value="인쇄출력 ▶" onclick="printLocation()"/>
	               		</div>
	               		<div id="location_txt">
	               			<ul>
	               				<li><img src="${contextPath}/resources/images/common/bullet_1.png" /><b>주소</b></li>
	               				<li><img src="${contextPath}/resources/images/common/bullet_2.png" />서울시 송파구 올림픽로35길 137 한국광고문화회관 5층</li>
	               			</ul>
	               			<ul>
	               				<li><img src="${contextPath}/resources/images/common/bullet_1.png" /><b>교통편</b></li>
	               				<li><img src="${contextPath}/resources/images/common/bullet_2.png" />지하철로 오실 경우 : <strong>지하철 2,8호선 잠실역 하차 (2호선 7번 출구)</strong> 도보 3분 거리</li>
	               				<li><img src="${contextPath}/resources/images/common/bullet_2.png" />버스로 오실 경우<br/>
								&nbsp;&nbsp;&nbsp;1) 간선버스(파란색) - <span>301번, 302번, 303번, 361번, 362번, 730번</span><br/>
								&nbsp;&nbsp;&nbsp;2) 지선버스(초록색) - <span>2225번, 2311번, 24121번, 3215번, 3216번, 3217번, 3312번, 3313번, 3314번, 3315번, 3411번, <span><br/>
								<span class="listTab">3412번,3413번, 3414번, 3415번</span><br/>
								&nbsp;&nbsp;&nbsp;3) 광역버스(빨간색) - <span>9202번, 9203번, 9403번</span><br/>
								&nbsp;&nbsp;&nbsp;4) 공항버스 - <span>600번, 606번 </span>
									
								</li>
	               			</ul>
	               			<ul>
	               				<li><img src="${contextPath}/resources/images/common/bullet_1.png" /><b>문의</b></li>
								<li><img src="${contextPath}/resources/images/common/bullet_2.png" />전화 : 02-2144-0320&nbsp;&nbsp;&nbsp;&nbsp; 팩스 :  02-2144-0323</li>
								
	               			</ul>
	               		</div>
	               </div>
	            </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>