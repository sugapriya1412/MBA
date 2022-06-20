//calling controller method for navigation of view using parameterized urlPattern
function loadmydiv(urlPattern) {
	
	
	var id = '#' + urlPattern.replace('/', '_');
	
	//alert (id);
	
	
	$.blockUI({
				message : '<img src="assets/img/482.GIF"> loading... Just a moment...'
			});
	$.ajax({
		type: "POST",
		url : urlPattern,
		success : function(response) {
			//console.log(response);
			$("#page-wrapper").html(response);			
			$.unblockUI();
		},
		error : function(e) {
			// alert('Error: ' + e);
			$.unblockUI();
			start(4);
		}
	});
	/*
	 * $('.nav li').removeClass('active'); $(id).addClass('active');
	 */
	$("html, body").animate({
		scrollTop : 0
	}, "slow");
}