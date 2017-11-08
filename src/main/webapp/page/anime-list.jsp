<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp"%>
<style type="text/css">
#searchForm .w15{width: 15%;}
#searchForm .w20{width: 20%;}
#searchForm .f-left{float: left;}
#searchForm .f-right{float: right;}
.container .table tr td{height: 37px;}
</style>
<script type="text/javascript">
	$(function(){
		$(".btn-reset").on("click", function(){
			$("#searchForm").find(".input-group input").val('');
			$("#searchForm").find(".input-group select").val('');
			$(this).blur();
		});
		$(".btn-import").on("click", function(){
			layer.open({
				type : 2,
				skin : 'layui-layer-rim',
				area : ['720px', '300px'],
				content : 'import.go',
				end : function(){
					location.href = $("#searchForm").attr("action");
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="container">
		<%@ include file="nav.jsp" %>
		<div>
			<form id="searchForm" action="list.go" method="post">
				<input type="hidden" name="pageNo" value="1" />
				<div class="input-group f-right w20">
					<select class="form-control" name="sa_eq_i_serialState">
						<option value="">全部</option>
						<c:forEach items="${SerialState }" var="e">
							<option value="${e.code }" <c:if test="${e.code == sa_eq_i_serialState }">selected</c:if>>${e.text }</option>
						</c:forEach>
					</select>
					<span class="input-group-btn" id="basic-addon0">
						<button class="btn btn-default btn-reset" type="button">Reset</button>
						<button class="btn btn-default" type="submit">Go!</button>
					</span>
				</div>
				<div class="input-group f-right w15">
					<span class="input-group-addon">Name</span>
					<input type="text" class="form-control" name="sa_like_s_name" value="${sa_like_s_name }" placeholder="name" aria-describedby="basic-addon2">
				</div>
				<div class="input-group f-left w15">
					<span class="input-gropu-btn">
						<button class="btn btn-default btn-import" type="button">导入</button>
					</span>
				</div>
			</form>
		</div>
		<div>
			<table class="table table-bordered">
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
							<td>${idx.count + (page.pageNo - 1) * page.pageSize }</td>
							<td>${e.name }</td>
							<td>${e.year }/${e.month }</td>
							<td>${e.curr }/${e.total }</td>
							<td>${dcwe:convert('SerialState', e.serialState) }</td>
						</tr>
					</c:forEach>
					<c:if test="${page.rowFound != page.pageSize }">
						<c:forEach begin="1" end="${page.pageSize - page.rowFound }">
							<tr><td></td><td></td><td></td><td></td><td></td></tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<%@ include file="pagination.jsp" %>
		</div>
	</div>
</body>
</html>