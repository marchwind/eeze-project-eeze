<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
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
	} else {
		error();
	}
}

function error(res){
	alert(msg.loginError);
}
</script>
</head>

<body class="login">

<div id="loginWrap">
	
	<div id="loginHeader" class="login_big_head">
		<img src='${contextPath}/resources/images/login_logo.png' />
	</div>
	<div id="loginCont">
		<form id="loginForm">
			<dl>
				<dt>아이디</dt>
				<dd><input type="text" name="managerId" id="managerId"/>				
			</dl>
			<dl>
				<dt>비밀번호</dt>
				<dd><input type="password" name="managerPassword" id="managerPassword" onkeyup="keyEvent();"/>				
			</dl>
		</form>
		<div class="loginBtnContainer">
			<input type="button" value="로그인" class="btn_big_red btn_big_mr" onclick="login()" />
			<input type="button" value="계정등록요청" class="btn_big_gray" onclick="goPage('${contextPath}/joinRequst.do')"/>
		</div>
	</div>

</div>

</body>
</html> 