<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp"%>
<link rel="stylesheet" type="text/css" href="${root }/script/css/anime.css" />
<script type="text/javascript">
	$(function() {
		$(".btn-reset").on("click", function() {
			$("#searchForm").find(".input-group input").val('');
			$("#searchForm").find(".input-group select").val('');
			$(this).blur();
		});
		$(".btn-delete-0").on("click", function(){
			$("#deleteForm").find("input").val("");
			var id = $(this).val();
			$("#deleteForm").find("input[name=id]").val(id);
			$("#deleteModal").modal();
		});
		$(".btn-delete-1").on("click", function(){
			var $form = $("#deleteForm");
			$.post($form.attr("action"), $form.serialize(), function(resp){
				location.reload();
			});
		});
		$(".btn-import-0").on("click", function(){
			$("#importForm").find("input").val("");
			$("#importModal").modal();
		});
		$(".btn-import-1").on("click", function(){
			var name = $("#importFileInput").val();
			if(name == '')
				alert("请先选择一个文件！");
			else{
				var $form = $("#importForm");
				var form = new FormData(document.getElementById('importForm'));
				$.ajax({
					url : $form.attr('action'),
					data : form,
					type : 'post',
					contentType : false,
					processData : false,
					success : function(resp){
						$("#importModal").modal("hide");
						alert("文件导入成功！");
						location.href = $("#searchForm").attr("action");
					}
				});
			}
		});
		$(".btn-add").on("click", function(){
			$("#editModal").find("input").val("");
			$("#editModal").find("select").val("");
			$("#editModal").modal();
		});
		$(".btn-edit").on("click", function(){
			var data = JSON.parse($(this).val());
						
			$("#editModal").find("input").val("");
			$("#editModal").find("select").val("");
			<%--
			$.post("edit.go", {id:id}, function(resp){
				$.each($("#editForm").find(".form-control"), function(i, e){
					$.each(resp.data, function(k, v){
						if($(e).attr("name") == k){
							$(e).val(v);
						}
					});
				});
				$("#editModal").modal();
			});
			--%>
		});
		$(".btn-save").on("click", function(){
			
		});
	});
</script>
</head>
<body>
<%@ include file="nav.jsp"%>
<div class="container">
	<form id="searchForm" action="list.go" method="post">
		<input type="hidden" name="pageNo" value="1" />
		<div class="input-group f-right w10">
			<span class="input-group-btn" id="basic-addon0">
				<button class="btn btn-default btn-reset" type="button">Reset</button>
				<button class="btn btn-default" type="submit">Go!</button>
			</span>
		</div>
		<div class="input-group f-right w20">
			<span class="input-group-addon">SerialState</span>
			<select class="form-control" name="sa_eq_i_serialState">
				<option value="">全部</option>
				<c:forEach items="${SerialState }" var="e">
					<option value="${e.code }" <c:if test="${e.code == sa_eq_i_serialState }">selected</c:if>>${e.text }</option>
				</c:forEach>
			</select>
		</div>
		<div class="input-group f-right w20">
			<span class="input-group-addon">Name</span>
			<input type="text" class="form-control" name="sa_like_s_name" value="${sa_like_s_name }" placeholder="name">
		</div>
		<div class="input-group f-left">
			<span class="input-gropu-btn">
				<button class="btn btn-default btn-import-0" type="button">导入</button>
				<button class="btn btn-default btn-add" type="button">添加</button>
			</span>
		</div>
	</form>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>index</th>
				<th>name</th>
				<th>ext</th>
				<th>year / month</th>
				<th>curr / total</th>
				<th>serialState</th>
				<th>operate</th>
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
						<button class="btn-edit" type="button" value='{"id":"${e.id }"}'>修改</button>
						<button class="btn-delete-0" type="button" value="${e.id }">删除</button></td>
				</tr>
			</c:forEach>
			<c:if test="${page.rowFound != page.pageSize }">
				<c:forEach begin="1" end="${page.pageSize - page.rowFound }">
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<%@ include file="pagination.jsp"%>
</div>

<!-- modals -->
<!-- edit modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="editModalLabel">编辑数据</h4>
			</div>
			<!-- model body -->
			<div class="modal-body">
				<form id="editForm" class="form-horizontal" action="edit.do" role="form">
					<input type="hidden" name="id" value="${e.name }" />
					<div class="form-group">
						<label for="input-name" class="col-sm-3 control-label">name</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="input-name" name="name" placeholder="name">
						</div>
					</div>
					<div class="form-group">
						<label for="input-ext" class="col-sm-3 control-label">ext</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="input-ext" name="ext" placeholder="ext">
						</div>
					</div>
					<div class="form-group">
						<label for="input-year" class="col-sm-3 control-label">year</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="input-year" name="year" placeholder="year">
						</div>
					</div>
					<div class="form-group">
						<label for="input-month" class="col-sm-3 control-label">month</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="input-month" name="month" placeholder="month">
						</div>
					</div>
					<div class="form-group">
						<label for="input-curr" class="col-sm-3 control-label">curr</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="input-curr" name="curr" placeholder="curr">
						</div>
					</div>
					<div class="form-group">
						<label for="input-total" class="col-sm-3 control-label">total</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="input-total" name="total" placeholder="total">
						</div>
					</div>
					<div class="form-group">
						<label for="input-total" class="col-sm-3 control-label">serialState</label>
						<div class="col-sm-6">
							<select class="form-control" id="input-serialState" name="serialState" >
								<option value="">全部</option>
								<c:forEach items="${SerialState }" var="e">
									<option value="${e.code }" <c:if test="${e.code == sa_eq_i_serialState }">selected</c:if>>${e.text }</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- import modal -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="importModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="importModalLabel">导入数据</h4>
			</div>
			<!-- model body -->
			<div class="modal-body">
				<form id="importForm" action="import.do">
					<input id="importFileInput" type="file" name="importFile" />
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-import-1">导入</button>
			</div>
		</div>
	</div>
</div>

<!-- delete modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="deleteModalLabel">删除数据</h4>
			</div>
			<!-- model body -->
			<div class="modal-body">
				<form id="deleteForm" action="delete.do">
					<input type="hidden" name="id" />
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary btn-delete-1">删除</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>