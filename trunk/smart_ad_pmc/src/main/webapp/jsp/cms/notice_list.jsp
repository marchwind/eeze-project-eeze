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
	var url = uri.serverUrl + uri.noticeListUrl;
	sendRequestJson(url, param, noticeListSuccess, error);
}

function noticeListSuccess(res) {
	if(res.result.resultCode = "0000"){
		var $table = $("#noticeList tbody");
		var cnt = 1;
		
		$table.empty();
		$(res.list).each(function(){
			var tag = "<tr onclick='goDetail(\""+this.notiNo+"\")'>" +
					"<td>" + cnt + "</td>" +
					"<td>" + this.notiSubject + "</td>" + 
					"<td>" + numberToDate(this.notiRegisteDate) + "</td>" + 
					"<td>ADMIN</td>" +
					"</tr>";
			
			$table.append(tag);
			cnt++;
		});
		
		page(pageValue.currentPage, res.page.totalPage);
	} else {
		error();
	}
}

function goDetail(no){
	goPage("${contextPath}/cms/noticeDetail.do?no=" + no);
}
</script>
<table id="noticeList" class="tc">
	<colgroup>
		<col width="10%" />
		<col width="*" />
		<col width="20%" />
		<col width="20%" />
	</colgroup>
	<thead>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>날짜</th>
			<th>등록자</th>
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
<input type="button" value="공지사항등록" class="btn_normal_red bottom_right_btn" onclick="goPage('${contextPath}/cms/noticReg.do')"/>