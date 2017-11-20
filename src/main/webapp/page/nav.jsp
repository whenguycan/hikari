<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="container">
	<div class="col-xs-12">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="javascript:void(0);">Hikari</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li <c:if test="${menuActive == 'home' }">class='active'</c:if>><a href="${root }/home.go">Home</a></li>
						<li <c:if test="${menuActive == 'anime' }">class='active'</c:if>><a href="${root }/anime/list.go">Anime</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
</div>
</html>