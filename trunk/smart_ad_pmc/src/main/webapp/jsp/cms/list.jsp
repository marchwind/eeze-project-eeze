<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
</head>
<script type="text/javascript">
$(document).ready(function(){
	var type = "${type}";
	
	if(type == "faq") {
		$("#tab_faq").trigger("click");
	} else if(type == "inquiry") {
		$("#tab_inquiry").trigger("click");
	} else if(type == "data") {
		$("#tab_data").trigger("click");
	} else {
		$("#tab_notice").trigger("click");
	}
	
})

function tabButton(obj){
	$(".btn_squre").removeClass("on");
	$(obj).addClass("on");
}

function noticeListCall(obj) {
	tabButton(obj);
	sendRequestHtml('${contextPath}/cms/noticeList.do', listCallSuccess, error);
}

function dataListCall(obj) {
	tabButton(obj);
	sendRequestHtml('${contextPath}/cms/dataList.do', listCallSuccess, error);
}

function faqListCall(obj){
	tabButton(obj);
	sendRequestHtml('${contextPath}/cms/faqList.do', listCallSuccess, error);
}

function inquiryListCall(obj){
	tabButton(obj);
	sendRequestHtml('${contextPath}/cms/inquiryList.do', listCallSuccess, error);
}

function listCallSuccess(res) {
	$(".mainTable").html(res);
}

function error() {
	alert(msg.pageLoadFail);
}
</script>
<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>웹CMS</h1>
		</div>
		<div id="listTop">
			<div id="tabSelect">
				<ul>
					<li><input type="button" value="공지사항" id="tab_notice" class="btn_squre on" onclick="noticeListCall(this)"/></li>
					<li><input type="button" value="자료실" id="tab_data" class="btn_squre on" onclick="dataListCall(this)"/></li>
					<li><input type="button" value="FAQ" id="tab_faq" class="btn_squre" onclick="faqListCall(this)"/></li>
					<li><input type="button" value="1:1문의" id="tab_inquiry" class="btn_squre" onclick="inquiryListCall(this)" /></li>
				</ul>
			</div>
		</div>
		<div class="mainTable">
			
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 