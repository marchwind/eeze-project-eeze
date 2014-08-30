<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var detailURL = "${contextPath}/member/memberDetailHTML.do"
var modifyURL = "${contextPath}/member/memberModifyHTML.do"

var userInfo = {
		userNo : "${no}",
		userName : "",
		userStatus : "",
		userId : "",
		userPhone : "", 
		userCellPhone : "",
		userEmail : "",		
		enterpriseName : "",
		enterpriseAddress : ""
}
$(document).ready(function(){
	callDetailPage();
	pageListCall();
});

function pageListCall() {
	pageValue.unitPerPage = 6;
	var param = pageValue;
	param['userNo'] = userInfo.userNo;
	
	var url = uri.serverUrl + uri.userReserveListUrl;
	sendRequestJson(url, param, listSuccess, error);
}

function listSuccess(res) {
	var $table = $("#memberReserveList tbody");
	$table.empty();
	if(res.result.resultCode == "0000"){
		
		$(res.list).each(function(){
			
			var timeZone = (this.reserveTimeZone == 'R01001') ? '오전' : '오후';
			
			var tag = '<tr>' +
					'<td>'+numberToDate(this.reserveStartDate)+' ' + timeZone + '</td>' +
					'<td>'+this.facilityName+'</td>' +
					'<td>'+ ((this.facilityCheck == null) ? '양호' : this.facilityCheck) +'</td>' +
					'</tr>';
			
			$table.append(tag);
			
		}); 
		
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400"){
		var tag = '<tr><td colspan="3" class="tc">데이터가 없습니다.</td></tr>';
		$table.append(tag);
		page(pageValue.currentPage, 0);
	} else {
		error();
	}
}

function callDetailPage() {
	$("#detail_btn").show();
	$("#modify_btn").hide();
	sendRequestHtml(detailURL, callPageSuccess, error);
}

function callModifyPage() {
	$("#detail_btn").hide();
	$("#modify_btn").show();
	sendRequestHtml(modifyURL, callPageSuccess, error);
}

function callPageSuccess(res){
	
	var $container = $("#memberInfoFrom");
	$container.empty();
	
	$container.html(res);
}

function error() {
	alert(msg.failUserDetail);
}

function passwordReset(){
	
}

function memberBlock() {
	if(confirm(msg.userBlockConfirm)){
		var url = uri.serverUrl + uri.userBlockUrl;
		sendRequestJson(url, {userNo : userInfo.userNo }, userBlockSuccess, error);	
	}
}

function userBlockSuccess(res) {
	alert(msg.userBlock);
	location.reload();
}

function memberBlockCancel() {
	if(confirm(msg.userNormalConfirm)){
		var url = uri.serverUrl + uri.userNormalUrl;
		sendRequestJson(url, {userNo : userInfo.userNo }, userNormalSuccess, error);	
	}
}

function userNormalSuccess(res) {
	alert(msg.userNormal);
	location.reload();
}

function memberDel(){
	if(confirm(msg.userDelConfirm)){
		var url = uri.serverUrl + uri.userDeleteUrl;
		sendRequestJson(url, {userNo : userInfo.userNo }, userDelSuccess, error);	
	}
}

function userDelSuccess(res){
	alert(msg.userDel);
	goPage('${contextPath}/member/list.do');
}

function modifySubmit() {
	var tel = $("#telSelect").val() + "-" + $("#tel1").val() + "-" + $("#tel2").val();
	var mobile = $("#mobileSelect").val() + "-" + $("#mobile1").val() + "-" + $("#mobile2").val();
	var email = $("#emailId1").val() + "@" + $("#emailId2").val();
	
	if($("#userName").val().trim() == "") {
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
			alert(msg.checkNum);
			return false;
		}
	} 
	
	if(!$("#mobile1").val().isNum() || !$("#mobile1").val().isNum()) {
		alert(msg.checkNum);
		return false;
	} else if(mobile.length < 12){
		alert(msg.mostMobile);
		return false;
	}
	
	$("#userName").val($("#userName").val().trim());
	
	var param = $("#memberInfoFrom").serializeObject();
	param['userNo'] = userInfo.userNo;
	param['userPhone'] = tel;
	param['userCellPhone'] = mobile;
	param['userEmail'] = email;
	
	var url = uri.serverUrl + uri.userUpdateUrl;
	sendRequestJson(url, param, updateSuccess, error);
	
}

function updateSuccess(res){
	if(res.result.resultCode == "0000"){
		alert(msg.updateSuccess);
		callDetailPage();
	} else {
		alert(msg.updateFail);
	}
}
</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>회원관리 > 회원상세</h1>
		</div>
		<div class="mainTable">
			<div id="mainLeftLayer" class="member tableType">
				<form id="memberInfoFrom">
				</form>
			</div>
			<div id="mainRightLayer" class="member">
				<table id="memberReserveList" class="noClick">
					<colgroup>
						<col width="*" />
						<col width="30%" />
						<col width="30%" />
					</colgroup>
					<thead>
						<tr>
							<th>예약일시</th>
							<th>예약시설</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>					
				</table>
				<div class="page">
					<ul>
						<li> << </li>
						<li class="on">1</li>
						<li>2</li>
						<li> >> </li>
					</ul>
				</div>
			</div>
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/member/list.do')"/>
				</div>
				<div class="fr">
					<ul id="detail_btn">
						<!-- <li><input type="button" value="암호초기화" class="btn_normal_red" onclick="passwordReset()"/></li> -->
						<li><input type="button" value="회원수정" class="btn_normal_red" onclick="callModifyPage()"/></li>
						<li><input id="memberBlockBtn" type="button" value="회원차단" class="btn_normal_red" onclick="memberBlock()"/></li>
						<li><input id="memberBlockCancelBtn" type="button" value="차단해제" class="btn_normal_red" onclick="memberBlockCancel()"/></li>
						<li><input type="button" value="회원삭제" class="btn_normal_red" onclick="memberDel()"/></li>
					</ul>
					<ul id="modify_btn">
						<li><input type="button" value="수정완료" class="btn_normal_red" onclick="modifySubmit()"/></li>
						<li><input type="button" value="취소" class="btn_normal_red" onclick="callDetailPage()"/></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 