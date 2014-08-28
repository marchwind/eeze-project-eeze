<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#userName").val(userInfo.userName);
	
	var state = "일반회원";
	switch(userInfo.userStatus){
	case "U01100" : state = "일반회원"; break;
	case "U01110" : state = "탈퇴회원"; break;
	case "U01120" : state = "차단회원"; break;
	}
	
	$("#userStatus").text(state);
	$("#userId").text(userInfo.userId);
	if(userInfo.userPhone != "" || userInfo.userPhone != null){
		var pArr = userInfo.userPhone.split("-");
		$("#telSelect").val(pArr[0]);
		$("#tel1").val(pArr[1]);
		$("#tel2").val(pArr[2]);
	}
	if(userInfo.userCellPhone != "" || userInfo.userCellPhone != null){
		var cArr = userInfo.userCellPhone.split("-");
		$("#mobileSelect").val(cArr[0]);
		$("#mobile1").val(cArr[1]);
		$("#mobile2").val(cArr[2]);
	}
	if(userInfo.userEmail != "" || userInfo.userEmail != null) {
		var eArr = userInfo.userEmail.split("@");
		$("#emailId1").val(eArr[0]);
		$("#emailId2").val(eArr[1]);
		$("#emailAddress").val(eArr[1]);
	}
	
	$("#enterpriseName").val(userInfo.enterpriseName);
	$("#enterpriseAddress").val(userInfo.enterpriseAddress);
})

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
<dl>
	<dt>이름</dt>
	<dd class="input"><input type="text" id="userName" name="userName" value="" class="input_red input_type1" /></dd>
</dl>
<dl>
	<dt>상태</dt>
	<dd id="userStatus"></dd>
</dl>
<dl>
	<dt>아이디</dt>
	<dd id="userId"></dd>
</dl>
<dl>
	<dt>전화번호</dt>
	<dd class="input">
		<select id="telSelect" class="input_red">
        	<option value="02">02</option>
        	<option value="031">031</option>
        	<option value="032">032</option>
        	<option value="033">033</option>
        	<option value="041">041</option>
        	<option value="042">042</option>
        	<option value="043">043</option>
        	<option value="051">051</option>
        	<option value="052">052</option>
        	<option value="053">053</option>
        	<option value="054">054</option>
        	<option value="055">055</option>
        	<option value="061">061</option>
        	<option value="062">062</option>
        	<option value="063">063</option>
        	<option value="064">064</option>
        </select> - 
		<input type="text" id="tel1" value="" class="input_red input_type2" /> - 
		<input type="text" id="tel2" value="" class="input_red input_type2" />
	</dd>
</dl>
<dl>
	<dt>휴대전화</dt>
	<dd class="input">
		<select id="mobileSelect" class="input_red">
        	<option value="010">010</option>
        	<option value="011">011</option>
        	<option value="017">017</option>
        </select> - 
		<input type="text" id="mobile1" value="" class="input_red input_type2" /> - 
		<input type="text" id="mobile2" value="" class="input_red input_type2" />
	</dd>
</dl>
<dl>
	<dt>이메일</dt>
	<dd class="input">
		<input type="text" id="emailId1" value="" class="input_red input_type3" /> @
		<input type="text" id="emailId2" value="" class="input_red input_type3" /> 
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
	<dd class="input"><input type="text" id="enterpriseName" name="enterpriseName" value="(주)리딩" class="input_red  input_type1" /></dd>
</dl>
<dl>
	<dt>직장주소</dt>
	<dd class="input"><input type="text" id="enterpriseAddress" name="enterpriseAddress" value="서울시 성동구 성수동" class="input_red input_type1" /></dd>
</dl>
