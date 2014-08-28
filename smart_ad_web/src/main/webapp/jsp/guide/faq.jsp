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
	var url = uri.serverUrl + uri.faqListUrl;
	sendRequest("listForm", url, faqList, error);
}

function faqList(res) {
	log(res);
	
	var $table = $("#listCont");
	$table.empty();
	
	var tag = '';
	
	$(res.list).each(function(){
		tag += '<li id="'+this.faqNo+'" class="question" onclick="viewAnswer(this)">';
		tag += '<span></span>';
		tag += '<p>'+this.question+'</p>';
		tag += '</li>';
		tag += '<li id="'+this.faqNo+'_answer" class="answer">';
		tag += '<span></span>';
		tag += '<p>'+this.answer+'</p>';
		tag += '</li>';
	});
	
	$table.html(tag);
	
	page(res.page.currentPage, res.page.totalPage);	
}

function viewAnswer(obj){
	var $obj = $(obj);
	var $answer = $("#"+$obj.attr("id")+"_answer");
	if($answer.hasClass("view")) {
		$answer.removeClass("view");
		$answer.hide();
	} else {
		$answer.addClass("view");
		$answer.show();
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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_faq.png" /></span>
                    <span class="subPageTitleRight">/ 잦은 질문</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- FAQ</span>
                </span>
            </div>
            
            <div id="subPageCont">
            	<div id="listContainer">
	                <span id="listTop">
	                	총 <span id="totalCnt">8</span>개의 게시물이 있습니다.<span id="pageCnt">(1page / 1page)</span>
	                </span>
	                <ul id="listCont" class="guideContainer">
	                </ul>
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
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>