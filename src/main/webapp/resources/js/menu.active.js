$(document).ready(function() {
	var obj = $(".main-nav").find("ul>li>a");
	var path = window.location.pathname.split("/");
	var rel = path.pop(path.length);
	$.each(obj, function() {
		if ($(this).attr("rel") === rel) {
			$(this).addClass("active");
		}
	});
});
