<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
$(document).ready(function() {
	var url = uri.serverUrl + uri.reserveGetCheckInUrl;
	sendRequestJson(url, reserveUser, reserveCheckInCallSuccess, error);
});

function reserveCheckInCallSuccess(res) {
	if(res.result.resultCode == "0000"){
		$("#popupWorker").text(res.info.workerName + "외 " + res.info.visitCount + "명");
		
		var timeZone = (res.info.reserveTimeZone < 2) ? "오전" : "오후";
		$("#popupDate").text(numberToDate(res.info.reserveStartDate) + " " + timeZone);
		
		$("#popupFacility").text(res.info.facilityName);
		$("#popupId").text(res.info.userId);
		$("#popupCellPhone").text(res.info.userCellPhone);
		$("#popupEnterpriseName").text(res.info.enterpriseName);
		$("#popupWorkerContent").text(res.info.workContent);
		
	} else {
		error();
	}
}

function reserveCheckOutModifyCall(){
	var url = uri.serverUrl + uri.reserveCheckOutUrl;
	reserveUser['facilityCheck'] = $("#popupWorkerCheck").val();
	sendRequestJson(url, reserveUser, reserveCheckOutModifyCallSuccess, error);
}

function reserveCheckOutModifyCallSuccess(res) {
	if(res.result.resultCode == "0000"){
		closePopup();
	} else {
		error();
	}
}

</script>

<input type="button" class="btn_popup_close" onclick="closePopup()"/>
<h1>입실 확인</h1>
<table>
	<tr>
		<td>사용인원</td>
		<td class="tl" id="popupWorker"></td>
	</tr>
	<tr>
		<td>예약일시</td>
		<td class="tl" id="popupDate"></td>
	</tr>
	<tr>
		<td>예약시설</td>
		<td class="tl" id="popupFacility"></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td class="tl" id="popupId"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td class="tl" id="popupCellPhone"></td>
	</tr>
	<tr>
		<td>소속</td>
		<td class="tl" id="popupEnterpriseName"></td>
	</tr>
	<tr>
		<td>작업내용</td>
		<td class="tl" id="popupWorkerContent"></td>
	</tr>
</table>

<div class="popup_write">
<h2>사용 후 상태</h2>
<textarea class="input_gray" id="popupWorkerCheck"></textarea>
</div>

<div class="popupBtnContainer">
	<input type="button" value="퇴실" class="btn_normal_red" onclick="reserveCheckOutModifyCall()"/>
	<input type="button" value="닫기" class="btn_normal_red" onclick="closePopup()" />
</div>