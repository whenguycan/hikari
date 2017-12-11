<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp"%>
<script type="text/javascript" src="${root }/plugins/video-js-6.2.8/video.min.js"></script>
<link rel="stylesheet" type="text/css" href="${root }/plugins/video-js-6.2.8/video-js.min.css" />
<script type="text/javascript">
	$(function() {
		
	});
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
<div class="container">
	<div class="col-xs-12">
		<video id="player" class="vodeo-js vjs-big-play-centered" controls width="640" height="264">
			<source src="" type="video/mp4">
		</video>
	</div>
	<div class="col-xs-12">
		<c:forEach items="${map }" var="e">
			${e.value.sign }<br/>
		</c:forEach>
	</div>
</div>


</body>
</html>