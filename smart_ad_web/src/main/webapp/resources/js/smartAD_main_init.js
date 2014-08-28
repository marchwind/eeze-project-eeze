var mainSlidePo = 0;
var mainSlideImageW = 0;

$(function(){
	mainImageSet();
	mainSlide();
});

window.onresize = mainImageSet;

function mainImageSet(){
	var leftNaviW = 185;
	var docWidth = $(document).width();
	var docHeight = $(document).height();
	
	var $mainFrame = $("#mainFrame");	
	var $mainimgCon = $("#mainFrame #mainImage ul");
	var $mainimgLi = $("#mainFrame #mainImage ul li");
	var $mainImg = $("#mainFrame #mainImage ul li > img");
	
	$mainImg.each(function(index, element) {
        $(this).height(docHeight);
		mainSlideImageW = $(this).width();
    });
	
	log("imgH : " + docHeight + ", imgW : " + mainSlideImageW);	
	
	$mainimgLi.height(docHeight);
	$mainimgLi.width(mainSlideImageW);
	
	$mainimgCon.width(mainSlideImageW * 5);
	$mainimgCon.css("left", (-1*mainSlideImageW * mainSlidePo)+"px");
	
	var $mainRightMenu = $(".mainRightBox");
	$mainRightMenu.width(docWidth - mainSlideImageW - leftNaviW);
	
	$mainFrame.css("margin-right", $mainRightMenu.width() + "px");
	var centerWidth = docWidth - leftNaviW - $mainRightMenu.width();
	
	var $mainSlideBtn = $("#mainFrame #mainButton");
	$mainSlideBtn.css("top",((docHeight/2)-80)+"px");
	
}

function mainSlide(){
	var $slideLeft = $("#mainButtonLeft");
	var $slideRight = $("#mainButtonRight");
	
	var $slideIcon = $(".mainIconBtn");
	
	$slideIcon.each(function(index, element) {
		$(this).bind("click", function(){
			mainSliding(Number($(this).data("ponum")));	
		});
    });
	
	$slideLeft.bind("click", function(){
		mainSliding(mainSlidePo - 1);
	});
	$slideRight.bind("click", function(){
		mainSliding(mainSlidePo + 1);
	});
}

function mainSliding(po){
	log("position : " + po);	
	if(po < 0) {
		mainSlidePo = 0;
	} else if(po > 4) {
		mainSlidePo = 4;	
	} else {
		mainSlidePo = po;	
		
		$(".mainIconBtn").removeClass("active");
		$("#mainIcon_"+ (po+1)).addClass("active");
		
		var $mainimgCon = $("#mainFrame #mainImage ul");
		$mainimgCon.animate({
			left : mainSlideImageW * mainSlidePo * -1
		}, 500, function(){
			$(this).css("left", (-1*mainSlideImageW * mainSlidePo)+"px");
		});
	}
}
