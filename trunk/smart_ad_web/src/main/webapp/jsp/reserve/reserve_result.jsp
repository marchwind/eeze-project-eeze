<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#reserveName").text(reserve.facName);
	
	resultSet();
});

function resultSet() {
	$("#userName").text(reserveParamInfo.workerName);
	$("#facility").text(reserve.facName);
	$("#reserveDayList").html(reserveDayListText);
}
</script>

<h2><span id="reserveName">녹음스튜디오</span> 예약 관련 절차안내입니다.</h2>
<hr />
<div id="reserveFlow">
	<img src="${contextPath}/resources/images/common/reserve_flow_4.png" />
</div>
<div id="reserveResult">
	<div>
		<b id="userName"></b> 님의  <span id="facility"></span> 예약이 완료 되었습니다.
		<ul id="reserveDayList">
		</ul>
	</div>	
</div>
<div id="reserveBtn">
	<input type="button" class="btn_5" value="처음으로" onclick="reserveInitCall()"/>
	<input type="button" class="btn_4" value="예약확인" onclick="goMyPage('${contextPath}/member/myReserve.do')"/>
</div>
<div id="reserveDesc">
	<div class="dot_box">
		<ul>
			<li> <b>문의처</b> : 창작공간AD</li>
			<li> <b>E-mail</b> : smartad@smartad.or.kr</li>
		</ul>
	</div>
</div>
               		