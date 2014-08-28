<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
$(document).ready(function(){
	sendRequestHtml('${contextPath}/member/popupList.do', memberList, error);	
})

function memberList(res){
	$("#reserveMemberList").html(res);
}

function selectReserveMember(){
	var $data = $(".popupMemberTr.on");
	log("member response size : " + $data.size());
	if($data.size() > 0){
		var user = {
			userNo : $data.data("userno"),
			userName : $data.data("username"),
			userId : $data.data("userid"),
			userCellPhone : $data.data("usercellphone"),
			enterpriseName : $data.data("enterprisename"),
			userStatus : $data.data("userStatus"),
		}
		
		setMemberValue(user);
	} 
	closePopup();
}

</script>
<input type="button" class="btn_popup_close" onclick="closePopup()"/>
<h1>회원검색</h1>
<div id="reserveMemberList">

</div>
<div class="popupBtnContainer">
	<input type="button" value="확인" class="btn_normal_red" onclick="selectReserveMember()"/>
	<input type="button" value="취소" class="btn_normal_red" onclick="closePopup()"/>
</div>
			