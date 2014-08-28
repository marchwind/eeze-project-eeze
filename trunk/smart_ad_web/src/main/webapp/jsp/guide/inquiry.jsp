<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	if(user.login){
		$("#queryName").val(user.userName);
		
		/*var mobile = user.userCellPhone.split("-");
		$("#mobileSelect").val(mobile[0]);
		$("#mobile1").val(mobile[1]);
		$("#mobile2").val(mobile[2]);*/
		$("#queryEmail").val(user.userEmail);
	}
});

function checkForm() {
	
	var mobile = $("#mobileSelect").val() + "-" + $("#mobile1").val() + "-" + $("#mobile2").val();
	
	if($('input[name="agreement"]:checked').val() != "Y"){
		alert(msg.privateCheck);
	} else if($("#querySubject").val().trim() == "") {
		alert(msg.mostSubject);
	} else if($("#queryName").val().trim() == "") {
		alert(msg.mostName);
	} else if(!$("#mobile1").val().isNum() || !$("#mobile1").val().isNum()) {
		alert(msg.checkNum);
	} else if(mobile.length < 12){
		alert(msg.mostMobile);
	} else if($("#queryEmail").val().trim() == "") {
		alert(msg.mostEmail );
	} else if(!$("#queryEmail").val().isEmail()) { 
		alert(msg.checkEmail );
	} else if($("#queryContent").val().trim() == "") {
		alert(msg.mostContent);
	} else {
		$("#queryName").val($("#queryName").val().trim());
		$("#queryEmail").val($("#queryEmail").val().trim());
		
		$("#queryPhone").val(mobile);
		
		var url = uri.serverUrl + uri.qnaAddUrl;
		sendRequest("qnaInputForm", url, addSuccess, error);
	}
}

function addSuccess(res) {
	if(res.result.resultCode == "0000"){
		alert(msg.qnaSuccess);
		goPage('${contextPath}/main.do');
	} else {
		error();
	}
}

function error(){
	alert(msg.qnaFail);
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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_contact.png" /></span>
                    <span class="subPageTitleRight">/ 1:1문의</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 고객센터</span>
                    <span class="navi_2dep">- 1:1문의</span>
                </span>
            </div>
            
            <div id="subPageCont">
            	<div id="inquiryPrivacy">
            		<h2 class="inlineBlock">개인정보를 위한 이용자 동의</h2>
            		<div class="box">
            			개인정보를 위한 이용자 동의사<br/>
            			<ol>
            				<li>수집·이용 목적 : 상담 및 온라인 신청 접수에 따른 수집·이용</li>
            				<li>개인정보 수집 항목 : 
		            			<ul>
		            				<li>필수항목 : 작성자명, 이메일</li>
		            				<li>선택항목 : 연락처</li>
		            			</ul>
		            		</li>
		            		<li>개인정보 보유 및 이용기간 : 3년</li>
		            		<li>동의 거부 시 불이익에 관한 사항 : 수집·이용에 관한 사항의 동의를 거부할 때에는 서비스의 이용이 제한됩니다. <br/>
							   단, 선택정보 사항을 획득하지 못한 사유로 인하여 서비스 제공을 거부하지 않습니다.</li>
						</ol> 
            		</div>
            		<div class="btnContainer">
            			<input type="radio" id="agree" name="agreement" checked="checked" value="Y"/><label for="agree" >동의합니다.</label>
            			<input type="radio" id="notagree" name="agreement"  value="N"/><label for="notagree" >동의하지 않습니다.</label>
            		</div>
            	</div>
                <div id="inquiryCont">
                	<h2 class="inlineBlock">작성하기</h2>
                    <p class="inlineBlock orangeColor">*필수입력란입니다.</p>
	                <div class="inputForm">
	                	<form id="qnaInputForm">
	                       	<dl>
	                           	<dt><em>*</em> 제목</dt>
	                            <dd>
	                            	<input type="text" value="" name="querySubject" id="querySubject" />
	                            </dd>    
	                        </dl>
	                        <dl>
	                        	<dt><em>*</em> 작성자명 </dt>
	                            <dd>	
	                            	<input type="text" value="" name="queryName" id="queryName"/>
	                            </dd>
	                        </dl>
	                        <dl>
	                        	<dt><em>*</em> 연락처</dt>
	                            <dd>
	                            	<input type="hidden" name="queryPhone" id="queryPhone" />
	                            	<select id="mobileSelect">
	                                	<option value="010">010</option>
	                                	<option value="011">011</option>
	                                	<option value="017">017</option>
	                                </select> -
	                                <input class="phoneNum" id="mobile1" type="text" value="" /> - <input class="phoneNum" id="mobile2" type="text" value="" />
	                           	</dd>
	                        </dl>
	                        <dl>
	                        	<dt><em>*</em> 이메일</dt>
	                            <dd>
	                            	<input type="text" value="" id="queryEmail" name="queryEmail" />
	                            	<!-- 
	                            	<input type="text" value="" />@<input type="text" value="" />
	                            	<select>
	                                	<option>직접입력</option>
	                                </select>
	                                 -->
	                           	</dd>
	                        </dl>
	                        <dl>
	                        	<dt><em>*</em> 문의내용</dt>
	                            <dd>
	                            	<textarea name="queryContent" id="queryContent"></textarea>
	                            </dd>
	                        </dl>
                        </form>
               		</div>
	                <div class="btnContainer">
	                	<input type="button" class="btn_3" value="확인" onclick="checkForm()"/>
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