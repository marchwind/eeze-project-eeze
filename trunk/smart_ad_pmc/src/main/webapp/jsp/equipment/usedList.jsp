<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

var colors = ['#8095f1','#80d1f1','#80efa6','#f2d07c','#e1ef7c','#f26bf0','#b56bf1'
              ,'#8095f1','#80d1f1','#80efa6','#f2d07c','#e1ef7c','#f26bf0','#b56bf1'
              ,'#8095f1','#80d1f1','#80efa6','#f2d07c','#e1ef7c','#f26bf0','#b56bf1'];

// Load the Visualization API and the piechart package.
google.load('visualization', '1.0', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(statisticsLoad);

$(document).ready(function(){
	var url = uri.serverUrl + uri.equipGetUrl;
	sendRequestJson(url, {equipNo : "${no}"}, equipGetSuccess, error);
	
	//pageListCall();	
});

function equipGetSuccess(res) {
	$("#equipName").text(res.info.equipName);
	$("#facilityName").text(res.info.facilityName);
	$("#equipIp").text(res.info.equipIp);
	$("#equipOs").text(res.info.equipOs);
}

function pageListCall(){
	var param = pageValue;
	param['equipNo'] = "${no}"; 
	var url = uri.serverUrl + uri.equipUsedStatistics;
	sendRequestJson(url, param, equipUsedListSuccess, error);
}

function statisticsLoad() {
	var url = uri.serverUrl + uri.equipUsedStatistics;
	sendRequestJson(url, {equipNo : "${no}"}, statisticsLoadSuccess, error);
}

function statisticsLoadSuccess(res){
	
	var cpuData = [['Time', 'CPU']];
	$(res.info.cpuUse).each(function(){
		var el = [];
		el.push(this.gTime);
		el.push(Number(this.gValue));
		
		cpuData.push(el);
	});
	
	var data = google.visualization.arrayToDataTable(cpuData);

	var options = {
		legend : {position: 'none'},
	  	hAxis: {title: 'Time',  titleTextStyle: {color: '#333'}},
	  	vAxis: {minValue: 0},
	  	pointShape : "circle",
	  	pointSize : 5,
	  	series : { 0 : {color : colors[0]} }
	};
	
	var chart = new google.visualization.AreaChart(document.getElementById('cpuChart'));
	chart.draw(data, options);
	
	memoryChartDraw(res);
}

function memoryChartDraw(res){
	
	var memoryData = [['Time', 'Memory']];
	$(res.info.memoryUse).each(function(){
		var el = [];
		el.push(this.gTime);
		el.push(Number(this.gValue));
		
		memoryData.push(el);
	});
	
	var data = google.visualization.arrayToDataTable(memoryData);

 	var options = {
 		legend : {position: 'none'},
 	  	hAxis: {title: 'Time',  titleTextStyle: {color: '#333'}},
 	  	vAxis: {minValue: 0},
 	  	pointShape : "circle",
 	  	pointSize : 5,
 	  	series : { 0 : {color : colors[1]} }
 	};
 	
 	var chart = new google.visualization.AreaChart(document.getElementById('memoryChart'));
 	chart.draw(data, options);
 	
 	processChartDraw(res);
}

function processChartDraw(res) {
	
	var pData = [['Process', '지속율', { role: 'style' }]];
	
	$(res.info.process).each(function(idx){
		var el = [];
		el.push(this.pName);
		el.push(this.cRate);
		el.push(colors[idx]);
		
		pData.push(el);
	});
	
	var data = google.visualization.arrayToDataTable(pData);

	var options = {
  		legend : {position: 'none'},
  		bar: {groupWidth: "95%"}
  	};
  	
  	var chart = new google.visualization.ColumnChart(document.getElementById('processChart'));
  	chart.draw(data, options);
}

function equipUsedListSuccess(res){
	/*
	var $table = $("#usedList tbody");
	$table.empty();
	if(res.result.resultCode == "0000") {
		
		$(res.list).each(function(){
			var tag = '<tr>'+
					'<td class="tc">' + numberToFull(this.usedDate) + '</td>' +
					'<td>' + this.usedSoftware + '</td>'+
					'</tr>';
			$table.append(tag);
			
		});
		page(pageValue.currentPage, res.page.totalPage);	
	} else if(res.result.resultCode == "0400") {
		$table.html('<tr><td class="tc" colspan="2">데이터가 없습니다.</td></tr>');
		page(pageValue.currentPage, 0);
	}
	*/
	
	
}

function error(){
	alert(msg.equipmentError);
}

function back() {
	window.history.back();
}

</script>
</head>

<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>장비관리 > 장비상세 > 사용이력</h1>
		</div>
		<div class="mainTable">
			<table  class="noClick">
				<colgroup>
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
					<col width="25%" />
				</colgroup>
				<thead>
					<tr>
						<th>장비명</th>
						<th>설치시설</th>
						<th>IP 주소</th>
						<th>OS</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="tc" id="equipName"></td>
						<td class="tc" id="facilityName"></td>
						<td class="tc" id="equipIp"></td>
						<td class="tc" id="equipOs"></td>
					</tr>
				</tbody>
			</table>
			
			<div id="equipStatisticsTop">
				<div class="fl mainBox">
					<dl>
						<dt>
							<h2>CPU 분포 그래프</h2>
							<span>CPU 사용 현황</span>
						</dt>
						<dd>
							<div id="cpuChart" class="chart"></div>
						</dd>
					</dl>
				</div>
				<div class="fr mainBox">
					<dl>
						<dt>
							<h2>메모리 분포 그래프</h2>
							<span>메모리 사용 현황</span>
						</dt>
						<dd>
							<div id="memoryChart" class="chart"></div>
						</dd>
					</dl>
				</div>
			</div>
			
			<div id="equipStatisticsList" class="clear">
				<div class="mainBox">
					<dl>
						<dt>
							<h2>프로세스 사용 그래프</h2>
							<span>프로세스 사용 현황</span>
						</dt>
						<dd>
							<div id="processChart" class="chart"></div>
						</dd>
					</dl>
				</div>
			</div>
			
			<!-- 
			<table id="usedList" class="noClick">
				<colgroup>
					<col width="20%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>사용일시</th>
						<th>사용 프로그램</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="tc">2014/07/30 12:00</td>
						<td>PhotoShop CC, Chrome, Safari</td>
					</tr>
				</tbody>
			</table>
			<div class="table_bottom_overlap_btn">
				<input type="button" value="뒤로" class="btn_normal_red" onclick="back()"/>
			</div>
			<div class="page">
				<ul>
					<li> << </li>
					<li class="on">1</li>
					<li>2</li>
					<li> >> </li>
				</ul>
			</div>
			 -->
		</div>
		
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 