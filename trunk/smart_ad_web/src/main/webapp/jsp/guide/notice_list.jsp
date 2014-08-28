<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	listCall();
});

function listCall() {
	var url = uri.serverUrl + uri.noticeListUrl;
	sendRequest("listForm", url, noticeList, error);
}

function noticeList(res) {
	log(res);
	var $table = $("#listTable");
	$table.empty();
	
	var tag = '<colgroup><col width="5%"/><col width="*"/><col width="20%"/><col width="10%"/><col width="10%"/></colgroup>' +
	  '<tr><th>번호</th><th>제목</th><th>등록일</th><th>조회수</th></tr>';
	
	if(res.result.resultCode == "0000"){
		
		var cnt = res.page.totalCount - (res.page.currentPage - 1)*res.page.unitPerPage;
		
		var dt = new Date();
		var today = dt.getTime();
		
		$(res.list).each(function(){
			tag += '<tr>';
			tag += '<td>'+cnt+'</td>';
			tag += '<td class="tableContent">';
			tag += '<a href="${contextPath}/guide/noticeDetail.do?no='+this.notiNo+'" title="'+this.notiSubject+'">'+this.notiSubject+'</a>';
			if((today - this.notiRegisteDate) <= 86400000) {
				tag += '<img src="${contextPath}/resources/images/common/new_icon.png" />';	
			}
    		tag += '</td>';
    		tag += '<td>'+numberToDate(this.notiRegisteDate)+'</td>';
    		tag += '<td>'+this.notiConfirmCount+'</td>';
    		
    		//tag += '<td></td>';
    		tag += '</tr>';
    		
    		cnt--;
		});
		
		$table.html(tag);
		
		page(res.page.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400"){
		tag += '<tr><td colspan="5">공지사항이 없습니다.</td></tr>';
		$table.html(tag);
		page(res.page.currentPage, 0);	
	} else {
		error();
	}
}

function searchProccess() {
	var tag = $("#searchTag").val();
	if(tag == "notiContent"){
		$("#notiContent").val($("#searchTxt").val());
		$("#notiSubject").val("");
	} else {
		$("#notiSubject").val($("#searchTxt").val());
		$("#notiContent").val("");
	}
	
	listCall();
}

function keyEvent(e) {
	e = e || window.event;
	log(e.keyCode);
	if(e.keyCode == 13) {
		searchProccess();
	}
}

function error() {
	alert(msg.noticeListFail);
}

</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <div id="mainFrame" class="subFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_notice.png" /></span>
                    <span class="subPageTitleRight">/ 공지사항</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- 공지사항</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="listContainer">
                	<div id="listSearch">
                		<select id="searchTag">
                			<option value="notiSubject">제목</option>
                			<option value="notiContent">내용</option>
                		</select>
                		<input type="text" id="searchTxt" onkeyup="keyEvent()"/>
                		<input type="button" id="searchBtn" onclick="searchProccess()"/>
                	</div>
                	
	                <table id="listTable" class="guideContainer">
	                	<colgroup>
	                		<col width="5%"/>
	                		<col width="*"/>
	                		<col width="20%"/>
	                		<col width="10%"/>
	                		<col width="10%"/>
	                	</colgroup>
	                	<tr>
	                		<th>번호</th>
	                		<th>제목</th>
	                		<th>등록일</th>
	                		<th>조회수</th>
	                		<th>첨부파일</th>
	                	</tr>
	                	
	                </table>
	                <ul class="page">
	                	<li class="btn_6 prePage"><</li>
	                	<li class="btn_6 on">1</li>
	                	<li class="btn_6">2</li>
	                	<li class="btn_6 nextPage">></li>
	                </ul>
	                <form id="listForm">
	                	<input type="hidden" name="currentPage" id="currentPage" value="1" />
	                	<input type="hidden" id="totalPage" value="1" />
	                	<input type="hidden" name="unitPerPage" value="10" />
	                	<input type="hidden" name="notiSubject" id="notiSubject" value="" />
	                	<input type="hidden" name="notiContent" id="notiContent" value="" />
	                </form>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>