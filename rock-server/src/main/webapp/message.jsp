<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty message}">
	<div class="row-fluid">
		<div class="span12">
			<div class="alert">
				<h4>${message}</h4>
			</div>
		</div>
	</div>
</c:if>

<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<%@ page contentType="text/html" pageEncoding="UTF-8"%>