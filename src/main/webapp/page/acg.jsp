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
		$(".click").click(function(){
			$.post("anime/page.ajax", {}, function(resp){
				$(".show").html(resp);
			});
		});
	});
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
<div class="container">
	<div class="col-xs-12">
		<button class="click">按钮</button>
		<div class="show"></div>
	</div>
</div>


</body>
</html>