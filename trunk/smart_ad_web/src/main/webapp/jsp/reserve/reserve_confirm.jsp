<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function() {
	$("#reserveName").text(reserve.facName);
	showValue();
});

function showValue() {
	$("#reserveDayList").html(reserveDayListText);
	$("#enterpriseNameValue").text(reserveParamInfo.enterpriseName);
	$("#workerNameValue").text(reserveParamInfo.workerName + " 외 " + reserveParamInfo.visitCount);
	$("#workContentValue").text(reserveParamInfo.workContent);
}

function reserveSubmit() {
	sendRequestJson(uri.serverUrl + uri.reserveAddUrl, reserveParamInfo, successSubmit, error);
}

function successSubmit(res) {
	if(res.result.resultCode == "0000") {
		sendRequestHtml('${contextPath}/reserve/reserveResult.do', successHtml, error);	
	} else {
		error();
	}
}

function reserveInputBack() {
	sendRequestHtml('${contextPath}/reserve/reserveInput.do', successHtml, error);
}

function successHtml(res) {
	$("#reserveCont").html(res);	
}
</script>
<h2><span id="reserveName">녹음스튜디오</span> 예약 관련 절차안내입니다.</h2>
<hr />
<div id="reserveFlow">
	<img src="${contextPath}/resources/images/common/reserve_flow_3.png" />
</div>
<div class="inputForm">
	<form id="reserveConfirmForm">
		<dl>
		   	<dt>요일선택</dt>
		    <dd>
		    	<ul id="reserveDayList">
		    	</ul>
		    </dd>    
		</dl>
		<dl>
			<dt>업체명 </dt>
		    <dd id="enterpriseNameValue"></dd>
		</dl>
		<dl>
			<dt>작업자</dt>
		    <dd id="workerNameValue"></dd>
		</dl>
		<dl>
			<dt>작업내용</dt>
		    <dd id="workContentValue"></dd>
		</dl>
	</form>
</div>
<div id="reserveBtn">
	<input type="button" class="btn_5" value="이전단계" onclick="reserveInputBack()"/>
	<input type="button" class="btn_4" value="예약하기" onclick="reserveSubmit()"/>
</div>
               			
               		