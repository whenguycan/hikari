<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/script/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function go(){
		$.post("test.ajax", {}, function(resp){
			console.debug(resp);
		});
	};
</script>
</head>
<body>
	${dcwe:convert('State', '1')}
	<input type="button" value="TEST" onclick="go()" />
</body>
</html>