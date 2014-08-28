<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/commonLogin.jsp" %>
<script type="text/javascript">

$(document).ready(function(){
	listCall();
});

function listCall() {
	var url = uri.serverUrl + uri.qnaListUrl;
	sendRequest("listForm", url, qnaList, error);
}

function qnaList(res) {
	log(res);
	
	if(res.result.resultCode == "0000"){
		
		var tag = '<colgroup><col width="16%"/><col width="*"/><col width="16%"/><col width="16%"/></colgroup>' +
				  '<tr><th>번호</th><th>제목</th><th>문의일</th><th>문의상태</th></tr>';
		
		var cnt = res.page.totalCount - (res.page.currentPage - 1)*res.page.unitPerPage;
		var $table = $("#listTable");
		$table.empty();
		
		var dt = new Date();
		var today = dt.getTime();
		
		$(res.list).each(function(){
			tag += '<tr>';
			tag += '<td>'+cnt+'</td>';
			tag += '<td>'+this.querySubject+'</td>';
    		tag += '<td>'+numberToDate(this.queryDate)+'</td>';
    		if(this.answerYn == "Y") {
    			tag += '<td><input type="button" class="btn_8" value="답변보기" onclick="answerView(\''+this.qnaNo+'\')"/></td>';	
    		} else {
    			tag += '<td><input type="button" class="btn_8" value="문의보기" onclick="answerView(\''+this.qnaNo+'\')"/></td>';
    		}
    		tag += '</tr>';
    		
    		cnt--;
		});
		
		$table.html(tag);
		
		page(res.page.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		//alert(msg.qnaListEmpty);
	} else {
		error();
	}
}

function error() {
	alert(msg.qnaListFail);
}

function answerView(no){
	goPage('${contextPath}/member/inquiryDetail.do?no='+no);
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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_mypage.png" /></span>
                    <span class="subPageTitleRight">/ 1:1문의 답변확인</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 1:1문의</span>
                </span>
            </div>
            
            <div id="subPageCont">
            	<div id="listContainer">
                	<div id="listSearch">
                		<select>
                			<option>제목</option>
                			<option>내용</option>
                		</select>
                		<input type="text" />
                		<input type="button" />
                	</div>
	                <table id="listTable" class="guideContainer">
	                	<colgroup>
	                		<col width="16%"/>
	                		<col width="*"/>
	                		<col width="16%"/>
	                		<col width="16%"/>
	                	</colgroup>
	                	<tr>
	                		<th>번호</th>
	                		<th>제목</th>
	                		<th>문의일</th>
	                		<th>문의상태</th>
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
	                </form>
                </div>
            </div>
        </div>
	</div>
	
</div>
</body>
</html>