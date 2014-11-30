<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Training</title>
</head>
<body>
	<div>
		<h1>Nhập văn bản để huẩn luyện</h1>
		<div class="" id="message"></div>
		<img src="<spring:theme code="theme.default.images"/>/ajax-loader.gif" class="hidden" id="loading">
		<textarea rows="10" cols="100" id="trainingParagraph"></textarea>
	</div>
	<button class="black-btn" id="trainingBtn">Đồng ý</button>
<script type="text/javascript">
	$(document).ready(function() {
		$("#trainingBtn").click(function(){
			$("#loading").show();
			$("#message").removeClass("success-message error-message");
			$("#message").html("");
			
			$.ajax({
				url : "rest/service/training",
				type: "POST",
				data: {
					"data": $("#trainingParagraph").val()
				},
				success: function(data) {
					if(data) {
						$("#message").html("Huấn luyện thành công!");
						$("#message").addClass("success-message");
						$("#loading").hide();
					} else {
						$("#message").html("Huấn luyện không thành công!");
						$("#message").addClass("error-message");
						$("#loading").hide();
					}
				}
			});
		});
	});
</script>
</body>

</html>