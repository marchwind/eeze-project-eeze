<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
var no = "${no}";
$(document).ready(function(){
	$(".dataDetailModify").hide();
	var url = uri.serverUrl + uri.dataDetailUrl;
	sendRequestJson(url, {archiveNo : no}, dataDetailSuccess, error);
});

function error() {
	alert(msg.pageLoadFail);
}

function dataDetailSuccess(res){
	if(res.result.resultCode == "0000"){
		$("#dataSubject").text(res.info.archiveSubject);
		$("#dataSubjectEle").val(res.info.archiveSubject);
		
		var content = res.info.archiveContent.replaceAll("\n","<br/>");
		$("#dataContent").html(content);
		$("#dataContentEle").val(res.info.archiveContent);		
		
		var downloadTag = "<a href='" + res.info.attachedFilePath + res.info.attachedFileName + "'>"+res.info.attachedFileName+"</a>";
		
		$("#dataFile").html(downloadTag);
	} else {
		error();
	}
}

function dataDelCall(){
	var url = uri.serverUrl + uri.dataDelUrl;
	sendRequestJson(url, {archiveNo : no}, dataDelSuccess, noticeDelError);
}

function dataDelSuccess(res){
	alert(msg.dataDelSuccess);
	goPage('${contextPath}/cms/list.do?type=data');
}

function dataDelError(res) {
	alert(msg.dataDelFail);
}

function dataModifyChange(){
	$(".dataDetailModify").show();
	$(".dataDetailView").hide();
}

function dataViewChange(){
	$(".dataDetailModify").hide();
	$(".dataDetailView").show();
}

function dataModifyCheck(){
	if($("#dataSubjectEle").val().trim() == "") {
		alert(msg.mostSubject);
		return false;
	} else if($("#dataContentEle").val().trim() == ""){
		alert(msg.mostContent);
		return false;
	}
	
	var url = uri.serverUrl + uri.dataUpdateUrl;
	sendRequestFile("dataModifyForm", url, dataModifySuccess, dataModifyError);
}

function dataModifySuccess(res) {
	alert(msg.dataUpdateSuccess);
	location.reload();
}

function dataModifyError(res){
	alert(msg.dataUpdateFail);
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
			<table class="dataDetailView noClick">
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td class="tl" id="dataubject"></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td class="tl" id="dataFile"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td class="tl">
							<p id="dataContent"></p>
						</td>
					</tr>
				</tbody>
			</table>
			
			<form id="dataModifyForm"  enctype="multipart/form-data">
			<input type="hidden" name="archiveNo" id="archiveNo" value="${no}" />
			<table class="dataDetailModify noClick">
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td class="tl"><input type="text" id="dataSubjectEle" name="archiveSubject" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td class="tl"><input type="file" id="dataFile" name="file" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>내용</td>
						<td class="tl">
							<textarea id="dataContentEle" name="archiveContent" class="input_red input_fill" style="height: 200px"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			
			<div id="mainBottomLayer" class="member">
				<div class="fl">
					<input type="button" value="목록" class="btn_normal_red" onclick="goPage('${contextPath}/cms/list.do?type=data')"/>
				</div>
				<div class="fr">
					<ul>
						<li class="dataDetailView"><input type="button" value="수정" class="btn_normal_red" onclick="dataModifyChange()"/></li>
						<li class="dataDetailView"><input type="button" value="삭제" class="btn_normal_red" onclick="dataDelCall()"/></li>
						<li class="dataDetailModify"><input type="button" value="수정완료" class="btn_normal_red" onclick="dataModifyCheck()"/></li>
						<li class="dataDetailModify"><input type="button" value="취소" class="btn_normal_red" onclick="dataViewChange()"/></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 