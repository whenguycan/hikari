<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.dc.com/w/jsp/jstl/taglib/enums" prefix="dcwe"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<title>Insert title here</title>
<%@ include file="/script/script.jsp"%>
<link rel="stylesheet" type="text/css" href="${root }/script/anime/anime.css" />
<script type="text/javascript">
	var validatorSettings = {
		message : 'lalala',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : true
				}
			},
			year : {
				validators : {
					notEmpty : true,
					between : {
						min : 1900,
						max : 2200
					}
				}
			},
			month : {
				validators : {
					notEmpty : true,
					between : {
						min : 1,
						max : 12
					}
				}
			},
			curr : {
				validators : {
					notEmpty : true,
					digits : true,
					between : {
						min : 0,
						max : 1000
					}
				}
			},
			total : {
				validators : {
					notEmpty : true,
					digits : true,
					between : {
						min : 0,
						max : 1000
					}
				}
			}
		}
	};
	$(function() {
		$(".btn-reset").on(
				"click",
				function() {
					$("#searchForm").find(".input-group input").val('');
					$("#searchForm").find(".input-group select").val('');
					$("#searchForm").find("input[type=checkbox]").prop(
							"checked", false);
					$(this).blur();
				});
		$(".btn-submit").on("click", function() {
			$("#searchForm").submit();
		});
		$(".btn-delete-0").on("click", function() {
			$("#deleteForm").find("input").val("");
			var id = $(this).val();
			$("#deleteForm").find("input[name=id]").val(id);
			$("#deleteModal").modal();
		});
		$(".btn-delete-1").on("click", function() {
			var $form = $("#deleteForm");
			$.post($form.attr("action"), $form.serialize(), function(resp) {
				location.reload();
			});
		});
		$(".btn-import-0").on("click", function() {
			$("#importForm").find("input").val("");
			$("#importModal").modal();
		});
		$(".btn-import-1").on("click", function() {
			var name = $("#importFileInput").val();
			if (name == '')
				alert("请先选择一个文件！");
			else {
				var $form = $("#importForm");
				var form = new FormData(document.getElementById('importForm'));
				$("#importModal").modal("hide");
				showMsg("文件导入中,请稍候...");
				$.ajax({
					url : $form.attr('action'),
					data : form,
					type : 'post',
					contentType : false,
					processData : false,
					success : function(resp) {
						showMsg("文件导入成功。");
					}
				});
			}
		});
		$(".btn-add").on("click", function() {
			$("#editModal").find(".form-control").val("");
			$("#editModal").modal();
		});
		$(".btn-edit").on("click", function() {
			$("#editModal").find(".form-control").val("");
			var data = JSON.parse($(this).val());
			$.each($("#editForm").find(".form-control"), function(i, e) {
				$.each(data, function(k, v) {
					if ($(e).attr("name") == k) {
						$(e).val(v).change();
					}
				});
			});
			$("#editModal").modal();
		});
		$(".btn-save")
				.on(
						"click",
						function() {
							$("#editForm")
									.bootstrapValidator(validatorSettings);
							$("#editForm").data('bootstrapValidator')
									.validate();
							if ($("#editForm").data('bootstrapValidator')
									.isValid() == true) {
								$.post($("#editForm").attr("action"), $(
										"#editForm").serialize(),
										function(resp) {
											location.reload();
										});
							}
						});
		$("#editModal").on("hidden.bs.modal", function() {
			if ($("#editForm").data('bootstrapValidator') != undefined)
				$("#editForm").data('bootstrapValidator').destroy();
		});
		$("#msgModal").on("hidden.bs.modal", function() {
			location.href = $("#searchForm").attr("action");
		});
		$("input[name=sa_eq_i_favo]").on("change", function() {
			$("#searchForm").submit();
		});
		$(".btn-favo").on("click", function() {
			var $btn = $(this);
			$.post("favo.do", {
				id : $btn.val()
			}, function(resp) {
				$("#searchForm").submit();
			});
		});
	});
	function showMsg(msg) {
		$("#msgModal").find(".msgModalContent").html(msg);
		$("#msgModal").modal();
	};
</script>
</head>
<body ng-app="anime" ng-controller="anime">
	<%@ include file="nav.jsp"%>
	<div class="container">
		<div class="col-xs-12 container-searcher">
			<form id="searchForm" action="list.go" method="post">
				<input type="hidden" name="pageNo" value="1" />
				<div class="input-group f-right w75">
					<!-- 
					<span class="input-group-addon">Favo</span>
					<div class="form-control">
						<input type="checkbox" name="sa_eq_i_favo" value="1" <c:if test="${sa_eq_i_favo == '1' }">checked</c:if> />
					</div>
					<span class="input-group-addon">Watch</span> <select class="form-control" name="sa_eq_i_watchState">
						<option value="">全部</option>
						<c:forEach items="${WatchState }" var="e">
							<option value="${e.code }" <c:if test="${e.code == sa_eq_i_watchState }">selected</c:if>>${e.text }</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">Serial</span>
					<select class="form-control" name="sa_eq_i_serialState">
						<option value="">全部</option>
						<c:forEach items="${SerialState }" var="e">
							<option value="${e.code }" <c:if test="${e.code == sa_eq_i_serialState }">selected</c:if>>${e.text }</option>
						</c:forEach>
					</select>
					 -->
					<span class="input-group-addon">Name</span>
					<input type="text" class="form-control" name="sa_like_s_name" value="${sa_like_s_name }" placeholder="name">
					<span class="input-group-btn" id="basic-addon0">
						<button class="btn btn-default btn-reset" type="button">Reset</button>
						<button class="btn btn-default btn-submit" type="button">Go!</button>
					</span>
				</div>
				<div class="input-group f-left" >
					<span class="input-gropu-btn">
						<!-- <button class="btn btn-default btn-import-0" type="button">导入</button> -->
						<button class="btn btn-default btn-add" type="button">添加</button>
						<button class="btn btn-default btn-manage" ng-click="toggle()" type="button">管理</button>
					</span>
				</div>
			</form>
		</div>
		<div class="col-xs-2" >
			<ul ng-repeat="year in years">
				<li>{{year.name}}</li>
			</ul>
			<p style="display: inline-block;" >
				
			</p>
		</div>
		<div class="col-xs-10 displayer" >
			<div class="row">
				<div class="col-xs-3" ng-repeat="x in data">
					<div class="thumbnail thumbnail-stable">
						<img src="${root}/img/01.jpg" alt="...">
						<div class="caption">
							<!-- <h3>Thumbnail label</h3> -->
							<p>{{x.name}}</p>
							<p>curr / total</p>
							<p>
								<a href="#" class="btn btn-primary" role="button" >Button</a>
								<a href="#" class="btn btn-default" role="button" ng-show="delShow">Button</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<table class="table table-bordered" ng-show="false">
				<thead>
					<tr>
						<th>index</th>
						<th>name</th>
						<th>link</th>
						<th>season</th>
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
							<td><c:if test="${!empty e.link }">
									<a class="btn" href="${e.link2 }" target="_blank">LINK</a>
								</c:if></td>
							<td>${dcwe:convert('Season', e.season) }</td>
							<td><span <c:if test="${e.curr == e.total && e.total != 0 }">class='color-blue'</c:if>>${e.curr } / ${e.total }</span>
							</td>
							<td>${dcwe:convert('SerialState', e.serialState) }</td>
							<td>
								<button class="btn btn-default btn-edit" type="button" value='{"id":"${e.id }","name":"${e.name }","ext":"${e.ext }","curr":"${e.curr }","total":"${e.total }","serialState":"${e.serialState }","season":"${e.season }","link":"${e.link }"}'>修改</button>
								<button class="btn btn-default btn-delete-0" type="button" value="${e.id }">删除</button>
								<button class="btn <c:if test="${e.favo == '1' }">btn-primary</c:if><c:if test="${e.favo != '1' }">btn-default</c:if> btn-favo" type="button" value="${e.id }">Favo</button></td>
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
		</div>
	</div>
	<!-- <%@ include file="pagination.jsp"%> -->

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
					<form id="editForm" class="form-horizontal" action="edit.do" role="form" method="post">
						<input type="hidden" class="form-control" name="id" value="${e.id }" />
						<div class="form-group">
							<label for="input-name" class="col-sm-3 control-label">name</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="input-name" name="name" placeholder="name">
							</div>
						</div>
						<div class="form-group">
							<label for="input-season" class="col-sm-3 control-label">season</label>
							<div class="col-sm-6">
								<select class="form-control" id="input-season" name="season">
									<c:forEach items="${Season }" var="e">
										<option value="${e.code }" <c:if test="${e.code == '1' }">selected</c:if>>${e.text }</option>
									</c:forEach>
								</select>
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
							<label for="input-link" class="col-sm-3 control-label">link</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="input-link" name="link" placeholder="link">
							</div>
						</div>
						<div class="form-group">
							<label for="input-total" class="col-sm-3 control-label">serialState</label>
							<div class="col-sm-6">
								<select class="form-control" id="input-serialState" name="serialState">
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
					<button type="button" class="btn btn-primary btn-save">保存</button>
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

	<!-- msg modal -->
	<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="msgModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="msgModalLabel">提示信息</h4>
				</div>
				<!-- model body -->
				<div class="modal-body">
					<div class="msgModalContent"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${root }/plugins/angular.js"></script>
	<script type="text/javascript" src="${root }/script/anime/anime.js"></script>
</body>
</html>