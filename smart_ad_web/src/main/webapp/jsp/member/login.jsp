<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">

function keyEvent(e) {
	e = e || window.event;
	log(e.keyCode);
	if(e.keyCode == 13) {
		login();
	}
}

function login() {
	var url = uri.serverUrl + uri.loginUrl;
	sendRequest("loginForm", url, loginSuccess, error);
}

function loginSuccess(res) {
	if(res.result.resultCode == "0000") {
		goPage('${contextPath}/main.do');
		/*$.post( contextPath + "/member/loginSession.do",{userNo : res.info.userNo, userName : res.info.userName, userEmail : res.info.userEmail}, function( data ) {
			log(data);
			if(data.result == "0000") {
				goPage('${contextPath}/main.do');
			}
		});*/
	} else {
		error();
	}
}

function error(res){
	alert(msg.loginError);
}
</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <div id="mainFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_login.png" /></span>
                    <span class="subPageTitleRight">/ 로그인</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>

                    <span class="navi_2dep">- 로그인</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="loginBox">
                    <div id="loginBoxTop"></div>
                    <div id="loginBoxCont">
                        <div id="loginBoxContLeft">
                            <div id="loginInputBox">
                            	<form id="loginForm">
	                                <span id="loginInput">
	                                	<input type="text" name="userId" placeholder="아이디" />
	    	                            <input type="password" name="userPassword" placeholder="비밀번호" onkeyup="keyEvent();"/>
	                                </span>
	                                <span id="loginBtn">
	                                    <input type="button" class="btn_1" value="로그인" onclick="login();"/>
	                                </span>
                                </form>
                            </div>
                            <div id="loginCommentBox" class="cl">
                                <div>
                                    <p>
                                        <b>아직 창작공간AD 회원이 아니세요?</b>
                                        <br/>회원이 되어 다양한 혜택을 누리세요.
                                    </p>
                                    <input type="button" class="btn_2" value="회원가입"  onclick="goPage('${contextPath}/member/joinStep1.do')"/>
                                </div>
                                <div>
                                    <p><b>회원아이디와 비밀번호</b>를 잊으셨나요?</p>
                                    <span>
	                                    <input type="button" class="btn_2" value="아이디 찾기"  onclick="goPage('${contextPath}/member/idSearch.do')"/>
	                                    <input type="button" class="btn_2" value="비밀번호 찾기"  onclick="goPage('${contextPath}/member/pwSearch.do')"/>
	                                </span>
                                </div>
                            </div>
                        </div>
                        <div id="loginBoxContRight">
                            <img src="${contextPath}/resources/images/common/login_ad.png" />
                        </div>
                    </div>
                    <div id="loginBoxBottom"></div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>