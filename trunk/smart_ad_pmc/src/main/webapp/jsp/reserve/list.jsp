<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">

var reserveUser = {
	reserveNo : "",
	reserveDetailNo : ""
}

var reserveParamInfo = {
	userNo : "",
	facilityNo : "",
	reserveArray : new Array(),
	enterpriseName : "",
	workerName : "",
	visitCount : 0,
	workContent : ""
};

var reservePage = {
	faclitityId : ""
}

$(document).ready(function() {
	
	reservePage.faclitityId = "c";
	calendarCall();
	
});

function calendarCall(){
	$(".mainTable").removeClass("reserveForm");
	sendRequestHtml('${contextPath}/reserve/list_cal.do', calendarCallSuccess, error);
}

function calendarCallSuccess(res){
	$(".mainTable").html(res);
}

function tabEvent(obj) {
	
	$(".btn_squre").removeClass("on");
	
	var $obj = $(obj);
	var data = $(obj).data("fac");
	
	$obj.addClass("on");
	
	reservePage.faclitityId = data;
	reserve.facName = $obj.val();
	
	calendarCall();
}

function error() {
	alert(msg.reserveListFail);
}

function reserveInputCall() {
	if(reserveParamInfo.reserveArray.length < 1){
		alert(msg.reserveSelect);
		return false;
	}
	
	$(".mainTable").addClass("reserveForm");
	sendRequestHtml('${contextPath}/reserve/input.do', reserveInputCallSuccess, error);
}

function reserveInputCallSuccess(res){
	$(".mainTable").html(res);
}
</script>

</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>예약관리</h1>
		</div>
		<div id="listTop">
			<div id="tabSelect">
				<ul>
					<li><input type="button" value="창작지원실" class="btn_squre on" data-fac="c" onclick="tabEvent(this)"/></li>
					<li><input type="button" value="녹음스튜디오" class="btn_squre" data-fac="r1" onclick="tabEvent(this)"/></li>
					<li><input type="button" value="매체적합성테스트실" class="btn_squre" data-fac="t1" onclick="tabEvent(this)" /></li>
					<li><input type="button" value="회의실" class="btn_squre" data-fac="m1" onclick="tabEvent(this)" /></li>
				</ul>
			</div>
		</div>
		<div class="mainTable">
				
		</div>
	</div>
	
	<div class="popup">
		
	</div>
	
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 