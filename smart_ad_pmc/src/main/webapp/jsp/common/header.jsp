<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
(function(){
	if(user.managerNo == "" || !user.login){
		alert("계정 아이디로 로그인해 주세요.");
		window.location.href= contextPath + "/login.do";
	}
})();

$(document).ready(function(){
	$("#gnbMenu_" + gnbPostion).addClass("on");	
	$("#gnb_member_greeting").text(user.managerName + "님 안녕하세요.");
});

function logout() {
	var url = uri.serverUrl + uri.logoutUrl;
	sendRequestJson(url, {}, logoutSuccess, gnbError);
}

function logoutSuccess(res){
	if(res.result.resultCode == "0000"){
		goPage("${contextPath}/login.do");
	} else {
		gnbError();
	}
}

function gnbError(){
	alert(msg.logoutFail);
}

</script>

	<div id="header">
		<div id="siteTop">
			<div id="topLogo" onclick="goPage('${contextPath}/main.do')"><img src="${contextPath}/resources/images/gnb_logo.png" /></div>
			<!-- <div id="topSearch">
				<input type="text" class="search_icon" />
				<input type="button" value="검색" class="btn_small_red" onclick="goPage('${contextPath}/gnbSearch.do')"/>
			</div> -->
			<div id="topLogout"><input type="button" value="로그아웃" class="btn_logout_gray" onclick="logout()"/></div>
		</div>
		<div id="gnb">
			<ul class="fl">
				<li id="gnbMenu_member" onclick="goPage('${contextPath}/member/list.do')">회원관리</li>
				<li id="gnbMenu_reserve" onclick="goPage('${contextPath}/reserve/list.do')">예약관리</li>
				<li id="gnbMenu_facility" onclick="goPage('${contextPath}/facility/list.do')">시설관리</li>
				<li id="gnbMenu_equipment" onclick="goPage('${contextPath}/equipment/list.do')">장비관리</li>
				<li id="gnbMenu_cms" onclick="goPage('${contextPath}/cms/list.do')">웹CMS</li>
				<li id="gnbMenu_admin" onclick="goPage('${contextPath}/admin/list.do')">계정관리</li>
			</ul>
			<span id="gnb_member_greeting" class="fr">김다정님 안녕하세요</span>
		</div>
	</div>
