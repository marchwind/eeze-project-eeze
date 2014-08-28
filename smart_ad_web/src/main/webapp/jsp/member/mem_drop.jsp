<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/commonLogin.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#userNo").val(user.userNo);
	$("#userId").val(user.userId);
	$("#userIdView").text(user.userId);	
});

function dropSubmit() {
	
	if($("#userPassword").val().trim() == "") {
		alert(msg.mostPw);
	} else if($("#unsubscribeContent").val().trim() == "") { 
		alert(msg.mostReason);
	} else {
	
		if(confirm(msg.dropConfirmQuestion)) {
			var url = uri.serverUrl + uri.userUnsubscribeUrl;
			sendRequest("dropForm", url, dropSuccess, error);
		}	
	}
}

function dropSuccess(res) {
	if(res.result.resultCode == "0000") {
		alert(msg.dropSuccessMsg);
		goPage('${contextPath}/main.do');
	} else {
		error();	
	}
}

function error() {
	alert(msg.dropFail);
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
                    <span class="subPageTitleRight">/ 회원탈퇴</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 회원탈퇴</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="memberCont">
                    	<h2>회원탈퇴</h2>
                    	<p class="inlineBlock orangeColor" style="margin-bottom: 7px;">*회원탈퇴를 신청하시면 현재 로그인 된 아이디는 즉시 탈퇴 처리 되며 재사용 및 복구가 불가 하오니 신중하게 선택해 주세요.</p>
                        <div class="inputForm">
                        	<form id="dropForm">
                        		<input type="hidden" name="userNo" id="userNo" />
                        		<input type="hidden" name="userId" id="userId" />
	                        	<dl>
	                            	<dt>아이디</dt>
	                                <dd id="userIdView"></dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em>  비밀번호</dt>
	                                <dd>
	                                	<input type="password" value="" id="userPassword" name="userPassword"/>
	                                </dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 탈퇴사유</dt>
	                                <dd>
	                                	<textarea id="unsubscribeContent" name="unsubscribeContent"></textarea>
	                                </dd>    
	                            </dl>
                            </form>
                        </div>
                    </div>    
                    
                    <div class="btnContainer">
                    	<input type="button" class="btn_3" value="확인" onclick="dropSubmit()"/>
                    	<input type="button" class="btn_3" value="취소" onclick="goPage('${contextPath}/main.do)"/>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>