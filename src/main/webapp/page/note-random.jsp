<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp"%>
<script type="text/javascript">
	$(function() {
		
	});
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
<div class="container">
	<div class="col-xs-12">
		<ul>
			<c:forEach items="${list }" var="e">
				<li>${e.title }</li>
			</c:forEach>
		</ul>
	</div>
	<div class="col-xs-12">
		<form action="import.do" method="post" enctype="multipart/form-data">
			<input type="file" name="importFile" />
			<button type="submit">导入</button>
		</form>
	</div>
</div>


</body>
</html>