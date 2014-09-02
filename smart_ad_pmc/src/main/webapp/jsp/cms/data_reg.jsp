<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
function checkAddData(){
	if($("#archiveSubject").val().trim() == "") {
		alert(msg.mostSubject);
		return false;
	} else if($("#archiveContent").val().trim() == ""){
		alert(msg.mostContent);
		return false;
	}
	
	var url = uri.serverUrl + uri.dataAddUrl;
	sendRequestFile("dataAddForm", url, dataAddSuccess, dataAddError);
}

function dataAddSuccess(res){
	goPage('${contextPath}/cms/list.do?type=data');
}

function dataAddError(res){
	alert(msg.dataAddFail);
}
</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>웹CMS > 공지사항등록</h1>
		</div>
		<div class="mainTable cmsCenter">
			<form id="dataAddForm" enctype="multipart/form-data">
			<table>
				<colgroup>
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<td>제목</td>
						<td class="tl"><input type="text" id="archiveSubject" name="archiveSubject" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td class="tl"><input type="file" id="file" name="file" class="input_red input_fill" /></td>
					</tr>
					<tr>
						<td>내용</td>
						<td class="tl">
							<textarea id="archiveContent" name="archiveContent" class="input_red input_fill" style="height: 200px"></textarea>
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
						<li><input type="button" value="등록" class="btn_normal_red" onclick="checkAddData()"/></li>
						<li><input type="button" value="취소" class="btn_normal_red" onclick="goPage('${contextPath}/cms/list.do?type=data')" /></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 