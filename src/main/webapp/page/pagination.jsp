<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="container">
	<div class="col-xs-12">
		<nav aria-label="page navigation">
			<ul class="pagination">
				<li><a href="javascript:go('1');" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<c:forEach items="${page.pagination }" var="e">
					<li><a href="javascript:go('${e }');">${e }</a></li>	
				</c:forEach>
				<li><a href="javascript:go('${page.pageCount }');" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</nav>
	</div>
</div>
<script type="text/javascript">
	function go(pageNo){
		if(pageNo == '' || pageNo == undefined)
			pageNo = 1;
		$("#searchForm").find("input[name=pageNo]").val(pageNo);
		$("#searchForm").submit();
	};
</script>
</html>