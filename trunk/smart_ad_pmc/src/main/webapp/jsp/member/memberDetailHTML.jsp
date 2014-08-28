<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	var url = uri.serverUrl + uri.userGetUrl;
	sendRequestJson(url, {userNo:userInfo.userNo}, detailContSuceess, error);
});

function detailContSuceess(res){
	
	if(res.result.resultCode == "0000"){
		
		userInfo.userName = res.info.userName;
		userInfo.userStatus = res.info.userStatus;
		userInfo.userId = res.info.userId;
		userInfo.userPhone = res.info.userPhone;
		userInfo.userCellPhone = res.info.userCellPhone;
		userInfo.userEmail = res.info.userEmail;
		userInfo.enterpriseName = res.info.enterpriseName;
		userInfo.enterpriseAddress = res.info.enterpriseAddress;
		
		var state = "일반회원";
		switch(res.info.userStatus){
		case "U01100" : state = "일반회원"; break;
		case "U01110" : state = "탈퇴회원"; break;
		case "U01120" : state = "차단회원"; break;
		}
		
		$("#userName").text(userInfo.userName);
		$("#userStatus").text(state);
		$("#userId").text(userInfo.userId);
		$("#userPhone").text(userInfo.userPhone);
		$("#userCellPhone").text(userInfo.userCellPhone);
		$("#userEmail").text(userInfo.userEmail);
		$("#enterpriseName").text(userInfo.enterpriseName);
		$("#enterpriseAddress").text(userInfo.enterpriseAddress);
		
	} else {
		error();
	}

}
</script>
<dl>
	<dt>이름</dt>
	<dd id="userName"></dd>
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
	<dd id="userPhone"></dd>
</dl>
<dl>
	<dt>휴대전화</dt>
	<dd id="userCellPhone"></dd>
</dl>
<dl>
	<dt>이메일</dt>
	<dd id="userEmail"></dd>
</dl>
<dl>
	<dt>소속</dt>
	<dd id="enterpriseName"></dd>
</dl>
<dl>
	<dt>직장주소</dt>
	<dd id="enterpriseAddress"></dd>
</dl>