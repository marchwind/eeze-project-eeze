<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">

function typeImageChange(obj){
	switch($(obj).val()){
	case "E04001" : $("#typeImage").attr("src","${contextPath}/resources/images/image_pc.png"); break;
	case "E04002" : $("#typeImage").attr("src","${contextPath}/resources/images/image_server.png"); break;
	case "E04003" : 
		$("#typeImage").attr("src","${contextPath}/resources/images/image_smart.png");
		$(".equipment_position").hide();
		$("#ipAddressTr").hide();
		break;
	case "E04004" : $("#typeImage").attr("src","${contextPath}/resources/images/image_etc.png"); break;
	}
	
	if($(obj).val() == "E04003"){
		$(".equipment_position").hide();
		$("#ipAddressTr").hide();
		$("#macTr").show();
		$("#osTr").show();
	} else if($(obj).val() == "E04004"){
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

function checkSubmit() {
	if($("#equipType").val() == "E04003"){
		$("#facilityNo").val("FC0000000857");
		$("#equipIp").val("");
	} else if($("#equipType").val() == "E04004") {
		$("#macTr").val("");
		$("#equipIp").val("");
		$("#osTr").val("");
	}
	
	var param = $("#equipForm").serializeObject();
	var url = uri.serverUrl + uri.equipAddUrl;
	sendRequestJson(url, param, addSuccess, error);
}

function addSuccess(res) {
	alert(msg.equipAdd);
	goPage('${contextPath}/equipment/list.do')
}

function error(){
	alert(msg.equipmentError);
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 장비등록</h1>
		</div>
		<div class="mainTable">
			<form id="equipForm">
			<div id="mainLeftLayer" class="mainBox equipment">
				<dl>
					<dt>
						<h2>장비 추가</h2>
					</dt>
					<dd class="tc">
						<img id="typeImage" class="equipmentIamage" src="${contextPath}/resources/images/image_pc.png" />
						<div class="equipment_position">
							<p>설치위치 : 
								<select class="input_red" id="facilityNo" name="facilityNo">
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
						</div>
					</dd>
				</dl>
			</div>
			<div id="mainRightLayer" class="equipment" >
				<table id="memberReserveList">
					<colgroup>
						<col width="30%" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<td>장비유형</td>
							<td>
								<select class="input_red" id="equipType" name="equipType" onchange="typeImageChange(this)">
									<option value="E04001">PC</option>
									<option value="E04002">서버</option>
									<option value="E04003">스마트 단말</option>
									<option value="E04004">기타</option>
								</select>
							</td>
						</tr>						
						<tr>
							<td>장비명</td>
							<td><input type="text" id="equipName" name="equipName" value="" class="input_red" /></td>
						</tr>
						<tr>
							<td>모델명</td>
							<td><input type="text" id="equipModel" name="equipModel" value="" class="input_red" /></td>
						</tr>
						<tr id="ipAddressTr">
							<td>IP주소</td>
							<td><input type="text" id="equipIp" name="equipIp" value="" class="input_red" /></td>
						</tr>
						<tr id="macTr">
							<td>MAC</td>
							<td><input type="text" id="equipMac" name="equipMac" value="" class="input_red" /></td>
						</tr>
						<tr id="osTr">
							<td>OS</td>
							<td><input type="text" id="equipOs" name="equipOs" value="" class="input_red" /></td>
						</tr>
						<tr>
							<td>장비사양</td>
							<td>
								<textarea id="equipSpec" name="equipSpec" class="input_red" style="width:283px; height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td>장비설명</td>
							<td>
								<textarea id="equipExplain" name="equipExplain" class="input_red" style="width:283px; height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td>기타</td>
							<td>
								<textarea id="equipMemo" name="equipMemo" class="input_red" style="width:283px; height:100px;"></textarea>
							</td>
						</tr>
					</tbody>					
				</table>
				<div class="table_bottom">
					<div class="fr">
						<input type="button" value="등록완료" class="btn_normal_red" onclick="checkSubmit()"/>
						<input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/equipment/list.do')"/>
					</div>
				</div>
				
			</div>
			</form>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 