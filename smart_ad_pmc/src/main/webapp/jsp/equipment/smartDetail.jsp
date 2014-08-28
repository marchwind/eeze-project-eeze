<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var no = "${no}"
$(document).ready(function(){
	detailLoad();
	pageListCall();
});

function detailLoad(){
	sendRequestHtml('${contextPath}/equipment/smartDetailHTML.do?no='+no, htmlCallSuccess, error);
}

function htmlCallSuccess(res){
	$("#equipContainer").html(res);
}

function pageListCall() {
	var param = pageValue;
	param["equipNo"] = no;
	var url = uri.serverUrl + uri.equipSmartHistoryListUrl;
	sendRequestJson(url, param, smartHistoryListSuccess, error);
}

function smartHistoryListSuccess(res) {
	var $table = $("#smartRentHistoryList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			var tag = '<tr>' +
					'<td class="tc">' + numberToFull(this.rentalDate) + '</td>' + 
					'<td>' + this.username + '</td>' + 
					'<td>' + this.enterpriseName + '</td>' +
					'<td>' + this.checkContent + '</td>' +
					'</tr>';
					
			$table.append(tag);
			
		});
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		$table.html('<tr><td class="tc" colspan="5">데이터가 없습니다.</td></tr>');
		page(pageValue.currentPage, 0);
	}
}


function error() {
	alert(msg.equipmentError);
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 장비상세</h1>
		</div>
		<div id="equipContainer" class="mainTable">
			
		</div>
		
		<div class="title clear">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 대여이력</h1>
		</div>
		<div class="mainTable">
			<table id="smartRentHistoryList" class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="10%" />
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>대여일시</th>
						<th>대여자 명</th>
						<th>소속</th>
						<th>대여 후 상태</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<div class="page">
				<ul>
					<li> << </li>
					<li class="on">1</li>
					<li>2</li>
					<li> >> </li>
				</ul>
			</div>
		</div>
		<div class="popup">
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 