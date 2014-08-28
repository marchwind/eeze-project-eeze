<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
var rentParam = {
		equipNo : "${no}",
		userNo : ""
};
$(document).ready(function(){
	sendRequestHtml('${contextPath}/member/popupList.do', memberList, error);	
})

function memberList(res){
	$("#rentMemberList").html(res);
}

function selectRentMember(){
	var $data = $(".popupMemberTr.on");
	
	log("member response size : " + $data.size());
	if($data.size() > 0){
		rentParam.userNo = $data.data("userno");
	} 
	
	if(confirm(msg.rentAgree)){
		var url = uri.serverUrl + uri.equipRentUrl;
		sendRequestJson(url, rentParam, smartRentSuccess, error);	
	}
}

function smartRentSuccess(res) {
	alert(msg.rent);
	closePopup();
	popupResultCall();
}

</script>
<input type="button" class="btn_popup_close" onclick="closePopup()" />
<h1>아이폰 4S 대여</h1>
<span class="font17 fr">대여일시 : 2014/07/30 15:31</span>
<div id="rentMemberList">

</div>
<div class="popupBtnContainer">
	<input type="button" value="대여" class="btn_normal_red" onclick="selectRentMember()"/>
	<input type="button" value="취소" class="btn_normal_red" onclick="closePopup()" />
</div>
			