<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svg.js"></script>
<script type="text/javascript">
var no = "${no}";
$(document).ready(function() {
	detailHTMLCall();
	pageListCall();
});

function detailHTMLCall() {
	sendRequestHtml('${contextPath}/equipment/detailHTML.do?no=' + no, pageCallSuccess, error);
}

function pageCallSuccess(res) {
	$("#detailContainer").html(res);
}

function pageListCall(){
	var param = pageValue;
	param['equipNo'] = no; 
	var url = uri.serverUrl + uri.equipCheckHistoryUrl;
	sendRequestJson(url, param, equipHistoryListSuccess, error);
}

function equipHistoryListSuccess(res) {
	var $table = $("#checkList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			var tag = '<tr>' +
					'<td class="tc">' + numberToFull(this.checkDate) + '</td>' + 
					'<td>' + this.checker + '</td>' +
					'<td>' + this.checkContent + '</td>' +
					'</tr>';
					
			$table.append(tag);
			
		});
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		$table.html('<tr><td class="tc" colspan="3">데이터가 없습니다.</td></tr>');
		page(pageValue.currentPage, 0);
	}
}

function error(){
	alert(msg.equipDetailFail);
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
		<div id="detailContainer" class="mainTable">
			
		</div>
		
		<div class="title clear">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 점검이력</h1>
		</div>
		<div class="mainTable">
			<table id="checkList" class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>점검일시</th>
						<th>점검자ID</th>
						<th>점검내용</th>
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
		
	</div>
	<%@ include file="../common/footer.jsp" %>
	<div class="popup">
	</div>
</div>

</body>
</html> 