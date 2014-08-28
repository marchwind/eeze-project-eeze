<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">

function checkRequest() {
	
	var tel = $("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val();
	var email = $("#emailId1").val() + "@" + $("#emailId2").val();
	
	if($("#managerName").val().trim() == "") {
		alert(msg.mostName);
	} else if($("#managerId").val().trim() == "") {
		alert(msg.mostId);
	} else if($("#managerPassword").val().trim() == "") {
		alert(msg.mostPw);
	} else if($("#managerPassword").val().trim().length < 8) { 
		alert(msg.pwLength);
	} else if($("#managerPassword").val().trim() != $("#managerPassword2").val().trim()) {
		alert(msg.checkPw );
	} else if($("#tel1").val().trim() == "" || $("#tel2").val().trim() == "" || $("#tel3").val().trim() == "") {
		alert(msg.mostTel);
	} else if(!$("#tel1").val().isNum() || !$("#tel2").val().isNum() || !$("#tel2").val().isNum()){
		alert(msg.checkNum );
	} else if(tel.length < 11){
		alert(msg.mostTel);
	} else if($("#department").val().trim() == ""){
		alert(msg.mostEnterprise );
 	} else if($("#emailId1").val().trim() == "" || $("#emailId2").val().trim() == "") {
		alert(msg.mostEmail );
	} else if(!email.isEmail()) { 
		alert(msg.checkEmail );
	} else {
		$("#managerId").val($("#managerId").val().trim());
		$("#managerName").val($("#managerName").val().trim());
		
		var param = $("#joinForm").serializeObject();
		param['managerPhone'] = tel;
		param['managerEmail'] = email;
		
		var url = uri.serverUrl + uri.joinUrl;
		sendRequestJson(url, param, joinSuccess, error);
	}
	
}

function joinSuccess(res) {
	if(res.result.resultCode = "0000") {
		alert(msg.successRequest);
		goPage("${contextPath}/login.do");
	} else {
		error();
	}
}

function emailChange(obj){
	if($(obj).val() != ""){
		$("#emailId2").attr("disabled", true);
		$("#emailId2").val($(obj).val());
	} else {
		$("#emailId2").attr("disabled", false);
		$("#emailId2").val("");
	}
}

function error() {
	alert(msg.failRequest);
}

</script>
</head>
<body class="login">

<div id="loginWrap">
	<div id="loginHeader" class="login_small_head">
		<span class="fl loginSubTitle">계정등록요청</span>
		<span class="fr"><img src="${contextPath}/resources/images/login_logo_small.png" /></span>
	</div>
	<div id="loginCont" class="login_small">
		<form id="joinForm">
			<dl>
				<dt>이름</dt>
				<dd><input type="text" class="input_1" name="managerName" id="managerName"/></dd>
			</dl>
			<dl>
				<dt>아이디</dt>
				<dd><input type="text" class="input_2" name="managerId" id="managerId"/></dd>
			</dl>
			<dl>
				<dt>비밀번호</dt>
				<dd><input type="password" class="input_2" name="managerPassword" id="managerPassword"/></dd>
			</dl>
			<dl>
				<dt>비밀번호 확인</dt>
				<dd><input type="password" class="input_2" id="managerPassword2"/></dd>
			</dl>
			<dl>
				<dt>연락처</dt>
				<dd>
					<input type="text" class="input_3" id="tel1"/> - <input type="text" class="input_3"  id="tel2" /> - <input type="text" class="input_3"  id="tel3"/>
				</dd>
			</dl>
			<dl>
				<dt>회사명</dt>
				<dd><input type="text" class="input_2" name="department" id="department"/></dd>
			</dl>
			<dl>
				<dt>E-mail</dt>
				<dd>
					<input type="text" class="input_1" id="emailId1"/> @
					<input type="text" class="input_1" id="emailId2"/>
					<select class="input_1" id="emailAddress" onchange="emailChange(this)">
						<option value="">직접입력</option>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="nate.com">nate.com</option>
						<option value="daum.net">daum.net</option>
						<option value="hotmail.com">hotmail.com</option>
					</select>
				</dd>
			</dl>
		</form>
		<div class="loginBtnContainer">
			<input type="button" value="로그인" class="btn_big_red btn_big_mr" onclick="goPage('${contextPath}/login.do')" />
			<input type="button" value="계정등록요청" class="btn_big_gray" onclick="checkRequest()"/>
		</div>
	</div>
</div>

</body>
</html>