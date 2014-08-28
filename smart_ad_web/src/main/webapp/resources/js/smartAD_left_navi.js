var $subBack;
var $subMenu;

$(function(){
	$(".subMenu > div").hide();
	leftNavi();	
});

function depthView() {
	$(".subMenu > div").hide();
	var id = $(this).data("idx");
	
	$("#subMenu_"+id).show();
	
	$subBack.stop().animate({
		left : "185"
	}, 300, function(){
		$subBack.css("left","185px");
	});
	
	$subMenu.stop().animate({
		left : "10"
	}, 300, function() {
		$subMenu.css("left","10px");
	});
}

function depthHide() {
	$subBack.stop().animate({
		left : "0"
	}, 300, function(){
		$subBack.css("left","0");
	});
	
	$subMenu.stop().animate({
		left : "-175"
	}, 300, function() {
		$subMenu.css("left","-175px");
		$(".subMenu > div").hide();
	});
}

function leftNavi() {
	$subBack = $("#subMenuBack");
	$subMenu = $(".subMenu");
	
	$(".mainMenuList").each(function() {
		$(this).mouseover(depthView).focus(depthView).mouseout(depthHide);
	});
	
}


