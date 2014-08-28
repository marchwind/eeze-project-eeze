<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">

var reserveTime = [];
var reserveDayListText = "";

var reserveParamInfo = {
	userNo : "",
	facilityNo : "",
	reserveArray : new Array(),
	enterpriseName : "",
	workerName : "",
	visitCount : 0,
	workContent : ""
};

$(document).ready(function(){
	var date = new Date();
	var y = ('${y}' == "") ? date.getFullYear() : Number('${y}'); 
	var m = ('${m}' == "") ? (date.getMonth() + 1) : Number('${m}');
	var id = '${id}';
	
	reserve.year = y;
	reserve.month = m;
	
	if(id == "") {
		id = facility.c1;
		reserve.facName = "창작1실";
		reserve.fac = id;
		
		reserveInitCall();
	} else {
		if(id == "r1") {
			$("#r1").trigger("click");
		} else if(id == "t1") {
			$("#t1").trigger("click");
		} else {
			$("#c").trigger("click");
			$("#"+id).trigger("click");
		}
	}
	
});

function tabChange(obj) {
	var $obj = $(obj);
	
	$("#tabBoxMenu li").removeClass("on");
	$obj.addClass("on");
	
	reserve.cd = $obj.data("tab");
	
	if($obj.data("tab") == "c"){
		$("#subTabMenu").show();
		$("#subTabMenu li:first-child").trigger("click");
	} else {
		$("#subTabMenu").hide();		
		reserve.facName = $obj.text();
		reserve.fac = facility[$obj.data("tab")];
		
		reserveInitCall();
		
	}
}

function subTabChange(obj){
	var $obj = $(obj);
	
	$("#subTabMenu li").removeClass("on");
	$obj.addClass("on");
	
	reserve.facName = $obj.text();
	reserve.fac = facility[$obj.data("tab")];
	
	reserve.cd = $obj.data("tab");
	
	reserveInitCall();
}

function reserveInitCall() {
	
	sendRequestHtml('${contextPath}/reserve/reserveCal.do', successCal, error);
}

function successCal(res) {
	$("#reserveCont").html(res);
}

function error() {
	alert(msg.reserveListFail);
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
                	<span class="subPageTitleLeft"><img src="${contextPath}/resources/images/common/title_reservation.png" /></span>
                    <span class="subPageTitleRight">/ 예약</span>
                </span>
                <span id="subPageNavi">
                    <span class="navi_home"><img src="${contextPath}/resources/images/common/sub_navi_home_icon.png" /></span>
                    <span class="navi_1dep">- 창작공간AD예약</span>
                    <span class="navi_2dep">- 예약</span>
                </span>
            </div>
            <div id="subPageCont">
               <div id="tabBox">
               		<ul id="tabBoxMenu">	
               			<li id="c" class="on" data-tab="c" onclick="tabChange(this)">창작지원실</li>
               			<li id="r1" data-tab="r1" onclick="tabChange(this)">녹음스튜디오</li>
               			<li id="t1" data-tab="t1" onclick="tabChange(this)">매체적합성테스트실</li>
               		</ul>
               		<ul id="subTabMenu">
               			<li id="c1" class="on" data-tab="c1" onclick="subTabChange(this)">창작1실</li>
               			<li id="c2" data-tab="c2" onclick="subTabChange(this)">창작2실</li>
               			<li id="c3" data-tab="c3" onclick="subTabChange(this)">창작3실</li>
               			<li id="c4" data-tab="c4" onclick="subTabChange(this)">창작4실</li>
               		</ul>
               		<div id="reserveCont">
               			
               		</div>
               		
               </div>
            </div>
        </div>
	</div>
	<%@ include file="/common/rightBox.jsp" %>
</div>
</body>
</html>