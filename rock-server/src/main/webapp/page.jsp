<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="pagination text-center">
	
	
	
	
	<ul>
		<c:forEach items="${page.iterator()}" var="pageNo">
			<li><c:choose>
					<c:when test="${pageNo.isCurrentPage()}">
						<a class="color-red"><c:out value="${pageNo.name}" /></a>
					</c:when>
					<c:when test="${pageNo.isEllipsis()}">
						<a><c:out value="${pageNo.name}" /></a>
					</c:when>
					<c:otherwise>
						<a href="${pageNo.value}"><c:out value="${pageNo.getName()}" /><c:out value="${requestURL}" /></a>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>
	</ul>
</div>