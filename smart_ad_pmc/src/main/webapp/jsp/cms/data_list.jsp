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
	var url = uri.serverUrl + uri.dataListUrl;
	sendRequestJson(url, param, dataListSuccess, error);
}

function dataListSuccess(res) {
	if(res.result.resultCode = "0000"){
		var $table = $("#dataList tbody");
		var cnt = 1;
		
		$table.empty();
		$(res.list).each(function(){
			var tag = "<tr onclick='goDetail(\""+this.archiveNo+"\")'>" +
					"<td>" + cnt + "</td>" +
					"<td>" + this.archiveSubject + "</td>" + 
					"<td>" + numberToDate(this.registeDate) + "</td>" + 
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
	goPage("${contextPath}/cms/dataDetail.do?no=" + no);
}
</script>
<table id="dataList" class="tc">
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
<input type="button" value="자료실등록" class="btn_normal_red bottom_right_btn" onclick="goPage('${contextPath}/cms/dataReg.do')"/>