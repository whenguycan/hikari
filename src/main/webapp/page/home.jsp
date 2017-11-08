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
	<div class="container">
		<div>
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#home"
					aria-controls="home" role="tab" data-toggle="tab">Home</a>
				</li>
				<li role="presentation"><a href="#anime" aria-controls="anime"
					role="tab" data-toggle="tab">Anime</a>
				</li>
				<li role="presentation"><a href="#note" aria-controls="note"
					role="tab" data-toggle="tab">Note</a>
				</li>
				<li role="presentation"><a href="#settings"
					aria-controls="settings" role="tab" data-toggle="tab">Settings</a>
				</li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">首页</div>
				<div role="tabpanel" class="tab-pane" id="anime">
					<div>
						<form>
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">@</span>
								<input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
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
				<div role="tabpanel" class="tab-pane" id="note">Note</div>
				<div role="tabpanel" class="tab-pane" id="settings">Settings</div>
			</div>
		</div>

		<div>
			<form id="searchForm" action="list.go" method="post">
				<input type="text" name="sa_like_s_name" value="${sa_like_s_name }" />
				<select name="sa_eq_i_serialState">
					<option value="">全部</option>
					<c:forEach items="${SerialState }" var="e">
						<option value="${e.code }"
							<c:if test="${e.code == sa_eq_i_serialState }">selected</c:if>>${e.text
							}</option>
					</c:forEach>
				</select> <input type="text" name="pageNo" value="${page.pageNo }" /> <input
					type="submit" value="Submit">
			</form>
		</div>
	</div>
</body>
</html>