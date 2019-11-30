$("#searchButton").click(function() {
	var data = $("#query").val();
	$.ajax({
		type : "POST",
		url : "Controller.jsp",
		data : {
			submit : "search",
			searchString : data
		},
	});
})