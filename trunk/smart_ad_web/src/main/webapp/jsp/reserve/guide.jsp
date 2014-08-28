<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
 
</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <div id="mainFrame" class="subFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_reservation.png" /></span>
                    <span class="subPageTitleRight">/ 예약안내</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 창작공간AD예약</span>
                    <span class="navi_2dep">- 예약안내</span>
                </span>
            </div>
            
            <div id="subPageCont">
            	<div id="reserveGuide">
            		<dl>
            			<dt><img src="${contextPath}/resources/images/common/reserve_guide_1.png" /></dt>
            			<dd><img src="${contextPath}/resources/images/common/reserve_guide_img.png" /></dd>
            		</dl>
	                <dl>
            			<dt><img src="${contextPath}/resources/images/common/reserve_guide_2.png" /></dt>
            			<dd>
            				 <ul>
            				 	<li>- 멀티플랫폼(스마트TV , 스마트폰 등)에서 스마트 광고를 제작하고자 하는  중소기업 및 개발자</li>
            				 	<li>- 스마트광고 앱개발을 시작하거나 실습을 통해 실력을 키우고 싶은 학생</li>
            				 	<li>- 스마트광고 개발 관련 분야로 사업을 진출코자 하는 중소기업</li>
            				 	<li>- 스마트광고 테스트베드에 관심이 있는 제작자</li>
            				 	<li>- 최신 스마트 단말기를 보유하지 못한 중소기업</li>
            				 </ul>
            			</dd>
            		</dl>
            		<dl>
            			<dt><img src="${contextPath}/resources/images/common/reserve_guide_3.png" /></dt>
            			<dd>
            				 <ul>
            				 	<li>
            				 		- 월~금 09:00 ~ 17:00<br/>
									  <em>오전 : 09:00 ~ 13:00</em><br/>
									  <em>오후 : 13:00 ~ 17:00</em>
								</li>
            				 	<li>- 휴관일 : 토, 일 , 공휴일 휴무</li>
            				 	<li class="important">**이용시간은 公社사정에 따라 조정될 수 있습니다.**</li>
            				 </ul>
            			</dd>
            		</dl>
            		<dl>
            			<dt><img src="${contextPath}/resources/images/common/reserve_guide_4.png" /></dt>
            			<dd>
            				 <ul>
            				 	<li>- 회원만 대여 가능합니다.</li>
            				 	<li>- 이용일 30일전부터 이용일 3일전까지 이용 신청</li>
            				 	<li>- 이용 당일 신분증 반드시 지참</li>
            				 	<li>- 시설대여시 재임대등의 목적으로 재임대불가합니다.</li>
            				 	<li class="contact">TEL : 02-2144-0320 FAX : 02-2144-0323<br/>
            				 		E-mail : smartad@smartad.or.kr
            				 	</li>
            				 </ul>
            			</dd>
            		</dl>
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>