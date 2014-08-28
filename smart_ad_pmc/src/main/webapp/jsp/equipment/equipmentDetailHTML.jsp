<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	$(".modifyView").hide();
	var url = uri.serverUrl + uri.equipGetUrl;
	sendRequestJson(url, {equipNo : no}, equipGetSuccess, error);
	
	var svg = $("#map").svg({loadURL : '${contextPath}/resources/map3.svg', onLoad : svgloaded, changeSize:true});
});

function svgloaded(svg, error){
	$(".map_circle").hide();
}

function detailChange() {
	$(".detailView").show();
	$(".modifyView").hide();
	
	if($("#equipType").data("cd") == "E04001" || $("#equipType").data("cd") == "E04002") {
		$("#usedBtn").show();
	} else {
		$("#usedBtn").hide();
	}
	
	if($("#equipType").data("cd") != "E04004"){
		$("#equipPowerTr").show();
	}
}

function modifyChange(){
	$("select[name=facilityNo]").val($("#facilityName").data("cd"));
	$("select[name=equipType]").val($("#equipType").data("cd"));
	$("input[name=equipName]").val($("#equipName").text());
	$("input[name=equipModel]").val($("#equipModel").text());
	$("input[name=equipMac]").val($("#equipMac").text());
	$("input[name=equipIp]").val($("#equipIp").text());
	$("input[name=equipOs]").val($("#equipOs").text());
	$("textarea[name=equipSpec]").val($("#equipSpec").text().replaceAll("<br>","\n").replaceAll("</br>","\n"));
	$("textarea[name=equipExplain]").val($("#equipExplain").text().replaceAll("<br>","\n").replaceAll("</br>","\n"));
	$("textarea[name=equipMemo]").val($("#equipMemo").text().replaceAll("<br>","\n").replaceAll("</br>","\n"));
	$("select[name=equipState]").val($("#equipState").data("cd"));
	
	$(".detailView").hide();
	$(".modifyView").show();
	
	$("#equipPowerTr").hide();
}

function equipGetSuccess(res) {
	
	var type = "PC";
	switch (res.info.equipType) {
	case "E04001" : type = "PC"; break;
	case "E04002" : type = "서버"; break;
	case "E04004" : type = "기타"; $("#usedBtn").hide(); $("#equipPowerTr").hide(); break;
	}
	
	typeRelexChange(res.info.equipType);
	
	$("#equipPower").val(res.info.equipPowerState);
	
	$("#facilityName").text(res.info.facilityName).data("cd",res.info.facilityNo);
	$("#" + res.info.facilityNo + "_c").show();
	$("#equipType").text(type).data("cd",res.info.equipType);
	$("#equipName").text(res.info.equipName);
	$("#equipModel").text(res.info.equipModel);
	$("#equipIp").text(res.info.equipIp);
	$("#equipMac").text(res.info.equipMac);
	$("#equipOs").text(res.info.equipOs);
	
	if(res.info.equipSpec != null){
		$("#equipSpec").html(res.info.equipSpec.replaceAll("\n","<br/>"));	
	}
	
	if(res.info.equipExplain != null){
		$("#equipExplain").html(res.info.equipExplain.replaceAll("\n","<br/>"));	
	}
	
	if(res.info.equipMemo != null){
		$("#equipMemo").html(res.info.equipMemo.replaceAll("\n","<br/>"));	
	}
	
	
	var state = "정상";
	switch (res.info.equipState) {
	case "E01001" : state = "정상"; break;
	case "E01002" : state = "고장"; break;
	case "E01003" : state = "수리중"; break;
	}
	$("#equipState").text(state).data("cd", res.info.equipState);
	$("#equipLastCheckDate").text(numberToFull(res.info.equipLastCheckDate));
	
}

function equipDel() {
	if(confirm(msg.equipDelConfirm)){
		var url = uri.serverUrl + uri.equipDelUrl;
		sendRequestJson(url, {equipNo : no}, equipDelSuccess, error);	
	}
}

function equipDelSuccess() {
	alert(msg.equipDel);
	goPage('${contextPath}/equipment/list.do');
}

function checkPopup() {
	sendRequestHtml('${contextPath}/equipment/checkHTML.do', showPopup, error);
	
}

function usedListCall() {
	goPage('${contextPath}/equipment/usedList.do?no=' + no);
}

function typeImageChange(obj){
	typeRelexChange($(obj).val());
}

function typeRelexChange(type) {
	switch(type){
	case "E04001" : $("#typeImage").attr("src","${contextPath}/resources/images/image_pc.png"); break;
	case "E04002" : $("#typeImage").attr("src","${contextPath}/resources/images/image_server.png"); break;
	case "E04003" : 
		$("#typeImage").attr("src","${contextPath}/resources/images/image_smart.png");
		$(".equipment_position").hide();
		$("#ipAddressTr").hide();
		break;
	case "E04004" : $("#typeImage").attr("src","${contextPath}/resources/images/image_etc.png"); break;
	}
	
	if(type == "E04003"){
		$(".equipment_position").hide();
		$("#ipAddressTr").hide();
		$("#macTr").show();
		$("#osTr").show();
	} else if(type == "E04004"){
		$(".equipment_position").show();
		$("#ipAddressTr").hide();
		$("#macTr").hide();
		$("#osTr").hide();
	} else {
		$(".equipment_position").show();
		$("#ipAddressTr").show();
		$("#macTr").show();
		$("#osTr").show();
	}
}

function modifySubmit() {
	if($("#equipType").val() == "E04004") {
		$("#macTr").val("");
		$("#equipIp").val("");
		$("#osTr").val("");
	}
	
	var param = $("#equipModifyForm").serializeObject();
	var url = uri.serverUrl + uri.equipUpdateUrl;
	sendRequestJson(url, param, equipUpdateSuccess, error);
}

function equipUpdateSuccess(res) {
	alert(msg.equipUpdate);
	location.reload();
}

function equipPowerCall(obj) {
	var url = uri.serverUrl + uri.equipPowerStateUrl;
	var pParam = {
		equipNo : no,
		powerStateCd : $(obj).val()
	};
	
	sendRequestJson(url, pParam, equipPowerSuccess, error);
}

function equipPowerSuccess(res) {
	alert(msg.equipPowerChange);
}

</script>
<form id="equipModifyForm">
<input type="hidden" value="${no}" name="equipNo" />
<div id="mainLeftLayer" class="mainBox equipment">
	<dl>
		<dt>
			<h2 class="equipName">애플 맥 프로</h2>
		</dt>
		<dd class="tc">
			<img class="equipmentIamage" id="typeImage" src="${contextPath}/resources/images/image_pc.png" />
			<div class="equipment_position">
				<p>설치위치 : 
					<span id="facilityName" class="detailView">창작지원 1실</span>
					<select class="input_red modifyView" name="facilityNo">
						<option value="FC0000000856">녹음스튜디오</option>
						<option value="FC0000000852">창작지원1실</option>
						<option value="FC0000000853">창작지원2실</option>
						<option value="FC0000000854">창작지원3실</option>
						<option value="FC0000000855">창작지원4실</option>
						<option value="FC0000000858">서버실</option>
						<option value="FC0000000857">매체적합성테스트실</option>
						<option value="FC0000000859">회의실</option>
					</select>
				</p>
				<div id="map"></div>
			</div>
		</dd>
	</dl>
</div>
<div id="mainRightLayer" class="equipment" >
	<table id="memberReserveList" class="noClick">
		<colgroup>
			<col width="30%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr id="equipPowerTr">
				<td>장비전원</td>
				<td>
					<select id="equipPower" class="input_red" onchange="equipPowerCall(this)">
						<option value="E05001">기본설정</option>
						<option value="E05002">상시운영</option>
						<option value="E05003">끄기</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>장비유형</td>
				<td id="equipType" class="detailView">일반PC</td>
				<td class="modifyView">
					<select class="input_red" name="equipType" onchange="typeImageChange(this)">
						<option value="E04001">PC</option>
						<option value="E04002">서버</option>
						<option value="E04004">기타</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>장비명</td>
				<td id="equipName" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipName" class="input_red" /></td>
			</tr>
			<tr>
				<td>모델명</td>
				<td id="equipModel" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipModel" class="input_red" /></td>
			</tr>
			<tr id="ipAddressTr">
				<td>IP 주소</td>
				<td id="equipIp" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipIp" class="input_red" /></td>
			</tr>
			<tr id="macTr">
				<td>MAC</td>
				<td id="equipMac" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipMac" class="input_red" /></td>
			</tr>
			<tr id="osTr">
				<td>OS</td>
				<td id="equipOs" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipOs" class="input_red" /></td>
			</tr>
			<tr>
				<td>장비사양</td>
				<td id="equipSpec" class="detailView"></td>
				<td class="modifyView">
					<textarea name="equipSpec" class="input_red" style="width:340px; height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<td>장비설명</td>
				<td id="equipExplain" class="detailView"></td>
				<td class="modifyView">
					<textarea name="equipExplain" class="input_red" style="width:340px; height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<td>기타</td>
				<td id="equipMemo" class="detailView"></td>
				<td class="modifyView">
					<textarea name="equipMemo" class="input_red" style="width:340px; height:100px;"></textarea>
				</td>
			</tr>
			<tr>
				<td>사용여부</td>
				<td id="equipState" class="detailView"></td>
				<td class="modifyView">
					<select class="input_red" name="equipState">
						<option value="E01001">정상</option>
						<option value="E01002">고장</option>
						<option value="E01003">수리중</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>최종점검일</td>
				<td id="equipLastCheckDate"></td>
			</tr>
		</tbody>					
	</table>
	<div class="table_bottom">
		<div class="fr">
			<input type="button" value="장비 목록" class="btn_normal_red detailView" onclick="goPage('${contextPath}/equipment/list.do')"/>
			<input type="button" value="점검" class="btn_normal_red detailView" onclick="checkPopup()"/>
			<input type="button" value="사용이력" id="usedBtn" class="btn_normal_red detailView" onclick="usedListCall()"/>
			<input type="button" value="수정" class="btn_normal_red detailView" onclick="modifyChange()"/>
			
			<input type="button" value="수정 완료" class="btn_normal_red modifyView" onclick="modifySubmit()"/>
			<input type="button" value="수정 취소" class="btn_normal_red modifyView" onclick="detailChange()"/>
			
			<input type="button" value="삭제" class="btn_normal_red" onclick="equipDel()"/>
		</div>
	</div>
</div>
</form>