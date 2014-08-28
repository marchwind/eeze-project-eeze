<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/common.jsp" %>
</head>

<body>

<div id="wrap">
	<%@ include file="common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>'지원'에 대한 검색결과</h1>
		</div>
		<div class="mainTable" class="search">
			<div class="mainBox">
				<dl>
					<dt>
						<h2>예약</h2>
					</dt>
					<dd>
						<div class="mainBoxCont searchResult">
							<ul>
								<li>3월 21일 예약자 : 홍지원</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			<div class="mainBox">
				<dl>
					<dt>
						<h2>시설</h2>
					</dt>
					<dd>
						<div class="mainBoxCont searchResult">
							<ul>
								<li>창작지원1실</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			<div class="mainBox">
				<dl>
					<dt>
						<h2>장비</h2>
					</dt>
					<dd>
						<div class="mainBoxCont searchResult">
							<ul>
								<li>찾으시는 정보가 없습니다.</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
			<div class="mainBox">
				<dl>
					<dt>
						<h2>공지사항</h2>
					</dt>
					<dd>
						<div class="mainBoxCont searchResult">
							<ul>
								<li>여러분의 창작을 지원해 드리고 있습니다.</li>
							</ul>
						</div>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	<%@ include file="common/footer.jsp" %>

</div>

</body>
</html> 