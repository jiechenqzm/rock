<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="span2 backgrond-color-grey">
	<ul class="nav nav-list">
		<li class="nav-header">配置管理</li>
		<li><a href="../view/search.html">查询数据</a></li>
		<li><a href="../view/add.html">新增数据</a></li>
		<li class="nav-header">订阅查询</li>
		<li><a href="#">暂不支持</a></li>
		<li><a href="#">暂不支持</a></li>

		<c:forEach var="i" begin="1" end="4" step="1">
			<li class="divider" />
			<li class="nav-header">配置管理</li>
			<li><a>暂不支持</a></li>
			<li><a>暂不支持</a></li>
			<li class="nav-header">订阅查询</li>
			<li><a>暂不支持</a></li>
			<li><a>暂不支持</a></li>
		</c:forEach>

	</ul>
</div>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>