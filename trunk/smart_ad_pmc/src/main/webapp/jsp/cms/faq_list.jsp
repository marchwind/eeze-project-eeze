<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">

var faqArr = [];

$(document).ready(function(){
	pageValue.currentPage = 1;
	pageListCall();
});

function pageListCall() {
	var param = pageValue;
	var url = uri.serverUrl + uri.faqListUrl;
	sendRequestJson(url, param, faqListSuccess, error);
}

function faqListSuccess(res){
	faqArr = [];
	var $table = $("#faqList tbody");
	$table.empty();
	$(res.list).each(function(){
		faqArr[this.faqNo] = {q : this.question, a : this.answer};
		
		var qt = '<tr id="'+this.faqNo+'_q" class="question">' + faqViewSet('q', this.faqNo) + '</tr>'; 
		var at = '<tr id="'+this.faqNo+'_a" class="answer">' + faqViewSet('a', this.faqNo) + '</tr>';
		
		$table.append(qt+at);
	});
	
	page(pageValue.currentPage, res.page.totalPage);
	
	faqButtonSet();
}

function faqViewSet(type, no){
	var returnHtml = "";
	if(type == "q"){
		returnHtml += '<td class="tc">Q</td>' +
					'<td id="'+no+'_qt">'+faqArr[no].q+'</td>' +
					'<td class="tc">admin</td>';
	} else {
		returnHtml += '<td class="tc">A</td>' +
					'<td id="'+no+'_at">'+faqArr[no].a.replaceAll("\n","<br/>")+'</td>' +
					'<td class="tc"><input type="button" value="수정" class="btn_normal_red mb5" onclick="faqModifyChange(\''+no+'\')" />' +
					'<input type="button" value="삭제" class="btn_normal_red" onclick="faqDelCall(\''+no+'\')"/></td>';
	}
	
	return returnHtml;
}

function faqModifySet(type, no){
	var returnHtml = "";
	if(type == "q"){
		returnHtml += '<td class="tc">Q</td>' +
					'<td id="'+no+'_qt"><input id="'+no+'_q_input" type="text" class="input_red input_fill" value="'+faqArr[no].q+'" /></td>' +
					'<td class="tc">admin</td>';
	} else {
		returnHtml += '<td class="tc">A</td>' +
					'<td id="'+no+'_at"><textarea id="'+no+'_a_input" class="input_red input_fill" style="height: 200px">'+faqArr[no].a.replaceAll("</br>","\n").replaceAll("<br/>","\n")+'</textarea></td>' +
					'<td class="tc"><input type="button" value="수정완료" class="btn_normal_red mb5" onclick="faModifySubmit(\''+no+'\')" />' +
					'<input type="button" value="취소" class="btn_normal_red" onclick="faViewChange(\''+no+'\')"/></td>';
	}
	
	return returnHtml;
}

function faqButtonSet(){
	$(".question").click(function(){
		var id = $(this).attr("id").split("_")[0];
		if(!$(this).hasClass("mod")){
			if($(this).hasClass("on")){
				$(this).removeClass("on");
				$("#"+id+"_a").hide();
			} else {
				$(this).addClass("on");
				$("#"+id+"_a").show();
			}	
		}
	});
}

function faqDelCall(no){
	var url = uri.serverUrl + uri.faqDelUrl;
	sendRequestJson(url, {faqNo : no}, faqDellSuccess, faqDelError);
}

function faqDellSuccess(res){
	alert(msg.faqDelete);
	goPage("${contextPath}/cms/list.do?type=faq")
}

function faqDelError(res) {
	alert(msg.faqDeleteFail);
}

function faViewChange(no) {
	$("#" + no + "_q").removeClass("mod");
	$("#" + no + "_q").html(faqViewSet('q', no));
	$("#" + no + "_a").html(faqViewSet('a', no));
}
function faqModifyChange(no){
	$("#" + no + "_q").addClass("mod");
	$("#" + no + "_q").html(faqModifySet('q', no));
	$("#" + no + "_a").html(faqModifySet('a', no));
}

function faModifySubmit(no){
	var param = {
		faqNo : no,
		question : $("#"+no+"_q_input").val(),
		answer : $("#"+no+"_a_input").val()
	}
	
	var url = uri.serverUrl + uri.faqUpdateUrl;
	sendRequestJson(url, param, faqUpdateSuccess, faqUpdateError);
	
}

function faqUpdateSuccess(res) {
	alert(msg.faqUpdate);
	faqArr[res.info.faqNo] = {q : $("#"+res.info.faqNo+"_q_input").val(), a : $("#"+res.info.faqNo+"_a_input").val()};
	
	faViewChange(res.info.faqNo);
}

function faqUpdateError(res) {
	alert(msg.faqUpdateFail);
}

</script>
<table id="faqList">
	<colgroup>
		<col width="10%" />
		<col width="*" />
		<col width="15%" />
	</colgroup>
	<thead>
		<tr>
			<th>질문</th>
			<th>제목</th>
			<th>등록자</th>
		</tr>
	</thead>
	<tbody>
		<tr class="question">
			<td class="tc">Q</td>
			<td>회원가입은 어떻게 하나요?</td>
			<td class="tc">admin</td>
		</tr>
		<tr class="answer">
			<td class="tc">A</td>
			<td>
				메인에서 오른쪽 상단에 회원가입 버튼을 클릭하여 회원 가입 폼에 입력 후 가입하시면, 가입하신 이메일로 인증 URL이 발송 됩니다.<br/>
				인증 URL을 클릭하시면 자동으로 인증이 완료 되어 회원 가입이 완료 됩니다.
			</td>
			<td class="tc">
				<input type="button" value="수정" class="btn_normal_red mb5" />
				<input type="button" value="삭제" class="btn_normal_red" />
			</td>
		</tr>
		<tr class="question">
			<td>Q</td>
			<td class="tl">하루 종일 예약이 가능한가요?</td>
			<td>admin</td>
		</tr>
	</tbody>
</table>
<div class="page">
	<ul>
		<li> << </li>
		<li class="on">1</li>
		<li>2</li>
		<li> >> </li>
	</ul>
</div>
<input type="button" value="FAQ등록" class="btn_normal_red bottom_right_btn" onclick="goPage('${contextPath}/cms/faqReg.do')"/>