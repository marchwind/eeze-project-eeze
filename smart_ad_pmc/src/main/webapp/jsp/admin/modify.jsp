<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>계정관리 > 계정상세</h1>
		</div>
		<div class="mainTable memberJoinForm">
			<table>
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td class="tl">이름</td>
						<td class="tl input"><input type="text" value="" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">상태</td>
						<td class="tl"></td>
					</tr>
					<tr>
						<td class="tl">아이디</td>
						<td class="tl"></td>
					</tr>
					<tr>
						<td class="tl">전화번호</td>
						<td class="tl input">
							<input type="text" value="" class="input_red input_type2" /> - 
							<input type="text" value="" class="input_red input_type2" /> - 
							<input type="text" value="" class="input_red input_type2" />
						</td>
					</tr>
					<tr>
						<td class="tl">소속</td>
						<td class="tl input"><input type="text" value="" class="input_red input_type1" /></td>
					</tr>
					<tr>
						<td class="tl">이메일</td>
						<td class="tl input"><input type="text" value="" class="input_red input_type3" /> @ 
						<select class="input_red  input_type3">
							<option>naver.com</option>
							<option>nate.com</option>
							<option>daum.com</option>
						</select></td>
					</tr>
				</tbody>
			</table>
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/member/list.go')"/>
				</div>
				<div class="fr">
					<ul>
						<li><input type="button" value="수정완료" class="btn_normal_red" onclick="goPage('${contextPath}/member/detail.do')"/></li>
						<li><input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/member/detail.do')" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 