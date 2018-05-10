$(document).ready(showSpinner);

function showSpinner(){
    //$("body").prepend("<div id = 'spinner'>&#9650;</div>");
	//$("#content").css("filter","blur(5px)");
	//var spinner = $("#spinner");
	//var w = spinner.width();
	//spinner.css("height",w+"px");
	
	setTimeout(function(){
//		spinner.css("opacity","0");
		
		setTimeout(function(){
//			spinner.remove();
//			$("#content").css('filter',"blur(0)");
		},1000)
	},6000);
}
