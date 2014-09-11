<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

$(document).ready(function(){
	var $rightBox = $("#subRightSlide");
	
	$(".rightBoxSlideBtn").on('click',function(){
		
		if($(this).hasClass("open")){
			$(this).removeClass("open");
			
			$rightBox.stop().animate({
				width : "35px"
			}, 300, function(){
				$rightBox.css("width","35px");
			});
		} else {
			$(this).addClass("open");
			
			$rightBox.stop().animate({
				width : "256px"
			}, 300, function(){
				$rightBox.css("width","256px");
			});
		}
		
	});
	
	rightNoticeCall();
});

function rightNoticeCall() {
	var url = uri.serverUrl + uri.noticeListUrl;
	sendRequestJson(url,{currentPage : 1, unitPerPage : 4}, rightNoticeList, rightNoticeerror);
}

function rightNoticeList(res) {
	log(res);
	
	if(res.result.resultCode == "0000"){
		
		var $table = $("#rigtBoxNoticeList");
		$table.empty();
		
		$(res.list).each(function(){
			var tag = '<li>';
			tag += '<a  class="textover2" href="${contextPath}/guide/noticeDetail.do?no='+this.notiNo+'" title="'+this.notiSubject+'"> - '+this.notiSubject+'</a>';
    		tag += '</li>';
    		
    		$table.append(tag);
		});
			
	} else {
		rightNoticeerror();
	}
}

function rightNoticeerror() {
	alert(msg.noticeListFail);
}

</script>

<div id="subRightSlide">
	<div class="subRightContainer">
		<div class="rightBoxBtn">
			<span class="rightBoxSlideBtn"></span>
			<span class="rightBoxSlideBack"></span>
		</div>
		<div class="mainRightBox sub">
			<div id="notice">
		    	<div class="subCont">
		            <div class="rightTitle">
		                <h2>공지사항</h2>
		                <span><a href="${contextPath}/guide/noticeList.do">more</a></span>
		            </div>
		            <ul id="rigtBoxNoticeList" class="cl">
		            </ul>
		        </div>
		    </div>
		    <div id="guide">
		    	<div class="subCont">
		            <div id="playBtn" class="cl">
		                <img src="${contextPath}/resources/images/main/right_btn.png" />
		            </div>
		        </div>
		        <div id="movieContainer"></div>
		    </div>
		    <div id="reserve">
		    	<div class="subCont">
		            <div class="rightTitle">
		                <h2>예약 및 취소안내</h2>
		            </div>
		            <ul class="cl">
		            	<li><a href="${contextPath}/reserve/situation.do?id=c">창작지원실</a></li>
		                <li><a href="${contextPath}/reserve/situation.do?id=r1">녹음스튜디오</a></li>
	              	  	<li><a href="${contextPath}/reserve/situation.do?id=t1">매체적합성테스트실</a></li>
		            </ul>
		            
		        </div>
		    </div>
		    <div id="contact">
		    	<div class="subCont">
		            <div class="rightTitle">
		                <h2>찾아오시는길</h2>
		            </div>
		            <div class="cl">
		                <a href="${contextPath}/guide/location.do"><img src="${contextPath}/resources/images/common/right_menu_map.png" /></a>
		         	</div>
		            <div>
		            	<select onchange="goNewSite(this)">
		                	<option value='none'>관련사이트</option>
		                	<option value='http://www.msip.go.kr'>미래창조과학부</option>
		                	<option value='https://www.kobaco.co.kr/'>한국방송광고진흥공사</option>
		                	<option value='http://edu.kobaco.co.kr/'>kobaco광고교육원</option>
		                	<option value='http://adlib.kobaco.co.kr/'>kobaco광고도서관</option>
		                	<option value='http://hrd.kobaco.co.kr '>kobaco연수원</option>
		                	<option value='http://admuseum.kobaco.co.kr '>광고박물관</option>
		                </select>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
</div>