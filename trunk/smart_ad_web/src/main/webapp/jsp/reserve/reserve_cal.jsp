<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function() {
	$("#reserveName").text(reserve.facName);
	reserveCall();
	
	if(reserve.cd == "r1") {
		$(".recordNoti").show();
	} else {
		$(".recordNoti").hide();
	}
	
});

function reserveCall() {
	$("#facilityNo").val(reserve.fac);
	$("#reserveYear").val(reserve.year);
	$("#reserveMonth").val(((reserve.month < 10) ? ("0" + reserve.month) : reserve.month));
	
	var url = uri.serverUrl + uri.reserveListUrl;
	sendRequest("reserveListForm", url, reserveList, error);
}

function reserveList(res) {
	var data = [];
	if(res.result.resultCode == "0000") {
		$(res.list).each(function(){
			$(this.reserveDetail).each(function() {
				var ele = {};
				ele.date = (numberToDate(this.reserveDate));
				ele.state = this.reserveTimeZone;
				log("reserve Data : " + ele.date);
				data.push(ele);
			});	
		});
	}
	
	makeCalendar(reserve.year, reserve.month, data);
	buttonSet();
} 

function buttonSet() {
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

function reserveInput() {
	if(user.login) {
		if(reserveParamInfo.reserveArray.length > 0) {
			sendRequestHtml('${contextPath}/reserve/reserveInput.do', successInputHtml, error);	
		} else {
			alert(msg.reserveSelect);
		}
		
	} else {
		if(confirm(msg.needLogin)){
			goPage('${contextPath}/member/login.do');
		}
	}
}

function successInputHtml(res) {
	$("#reserveCont").html(res);	
}
</script>

<form id="reserveListForm">
	<input type="hidden" name="facilityNo" id="facilityNo" />
	<input type="hidden" name="reserveYear" id="reserveYear"/>
	<input type="hidden" name="reserveMonth" id="reserveMonth"/>
</form>
<h2><span id="reserveName">창작지원실</span> 예약을 선택하셨습니다. 원하는 시간 및 날짜를 선택하신 후 다음단계를 누르셔야 예약이 진행됩니다.</h2>
<p class="recordNoti">창작공간AD의 녹음스튜디오는 시설 및 공간만 지원해 드리고 있습니다. 따라서, 사용자가 녹음스튜디오 오퍼레이션을 직접 하셔야 합니다. 만약 오퍼레이터가 필요하실 경우 별도 문의하셔야 하며, 전문가를 섭외하여 작업을 진행하실 경우에는 별도의 비용이 발생함을 공지해 드립니다.</p>
<hr />
<div id="reserveFlow">
	<img src="${contextPath}/resources/images/common/reserve_flow_1.png" />
</div>
<div id="calendarDiv"></div>
<div id="reserveBtn">
	<input type="button" class="btn_7" value="다음단계" onclick="reserveInput()"/>
</div>
<div id="reserveDesc">
	<ul class="reserve_guide">
		<li><b>1. 오전가능</b> : AM 09:00 ~ 13:00 까지</li>
		<li><b>2. 오후가능</b> : PM 13:00 ~ 17:00 까지</li>
		<li><b>3. 예약불가</b> : 현재 예약을 할 수 없는 공간입니다.</li>
		<li><b>4. 예약마감</b> : 예약이 마감되었습니다.</li>
	</ul>
	<div class="dot_box">
		<ul>
			<li> <b>문의처</b> : 창작공간AD</li>
			<li> <b>E-mail</b> : smartad@smartad.or.kr</li>
			<li> <b>TEL</b> : 02-2144-0320</li>
		</ul>
	</div>
</div>
               			
               		