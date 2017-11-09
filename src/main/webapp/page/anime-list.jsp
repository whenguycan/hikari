<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp"%>
<style type="text/css">
#searchForm .w15 {
	width: 15%;
}

#searchForm .w20 {
	width: 20%;
}

#searchForm .f-left {
	float: left;
}

#searchForm .f-right {
	float: right;
}

.container .table tr td {
	height: 37px;
	text-align: center;
}

.container .table tr th {
	text-align: center;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".btn-reset").on("click", function() {
			$("#searchForm").find(".input-group input").val('');
			$("#searchForm").find(".input-group select").val('');
			$(this).blur();
		});
		$(".btn-import").on("click", function() {
			layer.open({
				type : 2,
				skin : 'layui-layer-rim',
				area : [ '720px', '300px' ],
				content : 'import.go',
				end : function() {
					location.href = $("#searchForm").attr("action");
				}
			});
		});
		$(".btn-delete").on("click", function() {
			var id = $(this).val();
			var idx = layer.confirm("确定删除？", function() {
				$.post("delete.do", {
					id : id
				}, function(resp) {
					layer.close(idx);
					location.reload();
				});
			});
		});
		$(".btn-edit").on("click", function() {
			var id = $(this).val();
			var content = "edit.go?id=" + id;
			layer.open({
				type : 2,
				skin : 'layui-layer-rim',
				area : [ '720px', '300px' ],
				content : content,
				end : function() {
					location.reload();
				}
			});
		});
	});
	function import1(){
		var name = $("#importFileInput").val();
		if(name == ''){
			layer.alert("请先选择一个文件！");
		}else{
			var $form = $("#importForm");
			var form = new FormData(document.getElementById('importForm'));
			$.ajax({
				url : $form.attr('action'),
				data : form,
				type : 'post',
				contentType : false,
				processData : false,
				success : function(resp){
					parent.layer.close(parent.layer.index);
				}
			});
		}
	};
</script>
</head>
<body>
<div class="container">
	<%@ include file="nav.jsp"%>
	<div>
		<form id="searchForm" action="list.go" method="post">
			<input type="hidden" name="pageNo" value="1" />
			<div class="input-group f-right w20">
				<select class="form-control" name="sa_eq_i_serialState">
					<option value="">全部</option>
					<c:forEach items="${SerialState }" var="e">
						<option value="${e.code }" <c:if test="${e.code == sa_eq_i_serialState }">selected</c:if>>${e.text }</option>
					</c:forEach>
				</select> <span class="input-group-btn" id="basic-addon0">
					<button class="btn btn-default btn-reset" type="button">Reset</button>
					<button class="btn btn-default" type="submit">Go!</button> </span>
			</div>
			<div class="input-group f-right w15">
				<span class="input-group-addon">Name</span>
				<input type="text" class="form-control" name="sa_like_s_name" value="${sa_like_s_name }" placeholder="name" aria-describedby="basic-addon2">
			</div>
			<div class="input-group f-left w15">
				<span class="input-gropu-btn">
					<button class="btn btn-default btn-import" type="button">导入</button>
					<button class="btn btn-default btn-edit" type="button">添加</button>
					<button class="btn btn-default" type="button" data-toggle="modal" data-target="#editModal">测试</button>
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
					<th>Ext</th>
					<th>Year / Month</th>
					<th>Curr / Total</th>
					<th>SerialState</th>
					<th>Operate</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="e" varStatus="idx">
					<tr>
						<td>${idx.count + (page.pageNo - 1) * page.pageSize }</td>
						<td>${e.name }</td>
						<td>${e.ext }</td>
						<td>${e.year } / ${e.month }</td>
						<td>${e.curr } / ${e.total }</td>
						<td>${dcwe:convert('SerialState', e.serialState) }</td>
						<td>
							<button class="btn-edit" type="button" value="${e.id }">修改</button>
							<button class="btn-delete" type="button" value="${e.id }">删除</button></td>
					</tr>
				</c:forEach>
				<c:if test="${page.rowFound != page.pageSize }">
					<c:forEach begin="1" end="${page.pageSize - page.rowFound }">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<%@ include file="pagination.jsp"%>
	</div>
</div>
<!-- models -->
<!-- edit model -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="editModalLabel">模态框（Modal）标题</h4>
			</div>
			<!-- model body -->
			<div class="modal-body">
				<form id="editForm" class="form-horizontal" action="edit.do" method="post">
					<input type="hidden" name="id" value="${e.id }" />
					<div class="form-group">
						<label for="input-id" class="col-sm-2 control-label">Id</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="input-id" placeholder="Id">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-6">
							<button type="submit" class="btn btn-default">Sign in</button>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">提交更改</button>
			</div>
		</div>
	</div>
</div>
<!-- import model -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="importModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="importModalLabel">模态框（Modal）标题</h4>
			</div>
			<!-- model body -->
			<div class="modal-body">
				<form id="importForm" action="import.do" method="post" enctype="multipart/form-data">
					<input id="importFileInput" type="file" name="importFile" />
					<button type="button" onclick="import1()" >上传</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">提交更改</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>