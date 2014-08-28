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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_networking.png" /></span>
                    <span class="subPageTitleRight">/ Smart AD Lab - Networking</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- Smart AD Lab - Networking</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div class="dot_box">
                	<div class="dot_box_top"></div>
                	<div class="dot_box_cont">
                		<p>예비창업자, 중소개발사, 광고기획사 들과 창작공간AD에서 오디오·편집·기획전문가들과의 네트워크를 제공하여 아이디어만으로 광고를 제작할 수 있는 지식 공유의 장을 제공합니다.</p>
                	</div>
                	<div class="dot_box_bottom"></div>
                </div>
                <div id="introCont">
                	<img src="${contextPath}/resources/images/common/networking.png" />
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>