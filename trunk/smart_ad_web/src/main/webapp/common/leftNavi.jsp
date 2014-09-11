<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

$(document).ready(function(){
	log(user.login);
	if(user.login){
		$("#guestTop").hide();
		$("#memberTop").css("display","inline-block");
	} else {
		$("#guestTop").css("display","inline-block");
		$("#memberTop").hide();
	}
	
	setInterval(fadeAnimation, 3000);
});

function logout() {
	$.get( uri.serverUrl + uri.logoutUrl, function(data){
		log(data);
		if(data.result.resultCode == "0000"){
			goPage('${contextPath}/main.do');
		} else {
			alert(msg.logoutFail);
		}
	}).fail(function(){
		alert(msg.logoutFail);
	});
}

function goMyPage(url) {
	if(user.login){
		goPage(url);
	} else {
		if(confirm(msg.needLogin)){
			goPage('${contextPath}/member/login.do');
		}
	}
}

var fadeNum = 1;
function fadeAnimation(){
	$("#fadeImage_"+fadeNum).fadeTo("slow", 0, function(){
		$(".fadeImage").css("z-index","2");
		$(this).css("z-index","0");
		$(this).css("opacity","1");
		
		switch(fadeNum) {
		case 1 :
			$("#fadeImage_2").css("z-index","2");
			$("#fadeImage_3").css("z-index","1");
			break;
		case 2 :
			$("#fadeImage_3").css("z-index","2");
			$("#fadeImage_1").css("z-index","1");
			break;
		case 3 :
			$("#fadeImage_1").css("z-index","2");
			$("#fadeImage_2").css("z-index","1");
			break;
		}
		
		fadeNum++;
		
		if(fadeNum > 3) {
			fadeNum = 1;
		}
	});
}

</script>

<div id="leftNavi">
     <div id="subMenuBack"></div>
     <div id="mainMenuContainer">
         <div id="logo">
             <a href="${contextPath}/main.do">
             	<span class="fadeAnimation">
             		<img class="fadeImage" src="${contextPath}/resources/images/gnb/logo01.png" id="fadeImage_1" />
             		<img class="fadeImage" src="${contextPath}/resources/images/gnb/logo02.png" id="fadeImage_2" />
             		<img class="fadeImage" src="${contextPath}/resources/images/gnb/logo03.png" id="fadeImage_3" />
             	</span>
             </a>
         </div>
         <div id="reg">
             <ul id="guestTop">
                 <li><a href="${contextPath}/member/login.do">로그인</a></li>
                 <li> : </li>
                 <li><a href="${contextPath}/member/joinStep1.do">회원가입</a></li>
             </ul>
             <ul id="memberTop">
                 <li><a href="#" onclick="logout()">로그아웃</a></li>
                 <li> : </li>
                 <li><a href="${contextPath}/member/myReserve.do">마이페이지</a></li>
             </ul>
         </div>
         <div id="mainMenu">
             <ul>
                 <li id="mainMenu_intro" class="mainMenuList" data-idx="intro">
                 	<a href="#none" class="mainMenuTitle">
                 		<span id="gnb_intro"></span>
                 	</a>
                     <div class="hiddenBox">
                         <div class="subMenu">
                         	<div id="subMenu_intro">
	                             <ul>
	                                 <li><a href="${contextPath}/intro/intro.do">창작공간AD 소개</a></li>
	                                 <li><a href="${contextPath}/intro/place_list.do">창작공간AD 안내</a></li>
	                             </ul>
                             </div>
                         </div>                        
                     </div>
                 </li>                    
                 <li id="mainMenu_reserve" class="mainMenuList" data-idx="reserve">
                 	<a href="#none" class="mainMenuTitle">
                 		<span id="gnb_reserve"></span>
                 	</a>
                     <div class="hiddenBox">
                         <div class="subMenu">
                         	<div id="subMenu_reserve">
	                             <ul>
	                                 <li><a href="${contextPath}/reserve/guide.do">예약안내</a></li>
	                                 <li><a href="${contextPath}/reserve/situation.do">예약</a></li>
	                             </ul>
	                        </div>
                         </div>
                     </div>
                 </li>
                 <li id="mainMenu_guide" class="mainMenuList"  data-idx="guide">
                 	<a href="#none" class="mainMenuTitle">
                 		<span id="gnb_guide"></span>
                 	</a>
                     <div class="hiddenBox">
                         <div class="subMenu">
                         	<div id="subMenu_guide">
	                             <ul>
	                                 <li><a href="${contextPath}/guide/noticeList.do">공지사항</a></li>
	                                 <li><a href="${contextPath}/guide/dataList.do">자료실</a></li>
	                                 <li><a href="${contextPath}/guide/faq.do">FAQ</a></li>
	                                 <li><a href="${contextPath}/guide/inquiry.do">1:1문의하기</a></li>
	                                 <li><a href="${contextPath}/guide/networking.do">네트워킹</a></li>
	                                 <li><a href="${contextPath}/guide/location.do">찾아오시는길</a></li>
	                             </ul>
	                         </div>
                         </div>
                      </div>
                 </li>
                 <li id="mainMenu_mypage" class="mainMenuList" data-idx="mypage">
                 	<a href="#none" class="mainMenuTitle">
                 		<span id="gnb_mypage"></span>
                 	</a>
                     <div class="hiddenBox">
                         <div class="subMenu">
                         	<div id="subMenu_mypage">
	                             <ul>
	                                 <li><a href="#none" onclick="goMyPage('${contextPath}/member/myReserve.do')">예약현황 및 취소</a></li>
	                                 <li><a href="#none" onclick="goMyPage('${contextPath}/member/modify.do')">회원정보수정</a></li>
	                                 <li><a href="#none" onclick="goMyPage('${contextPath}/member/pwChange.do')">비밀번호변경</a></li>
	                                 <li><a href="#none" onclick="goMyPage('${contextPath}/member/inquiryList.do')">1:1문의보기</a></li>
	                                 <li><a href="#none" onclick="goMyPage('${contextPath}/member/memDrop.do')">회원탈퇴</a></li>
	                             </ul>
	                         </div>
                         </div>
                      </div>
                 </li>
             </ul>
         </div>
         <div id="foot">
             <div id="footMenu">                	
                 <ul>
                     <li><a href="${contextPath}/member/privatePolicy.do">개인정보취급방안</a></li>
                     <li><a href="${contextPath}/member/emailPolicy.do">이메일무단수집거부</a></li>
                     <li><a href="${contextPath}/member/copyrightPolicy.do">이용지침</a></li>
                 </ul>
             </div>
             <div id="footDescript">
             	<ul>
                 	<li>서울시 송파구 올림픽로35길 137 한국광고문화회관 5층</li>
                     <li>T.02-2144-0320</li>
                     <li>F.02-2144-0323</li>
                 </ul>
             </div>
         </div>
     </div>
 </div>	