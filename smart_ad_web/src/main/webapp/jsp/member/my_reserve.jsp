<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/commonLogin.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	listCall();
});

function listCall() {
	var url = uri.serverUrl + uri.myReserveListUrl;
	sendRequest("listForm", url, reserveList, error);
}

function reserveList(res) {
	log(res);
	var $table = $("#listTable");
	$table.empty();
	
	var tag = '<colgroup><col width="16%"/><col width="16%"/><col width="*"/><col width="16%"/><col width="16%"/><col width="16%"/></colgroup>' +
	  '<tr><th>번호</th><th>공간</th><th>신청자</th><th>신청일시</th><th>신청상태</th><th>취소요청</th></tr>';
	
	if(res.result.resultCode == "0000"){
				  
		//var cnt = res.page.totalCount - (res.page.currentPage - 1)*res.page.unitPerPage;
		var cnt = 1;
		
		$(res.list).each(function(){
			tag += '<tr>';
			tag += '<td>'+cnt+'</td>';
			tag += '<td>'+this.facilityName+'</td>';
    		tag += '<td>'+user.userName+'</td>';
    		tag += '<td>'+numberToDate(this.reserveStartDate)+' ' + ((this.reserveTimeZone < 2)? "오전" : "오후") + '</td>';
    		
    		switch(this.reserveState) {
    		case 'R02001' :
    			tag += '<td class="roomcancel">취소</td>'; 
    			tag += '<td></td>';
    			break;
    		case 'R02002' :
    			tag += '<td class="roomwait">입실대기</td>';
    			tag += '<td><input type="button" class="btn_8" value="취소" onclick="reserveCancel(\''+this.reserveDetailNo+'\')" /></td>';
    			break;    			
    		case 'R02003' :
    			tag += '<td>입실중</td>'; 
    			tag += '<td></td>';
    			break;
    		case 'R02004' :
    			tag += '<td class="rooming">입실완료</td>'; 
    			tag += '<td></td>';
    			break;
    		}
    		
    		tag += '</tr>';
    		
    		//cnt--;
    		cnt++;
		});
		
		$table.html(tag);
		
		page(res.page.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		tag += "<tr><td colspan='6'>예약정보가 없습니다.</td></tr>"
		
		$table.html(tag);
		
		page(res.page.currentPage, res.page.totalPage);	
	} else {
		error();
	}
}

function error() {
	alert(msg.myReserveListFail);
}

function reserveCancel(no) {
	if(confirm(msg.reserveCancel)){
		sendRequestJson(uri.serverUrl + uri.myReserveCancelUrl, {reserveDetailNo : no}, successCancel, errorCancel);	
	}
}

function successCancel(res){
	if(res.result.resultCode == "0000"){
		listCall();
	} else {
		errorCancel();
	}
}

function errorCancel() {
	alert(msg.myReserveCancelFail);
}

</script>
</head>
<body>
<div id="wrap">
    <%@ include file="/common/leftNavi.jsp" %>
    <div id="mainFrame" class="subFrame">
    	<div id="subPage">
            <div id="subPageTop">
                <span id="subPageTitle">
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_mypage.png" /></span>
                    <span class="subPageTitleRight">/ 예약현황</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 마이페이지</span>
                    <span class="navi_2dep">- 예약현황</span>
                </span>
            </div>
            
            <div id="subPageCont">
                <div id="listContainer">
                	<div id="listSearch">
                		<form id="listForm">
		                	<input type="hidden" name="currentPage" id="currentPage" value="1" />
		                	<input type="hidden" id="totalPage" value="1" />
		                	<input type="hidden" name="unitPerPage" value="10" />
                			<select id="reserveListFac" name="facilityNo" onchange="listCall()">
	                			<option value="" selected="selected">지원실전체</option>
	                			<option value="FC0000000852">창작1실</option>
	                			<option value="FC0000000853">창작2실</option>
	                			<option value="FC0000000854">창작3실</option>
	                			<option value="FC0000000855">창작4실</option>
	                			<option value="FC0000000856">녹음스튜디오</option>
	                			<option value="FC0000000857">매체적합테스트실</option>
                			</select>
                		
                		</form>
                	</div>
	                <table id="listTable" class="guideContainer">
	                	<colgroup>
	                		<col width="16%"/>
	                		<col width="16%"/>
	                		<col width="*"/>
	                		<col width="16%"/>
	                		<col width="16%"/>
	                		<col width="16%"/>
	                	</colgroup>
	                	<tr>
	                		<th>번호</th>
	                		<th>공간</th>
	                		<th>신청자</th>
	                		<th>신청일시</th>
	                		<th>신청상태</th>
	                		<th>취소요청</th>
	                	</tr>
	                	
	                </table>
	                <ul class="page">
	                	<li class="btn_6 prePage"><</li>
	                	<li class="btn_6 on">1</li>
	                	<li class="btn_6">2</li>
	                	<li class="btn_6 nextPage">></li>
	                </ul>
	                
                </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>