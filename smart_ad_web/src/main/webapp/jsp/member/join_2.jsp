<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">

function idWrite() {
	$("#idCheckYn").val("N");
	$(".idCheckMsg").hide();
}

function idCheck() {
	if(!$("#userId").val().trim() == "") {
		var url = uri.serverUrl + uri.idCheckUrl;
		$.post( url,{ userId : $("#userId").val().trim()}, function( data ) {
			log(data);
			$(".idCheckMsg").show();
			if(data.result.resultCode == "0000") {
				$(".idCheckMsg").text("* 사용가능한 아이디 입니다.");
				$("#idCheckYn").val("Y");
			} else if(data.result.resultCode == "0201") {
				$(".idCheckMsg").text("* 중복된 아이디 입니다. 다른 아이디를 사용해주세요.");
				$("#idCheckYn").val("N");
			} 
		}).fail(function(){
			alert(msg.idCheckFail);
		});	
	} else {
		alert(msg.mostId);	
	}
}

function checkJoin() {
	
	var tel = $("#telSelect").val() + "-" + $("#tel1").val() + "-" + $("#tel2").val();
	var mobile = $("#mobileSelect").val() + "-" + $("#mobile1").val() + "-" + $("#mobile2").val();
	var email = $("#email1").val() + "@" + $("#email2").val();
	
	if($("#userId").val().trim() == "") {
		alert(msg.mostId);
		return false;
	} else if($("#idCheckYn").val() == "N") {
		alert(msg.checkId );
		return false;
	} else if($("#userPassword").val().trim() == "") {
		alert(msg.mostPw);
		return false;
	} else if($("#userPassword").val().trim().length < 8) { 
		alert(msg.pwLength);
		return false;
	} else if($("#userPassword").val().trim() != $("#userPassword2").val().trim()) {
		alert(msg.checkPw );
		return false;
	} else if($("#userName").val().trim() == "") {
		alert(msg.mostName );
		return false;
	} else if(email.trim() == "") {
		alert(msg.mostEmail );
		return false;
	} else if(!email.isEmail()) { 
		alert(msg.checkEmail );
		return false;
	} else if($("#enterpriseName").val().trim() == ""){
		alert(msg.mostEnterprise );
		return false;
 	} else if($("#tel1").val() != "" || $("#tel2").val() != "") {
		if(!$("#tel1").val().isNum() || !$("#tel2").val().isNum()){
			alert(msg.checkNum );
			return false;
		}
	} else if(!$("#mobile1").val().isNum() || !$("#mobile1").val().isNum()) {
		alert(msg.checkNum);
		return false;
	} else if(mobile.length < 12){
		alert(msg.mostMobile);
		return false;
	}
	
	$("#userId").val($("#userId").val().trim());
	$("#userName").val($("#userName").val().trim());
	$("#userEmail").val(email.trim());
	
	$("#userPhone").val(tel);
	$("#userCellPhone").val(mobile);
	
	var url = uri.serverUrl + uri.joinUrl;
	sendRequest("joinInputForm", url, joinSuccess, error);

	
}

function joinSuccess(res) {
	if(res.result.resultCode = "0000") {
		goPage('${contextPath}/member/joinStep3.do?email=' + res.info.userEmail);
	} else {
		error();
	}
}

function error() {
	alert(msg.failJoin);
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
    <div id="mainFrame" class="subFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="../resources/images/common/title_join.png" /></span>
                    <span class="subPageTitleRight">/ 회원가입</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="../resources/images/common/sub_navi_home_icon.png" /></span>
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
                        <dl class="active">
                        	<dt>STEP2</dt>
                            <dd>정보입력</dd>
                        </dl>
                        <dl>
                        	<dt>STEP3</dt>
                            <dd>가입인증</dd>
                        </dl>
                        <dl class="lastFlow">
                        	<dt>STEP4</dt>
                            <dd>회원가입완료</dd>
                        </dl>
                    </div>
                    <div id="joinCont">
                    	<h2 class="inlineBlock">정보입력</h2>
                    	<p class="inlineBlock orangeColor">*필수입력란입니다.</p>
                        <div class="inputForm">
                        	<form id="joinInputForm">
                        		<input type="hidden" name="consentTscsYn" value="Y" />
                        		<input type="hidden" id="idCheckYn" value="N" />
	                            <dl>
	                            	<dt><em>*</em> 아이디</dt>
	                                <dd>
	                                	<input type="text" value="" id="userId" name="userId" onkeyup="idWrite();"/>
	                                	<input type="button" value="중복확인" class="btn_5" onclick="idCheck()" />
	                                    <p class="idCheckMsg">* 중복된 아이디 입니다. 다른 아이디를 사용해주세요.</p>
	                                </dd>    
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 비밀번호 </dt>
	                                <dd>	
	                                	<span><input type="password" value="" id="userPassword" name="userPassword" maxlength="20"/></span>
	                                    <span><p>* 8~20자까지 사용 가능합니다.(영문은 대소문자 구분)</p></span>
	                                </dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 비밀번호확인</dt>
	                                <dd><input type="password" value="" id="userPassword2" maxlength="20"/></dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 이름 </dt>
	                                <dd><input type="text" value="" name="userName" id="userName"/></dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 이메일</dt>
	                                <dd>
	                                	<input type="hidden" name="userEmail" id="userEmail" value="" />
	                                	<input type="text" value="" id="email1" />@<input type="text" value=""  id="email2"/>
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
	                                <dd><input type="text" name="enterpriseName" id="enterpriseName" value="" /></dd>
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
	                                    	<option value="051">051</option>
	                                    	<option value="052">052</option>
	                                    	<option value="053">053</option>
	                                    	<option value="054">054</option>
	                                    	<option value="055">055</option>
	                                    	<option value="061">061</option>
	                                    	<option value="062">062</option>
	                                    	<option value="063">063</option>
	                                    	<option value="064">064</option>
	                                    </select> -
	                                    <input class="phoneNum" type="text" id="tel1" value="" /> - <input class="phoneNum" type="text" id="tel2" value="" />
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
	                                    <input class="phoneNum" type="text" id="mobile1" value="" /> - <input class="phoneNum" type="text" id="mobile2" value="" />
	                                </dd>
	                            </dl>
	                            <dl>
	                            	<dt><em>*</em> 정보수신 여부</dt>
	                                <dd>
	                                	<span>
		                                	<input type="radio" name="consentReceiveInfoYn" checked="checked" value="Y" /> 수신
		                                	<input type="radio" name="consentReceiveInfoYn" value="N" /> 수신안함
	                                	</span>
	                                	<span>
	                                		<p>* 스마트 창작AD 관련 다양한 정보를 수신하실 수 있습니다.</p>
	                                	</span>
	                                </dd>
	                            </dl>
                            </form>
                        </div>
                    </div>    
                    <p class="orangeColor">* 확인 메일이 발송되오니 메일 확인 후 사용해 주세요.</p>                
                    <div class="btnContainer">
                        <input type="button" class="btn_3" value="이전단계"  onclick="goPage('${contextPath}/member/joinStep1.do')"/>
                        <input type="button" class="btn_3" value="확인"  onclick="checkJoin()"/>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>