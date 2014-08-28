<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#userNo").val(user.userNo);
});

function pwChangeSubmit() {
	
	if($("#userPrePassword").val().trim() == "" || $("#userNewPassword").val().trim() == "") {
		alert(msg.mostPw);
	} else if($("#userNewPassword").val().trim().length < 8) { 
		alert(msg.pwLength);
	} else if($("#userNewPassword").val().trim() != $("#userNewPassword2").val().trim()) {
		alert(msg.checkPw );
	} else {
		
		var url = uri.serverUrl + uri.userPwChangeUrl;
		sendRequest("pwChangeForm", url, pwChangeSuccess, error);
	}
}

function pwChangeSuccess(res) {
	if(res.result.resultCode == "0000") {
		alert(msg.reLogin);
		logout();
	} else {
		error();
	}
}

function error() {
	alert(msg.pwChangeFail);
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
                    <span class="subPageTitleRight">/ 비밀번호 변경</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 비밀번호 변경</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="memberCont">
                    	<h2 class="inlineBlock">비밀번호 변경</h2>
                    	<p class="inlineBlock orangeColor">* 쉬운 비밀번호나 자주 쓰는 비밀번호가 같을경우, 도용되기 쉬우므로 주기적으로 변경하셔서 사용하는 것이 좋습니다.</p>
                        <div class="inputForm">
                        	<form id="pwChangeForm">
                        		<input type="hidden" name="userNo" id="userNo" />                 		
	                        	<dl>
	                            	<dt>현재 비밀번호</dt>
	                                <dd>
	                                	<input type="password" value="" name="userPassword" id="userPrePassword"  maxlength="20"/>
	                                </dd>    
	                            </dl>
	                            <dl>
	                            	<dt>새로운 비밀번호 </dt>
	                                <dd>	
	                                	<span><input type="password" value="" id="userNewPassword" name="userNewPassword"  maxlength="20"/></span>
	                                    <span><p>* 8~20자까지 사용 가능합니다.(영문은 대소문자 구분)</p></span>
	                                </dd>
	                            </dl>
	                            <dl>
	                            	<dt>비밀번호 확인</dt>
	                                <dd><input type="password" value="" id="userNewPassword2" maxlength="20"/></dd>
	                            </dl>
                            </form>
                        </div>
                    </div>
                    <div class="btnContainer">
                    	<input type="button" class="btn_3" value="확인" onclick="pwChangeSubmit()"/>
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