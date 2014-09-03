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
                	<span class="subPageTitleLeft"><img src="../resources/images/common/title_join.png" /></span>
                    <span class="subPageTitleRight">/ 회원가입</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="../resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_2dep">- 회원가입</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="joinFlow">
                    	<dl>
                        	<dt>STEP1</dt>
                            <dd>약관동의</dd>
                        </dl>
                        <dl>
                        	<dt>STEP2</dt>
                            <dd>정보입력</dd>
                        </dl>
                        <dl class="active">
                        	<dt>STEP3</dt>
                            <dd>가입인증</dd>
                        </dl>
                        <dl class="lastFlow">
                        	<dt>STEP4</dt>
                            <dd>회원가입완료</dd>
                        </dl>
                    </div>
                    <div id="joinCont">
                    	<h2>가입인증</h2>
                        <div class="memberResult">
                        	<span class="memberResultComment">
                        		<img src="${contextPath}/resources/images/common/kobaco_logo.png" />
                                <!-- <p>스마트광고창작공간AD 회원가입을 환영합니다!</p> -->
                                <p>창작공간AD회원계정으로 사용하실 이메일로 인증 메일을 발송하였습니다!</p>
                                <p class="userEmail">${email}</p>
                                <p>위 메일주소로 발송된 메일의 인증링크를 클릭하여 창작지원시설 회원계정 만들기를 완료해주세요.</p>
                            </span>
                        </div>
                    </div>                    
                    <div class="btnContainer">
                    	<!-- <input type="button" class="btn_3" value="다음"  onclick="goPage('${contextPath}/member/emailCerti.do')"/> -->
                        
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>