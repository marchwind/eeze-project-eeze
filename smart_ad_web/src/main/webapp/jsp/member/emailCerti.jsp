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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_join.png" /></span>
                    <span class="subPageTitleRight">/ 회원가입</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 회원가입</span>
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
                        <dl>
                        	<dt>STEP3</dt>
                            <dd>가입인증</dd>
                        </dl>
                        <dl class="lastFlow active">
                        	<dt>STEP4</dt>
                            <dd>회원가입완료</dd>
                        </dl>
                    </div>
                    <div id="joinCont">
                    	<h2>가입완료</h2>
                        <div class="memberResult">
                        	<span class="memberComplete">
                                <img src="${contextPath}/resources/images/common/kobaco_logo.png" />
                                <p>스마트광고창작공간AD 회원가입을 환영합니다!</p>
                                <span class="grayBox">
                                	<p>
                                	메일 계정 <span class="userEmail">parkjongsok@naver.com</span><br/>
                                	(가입일: 2014.06.05)
                                	</p>
                                </span>
                            </span>
                        </div>
                    </div>                    
                    <div class="btnContainer">
                    	<input type="button" class="btn_3" value="확인" />
                        
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>