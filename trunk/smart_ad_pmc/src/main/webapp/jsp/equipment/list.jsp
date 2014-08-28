<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
</head>
<script type="text/javascript">
var equipState = "";
var facilityNo = "";
var type = "${type}";

$(document).ready(function(){
	
	selectCall();
})

function selectCall() {
	equipState = $("#equipState").val();
	facilityNo = $("#facilityNo").val();
	
	if(type == "smart") {
		$("#tab_smart").trigger("click");
	} else {
		
		$("#tab_common").trigger("click");
	} 
	
}

function commonEquipCall(obj) {
	tabEvent(obj);
	
	type = "common";
	
	$("#facilityNo").show();
	sendRequestHtml('${contextPath}/equipment/commonList.do', listCallSuccess, error);
}

function smartEquipCall(obj) {
	tabEvent(obj);
	
	type = "smart";
	
	$("#facilityNo").hide();
	sendRequestHtml('${contextPath}/equipment/smartList.do', listCallSuccess, error);
}

function listCallSuccess(res){
	$(".mainTable").html(res);
}

function tabEvent(obj) {
	$(".btn_squre").removeClass("on");
	$(obj).addClass("on");
	pageValue.currentPage = 1;
}


function error(){
	alert(msg.equipmentError);
}

</script>
<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리</h1>
		</div>
		<div id="listTop">
			<div id="tabSelect">
				<ul>
					<li><input type="button" id="tab_common" value="일반장비" class="btn_squre on" onclick="commonEquipCall(this)" /></li>
					<li><input type="button" id="tab_smart" value="스마트장비" class="btn_squre" onclick="smartEquipCall(this)" /></li>
				</ul>
			</div>
			<div class="fr">
				<select class="input_red" id="facilityNo" name="facilityNo" onchange="selectCall()">
					<option value="">전체</option>
					<option value="FC0000000856">녹음스튜디오</option>
					<option value="FC0000000852">창작지원1실</option>
					<option value="FC0000000853">창작지원2실</option>
					<option value="FC0000000854">창작지원3실</option>
					<option value="FC0000000855">창작지원4실</option>
					<option value="FC0000000858">서버실</option>
					<option value="FC0000000857">매체적합성테스트실</option>
					<option value="FC0000000859">회의실</option>
				</select>
				<select id="equipState" class="input_red" onchange="selectCall()">
					<option value="">전체</option>
					<option value="E01001">정상</option>
					<option value="E01002">고장</option>
					<option value="E01003">수리중</option>
				</select>
			</div>
		</div>
		<div class="mainTable">
			
		</div>
		<div class="popup">
			
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 