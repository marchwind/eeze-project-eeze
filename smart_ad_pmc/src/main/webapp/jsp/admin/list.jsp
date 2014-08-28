<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	pageListCall();
});

function pageListCall() {
	var param = pageValue;
	param['searchKeyword'] = $("#searchKeyword").val();
	param['searchKey'] = $("#searchKey").val();
	
	var url = uri.serverUrl + uri.managerListUrl;
	sendRequestJson(url, param, listSuccess, error);
}

function listSuccess(res){
	var $table = $("#memberList tbody");
	$table.empty();
	
	if(res.result.resultCode == "0000"){
		
		var cnt = 1 + ((pageValue.currentPage - 1) * pageValue.unitPerPage);
		
		$(res.list).each(function(){
			
			var state = "대기";
			switch(this.managerStatus){
			case "P02001" : state = "대기"; break;
			case "P02002" : state = "정상"; break;
			case "P02003" : state = "차단"; break;
			case "P02004" : state = "삭제"; break;
			}
			
			var tag = '<tr onclick="goPage(\'${contextPath}/admin/detail.do?no='+this.managerNo+'\')">' +
					'<td>'+this.managerName+'</td>' +
					'<td>'+this.managerId+'</td>' +
					'<td>'+this.managerEmail+'</td>' +
					'<td>'+this.phone+'</td>' +
					'<td>'+this.department+'</td>' +
					'<td class="tc">'+state+'</td>' +
					'</tr>';
					
			$table.append(tag);
			cnt++;
		}); 
		
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400"){
		var tag = '<tr><td colspan="6" class="tc">데이터가 없습니다.</td></tr>'
		$table.append(tag);
		page(pageValue.currentPage, 0);
	} else {
		error();
	}
}

function error() {
	alert(msg.failUserList);
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>계정관리</h1>
		</div>
		<div id="listTop">
			<!-- <div id="searchForm" class="fl">
				<input type="text" placeholder="관리자검색" class="search_icon"/>
				<input type="button" value="검색" class="btn_small_red" />
			</div> -->
			<input type="button" value="계정 등록 " class="btn_normal_red fr" onclick="goPage('${contextPath}/admin/join.do')"/>
		</div>
		<div class="mainTable">
			<table id="memberList">
				<colgroup>
					<col width="10%" />
					<col width="15%" />
					<col width="25%" />
					<col width="20%" />
					<col width="20%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th>담당자 명</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>연락처</th>
						<th>소속</th>
						<th>상태</th>
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