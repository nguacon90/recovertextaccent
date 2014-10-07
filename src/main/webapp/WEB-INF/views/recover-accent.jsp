<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Recover Accent</title>
<script type="text/javascript">
	$(document).ready(function() {
		var app = new Application({
			originParagraph: $("#originParagraph"),
			inputParagraph : $("#noAccentParagraph"),
			outputParagraph : $("#accentParagraph"),
			recoverAccentBtn : $("#recoverAccent"),
			removeAccentBtn : $("#removeAccent"),
			urlRestService: "rest/service/recoverAccent",
			loadingElm: $("#loading")
		});
		app.init();
	});
</script>
</head>
<body>
	<div class="pair">
		<h1>Văn bản Gốc:</h1>
		<textarea rows="10" cols="100" id="originParagraph"></textarea>
	</div>
	<button class="black-btn" id="removeAccent">Xóa dấu</button>
	<div class="pair">
		<h1>Văn bản không dấu:</h1>
		<textarea rows="10" cols="100" id="noAccentParagraph"></textarea>
	</div>
	<button class="black-btn" id="recoverAccent">Khôi phục dấu</button>
	<div class="pair">
		<h1>Văn bản có dấu:</h1>
		<h2>Độ chính xác: <span id="percent"></span></h2>
		<img src="<spring:theme code="theme.default.images"/>/ajax-loader.gif" class="hidden" id="loading">
		<div id="accentParagraph" class="box-755"></div>
	</div>

</body>
</html>