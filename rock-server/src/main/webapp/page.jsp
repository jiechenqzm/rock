<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="pagination text-center">
	<ul>
		<c:forEach items="${page.iterator()}" var="pageNo"
			varStatus="pageStatus">
			<li><c:choose>
					<c:when test="${pageNo.isCurrentPage()}">
						<a class="color-red"><c:out value="${pageNo.name}" /></a>
					</c:when>
					<c:when test="${pageNo.isEllipsis()}">
						<a><c:out value="${pageNo.name}" /></a>
					</c:when>
					<c:otherwise>
						<a
							href="
							<c:url value="${requestUrl}">
								<c:forEach items="${requestParams.entrySet()}" var="paramEntry">

									<c:if test="${paramEntry.key != 'pageNo'}">
										<c:forEach items="${paramEntry.value}" var="value">
											<c:param name="${paramEntry.key}" value="${value}" />			
										</c:forEach>
									</c:if>
												
								</c:forEach>
								<c:param name="pageNo" value="${pageNo.value}" />
								
							</c:url>
						">
							<c:out value="${pageNo.getName()}" />
						</a>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>
	</ul>
</div>