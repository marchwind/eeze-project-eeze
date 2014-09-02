<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svg.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svgdom.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svgfilter.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svggraph.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svgplot.js"></script>
<script type="text/javascript">
var no = "";
$(document).ready(function(){
	$("#searchKey").val("${no}");
	selectSubmit();
});

function selectSubmit(){
	no = $("#searchKey").val();
	detailLoad();
	pageListCall();
}

function detailLoad(){
	viewPhoto();
	sendRequestHtml('${contextPath}/facility/detailHTML.do', htmlCallSuccess, error);
}

function modifyLoad(){
	sendRequestHtml('${contextPath}/facility/modifyHTML.do', htmlCallSuccess, error);
}

function htmlCallSuccess(res){
	$("#facility_table_form").html(res);
}

function pageListCall() {
	var param = pageValue;
	param["facilityNo"] = no;
	var url = uri.serverUrl + uri.facilityHistoryLsitUrl;
	sendRequestJson(url, param, facilityHistoryListSuccess, error);
}

function facilityHistoryListSuccess(res){
	var $table = $("#facilityHistoryList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			var tag = '<tr>' +
					'<td class="tc">' + numberToFull(this.visitDate) + '</td>' + 
					'<td class="tc">' + this.userName + '</td>' + 
					'<td class="tc">' + this.userEnterpriseName + '</td>' +
					'<td class="tc">' + this.userEmail + '</td>' +
					'<td>' + ((this.etc == null) ? "" : this.etc) + '</td>' +
					'</tr>';
					
			$table.append(tag);
			
		});
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		$table.html('<tr><td class="tc" colspan="5">데이터가 없습니다.</td></tr>');
		page(pageValue.currentPage, 0);
	}
	
	
}

function error(){
	alert(msg.facilityError);
}

function viewPhoto(){
	var tag = "";
	var title = "";
	switch(no){
	case "FC0000000856" :
		title = "녹음스튜디오";
		tag += '<li><img src="${contextPath}/resources/images/facility/record_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/record_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/record_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/record_4.jpg" /></li>';
		break;
	case "FC0000000852" :
		title = "창작지원1실";
		tag += '<li><img src="${contextPath}/resources/images/facility/create1_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create1_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create1_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create1_4.jpg" /></li>';
		break;
	case "FC0000000853" :
		title = "창작지원2실";
		tag += '<li><img src="${contextPath}/resources/images/facility/create2_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create2_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create2_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create2_4.jpg" /></li>';
		break;
	case "FC0000000854" :
		title = "창작지원3실";
		tag += '<li><img src="${contextPath}/resources/images/facility/create3_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create3_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create3_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create3_4.jpg" /></li>';
		break;
	case "FC0000000855" :
		title = "창작지원4실";
		tag += '<li><img src="${contextPath}/resources/images/facility/create4_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create4_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create4_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/create4_4.jpg" /></li>';
		break;
	case "FC0000000857" :
		title = "매체적합성테스트실";
		tag += '<li><img src="${contextPath}/resources/images/facility/test_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/test_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/test_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/test_4.jpg" /></li>';
		break;
	case "FC0000000858" :	
		title = "서버실";
		tag += '<li><img src="${contextPath}/resources/images/facility/server_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/server_2.jpg" /></li>';
		break;
	case "FC0000000859" :
		title = "회의실";
		tag += '<li><img src="${contextPath}/resources/images/facility/con_1.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/con_2.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/con_3.jpg" /></li>'+
			'<li><img src="${contextPath}/resources/images/facility/con_4.jpg" /></li>';
		break;
	}
	
	$("#facilityTitle").text(title);
	$("#facility_list").html(tag);
}

</script>

</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>시설관리 > 시설정보</h1>
		</div>
		<div id="listTop">
			<div id="searchForm" class="fl" onchange="selectSubmit()">
				<select id="searchKey" class="input_red">
					<option value="FC0000000856">녹음스튜디오</option>
					<option value="FC0000000852">창작지원1실</option>
					<option value="FC0000000853">창작지원2실</option>
					<option value="FC0000000854">창작지원3실</option>
					<option value="FC0000000855">창작지원4실</option>
					<option value="FC0000000857">매체적합테스트실</option>
					<option value="FC0000000858">서버실</option>
					<option value="FC0000000859">회의실</option>
				</select>
			</div>
		</div>
		<div class="mainTable">
			<div class="mainBox facility">
				<dl>
					<dt>
						<h2 id="facilityTitle">녹음 스튜디오</h2>
					</dt>
					<dd>
						<div id="facility_list_Container">
							<ul id="facility_list">
								<li><img src="${contextPath}/resources/images/tmpPhoto.png" /></li>
								<li><img src="${contextPath}/resources/images/tmpPhoto.png" /></li>
								<li><img src="${contextPath}/resources/images/tmpPhoto.png" /></li>
								<!-- <li><input type="button" value="사진추가" class="btn_normal_red" /></li> -->
							</ul>
						</div>
					</dd>
				</dl>
				<div id="facility_table_form">
					
				</div>
			</div>
		</div>
		
		<div class="title clear">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>시설관리 > 시설 사용 이력</h1>
		</div>
		<div class="mainTable">
			<table id="facilityHistoryList" class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="15%" />
					<col width="15%" />
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>사용일시</th>
						<th>사용자 명</th>
						<th>소속</th>
						<th>이메일</th>
						<th>비고</th>
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

</div>

</body>
</html> 