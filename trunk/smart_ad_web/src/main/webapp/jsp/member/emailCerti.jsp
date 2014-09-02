<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
var key = "${emailCertKey}";

$(document).ready(function(){
	var url = uri.serverUrl + uri.emailCeriUrl;
	sendRequestJson(url, {emailCertKey : key}, ceriSuccess, error);
});

function ceriSuccess(res){
	if(res.result.resultCode == "0000"){
		$("#certTitle").text("스마트광고창작공간AD 회원가입을 환영합니다!");
		$("#certMsg").html('메일 계정 <span class="userEmail">'+res.info+'</span>');
	} else {
		error();
	}
}

function error(){
	$("#certTitle").text("이메일 인증 오류");
	$("#certMsg").text("이메일 인증에 실패하였습니다. 관리자에게 문의해 주세요.");
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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_join.png" /></span>
                    <span class="subPageTitleRight">/ 회원가입</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 회원가입</span>
                    <span class="navi_2dep">- 회원가입</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="joinFlow">
                    	<dl>
                        	<dt>STEP1</dt>
                            <dd>약관동의</dd>
                        </dl>
                        <dl>
                        	<dt>STEP2</dt>
                            <dd>정보입력</dd>
                        </dl>
                        <dl>
                        	<dt>STEP3</dt>
                            <dd>가입인증</dd>
                        </dl>
                        <dl class="lastFlow active">
                        	<dt>STEP4</dt>
                            <dd>회원가입완료</dd>
                        </dl>
                    </div>
                    <div id="joinCont">
                    	<h2>가입완료</h2>
                        <div class="memberResult">
                        	<span class="memberComplete">
                                <img src="${contextPath}/resources/images/common/kobaco_logo.png" />
                                <p id="certTitle"></p>
                                <span class="grayBox">
                                	<p id="certMsg" ></p>
                                </span>
                            </span>
                        </div>
                    </div>                    
                    <div class="btnContainer">
                    	<input type="button" class="btn_3" value="로그인하기" onclick="${contextPath}/member/login.do" />
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>