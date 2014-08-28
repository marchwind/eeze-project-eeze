<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">

var id = '${id}';

$(document).ready(function(){
	var date = new Date();
	var y = date.getFullYear(); 
	var m = date.getMonth() + 1;
	
	reserve.fac = facility[id];
	reserve.year = y;
	reserve.month = m;
	
	reserveCall();
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
				
				data.push(ele);
			});
		});
	}
	makeCalendar(reserve.year, reserve.month, data);
} 

function error() {
	alert(msg.reserveListFail);
}

function closeAndReserve() {
	goClose('${contextPath}/reserve/situation.do?id='+id+'&y='+reserve.year+'&m='+reserve.month);
}

</script>
</head>
<body>
<div id="wrap">
    <div id="popupFrame">
    	<div id="subPage">
    		<form id="reserveListForm">
				<input type="hidden" name="facilityNo" id="facilityNo" />
				<input type="hidden" name="reserveYear" id="reserveYear"/>
				<input type="hidden" name="reserveMonth" id="reserveMonth"/>
			</form>
            <div id="popupCont">
            	<div id="calendarDiv"></div>
          		<div id="reserveBtn">
          			<input type="button" class="btn_7" value="예약하기" onclick="closeAndReserve()"/>
          		</div>
            </div>
        </div>
	</div>
</div>
</body>
</html>