<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/commonLogin.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var url = uri.serverUrl + uri.qnaViewUrl;
	sendRequest("qnaDetailForm", url, qnaDetail, error);
});

function qnaDetail(res) {
	if(res.result.resultCode == "0000") {
		$("#qnaSubject").text(res.info.querySubject);
		$("#qnaQueryDate").text(numberToDate(res.info.queryDate));
		$("#qnaAnswerDate").text(numberToDate(res.info.anwserDate));
		$("#qnaQueryContent").text(res.info.queryContent);
		$("#qnaAnswerContent").text(res.info.answerContent);
	} else {
		error();
	}	
}

function error(){
	alert(msg.qnaDetailFail);
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
                    <span class="subPageTitleRight">/ 1:1문의 답변보기</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 1:1 문의 답변확인</span>
                </span>
            </div>
            <form id="qnaDetailForm">
            	<input type="hidden" name="qnaNo" value="${no}" />
            </form>
            <div id="subPageCont">
                <div id="listContainer">
	                <div class="inputForm">
                       	<dl>
                           	<dt>제목</dt>
                            <dd id="qnaSubject"></dd>    
                        </dl>
                        <dl>
                        	<dt>작성일</dt>
                            <dd id="qnaQueryDate"></dd>
                        </dl>
                        <dl>
                        	<dt>답변일</dt>
                            <dd id="qnaAnswerDate"></dd>
                        </dl>
                        <dl>
                        	<dt>내용</dt>
                            <dd id="qnaQueryContent"></dd>
                        </dl>
                        <dl>
                        	<dt>답변</dt>
                            <dd id="qnaAnswerContent"></dd>
                        </dl>
               		</div>
	                <div class="btnContainer">
	                	<input type="button" class="btn_3" value="목록" onclick="goPage('${contextPath}/member/inquiryList.do')"/>
	                </div>
                </div>
            </div>
        </div>
	</div>
	
</div>
</body>
</html>