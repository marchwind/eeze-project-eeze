<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">

</script>
</head>
<body class="login">

<div id="loginWrap">
	<div id="loginHeader" class="login_small_head">
		<span class="fl loginSubTitle">비밀번호 수정</span>
		<span class="fr"><img src="${contextPath}/resources/images/login_logo_small.png" /></span>
	</div>
	<div id="loginCont" class="login_small">
		<form id="changePwForm">
			<dl>
				<dt>현재 비밀번호</dt>
				<dd><input type="text" id="currentPw" class="input_2"/></dd>
			</dl>
			<dl>
				<dt>새로운 비밀번호</dt>
				<dd><input type="text" id="newPw" class="input_2"/></dd>
			</dl>
			<dl>
				<dt>새로운 비밀번호 확인</dt>
				<dd><input type="text" id="newPw2" class="input_2"/></dd>
			</dl>
			
		</form>
		<div class="loginBtnContainer">
			<input type="button" value="수정완료" class="btn_big_red btn_big_mr" onclick="goPage('${contextPath}/login.do')" />
			<input type="button" value="취소" class="btn_big_gray" onclick="goPage('${contextPath}/login.do')"/>
		</div>
	</div>
</div>

</body>
</html>