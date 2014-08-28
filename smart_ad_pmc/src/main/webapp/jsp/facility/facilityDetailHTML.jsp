<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	$(".modifyEle").hide();
	var svg = $("#map").svg({loadURL : '${contextPath}/resources/map3.svg', onLoad : svgloaded, changeSize:true});
});

function svgloaded(svg, error){
	$(".map_circle").hide();
	
	facilityGetCall();
}

function facilityGetCall(){
	var url = uri.serverUrl + uri.facilityGetUrl;
	sendRequestJson(url, {facilityNo : no}, detailSuccess, error);
}

function detailSuccess(res) {
	
	if(res.result.resultCode == "0000"){
	
		$(".modifyEle").hide();
		$(".detailEle").show();
		
		$("#" + res.info.facilityNo + "_c").show();
		$("#facilityName").text(res.info.facilityName);
		$("#facilityExplain p").text(res.info.facilityExplain);
		
		var status = "";
		switch(res.info.facilityState) {
		case "F01001" : status = "정상운영"; break;
		case "F01002" : status = "운영중지"; break;
		case "F01003" : status = "보수중"; break;	
		}
		$("#facilityState p").data("code", res.info.facilityState);
		$("#facilityState p").text(status);
		
		var $table = $("#facilityEquipment tbody");
		$table.empty();
		$(res.info.list).each(function() {
			var type = "PC";
			switch(this.equipType) {
			case "E04001" : type = "PC"; break;
			case "E04002" : type = "서버"; break;
			case "E04003" : type = "스마트단말기"; break;
			case "E04004" : type = "기타"; break;
			}
			
			var state = "";
			switch(this.equipState) {
			case "E01001" : state = "정상"; break;
			case "E01002" : state = "고장"; break;
			case "E01003" : state = "수리중"; break;
			}
			
			var tag = '<tr>' +
					'<td>' + type + '</td>' + 
					'<td>' + this.equipName + '</td>' + 
					'<td>' + this.equipModel + '</td>' +
					'<td>' + this.equipIp + '</td>' +
					'<td>' + state + '</td>';
			
			if(this.equipType == "E04001") {
				tag += '<td><input type="button" value="확인" class="btn_normal_red" onclick="goUsedListCall(\''+this.equipNo+'\')" /></td>';
			} else {
				tag += '<td></td>';
			}
			tag += '</tr>';
					
			$table.append(tag);
		});
	} else if(res.result.resultCode == "0400"){
		alert(msg.noData);
	}
}

function detailChange(){
	
	$("#facilityExplainInput").val($("#facilityExplain p").text());
	$("#facilityStateInput").val($("#facilityState p").data("code"));
	
	$(".detailEle").show();
	$(".modifyEle").hide();
}

function modifyChange(){
	
	$("#facilityExplainInput").val($("#facilityExplain p").text());
	$("#facilityStateInput").val($("#facilityState p").data("code"));
	
	$(".detailEle").hide();
	$(".modifyEle").show();
}

function modifySubmit() {
	var param = {
		facilityNo : no,
		facilityName : $("#facilityName").text(),
		facilityExplain : $("#facilityExplainInput").val(),
		facilityState : $("#facilityStateInput").val()
	}
	
	var url = uri.serverUrl + uri.facilityUpdateUrl;
	sendRequestJson(url, param, facilityUpdateSuccess, error);
}

function facilityUpdateSuccess(res) {
	alert(msg.failityUpdate);
	facilityGetCall();
}

function goUsedListCall(no) {
	goPage('${contextPath}/equipment/usedList.do?no=' + no);
}

</script>
<table class="facility_detail noClick">
	<colgroup>
		<col width="15%" />
		<col width="*" />
		<col width="40%" />
	</colgroup>
	<tr>
		<td class="tc">시설 이름</td>
		<td id="facilityName"></td>
		<td rowspan="3">
			<div id="map"></div>
		</td>
	</tr>
	<tr>
		<td class="tc">시설 설명</td>
		<td id="facilityExplain">
			<p class="detailEle"></p>
			<input id="facilityExplainInput" type="text" class="modifyEle input_red" value="간단한 녹음, 더빙 작업을 통해" />
		</td>
	</tr>
	<tr>
		<td class="tc">상태</td>
		<td id="facilityState">
			<p class="detailEle"></p>
			<select id="facilityStateInput" class="modifyEle input_red">
				<option value="F01001">정상운영</option>
				<option value="F01002">운영중지</option>
				<option value="F01003">보수중</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="tc">장비현황</td>
		<td colspan="2">
			<table id="facilityEquipment" class="tc">
				<colgroup>
					<col width="10%" />
					<col width="20%" />
					<col width="*" />
					<col width="20%" />
					<col width="10%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th>장비유형</th>
						<th>장비명</th>
						<th>모델명</th>
						<th>IP주소</th>
						<th>가능여부</th>
						<th>사용이력</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<div class="table_bottom">
	<p class="fl modifyEle">* 장비 추가 및 삭제는 장비 관리에서 진행하세요</p>
	<div class="fr">
		<input type="button" value="수정" class="detailEle btn_normal_red" onclick="modifyChange()" />
		<input type="button" value="수정완료" class="modifyEle btn_normal_red" onclick="modifySubmit()"/>
		<input type="button" value="취소" class="modifyEle btn_normal_red" onclick="detailChange()" />
	</div>
</div>
				