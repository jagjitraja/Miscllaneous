

$(document).ready(function(){
	
	$('a').click(function(event){
		var target = $(event.target.hash);
		
		event.preventDefault();
		$('body,html').animate({
			scrollTop:target.offset().top-15
		},800);
		window.location.hash = event.target.hash;
	});
	
	
	
	var divs = $(".data");
	
	
	$(window).scroll(function(){
		
		divs.each(function(){
		var div = this;
		var divTop = $(div).offset().top
		
				console.log($(div))
		
		var bodyTopPosition = $('body').scrollTop();
			if(bodyTopPosition>=(divTop-300)){
				if(div.id!="Intro"){
					$('a[href="#'+div.id+'"]').addClass('active');
				}
			}
			if(bodyTopPosition>=(divTop+div.scrollHeight-400)||bodyTopPosition<divTop-400){
				$('a[href="#'+div.id+'"]').removeClass('active');
			}
		})
	})
});