<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
var returnParam = {
		equipNo : "${no}",
		equipRentalNo : "${equipRentalNo}",
		checkContent : ""
};

$(document).ready(function(){
	var url = uri.serverUrl + uri.equipRentInfoUrl;
	sendRequestJson(url, returnParam, rentInfoSuccess, error);
});

function rentInfoSuccess(res) {
	
	var d = new Date();
	
	$("#rentUserName").text(res.info.username);
	$("#rentUserId").text(res.info.userId);
	$("#rentUserDate").text(numberToFull(res.info.rentalDate));
	$("#rentUserReturnDate").text(d.format("yyyy-MM-dd hh:mm:ss"));
}

function rentReturnCall(){
	var checkContent = $("#checkContent").val();
	if(checkContent.trim() == "" || checkContent.trim() == null){
		checkContent = "양호";
	}
	
	returnParam.checkContent = checkContent;
	
	var url = uri.serverUrl + uri.equipReturnUrl;
	sendRequestJson(url, returnParam, smartReturnSuccess, error);	
	
}

function smartReturnSuccess(res) {
	alert(msg.rentReturn);
	closePopup();
	popupResultCall();
}

</script>

<input type="button" class="btn_popup_close" onclick="closePopup()"/>
<h1>아이폰 4S 반환</h1>
<table>
	<tr>
		<td>이름</td>
		<td class="tl" id="rentUserName"></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td class="tl" id="rentUserId"></td>
	</tr>
	<tr>
		<td>대여일시</td>
		<td class="tl" id="rentUserDate"></td>
	</tr>
	<tr>
		<td>반환일시</td>
		<td class="tl" id="rentUserReturnDate"></td>
	</tr>
	<tr>
		<td>반환상태</td>
		<td class="tl">
			<textarea class="input_gray intable" id="checkContent"></textarea>
		</td>
	</tr>
</table>
<div class="popupBtnContainer">
	<input type="button" value="반환" class="btn_normal_red" onclick="rentReturnCall()"/>
	<input type="button" value="취소" class="btn_normal_red" onclick="closePopup()"/>
</div>
			