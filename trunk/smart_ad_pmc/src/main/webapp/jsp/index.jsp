<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/common.jsp" %>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svg.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

  // Load the Visualization API and the piechart package.
google.load('visualization', '1.0', {'packages':['corechart']});

  // Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(loadFacilityData);

function loadFacilityData(){
	var url = uri.serverUrl + uri.statisticsFacilityUrl;
	sendRequestJson(url, {}, facilityDataSuccess, error);
}

function facilityDataSuccess(res) {
	var facilityChartArr = [];
	
	$(res.list).each(function(){
		var eleArr = [];
		eleArr.push(this.facilityName);
		eleArr.push(this.percentRate);
		
		facilityChartArr.push(eleArr);
	});
	
	pieChartDraw(facilityChartArr);
	
	loadWatchData();
}
  
function pieChartDraw(data){
	// Create the data table.
    var pieData = new google.visualization.DataTable();
    pieData.addColumn('string', 'Topping');
    pieData.addColumn('number', 'Slices');
    pieData.addRows(data);

    // Set chart options
    var pieOptions = {
    		chartArea : {width:'90%', height:'80%'},
    		pieSliceText: 'percentage',
    		legend : {position : 'Right', 
    			textStyle : {color : '#555555', fontSize : 15, fontName : 'Nanum Barun Gothic'}
    		},
    		pieSliceTextStyle : {color : "#ffffff", fontName : 'Nanum Barun Gothic', fontSize : 13},
    		slices:{
    			0 : {color : '#8095f1'},
    			1 : {color : '#80d1f1'},
    			2 : {color : '#80efa6'},
    			3 : {color : '#f2d07c'},
    			4 : {color : '#e1ef7c'},
    			5 : {color : '#f26bf0'},
    			6 : {color : '#b56bf1'}
    		}};

    // Instantiate and draw our chart, passing in some options.
    var pieChart = new google.visualization.PieChart(document.getElementById('pieChart'));
    pieChart.draw(pieData, pieOptions);
}

var watchUserDataArr = [];
var watchReserveDataArr = [];

function loadWatchData(){
	var url = uri.serverUrl + uri.statisticsUserUrl;
	sendRequestJson(url, {}, userDataSuccess, error);
		
}

function userDataSuccess(res){
	$("#totalUser").text(numberWithCommas(res.list[0].totalCnt));
	watchUserDataArr.push(res.list[0].firstWeekCnt);
	watchUserDataArr.push(res.list[0].secondWeekCnt);
	watchUserDataArr.push(res.list[0].thirdWeekCnt);
	watchUserDataArr.push(res.list[0].forthWeekCnt);
	watchUserDataArr.push(res.list[0].fifthWeekCnt);
	
	var url = uri.serverUrl + uri.statisticsReserveUrl;
	sendRequestJson(url, {}, ReserveDataSuccess, error);
}

function ReserveDataSuccess(res) {
	$("#totalReserve").text(numberWithCommas(res.list[0].totalCnt));
	watchReserveDataArr.push(res.list[0].firstWeekCnt);
	watchReserveDataArr.push(res.list[0].secondWeekCnt);
	watchReserveDataArr.push(res.list[0].thirdWeekCnt);
	watchReserveDataArr.push(res.list[0].forthWeekCnt);
	watchReserveDataArr.push(res.list[0].fifthWeekCnt);
	
	lineChartDraw();
}

function lineChartDraw() {
    
	var d = new Date();
	var m = d.getMonth() + 1;
	
	var weekCnt = weekCount(d.getFullYear(), m);
	var lData = [];
	lData.push(['month', '가입자추이', '이용자 추이']);
	
	for(i=0; i < weekCnt; i++){
		lData.push([m+'월 '+(i+1)+'째주', watchUserDataArr[i], watchReserveDataArr[i]]);
	}
	
    var lineData = google.visualization.arrayToDataTable(lData);

    var lineOptions = {
    		chartArea : {width: '70%', height:'80%', left:50},    		
    		legend : {position : 'right', 
    			textStyle : {color : '#555555', fontSize : 15, fontName : 'Nanum Barun Gothic'}
    		},
    		hAxis : {textStyle : {color : '#555555', fontSize : 15, fontName : 'Nanum Barun Gothic'}},
    		vAxis : {textStyle : {color : '#555555', fontSize : 15, fontName : 'Nanum Barun Gothic'}},
    		pointSize: 5,
    		pointShape : 'cicle'
    };

    var chart = new google.visualization.LineChart(document.getElementById('lineChart'));
    chart.draw(lineData, lineOptions);
    
}
  
$(document).ready(function(){
	var svg = $("#facilityMap").svg({loadURL : '${contextPath}/resources/map.svg', onLoad : svgloaded, changeSize:true});
});

function svgloaded(svg, error){
	$("svg .clickable").css("cursor","pointer");
	$("svg .clickable").click(function() {
		log($(this).attr("data"));
		//goPage("${contextPath}/facility/detail.do?no="+$(this).attr("id"));
		
		goPopupSite("http://" + $(this).attr("data"));
		
	});
	
	var url = uri.serverUrl + uri.facilityListUrl;
	sendRequestJson(url, {}, facilityListSuccess, error);
}

function facilityListSuccess(res){
	$(res.list).each(function(){
		var id = this.facilityNo;
		var checkIn = this.facilityCheckInYn;
		
		if(checkIn == "N"){
			$("#"+id+"_back").attr("fill","#B1B5B1");
		} else {
			$("#"+id+"_back").attr("fill","#F2997D");
		}
		
		equipmentSet(id, this.list);
		
	});
}

function equipmentSet(f, list){
	var cnt = 1;
	$(list).each(function(){
		if(this.equipState == "E01001") {
			if(this.equipWorkingYn == "Y") {
				$("#"+this.equipNo).attr("fill","#ffef42");
			} else {
				$("#"+this.equipNo).attr("fill","#80efa6");
			}	
		} else {
			$("#"+this.equipNo).attr("fill","#f73363");
		}
		
	});
}

function error(){
	alert(msg.mainError);
}
</script>
</head>
<body>

<div id="wrap">
	<%@ include file="common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>대쉬보드</h1>
		</div>
		<div class="mainTable">
			<div id="mainLeftLayer">
				<div class="mainBox">
					<dl>
						<dt>
							<h2>시설 및 장비 사용현황</h2>
							<span>각 시설의 현재 상황을 표시한 도면</span>
						</dt>
						<dd>
							<div class="mainBoxCont">
								<div id="facilityAlert">
									<div id="equipmentBox">
										<h3>장비</h3>
										<ul>
											<li><label>미구동</label>
												<svg>
												  <circle cx="7" cy="7" r="7" fill="#80efa6" />
												</svg>
											</li>
											<li><label>구동중</label>
												<svg>
												  <circle cx="7" cy="7" r="7" fill="#ffef42" />
												</svg>
											</li>
											<li><label>고장</label>
												<svg>
												  <circle cx="7" cy="7" r="7" fill="#f73363" />
												</svg>
											</li>
										</ul>
									</div>
									<div id="facilityBox">
										<h3>설비</h3>
										<ul>
											<li>공실중
												<svg>
												  <rect width="30" height="11" style="fill:#B1B5B1;" />
												</svg>
											</li>
											<li>입실중
												<svg>
												  <rect width="30" height="11" style="fill:#F2997D;" />
												</svg>
											</li>
										</ul>
									</div>
								</div>
								<div id="facilityMap">
									
								</div>
								<div id="facilityGuide">
									<ul>
										<li><span>1</span><a href="${contextPath}/facility/detail.do?no=FC0000000856">녹음스튜디오</a></li>
										<li><span>2</span><a href="${contextPath}/facility/detail.do?no=FC0000000852">창작지원1실</a></li>
										<li><span>3</span><a href="${contextPath}/facility/detail.do?no=FC0000000853">창작지원2실</a></li>
										<li><span>4</span><a href="${contextPath}/facility/detail.do?no=FC0000000858">서버실</a></li>
									</ul>
									<ul>
										<li><span>5</span><a href="${contextPath}/facility/detail.do?no=FC0000000857">매체적합성테스트실</a></li>
										<li><span>6</span><a href="${contextPath}/facility/detail.do?no=FC0000000854">창작지원3실</a></li>
										<li><span>7</span><a href="${contextPath}/facility/detail.do?no=FC0000000855">창작지원4실</a></li>
										<li><span>8</span><a href="${contextPath}/facility/detail.do?no=FC0000000859">회의실</a></li>
									</ul>
								</div>
							</div>
						</dd>
					</dl>
				</div>
			</div>
			<div id="mainRightLayer">
				<div class="mainBox">
					<dl>
						<dt>
							<h2>시설 이용 분포 그래프</h2>
							<span>각 시설별 이용 분포 현황</span>
						</dt>
						<dd>
							<div id="pieChart"></div>
						</dd>
					</dl>
				</div>
				<div class="mainBox mt">
					<dl>
						<dt>
							<h2>회원 현황</h2>
							<span>회원 가입자 및 이용 현황</span>
						</dt>
						<dd>
							<div id="memberState">
								<span><label>전체가입자</label> <em id="totalUser"></em>명</span>
								<span><label>시설이용자</label> <em id="totalReserve"></em>명</span>
							</div>
							<div id="lineChart"></div>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="common/footer.jsp" %>

</div>

</body>
</html> 