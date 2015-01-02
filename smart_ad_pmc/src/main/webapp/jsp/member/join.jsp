<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
</head>
<script type="text/javascript">
function checkForm() {
	var tel = $("#telSelect").val() + "-" + $("#tel1").val() + "-" + $("#tel2").val();
	var mobile = $("#mobileSelect").val() + "-" + $("#mobile1").val() + "-" + $("#mobile2").val();
	var email = $("#emailId1").val() + "@" + $("#emailId2").val();
		
	if($("#userId").val().trim() == "") {
		alert(msg.mostId);
		return false;
	} else if($("#idCheckYn").val() == "N") {
		alert(msg.checkId );
		return false;
	} else if($("#userPassword").val().trim() == "") {
		alert(msg.mostPw);
		return false;
	} else if($("#userPassword").val().trim().length < 8) { 
		alert(msg.pwLength);
		return false;
	} else if($("#userPassword").val().trim() != $("#userPassword2").val().trim()) {
		alert(msg.checkPw );
		return false;
	} else if($("#userName").val().trim() == "") {
		alert(msg.mostName );
		return false;
	} else if(email.trim() == "") {
		alert(msg.mostEmail );
		return false;
	} else if(!email.isEmail()) { 
		alert(msg.checkEmail );
		return false;
	} else if($("#enterpriseName").val().trim() == ""){
		alert(msg.mostEnterprise );
		return false;
 	} 
	
	if($("#tel1").val() != "" || $("#tel2").val() != "") {
		if(!$("#tel1").val().isNum() || !$("#tel2").val().isNum()){
			alert(msg.checkNum );
			return false;
		}
	} 
	
	if(!$("#mobile1").val().isNum() || !$("#mobile2").val().isNum()) {
		alert(msg.checkNum);
		return false;
	} else if(mobile.length < 12){
		alert(msg.mostMobile);
		return false;
	} 
	
	$("#userId").val($("#userId").val().trim());
	$("#userName").val($("#userName").val().trim());
		
	var param = $("#memberJoinForm").serializeObject();
	param['consentTscsYn'] = "Y";
	param['consentReceiveInfoYn'] = "Y";
	param['userPhone'] = tel;
	param['userCellPhone'] = mobile;
	param['userEmail'] = email;
	
	var url = uri.serverUrl + uri.userAddUtl;
	sendRequestJson(url, param, addSuccess, error);
	
}

function addSuccess(res){
	if(res.result.resultCode == "0000") {
		alert(msg.successAdd);
		goPage('${contextPath}/member/list.do');
	} else {
		error();
	}
}

function error(){
	alert(msg.failJoin);
}

function emailChange(obj) {
	if($(obj).val() == "") {
		$("#emailId2").attr("disabled", false);
		$("#emailId2").val("");
	} else {
		$("#emailId2").attr("disabled", true);
		$("#emailId2").val($(obj).val());
	}
}
</script>
<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>회원관리 > 회원등록</h1>
		</div>
		<div class="mainTable memberJoinForm">
			<div class="member tableType">
				<form id="memberJoinForm">
				<dl>
					<dt>이름</dt>
					<dd class="input"><input id="userName" name="userName" type="text" class="input_red input_type1" /></dd>
				</dl>
				<dl>
					<dt>아이디</dt>
					<dd class="input"><input id="userId" name="userId" type="text" class="input_red input_type1" /></dd>
				</dl>
				<dl>
					<dt>비밀번호</dt>
					<dd class="input"><input id="userPassword" name="userPassword" type="password" class="input_red input_type1" /></dd>
				</dl>
				<dl>
					<dt>비밀번호 확인</dt>
					<dd class="input"><input id="userPassword2" type="password" class="input_red input_type1" /></dd>
				</dl>
				<dl>
					<dt>전화번호</dt>
					<dd class="input">
						<select id="telSelect" class="input_red input_type3">
                        	<option value="02">02</option>
                        	<option value="031">031</option>
                        	<option value="032">032</option>
                        	<option value="033">033</option>
                        	<option value="041">041</option>
                        	<option value="042">042</option>
                        	<option value="043">043</option>
							<option value="043">044</option>
                        	<option value="051">051</option>
                        	<option value="052">052</option>
                        	<option value="053">053</option>
                        	<option value="054">054</option>
                        	<option value="055">055</option>
                        	<option value="061">061</option>
                        	<option value="062">062</option>
                        	<option value="063">063</option>
                        	<option value="064">064</option>
							<option value="070">070</option>
                        </select> -
						<input id="tel1" type="text" class="input_red input_type2" /> - 
						<input id="tel2" type="text" class="input_red input_type2" />
					</dd>
				</dl>
				<dl>
					<dt>휴대전화</dt>
					<dd class="input">
						<select id="mobileSelect" class="input_red input_type3">
                        	<option value="010">010</option>
                        	<option value="011">011</option>
                        	<option value="017">017</option>
                        </select> -
						<input id="mobile1" type="text" class="input_red input_type2" /> - 
						<input id="mobile2" type="text" class="input_red input_type2" />
					</dd>
				</dl>
				<dl>
					<dt>이메일</dt>
					<dd class="input">
						<input id="emailId1" type="text" class="input_red input_type3" /> @ 
						<input id="emailId2" type="text" class="input_red input_type3" />
						<select id="emailAddress" class="input_red input_type3" onchange="emailChange(this)">
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="nate.com">nate.com</option>
							<option value="daum.net">daum.net</option>
							<option value="hotmail.com">hotmail.com</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>소속</dt>
					<dd class="input"><input id="enterpriseName" name="enterpriseName" type="text" class="input_red  input_type1" /></dd>
				</dl>
				<dl>
					<dt>직장주소</dt>
					<dd class="input"><input id="enterpriseAddress" name="enterpriseAddress" type="text" class="input_red input_type1" /></dd>
				</dl>
				</form>
			</div>
			
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/member/list.do')"/>
				</div>
				<div class="fr">
					<ul>
						<li><input type="button" value="회원등록" class="btn_normal_red" onclick="checkForm()"/></li>
						<li><input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/member/list.do')" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 