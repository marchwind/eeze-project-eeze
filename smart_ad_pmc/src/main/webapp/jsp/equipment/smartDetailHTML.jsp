<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	$(".modifyView").hide();
	equipGetCall();
});

function popupResultCall(){
	equipGetCall();
	pageListCall();
}

function equipGetCall(){
	var url = uri.serverUrl + uri.equipGetUrl;
	sendRequestJson(url, {equipNo : no}, equipGetSuccess, error);
}

function equipGetSuccess(res) {
	$(".equipName").text(res.info.equipName);
	$("#equipModel").text(res.info.equipModel);
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
	
	if(res.info.equipRentalNo == null || res.info.equipRentalNo == "") {
		$("#equipRentYn").html('대여가능<input type="button" value="대여" class="btn_small2_red fr"  data-no="'+res.info.equipNo+'" onclick="rentPopup(this)"/>');
	} else {
		$("#equipRentYn").html('대여중<input type="button" value="반환" class="btn_small2_gray fr"  data-no="'+res.info.equipNo+'" data-rentno="'+res.info.equipRentalNo+'" onclick="returnPopup(this)"/>');
	}
}

function changeView() {
	$(".detailView").show();
	$(".modifyView").hide();
}

function changeModify() {
	
	$("input[name=equipName]").val($(".equipName").text());
	$("input[name=equipModel]").val($("#equipModel").text());
	$("input[name=equipMac]").val($("#equipMac").text());
	$("input[name=equipOs]").val($("#equipOs").text());
	$("textarea[name=equipSpec]").val($("#equipSpec").text().replaceAll("<br>","\n").replaceAll("</br>","\n"));
	$("textarea[name=equipExplain]").val($("#equipExplain").text().replaceAll("<br>","\n").replaceAll("</br>","\n"));
	$("textarea[name=equipMemo]").val($("#equipMemo").text().replaceAll("<br>","\n").replaceAll("</br>","\n"));
	$("select[name=equipState]").val($("#equipState").data("cd"));
	
	$(".detailView").hide();
	$(".modifyView").show();
}

function smartDel() {
	if(confirm(msg.equipDelConfirm)){
		var url = uri.serverUrl + uri.equipDelUrl;
		sendRequestJson(url, {equipNo : no}, equipDelSuccess, error);	
	}
}

function equipDelSuccess() {
	alert(msg.equipDel);
	goPage('${contextPath}/equipment/list.do?type=smart');
}

function rentPopup(obj) {
	sendRequestHtml('${contextPath}/equipment/smartRentHTML.do?no='+$(obj).data("no"), showPopup, error);
}

function returnPopup(obj) {
	sendRequestHtml('${contextPath}/equipment/smartReturnHTML.do?no='+$(obj).data("no")+"&equipRentalNo="+$(obj).data("rentno"), showPopup, error);
}

function modifySubmit() {
	var param = $("#smartModifyForm").serializeObject();
	var url = uri.serverUrl + uri.equipUpdateUrl;
	sendRequestJson(url, param, equipUpdateSuccess, error);
}

function equipUpdateSuccess(res) {
	aler(msg.equipUpdate);
	location.reload();
}

</script>
<div id="mainLeftLayer" class="mainBox equipment">
	<dl>
		<dt>
			<h2 class="equipName">아이폰 4S</h2>
		</dt>
		<dd class="tc">
			<img class="equipmentIamage" src="${contextPath}/resources/images/image_smart.png" />
		</dd>
	</dl>
</div>
<div id="mainRightLayer" class="equipment" >
	<form id="smartModifyForm">
	<input type="hidden" value="E04003" name="equipType" />
	<input type="hidden" value="${no}" name="equipNo" />
	<table id="memberReserveList" class="noClick">
		<colgroup>
			<col width="30%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<td>장비유형</td>
				<td>스마트 장비</td>
			</tr>
			<tr>
				<td>장비명</td>
				<td class="equipName detailView"></td>
				<td class="modifyView"><input type="text" name="equipName" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>모델명</td>
				<td id="equipModel" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipModel" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>MAC</td>
				<td id="equipMac" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipMac" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>OS</td>
				<td id="equipOs" class="detailView"></td>
				<td class="modifyView"><input type="text" name="equipOs" value="" class="input_red" /></td>
			</tr>
			<tr>
				<td>장비스팩</td>
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
				<td>대여여부</td>
				<td id="equipRentYn">
					
				</td>
				
			</tr>
		</tbody>					
	</table>
	</form>
	<div class="table_bottom">
		<div class="fr">
			<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/equipment/list.do?type=smart')"/>
			<input type="button" value="수정" class="btn_normal_red detailView" onclick="changeModify()"/>
			<input type="button" value="삭제" class="btn_normal_red detailView" onclick="smartDel()"/>
			
			<input type="button" value="수정완료" class="btn_normal_red modifyView" onclick="modifySubmit()"/>
			<input type="button" value="취소" class="btn_normal_red modifyView" onclick="changeView()()"/>
		</div>
	</div>
	
</div>
		