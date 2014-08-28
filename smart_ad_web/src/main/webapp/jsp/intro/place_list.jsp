<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
function goReserve(obj){
	var $obj = $(obj);
	goPage('${contextPath}/reserve/situation.do?id='+$obj.data("fac"));
}

function reservePopup(obj){
	var $obj = $(obj);
	goPopup('${contextPath}/reserve/popupSituation.do?id='+$obj.data("fac"));
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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_intro.png" /></span>
                    <span class="subPageTitleRight">/ 창작공간AD 안내</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 창작공간AD 소개</span>
                    <span class="navi_2dep">- 창작공간AD 안내</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div class="dot_box">
                	<div class="dot_box_top"></div>
                	<div class="dot_box_cont">
                		<p>창작공간AD는 창작지원실, 녹음스튜디오, 매체적합성테스트실, 회의실 등의 공간이 마련되어 양질의 스마트광고 제작이 가능한 최적의 환경이 제공됩니다. </p>
                	</div>
                	<div class="dot_box_bottom"></div>
                </div>
                <div id="place_desc">
               		<div id="place_img">
               			<img src="${contextPath}/resources/images/common/intro_struct.png" />
               		</div>
               		<div id="place_list">
               			<ul>
               				<li>
               					<h4>창작지원실1</h4>
               					<p>창작지원기능 제공을 위한 공간으로 스마트 광고 개발, 편집, 광고효과 삽입</p>
               					<ul>
               						<li><a href="#none" data-fac="c1" onclick="goReserve(this)">예약하기</a></li>
               						<li><a href="#none" data-fac="c1" onclick="reservePopup(this)">예약현황</a></li>
               					</ul>
               				</li>
               				<li>
               					<h4>창작지원실2</h4>
               					<p>디지털화된 편집물에 대하여 컷편집, 효과처리 등 디지털 편집 작업</p>
               					<ul>
               						<li><a href="#none" data-fac="c2" onclick="goReserve(this)">예약하기</a></li>
               						<li><a href="#none" data-fac="c2" onclick="reservePopup(this)">예약현황</a></li>
               					</ul>
               				</li>
               			</ul>
               			<ul>
               				<li>
               					<h4>창작지원실3</h4>
               					<p>렌더링을 통한 스마트광고 시각화 작업</p>
               					<ul>
               						<li><a href="#none" data-fac="c3" onclick="goReserve(this)">예약하기</a></li>
               						<li><a href="#none" data-fac="c3" onclick="reservePopup(this)">예약현황</a></li>
               					</ul>
               				</li>
               				<li>
               					<h4>창작지원실4</h4>
               					<p>매체에 맞게 포맷 변환, 아날로그/디지털 변환 작업</p>
               					<ul>
               						<li><a href="#none" data-fac="c4" onclick="goReserve(this)">예약하기</a></li>
               						<li><a href="#none" data-fac="c4" onclick="reservePopup(this)">예약현황</a></li>
               					</ul>
               				</li>               				
               			</ul>
               			<ul>
               				<li>
               					<h4>녹음스튜디오</h4>
               					<p>녹음스튜디오에서 스마트 광고용 더빙, 성우녹음 오디오작업을 통해 광고제작 지원 기능 제공</p>
               					<ul>
               						<li><a href="#none" data-fac="r1" onclick="goReserve(this)">예약하기</a></li>
               						<li><a href="#none" data-fac="r1" onclick="reservePopup(this)">예약현황</a></li>
               					</ul>
               				</li>
               				<li>
               					<h4>매체적합성테스트실</h4>
               					<p>모바일 광고 유형 및 스마트 입체광고 제작.편집,테스트 환경 구축 및 상용화 지원 기능 제공</p>
               					<ul>
               						<li><a href="#none" data-fac="t1" onclick="goReserve(this)">예약하기</a></li>
               						<li><a href="#none" data-fac="t1" onclick="reservePopup(this)">예약현황</a></li>
               					</ul>
               				</li>
               			</ul>
               			<ul>
               				<li>
               					<h4>서버실</h4>
               					<p>제작장비 및 서버공간실</p>
               				</li>
               				<li>
               					<h4>지원실</h4>
               					<p>제작지원을 위한 지원 공간</p>
               				</li>
               			</ul>
               			<ul>
               				<li>
               					<h4>회의실</h4>
               					<p>제작 회의 및 미팅공간</p>
               				</li>
               			</ul>
               		</div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>