<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp" %>
<script type="text/javascript">
	$(function(){
		
	});
</script>
</head>
<body>
	<div class="container">
		<div>
			<form id="searchForm" action="list.go" method="post">
				<input type="text" name="sa_like_s_name" value="${sa_like_s_name }" />
				<input type="text" name="pageNo" value="${page.pageNo }" />
				<input type="submit" value="Submit">
			</form>
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th>Index</th>
						<th>Name</th>
						<th>Year/Month</th>
						<th>Curr/Total</th>
						<th>SerialState</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="e" varStatus="idx">
						<tr>
							<td>${idx.count }</td>
							<td>${e.name }</td>
							<td>${e.year }/${e.month }</td>
							<td>${e.curr }/${e.total }</td>
							<td>${dcwe:convert('SerialState', e.serialState) }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>