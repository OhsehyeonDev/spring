<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BAR graph</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.js"></script>
</head>
<body>
	<div id="canvas-holder" style="width: 100%; height: 100%;">
		<canvas id="chart-area" width="100%" ; height="100%";></canvas>
	</div>
	<script type="text/javascript">
  var randomColorFactor = function(){
  	return Math.round(Math.random() * 255);
  }
  var randomColor = function(opacity) {
  	return "rgba(" + randomColorFactor() + ","
  			+ randomColorFactor() + ","
  			+ randomColorFactor() + ","
  			+ (opacity || '.3') + ")";
  }
  var color = randomColor(1);
  var config = {
  		type : 'bar',   
  		data : {
  			datasets : [{
  				label : "",
	data:[<c:forEach items="${map}" var="m">"${m.value}",</c:forEach>],
	backgroundColor :
		[<c:forEach items="${map}" var="m">randomColor(1),</c:forEach>],
  			}],
	labels:[<c:forEach items="${map}" var="m">"${m.key}",</c:forEach>]
  		},
  		options : {
  			responsive : true,
  			legend : {
  				position : 'bottom',
  			},
  			title : {
  				display : true,
  				text : '게시판 작성자 분석'
  					}, scales : {
  		  				yAxes : [{
  		  					ticks : {
  		  						beginAtZero : true
  		  					}
  		  				}]
  		  			}
  		  			}
  };
  window.onload = function() {
  	var ctx = document.getElementById("chart-area").getContext("2d");
	    window.myPid = new Chart(ctx,config);
  }
</script>
</body>
</html>