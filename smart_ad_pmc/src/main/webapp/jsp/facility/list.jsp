<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svg.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svgdom.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svgfilter.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svggraph.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.svgplot.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var svg = $("#facilityMap").svg({loadURL : '${contextPath}/resources/map2.svg', onLoad : svgloaded, changeSize:true});
});

function svgloaded(svg, error){
	$("svg .btn").css("cursor","pointer");
	$("svg .btn").click(function() {
		log($(this).attr("id"));
		goPage("${contextPath}/facility/detail.do?no="+$(this).attr("id"));
	});
	
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
	alert(msg.facilityError);
}

</script>

</head>


<body>

<div id="wrap">
	<%@ include file="../common/header.jsp" %>
	<div id="content">
		<div class="title">
			<img src="${contextPath}/resources/images/icon_bullet_red.png" /><h1>시설관리</h1>
		</div>
		<div class="mainTable">
			<div class="mainBox facility">
				<dl>
					<dt>
						<h2>시설 사용현황</h2>
						<span>각 시설의 현재 이용 현황</span>
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
						</div>
					</dd>
				</dl>
			</div>
			
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html> 