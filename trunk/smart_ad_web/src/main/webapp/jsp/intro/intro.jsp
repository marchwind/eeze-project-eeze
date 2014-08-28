<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
 
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
                    <span class="subPageTitleRight">/ 창작공간AD 소개</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 창작공간AD소개</span>
                    <span class="navi_2dep">- 창작공간AD소개</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div class="dot_box">
                	<div class="dot_box_top"></div>
                	<div class="dot_box_cont">
                		<p>국내 스마트광고 산업의 국내외 경쟁력 강화를 위해 제작·개발 및 상용화 테스트를 지원하여 개발비용 절감하며, 양방향성이 결합된 스마트광고 산업 활성화 및개발 장비의 이용을 통해 스마트 광고 산업 제작에 최적의 제작지원 시스템과 서비스를 제공하고자 합니다. </p>
                	</div>
                	<div class="dot_box_bottom"></div>
                </div>
                <div id="introCont">
                	<img src="${contextPath}/resources/images/common/intro_img_1.png" />
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>