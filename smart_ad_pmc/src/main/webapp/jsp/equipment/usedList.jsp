<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var url = uri.serverUrl + uri.equipGetUrl;
	sendRequestJson(url, {equipNo : "${no}"}, equipGetSuccess, error);
	
	pageListCall();	
});

function equipGetSuccess(res) {
	$("#equipName").text(res.info.equipName);
	$("#facilityName").text(res.info.facilityName);
	$("#equipIp").text(res.info.equipIp);
	$("#equipOs").text(res.info.equipOs);
}

function pageListCall(){
	var param = pageValue;
	param['equipNo'] = "${no}"; 
	var url = uri.serverUrl + uri.equipUsedHistoryUrl;
	sendRequestJson(url, param, equipUsedListSuccess, error);
}

function equipUsedListSuccess(res){
	var $table = $("#usedList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			var tag = '<tr>'+
					'<td class="tc">' + numberToFull(this.usedDate) + '</td>' +
					'<td>' + this.usedSoftware + '</td>'+
					'</tr>';
			$table.append(tag);
			
		});
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		$table.html('<tr><td class="tc" colspan="2">데이터가 없습니다.</td></tr>');
		page(pageValue.currentPage, 0);
	}
}

function error(){
	alert(msg.equipmentError);
}

function back() {
	window.history.back();
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 장비상세 > 사용이력</h1>
		</div>
		<div class="mainTable">
			<table  class="noClick">
				<colgroup>
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
				</colgroup>
				<thead>
					<tr>
						<th>장비명</th>
						<th>설치시설</th>
						<th>IP 주소</th>
						<th>OS</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="tc" id="equipName">MAXPro</td>
						<td class="tc" id="facilityName">창작지원1실</td>
						<td class="tc" id="equipIp">192.168.10.12</td>
						<td class="tc" id="equipOs">MAC</td>
					</tr>
				</tbody>
			</table>
			<table id="usedList" class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>사용일시</th>
						<th>사용 프로그램</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="tc">2014/07/30 12:00</td>
						<td>PhotoShop CC, Chrome, Safari</td>
					</tr>
				</tbody>
			</table>
			<div class="table_bottom_overlap_btn">
				<input type="button" value="뒤로" class="btn_normal_red" onclick="back()"/>
			</div>
			<div class="page">
				<ul>
					<li> << </li>
					<li class="on">1</li>
					<li>2</li>
					<li> >> </li>
				</ul>
			</div>
		</div>
		
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 