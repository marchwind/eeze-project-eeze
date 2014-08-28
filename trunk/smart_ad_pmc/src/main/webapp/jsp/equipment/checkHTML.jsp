<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function() {
	
	var d = new Date();
	$("#checkDate").text(d.format("yyyy/MM/dd hh:mm:ss"));
	
	$("#checker").text(user.managerId);
})

function checkSubmit() {
	
	if($("#checkContent").val() == ""){
		$("#checkContent").val("양호");
	}
	
	var param = {
		equipState : $("#equipState").val(),
		checker : $("#checker").text(),
		checkContent : $("#checkContent").val(),
		equipNo : no
	}
	
	var url = uri.serverUrl + uri.equipCheckUrl;
	sendRequestJson(url, param, equipCheckSuccess, error);	
}

function equipCheckSuccess(res) {
	alert(msg.equipCheck);
	location.reload();
}

</script>
<input type="button" class="btn_popup_close" onclick="closePopup()"/>
<h1>점검</h1>
<table>
	<tr>
		<td>점검일시</td>
		<td class="tl" id="checkDate"></td>
	</tr>
	<tr>
		<td>점검자ID</td>
		<td class="tl" id="checker"></td>
	</tr>
	<tr>
		<td>가능여부</td>
		<td class="tl">
			<select class="input_red" id="equipState">
				<option value="E04001">정상</option>
				<option value="E04002">고장</option>
				<option value="E04003">수리중</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>점검내용</td>
		<td class="tl">
			<textarea id="checkContent" class="input_gray intable"></textarea>
		</td>
	</tr>
</table>

<div class="popupBtnContainer">
	<input type="button" value="점검완료" class="btn_normal_red" onclick="checkSubmit()"/>
	<input type="button" value="취소" class="btn_normal_red" onclick="closePopup()" />
</div>
