<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/top.jsp"%>

<div class="container-fluid">
	<div class="row-fluid">
		<%@ include file="/left.jsp"%>
		<div class="span10">
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->



			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<c:forEach var="i" begin="1" end="16" step="1">
							<br />
						</c:forEach>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span2"></div>
					<div class="span8">
						<div class="alert alert-danger" align="center">
							<h1 class="color-black">您的操作出错了！</h1>
							<c:choose>
								<c:when test="${not empty error_message}">
									<h3>${error_message}</h3>
								</c:when>
								<c:otherwise>
									<h3>没有任何信息，请联系管理员</h3>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="span2"></div>
				</div>
			</div>


			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
		</div>
	</div>
</div>
<%@ include file="/foot.jsp"%>
