<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
function checkFaqSubmit(){
	if($("#question").val().trim() == ""){
		alert(msg.mostfaqQustion);
		return false;
	} else if($("#answer").val().trim() == ""){
		alert(msg.mostfaqAnswer);
		return false;
	}
	
	var url = uri.serverUrl + uri.faqAddUrl;
	sendRequest("faqAddForm", url, faqAddSuccess, faqAddError);
}

function faqAddSuccess() {
	goPage('${contextPath}/cms/list.do?type=faq');
}

function faqAddError() {
	alert(msg.faqAddFail);
}
</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>회원관리 > 회원등록</h1>
		</div>
		<div class="mainTable cmsCenter">
			<form id="faqAddForm">
			<table class="noClick">
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>질문</td>
						<td class="tl"><input name="question" id="question" type="text" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>답변</td>
						<td class="tl">
							<textarea name="answer" id="answer" class="input_red input_fill" style="height: 200px"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/cms/list.do?type=faq')"/>
				</div>
				<div class="fr">
					<ul>
						<li><input type="button" value="등록" class="btn_normal_red" onclick="checkFaqSubmit()"/></li>
						<li><input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/cms/list.do?type=faq')" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 