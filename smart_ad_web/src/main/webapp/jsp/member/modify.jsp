<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/commonLogin.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#preUserNo").val(user.userNo);
	$("#preUserId").val(user.userId);
	
	var url = uri.serverUrl + uri.userGetUrl;
	sendRequest("userGetForm", url, userDetail, error);
}); 

function userDetail(res) {
	if(res.result.resultCode == "0000") {	
		$("#userNo").val(user.userNo);
		$("#userId").val(user.userId);
		$("#user_id").text(res.info.userId);
		$("#userName").val(res.info.userName);
		$("#userEmail").val(res.info.userEmail);
		
		var emailArr = res.info.userEmail.split("@");
		$("#email1").val(emailArr[0]);
		$("#email2").val(emailArr[1]);
		$("#emailSelect").val(emailArr[1]);
		
		$("#enterpriseName").val(res.info.enterpriseName);
		
		if(res.info.userPhone != "" && res.info.userPhone != null) {
			$("#userPhone").val(res.info.userPhone);
			var telArr = res.info.userPhone.split("-");
			$("#telSelect").val(teArr[0]);
			$("#tel1").val(teArr[1]);
			$("#tel2").val(teArr[2]);
		}
		
		if(res.info.userCellPhone != "" && res.info.userCellPhone != null) {
			$("#userCellPhone").val(res.info.userCellPhone);
			var mobileArr = res.info.userCellPhone.split("-");
			$("#mobileSelect").val(mobileArr[0]);
			$("#mobile1").val(mobileArr[1]);
			$("#mobile2").val(mobileArr[2]);
		}
		
		if(res.info.consentReceiveInfoYn == "Y") {
			$("#consentReceiveInfoY").attr("checked", true);
		} else {
			$("#consentReceiveInfoN").attr("checked", true);
		}
		
	} else {
		error();
	}
}

function error() {
	alert(msg.userGetFail);
}

function checkModify() {
	
	var tel = $("#telSelect").val() + "-" + $("#tel1").val() + "-" + $("#tel2").val();
	var mobile = $("#mobileSelect").val() + "-" + $("#mobile1").val() + "-" + $("#mobile2").val();
	var email = $("#email1").val() + "@" + $("#email2").val();
	
	if($("#userPassword").val().trim() == "") {
		alert(msg.mostPw);
	} else if($("#userName").val().trim() == "") {
		alert(msg.mostName );
	} else if(email.trim() == "") {
		alert(msg.mostEmail );
	} else if(!email.isEmail()) { 
		alert(msg.checkEmail );
	} else if($("#enterpriseName").val().trim() == ""){
		alert(msg.mostEnterprise );
	} else if($("#tel1").val() != "" || $("#tel2").val() != "") {
		if(!$("#tel1").val().isNum() || !$("#tel2").val().isNum()){
			alert(msg.checkNum );
		}
	} else if(!$("#mobile1").val().isNum() || !$("#mobile1").val().isNum()) {
		alert(msg.checkNum);
	} else if(mobile.length < 12){
		alert(msg.mostMobile);
	} else {
		$("#userName").val($("#userName").val().trim());
		$("#userEmail").val(email.trim());
		
		$("#userPhone").val(tel);
		$("#userCellPhone").val(mobile);
		
		var url = uri.serverUrl + uri.userModifyUrl;
		sendRequest("modifyForm", url, modifySuccess, addError);
	}
	
}

function modifySuccess(res) {
	if(res.result.resultCode = "0000") {
		alert(msg.modifySuccess);
		location.reload();
	} else {
		addError();
	}
}

function addError() {
	alert(msg.modifyFail);
}

function emailSelector(obj){
	if($(obj).val() == "none"){
		$("#email2").val("");
		$("#email2").attr("disabled", false);
	} else {
		$("#email2").val($(obj).val());
		$("#email2").attr("disabled", true);
	}
}

</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <form id="userGetForm">
    	<input type="hidden" name="userNo" id="preUserNo" />
    	<input type="hidden" name="userId" id="preUserId" />
    </form>
    <div id="mainFrame" class="subFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_mypage.png" /></span>
                    <span class="subPageTitleRight">/ 회원정보수정</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 회원정보수정</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                    <div id="memberCont">
                    	<h2 class="inlineBlock">정보입력</h2>
                    	<p class="inlineBlock orangeColor">*필수입력란입니다.</p>
                        <div class="inputForm">
                        	<form id="modifyForm">
                        		<input type="hidden" name="userNo" id="userNo" />
                        		<input type="hidden" name="userId" id="userId" />
	                        	<dl>
	                            	<dt>아이디</dt>
	                                <dd id="user_id"></dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 비밀번호 </dt>
	                                <dd>	
	                                	<span><input type="password" value="" name="userPassword" id="userPassword" /></span>
	                                    <!-- <span><p>* 8~20자까지 사용 가능합니다.(영문은 대소문자 구분)</p></span> -->
	                                </dd>
	                            </dl>     
	                            <dl>
	                            	<dt><em>*</em> 이름 </dt>
	                                <dd><input type="text" value="" id="userName" name="userName" /></dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 이메일</dt>
	                                <dd>
	                                	<input type="hidden" name="userEmail" id="userEmail" value="" />
	                                	<input type="text" value="" id="email1" /> @ <input type="text" value=""  id="email2"/>
	                                	<select id="emailSelect" onchange="emailSelector(this)">
	                                    	<option value="none">직접입력</option>
	                                    	<option value="naver.com">naver.com</option>
	                                    	<option value="gmail.com">gmail.com</option>
	                                    	<option value="nate.com">nate.com</option>
	                                    	<option value="daum.net">daum.net</option>
	                                    	<option value="hotmail.com">hotmail.com</option>
	                                    </select>
	                                    <p>* 메일 확인 절차가 있으니 수신 가능한 이메일을 사용해 주세요.</p>
	                                </dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 회사명</dt>
	                                <dd><input type="text" value="" id="enterpriseName" name="enterpriseName" /></dd>
	                            </dl>
	                            <dl>
	                            	<dt>전화번호</dt>
	                                <dd>
	                                	<input type="hidden" name="userPhone" id="userPhone" />
	                                	<select id="telSelect">
	                                    	<option value="02">02</option>
	                                    	<option value="031">031</option>
	                                    	<option value="032">032</option>
	                                    	<option value="033">033</option>
	                                    	<option value="041">041</option>
	                                    	<option value="042">042</option>
	                                    	<option value="043">043</option>
											<option value="043">044</option>
	                                    	<option value="051">051</option>
	                                    	<option value="052">052</option>
	                                    	<option value="053">053</option>
	                                    	<option value="054">054</option>
	                                    	<option value="055">055</option>
	                                    	<option value="061">061</option>
	                                    	<option value="062">062</option>
	                                    	<option value="063">063</option>
	                                    	<option value="064">064</option>
											<option value="070">070</option>
	                                    </select> -
	                                    <input class="phoneNum" id="tel1" type="text" value="" /> - <input class="phoneNum" id="tel2" type="text" value="" />
	                                </dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 휴대전화</dt>
	                                <dd>
	                                	<input type="hidden" name="userCellPhone" id="userCellPhone" />
	                                	<select id="mobileSelect">
	                                    	<option value="010">010</option>
	                                    	<option value="011">011</option>
	                                    	<option value="017">017</option>
	                                    </select> -
	                                    <input class="phoneNum" id="mobile1" type="text" value="" /> - <input class="phoneNum" id="mobile2" type="text" value="" />
	                                </dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 정보수신 여부</dt>
	                                <dd>
	                                	<span>
		                                	<input type="radio" name="consentReceiveInfoYn" id="consentReceiveInfoY" value="Y" /> 수신
		                                	<input type="radio" name="consentReceiveInfoYn" id="consentReceiveInfoN" value="N" /> 수신안함
	                                	</span>
	                                	<span>
	                                		<p>* 스마트 창작AD 관련 다양한 정보를 수신하실 수 있습니다.</p>
	                                	</span>
	                                </dd>
	                            </dl>
                            </form>
                        </div>
                    </div>    
                                  
                    <div class="btnContainer">
                    	<input type="button" class="btn_3" value="수정" onclick="checkModify()"/>
                        <input type="button" class="btn_3" value="취소" onclick="goPage('${contextPath}/main.do')"/>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>