<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var url = uri.serverUrl + uri.noticeDetailUrl;
	sendRequest("noticeDetailForm", url, noticeDetail, error);
});

function noticeDetail(res) {
	if(res.result.resultCode == "0000") {
		$("#noticeDetailSubject").text(res.info.notiSubject);
		$("#noticeDetailDate").text(numberToDate(res.info.notiRegisteDate));
		$("#noticeDetailFile").text("");
		$("#noticeDetailCount").text(res.info.notiConfirmCount);
		$("#noticeDetailCont").html(res.info.notiContent.replaceAll("\n","<br/>"));
	} else {
		error();
	}	
}

function error(){
	alert(msg.noticeFail);
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
            <form id="noticeDetailForm">
            	<input type="hidden" name="notiNo" value="${no}" />
            </form>
            <div id="subPageCont">
                <div id="listContainer">
	                <ul id="detailCont" class="guideContainer">
	                	<li>
	                		<dl class="leftCont">
	                			<dt>제목</dt>
	                			<dd><b id="noticeDetailSubject"></b></dd>
	                		</dl>
	                		<dl class="rightCont notiDetail">
	                			<dt>등록일</dt>
	                			<dd id="noticeDetailDate"></dd>
	                		</dl>
	                		<dl class="rightCont notiDetail">
	                			<dt>조회수</dt>
	                			<dd id="noticeDetailCount"></dd>
	                		</dl>
	                	</li>
	                	<!-- <li>
	                		<dl class="leftCont">
	                			<dt>첨부</dt>
	                			<dd id="noticeDetailFile"></dd>
	                		</dl>
	                		<dl class="rightCont">
	                			<dt>조회수</dt>
	                			<dd id="noticeDetailCount"></dd>
	                		</dl>
	                	</li> -->
	                	<li>
	                		<p id="noticeDetailCont">
	                		</p>
	                	</li>
	                </ul>
	                <div id="listBtn">
	                	<input type="button" class="btn_4" value="목록" onclick="goPage('${contextPath}/guide/noticeList.do')"/>
	                </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>