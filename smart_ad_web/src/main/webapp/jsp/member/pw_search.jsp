<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
function pwSearchSubmit() {
	
	var mobile = $("#mobileSelect").val() + "-" + $("#mobile1").val() + "-" + $("#mobile2").val();
	
	if($("#userId").val().trim() == "") {
		alert(msg.mostId);
	} else if($("#userName").val().trim() == "") {
		alert(msg.mostName );
	} else if($("#userEmail").val().trim() == "") {
		alert(msg.mostEmail );
	} else if(!$("#userEmail").val().isEmail()) { 
		alert(msg.checkEmail );
	
//	} else if(!$("#mobile1").val().isNum() || !$("#mobile1").val().isNum()) {
//		alert(msg.checkNum);
//	} else if(mobile.length < 12){
//		alert(msg.mostMobile);
	} else {
		$("#userId").val($("#userId").val().trim());
		$("#userName").val($("#userName").val().trim());
		//$("#userCellPhone").val(mobile);
		
		var url = uri.serverUrl + uri.userPwSearchUrl;
		sendRequest("pwSearchForm", url, pwSearchSuccess, error);
	}
}

function pwSearchSuccess(res) {
	if(res.result.resultCode == "0000") {
		$("#resultUserName").val(res.info.userId);
		$("#goPwSearchResultForm").submit();
	} else {
		error();
	}
}

function error() {
	alert(msg.pwSearchFail);
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
                    <span class="subPageTitleRight">/ 비밀번호찾기</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 비밀번호찾기</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="memberCont">
                    	<h2 class="inlineBlock">비밀번호 찾기</h2>
                    	<p class="inlineBlock orangeColor">* 비밀번호를 잊으셨나요? 아이디, 이름, 이메일 주소를 입력해 주세요.</p>
                        <div class="inputForm">
                        	<form id="pwSearchForm">
	                        	<dl>
	                            	<dt><em>*</em> 아이디</dt>
	                                <dd>
	                                	<input type="text" value="" id="userId" name="userId"/>
	                                </dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 이름</dt>
	                                <dd>
	                                	<input type="text" value="" id="userName" name="userName"/>
	                                </dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 이메일</dt>
	                                <dd>
	                                	<input type="text" value="" id="userEmail" name="userEmail"/>
	                                </dd>    
	                            </dl>
	                            <!-- <dl>
	                            	<dt><em>*</em> 휴대번호</dt>
	                                <dd>
	                                	<input type="hidden" name="userCellPhone" id="userCellPhone" />
	                                	<select id="mobileSelect">
	                                    	<option value="010">010</option>
	                                    	<option value="011">011</option>
	                                    	<option value="017">017</option>
	                                    </select> -
	                                    <input class="phoneNum" type="text" id="mobile1" value="" /> - <input class="phoneNum" type="text" id="mobile2" value="" />
	                                </dd>    
	                            </dl>
	                             -->
                            </form>
                        </div>
                    </div>    
                    <form id="goPwSearchResultForm" action="${contextPath}/member/pwSearchResult.do" method="post">
                    	<input type="hidden" name="userName" id="resultUserName">
                    </form>   
                    <p class="orangeColor">* 임시비밀번호는 이용자 이메일주소로 전송됩니다.</p>          
                    <div class="btnContainer">
                    	<input type="button" class="btn_3" value="확인"  onclick="pwSearchSubmit()"/>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>