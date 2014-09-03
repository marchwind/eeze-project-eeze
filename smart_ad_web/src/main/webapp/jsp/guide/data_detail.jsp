<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var url = uri.serverUrl + uri.dataDetailUrl;
	sendRequest("dataDetailForm", url, dataDetail, error);
});

function dataDetail(res) {
	if(res.result.resultCode == "0000") {
		$("#dataDetailSubject").text(res.info.archiveSubject);
		$("#dataDetailDate").text(numberToDate(res.info.registeDate));
		
		if(res.info.attachedFileName != null){
			var downloadTag = "<a href='" + uri.serverUrl + uri.fileDownloadUrl + "?fileName=" +res.info.attachedFileName + "&filePath="+res.info.attachedFilePath+"'>"+res.info.attachedFileName+"</a>";
			
			$("#dataDetailFile").html(downloadTag);	
		}
		
		$("#dataDetailCount").text(res.info.viewCount);
		$("#dataDetailCont").html(res.info.archiveContent.replaceAll("\n","<br/>"));
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
                    <span class="subPageTitleRight">/ 자료실</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- 자료실</span>
                </span>
            </div>
            <form id="dataDetailForm">
            	<input type="hidden" name="archiveNo" value="${no}" />
            </form>
            <div id="subPageCont">
                <div id="listContainer">
	                <ul id="detailCont" class="guideContainer">
	                	<li>
	                		<dl class="leftCont">
	                			<dt>제목</dt>
	                			<dd><b id="dataDetailSubject"></b></dd>
	                		</dl>
	                		<dl class="rightCont">
	                			<dt>등록일</dt>
	                			<dd id="dataDetailDate"></dd>
	                		</dl>
	                	</li>
	                	<li>
	                		<dl class="leftCont">
	                			<dt>첨부</dt>
	                			<dd id="dataDetailFile"></dd>
	                		</dl>
	                		<dl class="rightCont">
	                			<dt>조회수</dt>
	                			<dd id="dataDetailCount"></dd>
	                		</dl>
	                	</li>
	                	<li>
	                		<p id="dataDetailCont">
	                		</p>
	                	</li>
	                </ul>
	                <div id="listBtn">
	                	<input type="button" class="btn_4" value="목록" onclick="goPage('${contextPath}/guide/dataList.do')"/>
	                </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>