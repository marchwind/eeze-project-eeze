<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	pageValue.unitPerPage = 6;
	memberPageListCall();
});

function memberPageListCall() {
	var param = pageValue;
	param['searchKeyword'] = $("#searchKeyword").val();
	param['searchKey'] = $("#searchKey").val();
	
	var url = uri.serverUrl + uri.userListUrl;
	sendRequestJson(url, param, listSuccess, memberListError);
}

function listSuccess(res){
	var $table = $("#popupMemberList tbody");
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
			
			var tr = $('<tr onclick="memberListSelected(this)" class="popupMemberTr">');
			var td = '<td>'+this.userName+'</td>' +
					'<td>'+this.userId+'</td>' +
					'<td>'+this.userCellPhone+'</td>' +
					'<td>'+this.enterpriseName+'</td>' +
					'<td>'+state+'</td>';
			
			tr.data("userno", this.userNo);
			tr.data("username", this.userName);
			tr.data("userid", this.userId);
			tr.data("usercellphone", this.userCellPhone);
			tr.data("enterprisename", this.enterpriseName);
			tr.data("userStatus", state);
			tr.html(td);
			
			$table.append(tr);
		}); 
		
		page(pageValue.currentPage, res.page.totalPage, memberPageListCall, 'popupMemberPage');	
	} else if(res.result.resultCode == "0400"){
		$table.append('<tr><td colspan="5">검색 리스트가 없습니다.</td></tr>');
		page(pageValue.currentPage, 0, memberPageListCall, 'popupMemberPage');
	} else {
		memberListError();
	}
}

function memberListSelected(obj){
	$(".popupMemberTr").removeClass("on");
	$(obj).addClass("on");
}

function memberListError() {
	alert(msg.failUserList);
}

function memberPopupSearch(){
	pageValue.currentPage = 1;
	memberPageListCall();
}

function memnerKeyEvent(e){
	e = e || window.event;
	log(e.keyCode);
	if(e.keyCode == 13) {
		memberPopupSearch();
	}
}

</script>
<div id="listTop" class="clear">
	<div id="searchForm">
		<select id="searchKey" class="search_select">
			<option value="1">아이디</option>
			<option value="2">회원명</option>
			<option value="3">소속</option>
		</select>
		<input type="text" placeholder="회원검색" class="search_icon" id="searchKeyword" onkeyup="memnerKeyEvent()"/>
		<input type="button" value="검색" class="btn_small_red" onclick="memberPopupSearch()" />
	</div>
</div>
<table class="select" id="popupMemberList">
	<thead>
		<tr>
			<th>회원명</th>
			<th>아이디</th>
			<th>전화번호</th>
			<th>소속</th>
			<th>상태</th>
		</tr>
	</thead>
	<tbody>
		
	</tbody>
	
</table>
<div id="popupMemberPage" class="page">
	<ul>
		
	</ul>
</div>
