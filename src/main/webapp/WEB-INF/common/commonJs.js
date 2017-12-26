

document.write('<script src="/htmlintegration/statics/js/jquery-3.1.1.min.js"></script>');
document.write('<script src="/htmlintegration/statics/plugin/jqgrid/necessary/jquery.jqGrid.min.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/jqgrid/necessary/i18n/grid.locale-en.js" type="text/javascript"></script>');
//document.write('<script src="/htmlintegration/statics/plugin/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/vue/vue.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/treetable/jquery.treetable.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/echarts/3.4.0/echarts.common.min.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/layer/layer.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/validate/validate.js" type="text/javascript"></script>');
document.write('<script src="/htmlintegration/statics/plugin/laydate/laydate.js" type="text/javascript"></script>');

//关闭当前弹出层
function layerClose() {
	var index  =  parent.layer.getFrameIndex(name);  //获取当前窗体索引　
	parent.layer.close(index); //执行关闭
}

//查询当前用户的身份是管理员还是普通用户
function queryIdentity() {
	var res;
	$.ajax({
		url : "/htmlintegration/sys/role/identity",
		type : "POST",
		dataType : "json",
		async : false,
//		data : {
//			"id" : id
//		},
		success : function(result) {
			if(result.code == "200" && result.roleNames != null && result.roleNames.length > 0) {
				if($.inArray("admin",result.roleNames) >= 0) {
					$(".admin").show();
				} else if ($.inArray("ordinaryUser",result.roleNames) >= 0) {
					$(".ordinaryUser").show();
				}
				res = true;
			} else {
				res =  false;
			}
		},
		error :  function() {
			res =  false;
		}
	});
	return res;
}