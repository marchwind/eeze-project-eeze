<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">

function checkSubmit() {
	var mobile = $("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val();
	var email = $("#email1").val() + "@" + $("#email2").val();
	
	if($("#managerId").val().trim() == "") {
		alert(msg.mostId);
		return false;
	} else if($("#managerPassword").val().trim() == "") {
		alert(msg.mostPw);
		return false;
	} else if($("#managerPassword").val().trim().length < 8) { 
		alert(msg.pwLength);
		return false;
	} else if($("#managerPassword").val().trim() != $("#managerPassword2").val().trim()) {
		alert(msg.checkPw );
		return false;
	} else if($("#managerName").val().trim() == "") {
		alert(msg.mostName );
		return false;
	} else if(email.trim() == "") {
		alert(msg.mostEmail );
		return false;
	} else if(!email.isEmail()) { 
		alert(msg.checkEmail );
		return false;
	} else if($("#department").val().trim() == ""){
		alert(msg.mostEnterprise );
		return false;
 	} 
	
 	if(!$("#tel1").val().isNum() || !$("#tel2").val().isNum() || !$("#tel3").val().isNum()) {
 		alert(msg.checkNum);
 		return false;
 	} else if(mobile.length < 12){
 		alert(msg.mostMobile);
 		return false;
 	} 
	
	$("#managerId").val($("#managerId").val().trim());
	$("#managerName").val($("#managerName").val().trim());
		
	var param = $("#managerAddForm").serializeObject();
	param['managerPhone'] = mobile;
	param['managerEmail'] = email;
	
	var url = uri.serverUrl + uri.managerAddUrl;
	sendRequestJson(url, param, addSuccess, error);
}

function addSuccess(res) {
	if(res.result.resultCode == "0000") {
		alert(msg.successAdd);
		goPage('${contextPath}/admin/list.do');
	} else {
		error();
	}
}

function emailChange(obj){
	if($(obj).val() != ""){
		$("#email2").attr("disabled", true);
		$("#email2").val($(obj).val());
	} else {
		$("#email2").attr("disabled", false);
		$("#email2").val("");
	}
}

function error() {
	alert(msg.managerFail);
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>계정관리 > 계정등록</h1>
		</div>
		<div class="mainTable memberJoinForm">
			<form id="managerAddForm">
			<table class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td class="tl">이름</td>
						<td class="tl input"><input type="text" id="managerName" name="managerName" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">아이디</td>
						<td class="tl input"><input type="text" id="managerId" name="managerId" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">비밀번호</td>
						<td class="tl input"><input type="password" id="managerPassword" name="managerPassword" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">비밀번호 확인</td>
						<td class="tl input"><input type="password" id="managerPassword2" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">전화번호</td>
						<td class="tl input">
							<input type="text" id="tel1" value="" class="input_red input_type2" /> - 
							<input type="text" id="tel2" value="" class="input_red input_type2" /> - 
							<input type="text" id="tel3" value="" class="input_red input_type2" />
						</td>
					</tr>
					<tr>
						<td class="tl">소속</td>
						<td class="tl input"><input type="text" id="department" name="department" value="" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">이메일</td>
						<td class="tl input">
							<input type="text" id="email1" value="" class="input_red input_type3" /> @
							<input type="text" id="email2" value="" class="input_red input_type3" /> 
							<select class="input_red input_type3" id="emailSelect" onchange="emailChange(this)">
								<option value="">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="nate.com">nate.com</option>
								<option value="daum.net">daum.net</option>
								<option value="hotmail.com">hotmail.com</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/admin/list.do')"/>
				</div>
				<div class="fr">
					<ul>
						<li><input type="button" value="등록완료" class="btn_normal_red" onclick="checkSubmit()"/></li>
						<li><input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/admin/list.do')" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 