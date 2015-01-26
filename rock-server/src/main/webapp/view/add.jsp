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


			<form id="addFormId" class="form-horizontal" action="../action/doAdd.html" method="post">
				<div class="control-group">
					<label class="control-label">GROUP</label>
					<div class="controls">
						<input name="group" class="input width-medium" type="text" placeholder="数据的分组GROUP信息,请向管理员申请。" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">DATA_ID</label>
					<div class="controls">
						<input name="dataId" type="text" class="input width-large" placeholder="数据的名称.由数字，字母，下划线组成。"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">CONTENT</label>
					<div class="controls">
						<textarea name="content" style="width: 100%" rows="30" placeholder="数据的具体内容。"></textarea>
					</div>
				</div>
				<div align="left">
					<label class="control-label"></label>
					<div class="controls">
						<button type="button" class="btn"
						onclick="action_confirm('确定要新增数据?',submit_form, 'addFormId')">提交新增</button>
					</div>
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
