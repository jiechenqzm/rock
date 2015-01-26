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
					<h4>数据修改</h4>
					您的操作将会被记录下来，请只变更自己分组的数据
				</div>
			</div>
			
			<form id="deleteFormId" name="deleteForm" action="../action/doDelete.html" method="post">
				<input name="group" type="hidden" value="${data.group}" /> 
				<input name="dataId" type="hidden" value="${data.dataId}" /> 
				<input name="version" type="hidden" value="${data.version}" />
			</form>


			<form id="updateFormId" name="updateForm" action="../action/doUpdate.html"
				method="post">
				<input name="group" type="hidden" value="${data.group}" /> <input
					name="dataId" type="hidden" value="${data.dataId}" /> <input
					name="version" type="hidden" value="${data.version}" />


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
								<td colspan="6"><textarea name="content"
										style="width: 100%" rows="20">${data.content}</textarea>
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
				<div  align="right">
				<!-- 
					<button type="button" class="btn" onclick="action_confirm('确定要删除数据?',submit_form, 'deleteFormId')">删除数据</button>
				 
				 	<button type="button" class="btn" onclick="submit_form('deleteFormId')">删除数据</button>
					-->
					
					<button type="button" class="btn" onclick="action_confirm('确定要删除数据?',submit_form, 'deleteFormId')">删除数据</button>
					<button type="button" class="btn" onclick="action_confirm('确定要修改数据?',submit_form, 'updateFormId')">提交修改</button>
				</div>
			</form>


			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
			<!--***********下面的所有内容所有页面通用，请勿修改*********** -->
		</div>
	</div>
</div>
<%@ include file="/foot.jsp"%>
