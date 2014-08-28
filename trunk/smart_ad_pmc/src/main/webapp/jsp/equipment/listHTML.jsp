<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	pageListCall();
});

function pageListCall() {
	pageValue.unitPerPage = 10;
	
	var param = pageValue;
	
	param['equipType'] = "1";
	param['equipState'] = equipState;
	param['facilityNo'] = facilityNo;
	
	var url = uri.serverUrl + uri.equipListUrl;
	sendRequestJson(url, param, commonListSuccess, error);
}

function commonListSuccess(res) {
	var $table = $("#commonList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			
			var type = "PC";
			switch(this.equipType) {
			case "E04001" : type = "PC"; break;
			case "E04002" : type = "서버"; break;
			case "E04004" : type = "기타"; break;
			}
			
			var state = "정상";
			switch(this.equipState) {
			case "E01001" : state = "정상"; break;
			case "E01002" : state = "고장"; break;
			case "E01003" : state = "수리중"; break;
			}
			
			var tag = '<tr onclick="goPage(\'${contextPath}/equipment/detail.do?no='+this.equipNo+'\')">' +
					'<td>'+type+'</td>' +
					'<td>'+this.equipName+'</td>' + 
					'<td>'+this.equipModel+'</td>' +
					'<td>'+this.equipIp+'</td>' +
					'<td>'+this.equipOs+'</td>' +
					'<td>'+this.facilityName+'</td>' +
					'<td>'+state+'</td>' +
					'</tr>';
					
			$table.append(tag);
			
		});
		
		page(pageValue.currentPage, res.page.totalPage);
		
	} else if(res.result.resultCode == "0400"){
		$table.html('<tr><td class="tc" colspan="7">데이터가 없습니다.</td></tr>')
		page(pageValue.currentPage, 0);
	}
}

</script>
<div>
	<table id="commonList" class="tc">
		<colgroup>
			<col width="10%" />
			<col width="15%" />
			<col width="*" />
			<col width="15%" />
			<col width="15%" />
			<col width="15%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th>장비유형</th>
				<th>장비명</th>
				<th>모델명</th>
				<th>IP주소</th>
				<th>OS</th>
				<th>설치시설</th>
				<th>가능여부</th>
			</tr>
		</thead>
		<tbody>			
		</tbody>
	</table>
</div>
<div class="table_bottom_overlap_btn fr">
	<input type="button" value="장비등록" class="btn_normal_red" onclick="goPage('${contextPath}/equipment/add.do')"/>
</div>
<div class="page">
	<ul>
		<li> << </li>
		<li class="on">1</li>
		<li>2</li>
		<li> >> </li>
	</ul>
</div>
		
