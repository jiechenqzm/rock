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

			<div class="page-header">
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<h4>数据详情</h4>
					展示某一条数据的详细信息，并提供修改的入口
				</div>
			</div>
			
			<table class="table table-bordered table-condensed">
				<c:choose>
					<c:when test="${not empty data}">
						<tr>
					<th>GROUP</th>
					<td>${data.group}</td>
					<th>DATA_ID</th>
					<td colspan="3">${data.dataId}</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<th>VERSION</th>
					<td>${data.version}</td>
					<th>创建时间</th>
					<td>${data.gmtCreate}</td>
					<th>修改时间</th>
					<td>${data.gmtModified}</td>
				</tr>

				<tr>
					<th colspan="6">内容</th>
				</tr>
				<tr>
					<td colspan="6">
						<textarea style="width: 100%" rows="20" readonly>${data.content}</textarea>
					</td>
				</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">没有找到数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>

			<div align="right">
				<button type="submit" class="btn" onclick="location='../view/update.html?group=${data.group}&dataId=${data.dataId}'">修改/删除</button>
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
