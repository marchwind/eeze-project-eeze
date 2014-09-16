<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function() {
	var date = new Date();
	reserve.reserveYear = date.getFullYear(); 
	reserve.month = date.getMonth() + 1;
	
	if(reservePage.faclitityId == "c") {
		$("#createSelect").show();
		$("#createSelect").val(facility.c1);
		selectEvent($("#createSelect"));
	} else {
		reserveParamInfo.facilityNo = facility[reservePage.faclitityId];
		$("#createSelect").hide();
		setReserveCall(reserve.reserveYear, reserve.month, facility[reservePage.faclitityId]);
	}
});

function selectEvent(obj) {
	var $obj = $(obj);
	var data = $(obj).val();
	reserveParamInfo.facilityNo = facility[data];
	reserve.facName = $obj.find("option:selected").text();
	setReserveCall(reserve.reserveYear, reserve.month, facility[data]);
}

function reserveListCall() {
	var param = reserve;
	
	var url = uri.serverUrl + uri.reserveListUrl;	
	sendRequestJson(url, param, reserveListCallSuccess, error);
}

function reserveListCallSuccess(res) {
	var data = [];
	if(res.result.resultCode == "0000") {
		$(res.list).each(function(){
			var reserveNo = this.reserveNo;
			$(this.reserveDetail).each(function() {
				var ele = {};
				ele.reserveNo = reserveNo;
				ele.checkInYn = this.checkInYn;
				ele.checkOutYn = this.checkOutYn;
				ele.reserveDetailNo = this.reserveDetailNo;
				ele.enterpriseName = this.enterpriseName;
				ele.date = (numberToDate(this.reserveDate));
				ele.state = this.reserveTimeZone;
				log("reserveNO : " + ele.reserveNo);
				data.push(ele);
			});	
		});
	}
	
	makeCalendar(reserve.reserveYear, reserve.month, data);
	buttonSet();
}

function checkinPopup(obj) {
	var $obj = $(obj);
	reserveUser.reserveNo = $obj.data("reserveno");
	reserveUser.reserveDetailNo = $obj.data("reservedetailno");
	sendRequestHtml('${contextPath}/reserve/checkInConfirm.do', showPopup, error);
}

function usedPopup(obj){
	var $obj = $(obj);
	reserveUser.reserveNo = $obj.data("reserveno");
	reserveUser.reserveDetailNo = $obj.data("reservedetailno");
	sendRequestHtml('${contextPath}/reserve/checkOutModify.do', showPopup, error);
}

function reserveCheckPopup(obj) {
	var $obj = $(obj);
	reserveUser.reserveNo = $obj.data("reserveno");
	reserveUser.reserveDetailNo = $obj.data("reservedetailno");
	reserveUser['date'] = $obj.data("date");
	sendRequestHtml('${contextPath}/reserve/check.do', showPopup, error);
}

function buttonSet(){
	reserveParamInfo.reserveArray = [];
	//reserveTime = [];
	
	$(".clickable").click(function(){
		var $obj = $(this);
		var day = $obj.parent("td").attr("id");
		var endTime = day.replace("-","");
		
		if($obj.hasClass("on")){
			$obj.removeClass("on");
			if($obj.data("time") == "1") {
				$obj.val("오전가능");
			} else {
				$obj.val("오후가능");
			}
			reserveParamInfo.reserveArray.remove(day + "/" + $obj.data("time"));
		} else {
			if(reserveParamInfo.reserveArray.length > reserve.limit) {
				alert(msg.overPeriod);
			} else {
				$obj.addClass("on");
				if($obj.data("time") == "1") {
					$obj.val("오전");
				} else {
					$obj.val("오후");
				}
				reserveParamInfo.reserveArray.push(day + "/" + $obj.data("time"));
			}
		}
		
		log(reserveParamInfo.reserveArray);
	
	});
}
</script>
<div id="cal_top">
	<div id="createList">
		<select class="input_red" id="createSelect" onchange="selectEvent(this)">
			<option value="c1">창작지원1실</option>
			<option value="c2">창작지원2실</option>
			<option value="c3">창작지원3실</option>
			<option value="c4">창작지원4실</option>
		</select>
	</div>
	<div id="cal_title">
		<ul>
			<li> << </li>
			<li class="red"> 2014.7 </li>
			<li> >> </li>
		</ul>
	</div>
	<div id="cal_top_btn">
		<input type="button" value="예약하기" class="btn_normal_red" onclick="reserveInputCall()"/>
	</div>
</div>
<div id="calendarDiv">
	<table id="cal_list">
		<thead>
			<tr>
				<th>SUN</th>
				<th>MON</th>
				<th>TUE</th>
				<th>WED</th>
				<th>THU</th>
				<th>FRI</th>
				<th>SAT</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>		
</div>	
		