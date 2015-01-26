<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/top.jsp"%>

<div class="container-fluid">
	<div class="row-fluid">
		<%@ include file="/left.jsp"%>
		<div class="span10">
			<p />
			<%@ include file="/message.jsp"%>

			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<!--***********上面的所有内容所有页面通用，请勿修改***********-->
			<p />
			<table class="table table-bordered table-condensed ">
				<c:choose>
					<c:when test="${not empty data}">
						<tr>
							<th>数据分组(GROUP):</th>
							<td>${data.group}</td>
						</tr>
						<tr>
							<th>数据名称(DATA_ID):</th>
							<td>${data.dataId}</td>
						</tr>
						<tr>
							<th>版本号(VERSION):</th>
							<td>${data.version}</td>
						</tr>
						<tr>
							<th>创建时间(GMT_CREATE):</th>
							<td>${data.gmtCreate}</td>
						</tr>

						<tr>
							<th>修改时间(GMT_MODIFIED):</th>
							<td>${data.gmtModified}</td>
						</tr>

						<tr>
							<th colspan="2">内容(CONTENT):</th>
						</tr>
						<tr>
							<td colspan="2"><textarea style="width: 100%" rows="30"
									readonly>${data.content}</textarea></td>
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
				<button type="submit" class="btn"
					onclick="location='../view/update.html?group=${data.group}&dataId=${data.dataId}'">修改/删除</button>
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
