<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var nfcTagId = "${nfcTagId}";

$(document).ready(function(){
	var url = uri.serverUrl + uri.nfcUrl;
	sendRequestJson(url, {nfcTagId : nfcTagId}, nfcCallSuccess, error);
});

function nfcCallSuccess(res) {
	
	if(res.result.resultCode == "0000"){
		var type = "";
		switch(res.info.equipType){
		case "E04001" : type = "PC"; break;
		case "E04002" : type = "서버"; break;
		case "E04003" : type = "스마트단말"; break;
		case "E04004" : type = "기타"; break;
		}
		
		$("#facilityName").text(res.info.facilityName);
		$("#equipType").text(type);
		$("#equipName").text(res.info.equipName);
		$("#equipModel").text(res.info.equipModel);
		$("#equipIp").text(res.info.equipIp);
		$("#equipMac").text(res.info.equipMac);
		$("#equipOs").text(res.info.equipOs);
		
		var spec = res.info.equipSpec;
		if(spec != null){
			spec = spec.replaceAll("\n","<br/>");
		}
		$("#equipSpec").text(spec);
		
		var memo = res.info.equipMemo;
		if(memo != null){
			memo = memo.replaceAll("\n","<br/>");
		}
		$("#equipMemo").text(memo);
		
	} else if(res.result.resultCode == "0400") {
		alert("해당하는 장비가 없습니다.");
	}
	
}

function error() {
	alert("NFC정보 로드에 실패 했습니다.\n다시 시도해 주세요.");
}

</script>
</head>
<body>

<div id="wrap" class="nfc">
	<div id="header">
		<img src="${contextPath}/resources/images/nfc_logo.png" />
		<h2>DEVICE LIST & SPEC</h2>
	</div>
	<div id="content">
		<ul>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_1.png" /></span>
				<dl>
					<dt>설치위치</dt>
					<dd id="facilityName"></dd>
				</dl>
			</li>
			
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_2.png" /></span>
				<dl>
					<dt>장비유형</dt>
					<dd id="equipType"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_3.png" /></span>
				<dl>
					<dt>장비명</dt>
					<dd id="equipName"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_4.png" /></span>
				<dl>
					<dt>모델명</dt>
					<dd id="equipModel"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_5.png" /></span>
				<dl>
					<dt>장비IP</dt>
					<dd id="equipIp"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_6.png" /></span>
				<dl>
					<dt>MAC</dt>
					<dd id="equipMac"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_7.png" /></span>
				<dl>
					<dt>OS</dt>
					<dd id="equipOs"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_8.png" /></span>
				<dl>
					<dt>스펙</dt>
					<dd id="equipSpec"></dd>
				</dl>
			</li>
			<li>
				<span class="icon"><img src="${contextPath}/resources/images/nfc_icon_9.png" /></span>
				<dl>
					<dt>기타</dt>
					<dd id="equipMemo"></dd>
				</dl>
			</li>
		</ul>
	</div>
</div>

</body>
</html> 