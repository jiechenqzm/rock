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



			<form class="form-search" action="../manager/search.html"
				method="get">
				<input name="group" type="text" class="input-large"
					placeholder="group,不支持模糊查询" value="DEFAULT_GROUP" /> <input
					name="dataId" type="text" class="input-xxlarge"
					placeholder="dataId,支持在前后添加%进行模糊查询" value="%" />
				<button type="submit" class="btn">搜索</button>
			</form>


			<table class="table table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>Group</th>
						<th>DataId</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty data}">
							<tr>

								<c:forEach items="${data}" var="coreData" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td id="dbIp"><c:out value="${coreData.group}" /></td>
										<td id="dbPort"><c:out value="${coreData.dataId}" /></td>
										<td><a id="modal-${status.count}"
											href="#modal-container-${status.count}" data-toggle="modal">查看</a>，
											修改， 删除

											<div id="modal-container-${status.count}"
												class="modal hide fade" role="dialog"
												aria-labelledby="myModalLabel" aria-hidden="true">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">×</button>
													<h3 id="myModalLabel">数据信息</h3>
												</div>
												<div class="modal-body">
													<table class="table table-hover">
														<tr>
															<th>Group:</th>
															<td><c:out value="${coreData.group}" />
														</tr>
														<tr>
															<th>DataId:</th>
															<td><c:out value="${coreData.dataId}" />
														</tr>
														<tr>
															<th colspan="2">Value:</th>
														</tr>
														<tr>
														<td colspan="2">
														<textarea style="width: 100%" rows="10" readonly>${coreData.value}</textarea>
														</td>
														</tr>
													</table>
													
												</div>
												<div class="modal-footer">
													<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
												</div>
											</div></td>
									</tr>
								</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">没有找到任何数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

			<%@ include file="/page.jsp"%>


			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
		</div>
	</div>
</div>
<%@ include file="/foot.jsp"%>
