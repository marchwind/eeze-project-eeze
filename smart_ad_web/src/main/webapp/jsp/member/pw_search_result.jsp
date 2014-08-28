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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_mypage.png" /></span>
                    <span class="subPageTitleRight">/ 비밀번호찾기</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 비밀번호찾기</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="memberCont">
                    	<h2>비밀번호 찾기</h2>
                        <div class="memberResult">
                        	<span class="memberResultComment">
                                <p>${userName}님의 이메일 주소로 새로운 비밀번호를 전송했습니다.</p>
                                <p class="blueColor"><img src="${contextPath}/resources/images/common/alert_icon.png" class="mr10"/> 로그인 후 임시비밀번호를 변경하시기 바랍니다. </p>
                            </span>
                        </div>
                        <p class="tc mt30">* 위에서 제공하는 방법으로 재발급이 어려우시거나 기타 문의사항은 <b>고객센터</b>로 문의해 주세요. </p>
                    </div>                    
                    
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>