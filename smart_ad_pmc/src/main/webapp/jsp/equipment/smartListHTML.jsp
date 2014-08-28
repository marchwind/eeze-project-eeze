<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	pageListCall();
});

function popupResultCall(){
	pageListCall();
}

function pageListCall() {
	pageValue.unitPerPage = 10;
	
	var param = pageValue;
	param['equipType'] = "2";
	param['equipState'] = equipState;
	var url = uri.serverUrl + uri.equipListUrl;
	sendRequestJson(url, param, smartListSuccess, error);
}

function smartListSuccess(res) {
	var $table = $("#smartList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			
			var state = "정상";
			switch(this.equipState) {
			case "E01001" : state = "정상"; break;
			case "E01002" : state = "고장"; break;
			case "E01003" : state = "수리중"; break;
			}
			
			var rent = true;
			if(this.equipRentalNo != null && this.equipRentalNo != "") {
				rent = true;
			} else {
				rent = false;
			}
			
			var tag = '<tr>' +
					'<td>스마트단말</td>' +
					'<td onclick="goPage(\'${contextPath}/equipment/smartDetail.do?no='+this.equipNo+'\')">'+this.equipName+'</td>' + 
					'<td onclick="goPage(\'${contextPath}/equipment/smartDetail.do?no='+this.equipNo+'\')">'+this.equipModel+'</td>' +
					'<td>'+state+'</td>';
			if(rent){
				tag += '<td><input type="button" value="반환" class="btn_small2_gray" data-no="'+this.equipNo+'" data-rentno="'+this.equipRentalNo+'" onclick="returnPopup(this)"/></td>'; 
			} else {
				tag += '<td><input type="button" value="대여" class="btn_small2_red" data-no="'+this.equipNo+'" onclick="rentPopup(this)"/></td>';
			}
			tag += '</tr>';
					
			$table.append(tag);
			
		});
		
		page(pageValue.currentPage, res.page.totalPage);
		
	} else if(res.result.resultCode == "0400"){
		$table.html('<tr><td class="tc" colspan="7">데이터가 없습니다.</td></tr>')
		page(pageValue.currentPage, 0);
	}
}

function rentPopup(obj) {
	sendRequestHtml('${contextPath}/equipment/smartRentHTML.do?no='+$(obj).data("no"), showPopup, error);
}

function returnPopup(obj) {
	sendRequestHtml('${contextPath}/equipment/smartReturnHTML.do?no='+$(obj).data("no")+"&equipRentalNo="+$(obj).data("rentno"), showPopup, error);
}

</script>
<div>
	<table id="smartList" class="tc">
		<colgroup>
			<col width="10%" />
			<col width="20%" />
			<col width="*" />
			<col width="10%" />
			<col width="15%" />
		</colgroup>
		<thead>
			<tr>
				<th>장비유형</th>
				<th>장비명</th>
				<th>모델명</th>
				<th>가능여부</th>
				<th>대여여부</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>스마트장비</td>
				<td onclick="goPage('${contextPath}/equipment/detail.do')">아이폰 4S</td>
				<td onclick="goPage('${contextPath}/equipment/detail.do')">AIP5-R61W</td>
				<td>가능</td>
				<td><input type="button" value="대여" class="btn_small2_red" onclick="rendPopup(this)"/>
			</tr>
			<tr onclick="goPage('${contextPath}/equipment/detail.do')">
				<td>스마트장비</td>
				<td>아이폰 4S</td>
				<td>AIP5-R61W</td>
				<td>가능</td>
				<td><input type="button" value="반환" class="btn_small2_gray" onclick="returnPopup(this)" />
			</tr>
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
		