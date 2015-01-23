function addPageAndSubmit(oriForm) {
	var error_num = 0;
	var obj = $('.shard-key-input');
	obj.each(function() {
		var value = $(this).val();
		if(isNullOrEmpty(trim(value))) {
			error_num++;
		}
	});
	return error_num;
};