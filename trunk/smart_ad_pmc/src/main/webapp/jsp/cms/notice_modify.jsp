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
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>회원관리 > 회원상세</h1>
		</div>
		<div class="mainTable cmsCenter">
			
			<table>
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td class="tl"><input type="text" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>내용</td>
						<td class="tl">
							<textarea class="input_red input_fill" style="height: 200px"></textarea>
						</td>
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