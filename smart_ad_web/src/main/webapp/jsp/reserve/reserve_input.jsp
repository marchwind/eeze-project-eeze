<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
$(document).ready(function() {
	$("#reserveName").text(reserve.facName);
	
	dayInputSet();
	initValue();
});

function initValue() {
	log(user);
	
	reserveParamInfo.userNo = user.userNo;
	reserveParamInfo.workerName = user.userName;
	//reserveParamInfo.reserveArray = reserveTime;	
	reserveParamInfo.facilityNo = reserve.fac;
	
	if(reserveParamInfo.enterpriseName == "") {
		reserveParamInfo.enterpriseName = user.enterpriseName;
	}
	
	$("#enterpriseName").val(reserveParamInfo.enterpriseName);
	$("#workerName").val(reserveParamInfo.workerName);
	$("#visitCount").val(reserveParamInfo.visitCount);
	$("#workContent").val(reserveParamInfo.workContent);
		
}

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

function reserveConfirm() {
	if(checkForm()) {
		sendRequestHtml('${contextPath}/reserve/reserveConfirm.do', successConfirmHtml, error);
	}
}

function successConfirmHtml(res) {
	$("#reserveCont").html(res);	
}
</script>

<h2><span id="reserveName">녹음스튜디오</span> 예약 관련 절차안내입니다.</h2>
<hr />
<div id="reserveFlow">
	<img src="${contextPath}/resources/images/common/reserve_flow_2.png" />
</div>
<div class="inputForm">
	<dl>
	   	<dt>요일선택</dt>
	    <dd>
	    	<ul id="reserveDayList">
	    	</ul>
	    </dd>    
	</dl>
	<dl>
    	<dt><em>*</em> 업체명 </dt>
        <dd>	
        	<input type="text" value="" id="enterpriseName" />
        </dd>
	</dl>
	<dl>
		<dt><em>*</em> 작업자</dt>
        <dd>
        	<input type="text" value="" id="workerName" disabled="disabled"/> 외 
           	<select id="visitCount">
               	<option>0</option>
               	<option>1</option>
               	<option>2</option>
               	<option>3</option>
        	</select> 명
		</dd>
	</dl>
	<dl>
		<dt><em>*</em> 작업내용</dt>
		<dd>
           	<textarea id="workContent"></textarea>
        </dd>
	</dl>
</div>
<div id="reserveBtn">
	<input type="button" class="btn_5" value="이전단계" onclick="reserveInitCall()"/>
<input type="button" class="btn_4" value="다음단계" onclick="reserveConfirm()"/>
</div>
               			
               		