<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var no = "${no}";

$(document).ready(function(){
	$("#answerSubmitBtn").hide();
	var url = uri.serverUrl + uri.qnaDetailUrl;
	sendRequestJson(url, {qnaNo : no}, qnaDetailSuccess, error);
});

function qnaDetailSuccess(res){
	$("#querySubject").text((res.info.querySubject == null) ? "" : res.info.querySubject);
	$("#queryEmail").text((res.info.queryEmail == null) ? "" : res.info.queryEmail);
	$("#queryContent").html(res.info.queryContent.replaceAll("\n","<br/>"));
	
	$("#queryDate").text(numberToFull(res.info.queryDate));
	
	if(res.info.answerYn != "Y") {
		$("#answerSubmitBtn").show();
		$("#manager").text(user.managerName);
		$("#answerContent").html('<textarea name="answerContent" id="answerContentId" class="input_red input_fill" style="height: 200px"></textarea>');
	} else {
		$("#manager").text((res.info.answerManagerId == null) ? "" : res.info.answerManagerId);
		$("#answerContent").html(res.info.answerContent.replaceAll("\n","<br/>"));
	}
	
}

function error(){
	alert(msg.qnaDetailFail);
}

function answerSubmit(){
	if($("#answerContentId").val().trim() == "") {
		alert(msg.mostQnaAnswer);
		return;
	}
	
	var param = {
		qnaNo : no,
		answerSubject : "답변입니다.",
		answerContent : $("#answerContentId").val()
	}
	
	var url = uri.serverUrl + uri.qnaAnswerUrl;
	sendRequestJson(url, param, qnaAnswerSuccess, error);
}

function qnaAnswerSuccess(res) {
	alert(msg.qnaAnswerSend);
	//goPage('${contextPath}/cms/list.do?type=inquiry');
	location.reload();
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>웹CMS > 1:1 문의 상세</h1>
		</div>
		<div class="mainTable cmsCenter">
			<table class="mb5 noClick">
				<colgroup>
					<col width="20%" />
					<col width="*" />
					<col width="10%" />
					<col width="20%" />
				</colgroup>
				<tbody>
					<tr>
						<td>문의</td>
						<td colspan="3" class="tl" id="querySubject"></td>
					</tr>
					<tr>
						<td>문의자 이메일</td>
						<td class="tl" id="queryEmail"></td>
						<td>문의날짜</td>
						<td class="tl" id="queryDate"></td>
					</tr>
					<tr>
						<td>문의 내용</td>
						<td colspan="3" class="tl">
							<p id="queryContent"></p>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>답변자</td>
						<td class="tl" id="manager"></td>
					</tr>
					<tr>
						<td>답변내용</td>
						<td class="tl">
							<p id="answerContent"></p>
						</td>
						
					</tr>
				</tbody>
			</table>
			
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/cms/list.do?type=inquiry')"/>
				</div>
				<div class="fr">
					<ul>
						<li><input id="answerSubmitBtn" type="button" value="답변하기" class="btn_normal_red" onclick="answerSubmit()"/></li>						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 