<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#reserveName").text(reserve.facName);
	
	dayInputSet();
});

function dayInputSet() {
	$("#reserveDayList").empty();
	
	reserveParamInfo.reserveArray.sort();
	
	var tmpArr = [];
	for(var i=0; i < reserveParamInfo.reserveArray.length; i++) {
		var tmpDayArr = reserveParamInfo.reserveArray[i].split("/");
		var tmpDayArr2;
		if(reserveParamInfo.reserveArray[i+1] != null) {
			tmpDayArr2 = reserveParamInfo.reserveArray[i+1].split("/");
			if(tmpDayArr[0] == tmpDayArr2[0]) {
				tmpArr.push(tmpDayArr[0] + "/3");
			} else {
				if(reserveParamInfo.reserveArray[i-1] != null) {
					tmpDayArr2 = reserveParamInfo.reserveArray[i-1].split("/");
					if(tmpDayArr[0] != tmpDayArr2[0]) {
						tmpArr.push(tmpDayArr[0] + "/" + tmpDayArr[1]);
					}
				}else {
					tmpArr.push(tmpDayArr[0] + "/" + tmpDayArr[1]);
				}
			}
		} else {
			if(reserveParamInfo.reserveArray[i-1] != null) {
				tmpDayArr2 = reserveParamInfo.reserveArray[i-1].split("/");
				if(tmpDayArr[0] != tmpDayArr2[0]) {
					tmpArr.push(tmpDayArr[0] + "/" + tmpDayArr[1]);
				}
			} else {
				tmpArr.push(tmpDayArr[0] + "/" + tmpDayArr[1]);
			}
		}
		
	}
	
	tmpArr.sort();
	reserveDayListText = "";
	
	$(tmpArr).each(function(){
		var rArr = this.split("/");
		var dArr = rArr[0].split("-");
		var text = "<li>" + dArr[0] + "년 " + dArr[1] + "월 " + dArr[2] + "일 ";
		
		switch(rArr[1]) {
			case "1" :
				text += "오전";
				break;
			case "2" :
				text += "오후";
				break;
			case "3" :
				text += "종일";
				break;
		}
		
		text += "</li>";
		reserveDayListText += text;
		$("#reserveDayList").append(text);
	});
}

function checkForm(){
	
	if($("#enterpriseName").val().trim() == "") {
		alert(msg.mostEnterprise);
		return false;
	} else if($("#workContent").val().trim() == "") {
		alert(msg.mostWorkContent);
		return false;
	}
	
	reserveParamInfo.enterpriseName = $("#enterpriseName").val();
	reserveParamInfo.visitCount = $("#visitCount").val();
	reserveParamInfo.workContent = $("#workContent").val()
	
	return true;
}

function memberSearchPopup(){
	sendRequestHtml('${contextPath}/reserve/memberPopup.do', showPopup, error);
}

function setMemberValue(user) {
	
	$("#workerName").text(user.userName);
	$("#userId").text(user.userId);
	$("#userCellPhone").text(user.userCellPhone);
	$("#userStatus").text(user.userStatus);
	$("#enterpriseName").text(user.enterpriseName);
	$("#userName2").text(user.userName);
	
 	log("userNO : " + user.userNo);
	
	reserveParamInfo.userNo = user.userNo;
	reserveParamInfo.enterpriseName = user.enterpriseName;
	reserveParamInfo.workerName = user.userName;
	
}

function checkReserve(){
	reserveParamInfo.visitCount = $("#visitCount").val();
	reserveParamInfo.workContent = $("#workContent").val();
	
	if(reserveParamInfo.userNo == "" || reserveParamInfo.userNo == null){
		alert(msg.mostMemberInfo);
		return false;
	} else if(reserveParamInfo.workContent.trim() == ""){
		alert(msg.mostWorkContent);
		return false;
	}
	
	var url = uri.serverUrl + uri.reserveAddUrl;
	sendRequestJson(url, reserveParamInfo, reserveAddSuccess, error);
}

function reserveAddSuccess(res){
	if(res.result.resultCode == "0000"){
		alert(msg.reserveAddSuccess);
		calendarCall();
	} else {
		error();
	}
}

</script>


<input type="button" value="회원 찾기" class="btn_normal_red mb10" onclick="memberSearchPopup()"/>
<table class="hint_text mb10 noClick">
	<colgroup>
		<col width="25%" />
		<col width="*" />
	</colgroup>
	<tbody>
		<tr>
			<td class="tl">이름</td>
			<td class="tl" id="workerName"></td>
		</tr>
		<tr>
			<td class="tl">아이디</td>
			<td class="tl" id="userId"></td>
		</tr>
		<tr>
			<td class="tl">전화번호</td>
			<td class="tl" id="userCellPhone"></td>
		</tr>
		<tr>
			<td class="tl">상태</td>
			<td class="tl" id="userStatus"></td>
		</tr>
		<tr>
			<td class="tl">소속</td>
			<td class="tl" id="enterpriseName"></td>
		</tr>
	</tbody>
</table>

<table>
	<colgroup>
		<col width="25%" />
		<col width="*" />
	</colgroup>
	<tbody>
		<tr>
			<td class="tl">예약 요일 및 일시</td>
			<td class="tl">
				<ul id="reserveDayList">
					<li>2014/07/10 종일</li>
					<li>2014/07/10 오전</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="tl">예약시설</td>
			<td class="tl" id="reserveName">창작지원1실</td>
		</tr>
		<tr>
			<td class="tl">사용인원</td>
			<td class="tl"><span id="userName2"></span> 외
				<select class="input_red" id="visitCount">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select> 명
			</td>
		</tr>
		<tr>
			<td class="tl">작업 내용</td>
			<td class="tl input">
				<textarea id="workContent" class="input_red"></textarea>
			</td>
		</tr>
		
	</tbody>
</table>

<div id="mainBottomLayer" class="member">
	<div class="cen">
		<ul>
			<li><input type="button" value="예약하기" class="btn_normal_red" onclick="checkReserve()"/></li>
			<li><input type="button" value="취소" class="btn_normal_red" onclick="calendarCall()" /></li>
		</ul>
	</div>
</div>

	