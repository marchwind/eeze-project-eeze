<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var no = "${no}";

$(document).ready(function(){
	$(".modifyView").hide();
	var url = uri.serverUrl + uri.managerGetUrl;
	sendRequestJson(url, {managerNo : no}, detailContSuceess, error);
});

function detailContSuceess(res) {
	
	var state = "";
	switch(res.info.managerStatus){
	case "P02001" : state = "대기"; $("#managerAckInput").val("승인").data("cd", "P02002"); break;
	case "P02002" : state = "정상"; $("#managerAckInput").hide(); break;
	case "P02003" : state = "차단"; $("#managerAckInput").val("승인").data("cd", "P02002"); break;
	case "P02004" : state = "삭제"; $("#managerAckInput").val("승인").data("cd", "P02002"); break;
	}
	
	$("#managerName").text(res.info.managerName);
	$("#managerStatus").text(state);
	$("#managerId").text(res.info.managerId);
	$("#managerPhone").text(res.info.phone);
	$("#managerDepartment").text(res.info.department);
	$("#managerEmail").text(res.info.managerEmail);
	
	
}

function viewChange(){
	$(".detailView").show();
	$(".modifyView").hide();
}

function modifyChange() {
	
	$("input[name=managerName]").val($("#managerName").text());
	$("input[name=department]").val($("#managerDepartment").text());
	
	$("input[name=managerPhone]").val($("#managerPhone").text());
	
	if($("#managerPhone").text() != "" && $("#managerPhone").text() != null) {
		var eArr = $("#managerPhone").text().split("-");
		$("#tel1").val(eArr[0]);
		$("#tel2").val(eArr[1]);
		$("#tel3").val(eArr[1]);
	}
	
	if($("#managerEmail").text() != "" && $("#managerEmail").text() != null) {
		var eArr = $("#managerEmail").text().split("@");
		$("#email1").val(eArr[0]);
		$("#email2").val(eArr[1]);
		$("#emailSelect").val(eArr[1]);
	}
	
	$(".detailView").hide();
	$(".modifyView").show();
}

function modifySubmit(){
	var mobile = $("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val();
	var email = $("#email1").val() + "@" + $("#email2").val();
	
	if($("input[name=managerName]").val().trim() == "") {
		alert(msg.mostName );
		return false;
	} else if(email.trim() == "") {
		alert(msg.mostEmail );
		return false;
	} else if(!email.isEmail()) { 
		alert(msg.checkEmail );
		return false;
	} else if($("input[name=department]").val().trim() == ""){
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
	
		
	var param = $("#managerModifyForm").serializeObject();
	param['managerNo'] = no;
	param['managerPhone'] = mobile;
	param['managerEmail'] = email;
	
	var url = uri.serverUrl + uri.managerUpdateUrl;
	sendRequestJson(url, param, modifySuccess, error);
}

function modifySuccess(res) {
	if(res.result.resultCode == "0000") {
		alert(msg.managerModify);
		location.reload();
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

function ackChange(obj) {
	var param = {
		managerNo : no,
		managerStatus : $(obj).data("cd")
	}
	var url = uri.serverUrl + uri.managerAckUrl;
	sendRequestJson(url, param, ackSuccess, error);
}

function ackSuccess(res){
	alert(msg.ackSuccess);
	location.reload();
}

function error(){
	alert(msg.managerFail);
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>계정관리 > 계정상세</h1>
		</div>
		<div class="mainTable memberJoinForm">
			<form id="managerModifyForm">
			<table class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td class="tl">이름</td>
						<td class="tl detailView" id="managerName"></td>
						<td class="tl input modifyView"><input type="text" name="managerName" value="" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">상태</td>
						<td class="tl" id="managerStatus"></td>
					</tr>
					<tr>
						<td class="tl">아이디</td>
						<td class="tl" id="managerId"></td>
					</tr>
					<tr>
						<td class="tl">전화번호</td>
						<td class="tl detailView" id="managerPhone"></td>
						<td class="tl input modifyView">
							<input type="text" id="tel1" value="" class="input_red input_type2" /> - 
							<input type="text" id="tel2" value="" class="input_red input_type2" /> - 
							<input type="text" id="tel3" value="" class="input_red input_type2" />
						</td>
					</tr>
					<tr>
						<td class="tl">소속</td>
						<td class="tl detailView" id="managerDepartment"></td>
						<td class="tl input modifyView"><input type="text" name="department" value="" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">이메일</td>
						<td class="tl detailView" id="managerEmail"></td>
						<td class="tl input modifyView">						
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
<!-- 						<li><input type="button" value="암호초기화" class="btn_normal_red" onclick="pwdReset()" /></li> -->
						<li class="detailView"><input type="button" id="managerAckInput" value="승인" class="btn_normal_red" onclick="ackChange(this)" /></li>
						<li class="detailView"><input type="button" value="수정" class="btn_normal_red" onclick="modifyChange()"/></li>
						
						<li class="modifyView"><input type="button" value="수정완료" class="btn_normal_red" onclick="modifySubmit()" /></li>
						<li class="modifyView"><input type="button" value="취소" class="btn_normal_red" onclick="viewChange()" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 