<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글쓴이 분석</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.awesomeCloud-0.2.js"></script>
<style type="text/css">
.wordcloud {
	border: 1px solid #036;
	height: 6in;
	margin: 0.5in auto;
	padding: 0;
	page-break-after: always;
	page-break-inside: avoid;
	width: 6in;
}
</style>
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header style="margin-top: 50px;"> 
	</header>
	<div role="main">
		<div id="wordcloud1" class="wordcloud">
			<c:forEach items="${map}" var="m">
				<sapn data-weight="${m.value * 5}">${m.key}</sapn>
			</c:forEach>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#wordcloud1").awesomeCloud({
				"size" : {
					"grid" : 5,
					"factor" : 0,
					"normalize" : true
				},
				"options" : {
					"color" : "random-dark",
					"rotationRatio" : 0.35
				},
				"font" : "'Times New Roman', Times, serif",
				"shape" : "square"
			});
		});
	</script>
</body>
</html>