<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var no = "${no}";
$(document).ready(function(){
	$(".noticeDetailModify").hide();
	var url = uri.serverUrl + uri.noticeDetailUrl;
	sendRequestJson(url, {notiNo : no}, noticeDetailSuccess, error);
});

function error() {
	alert(msg.pageLoadFail);
}

function noticeDetailSuccess(res){
	if(res.result.resultCode == "0000"){
		$("#notiSubject").text(res.info.notiSubject);
		$("#notiSubjectEle").val(res.info.notiSubject);
		
		var content = res.info.notiContent.replaceAll("\n","<br/>");
		$("#notiContent").html(content);
		$("#notiContentEle").val(res.info.notiContent);		
	} else {
		error();
	}
}

function noticeDelCall(){
	var url = uri.serverUrl + uri.noticeDelUrl;
	sendRequestJson(url, {notiNo : no}, noticeDelSuccess, noticeDelError);
}

function noticeDelSuccess(res){
	alert(msg.noticeDelSuccess);
	goPage('${contextPath}/cms/list.do');
}

function noticeDelError(res) {
	alert(msg.noticeDelFail);
}

function noticeModifyChange(){
	$(".noticeDetailModify").show();
	$(".noticeDetailView").hide();
}

function noticeViewChange(){
	$(".noticeDetailModify").hide();
	$(".noticeDetailView").show();
}

function noticeModifyCheck(){
	if($("#notiSubjectEle").val().trim() == "") {
		alert(msg.mostSubject);
		return false;
	} else if($("#notiContentEle").val().trim() == ""){
		alert(msg.mostContent);
		return false;
	}
	
	var url = uri.serverUrl + uri.noticeUpdateUrl;
	sendRequestFile("noticeModifyForm", url, noticeModifySuccess, noticeModifyError);
}

function noticeModifySuccess(res) {
	alert(msg.noticeUpdateSuccess);
	location.reload();
}

function noticeModifyError(res){
	alert(msg.noticeUpdateFail);
}

</script>

</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>웹CMS > 공지사항 상세</h1>
		</div>
		<div class="mainTable cmsCenter">
			<table class="noticeDetailView noClick">
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td class="tl" id="notiSubject"></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td class="tl" id="notiFile"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td class="tl">
							<p id="notiContent"></p>
						</td>
					</tr>
				</tbody>
			</table>
			
			<form id="noticeModifyForm"  enctype="multipart/form-data">
			<input type="hidden" name="notiNo" id="notiNo" value="${no}" />
			<table class="noticeDetailModify noClick">
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td class="tl"><input type="text" id="notiSubjectEle" name="notiSubject" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td class="tl"><input type="file" id="notiFile" name="notiFile" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>내용</td>
						<td class="tl">
							<textarea id="notiContentEle" name="notiContent" class="input_red input_fill" style="height: 200px"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/cms/list.do')"/>
				</div>
				<div class="fr">
					<ul>
						<li class="noticeDetailView"><input type="button" value="수정" class="btn_normal_red" onclick="noticeModifyChange()"/></li>
						<li class="noticeDetailView"><input type="button" value="삭제" class="btn_normal_red" onclick="noticeDelCall()"/></li>
						<li class="noticeDetailModify"><input type="button" value="수정완료" class="btn_normal_red" onclick="noticeModifyCheck()"/></li>
						<li class="noticeDetailModify"><input type="button" value="취소" class="btn_normal_red" onclick="noticeViewChange()"/></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 