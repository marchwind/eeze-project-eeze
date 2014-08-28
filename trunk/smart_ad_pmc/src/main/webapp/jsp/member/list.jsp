<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	pageListCall();
})

function pageListCall() {
	var param = pageValue;
	param['searchKeyword'] = $("#searchKeyword").val();
	param['searchKey'] = $("#searchKey").val();
	
	var url = uri.serverUrl + uri.userListUrl;
	sendRequestJson(url, param, listSuccess, error);
}

function listSuccess(res){
	var $table = $("#memberList tbody");
	$table.empty();
	
	if(res.result.resultCode == "0000"){
		
		var cnt = 1 + ((pageValue.currentPage - 1) * pageValue.unitPerPage);
		
		$(res.list).each(function(){
			
			var state = "일반회원";
			switch(this.userStatus){
			case "U01100" : state = "일반회원"; break;
			case "U01110" : state = "탈퇴회원"; break;
			case "U01120" : state = "차단회원"; break;
			}
			
			var tag = '<tr onclick="goPage(\'${contextPath}/member/detail.do?no='+this.userNo+'\')">' +
					'<td>'+cnt+'</td>' +
					'<td>'+this.userName+'</td>' +
					'<td>'+this.userId+'</td>' +
					'<td>'+this.userCellPhone+'</td>' +
					'<td>'+this.enterpriseName+'</td>' +
					'<td>'+state+'</td>' +
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

function keyEvent(e) {
	e = e || window.event;
	log(e.keyCode);
	if(e.keyCode == 13) {
		pageListCall();
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
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>회원관리</h1>
		</div>
		<div id="listTop">
			<div id="searchForm" class="fl">
				<select id="searchKey" class="search_select">
					<option value="1">아이디</option>
					<option value="2">회원명</option>
					<option value="3">소속</option>
				</select>
				<input type="text" placeholder="회원검색" class="search_icon" id="searchKeyword" onkeyup="keyEvent()"/>
				<input type="button" value="검색" class="btn_small_red" onclick="pageListCall()" />
			</div>
			<input type="button" value="회원등록 " class="btn_normal_red fr" onclick="goPage('${contextPath}/member/join.do')"/>
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
						<th>NO</th>
						<th>회원명</th>
						<th>아이디</th>
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