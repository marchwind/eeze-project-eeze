<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	pageValue.currentPage = 1;
	pageListCall();
});

function pageListCall() {
	var param = pageValue;
	var url = uri.serverUrl + uri.qnaListUrl;
	sendRequestJson(url, param, qnaListSuccess, error);
}

function qnaListSuccess(res) {
	var $table = $("#inquiryList tbody");
	$table.empty();
	
	var cnt = 1;
	
	$(res.list).each(function(){
		var aYn = this.answerYn;
		if(aYn == "N") {
			aYn = "대기중";
		} else {
			aYn = "완료";
		}
		
		var manager = this.answerManagerId;
		if(manager == "" || manager == null){
			manager = "";
		} 
		
		var tag = '<tr onclick="goPage(\'${contextPath}/cms/inquiryDetail.do?no='+this.qnaNo+'\')">' +
				'<td class="tc">'+cnt+'</td>' +
				'<td>'+this.querySubject+'</td>' +
				'<td class="tc">'+numberToFull(this.queryDate)+'</td>' +
				'<td class="tc">'+((this.queryEmail == null) ? "" : this.queryEmail)+'</td>' +
				'<td class="tc">'+manager+'</td>' +
				'<td class="tc">'+aYn+'</td>' +
				'</tr>';
		
		$table.append(tag);
		
		cnt++;
	});
	page(pageValue.currentPage, res.page.totalPage);
}

</script>
<table id="inquiryList">
	<colgroup>
		<col width="5%" />
		<col width="*" />
		<col width="20%" />
		<col width="20%" />
		<col width="10%" />
		<col width="10%" />
	</colgroup>
	<thead>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>날짜</th>
			<th>문의자이메일</th>
			<th>등록자</th>
			<th>답변여부</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td class="tl">예약이 안돼요.</td>
			<td>2014/07/08 15:19</td>
			<td>askdfjl@naver.com</td>
			<td>admin</td>
			<td>완료</td>
		</tr>
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