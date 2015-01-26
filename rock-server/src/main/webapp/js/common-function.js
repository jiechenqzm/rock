/**
 * 1.弹出确认框，确认框参数为message
 * 2.如果用户确认，则执行function_name指定的函数
 * 3.如果执行function_name函数，则该函数的参数为function_arg
 * @param message
 * @param function_name
 * @param function_arg
 */
function action_confirm(message, function_name, function_arg) {
	if (confirm(message)) {
		function_name(function_arg);
	}
}

/**
 * 获取id和参数相同的表单并提交
 * @param form_name
 */
function submit_form(form_name) {
	$("#" + form_name).submit();
}