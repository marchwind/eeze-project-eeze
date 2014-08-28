<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	
}) ;
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
                    <span class="subPageTitleRight">/ 아이디찾기</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 아이디찾기</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="memberBox">
                   <div id="memberCont">
                    	<h2 class="inlineBlock">아이디 찾기</h2>
                    	<p class="inlineBlock orangeColor">* 입력하신 고객님의 정보로 창작지원AD센터에 등록되어 있는 아이디입니다.</p>
                        <div class="inputForm horizontal">
                        	<dl>
                            	<dt>아이디</dt>
                                <dd id="userId">${userId}</dd>    
                            </dl>
                            <!-- <dl>
                            	<dt>가입일</dt>
                                <dd id="user">
                                	2014-06-24 13:20:33	
                                </dd>    
                            </dl>
                             -->
                            <dl>
                            	<dt>비고 </dt>
                                <dd>	
                                	<input type="button" class="btn_7" value="비밀번호 찾기"  onclick="goPage('${contextPath}/member/pwSearch.do')"/>
                                </dd>
                            </dl>
                            
                        </div>
                    </div>    
                    
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>