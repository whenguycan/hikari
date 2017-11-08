<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<%@ include file="/script/script.jsp" %>
<script type="text/javascript">
	function import1(){
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
	};
</script>
</head>
<body>
	<div class="container">
		<form id="importForm" action="import.do" method="post" enctype="multipart/form-data">
			<input type="file" name="importFile" />
			<button type="button" onclick="import1()" >上传</button>
		</form>
	</div>
</body>
</html>