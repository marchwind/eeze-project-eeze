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
	var url = uri.serverUrl + uri.dataListUrl;
	sendRequest("listForm", url, dataList, error);
}

function dataList(res) {
	log(res);
	var $table = $("#listTable");
	$table.empty();
	
	var tag = '<colgroup><col width="5%"/><col width="*"/><col width="20%"/><col width="10%"/><col width="10%"/></colgroup>' +
	  '<tr><th>번호</th><th>제목</th><th>등록일</th><th>첨부파일</th><th>조회수</th></tr>';
	
	if(res.result.resultCode == "0000"){
		
		var cnt = res.page.totalCount - (res.page.currentPage - 1)*res.page.unitPerPage;
		
		var dt = new Date();
		var today = dt.getTime();
		
		$(res.list).each(function(){
			tag += '<tr>';
			tag += '<td>'+cnt+'</td>';
			tag += '<td class="tableContent">';
			tag += '<a href="${contextPath}/guide/dataDetail.do?no='+this.archiveNo+'" title="'+this.archiveSubject+'">'+this.archiveSubject+'</a>';
			if((today - this.registeDate) <= 86400000) {
				tag += '<img src="${contextPath}/resources/images/common/new_icon.png" />';	
			}
    		tag += '</td>';
    		tag += '<td>'+numberToDate(this.registeDate)+'</td>';
    		
    		if(this.attachedFileName != null){
    			tag += '<td><img src="${contextPath}/resources/images/common/file_icon.png" /></td>';	
    		} else {
    			tag += '<td></td>';
    		}
    		
    		tag += '<td>'+this.viewCount+'</td>';
    		tag += '</tr>';
    		
    		cnt--;
		});
		
		$table.html(tag);
		
		page(res.page.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400"){
		tag += '<tr><td colspan="5">자료가 없습니다.</td></tr>';
		$table.html(tag);
		page(res.page.currentPage, 0);	
	} else {
		error();
	}
}

function searchProccess() {
	var tag = $("#searchTag").val();
	if(tag == "archiveContent"){
		$("#archiveContent").val($("#searchTxt").val());
		$("#archiveSubject").val("");
	} else {
		$("#archiveSubject").val($("#searchTxt").val());
		$("#archiveContent").val("");
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
	alert(msg.dataListFail);
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
                    <span class="subPageTitleRight">/ 자료실</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- 자료실</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="listContainer">
                	<div id="listSearch">
                		<select id="searchTag">
                			<option value="archiveSubject">제목</option>
                			<option value="archiveContent">내용</option>
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
	                	<input type="hidden" name="archiveSubject" id="archiveSubject" value="" />
	                	<input type="hidden" name="archiveContent" id="archiveContent" value="" />
	                </form>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>