
$(document).ready(function() {
	//页面加载时获取数据库中所有当前用户自己的Thought并通过vue渲染到表格中
	/*初始加载时没有任何查询条件 */
	queryCommon('/htmlintegration/thought/queryMine');
	//显示加载了数据的表格
	$("#thoughtTable").show();
});
//创建时间的layerDate
laydate.render({
		elem: "input[name='createDate']", //指定元素
		type : 'date',//指定日期显示的程度 
		format: 'yyyy-MM-dd', //自定义日期显示的格式
		theme: '#95d4ff',  // 自定义背景色 
		calendar: true   //是否显示公历节日 
	});
//修改时间的layerDate
laydate.render({
		elem:  "input[name='updateDate']",//指定元素
		type : 'date',
		format: 'yyyy-MM-dd',
		theme: '#95d4ff',
		calendar: true
	});

//渲染thought的vue实例
var vm = new Vue({
	el: "#thoughtTable",
	data : {
		//用于记录所有checkbox的选中状态
		checkValue : [],
		//wholeData初始化为空 存放查询回来的记录
		wholeData : []
	}
});

//获取vue实例 在编辑时弹出的框中需要用到
function getVm() {
	return vm;
}

//将数据库中存放的时间date格式的字符串变成一个YYYY-MM-dd HH:mm:ss的格式
function ChangeDateFormat(val) {
    if (val != null) {
        var date = new Date(val);
        //月份为0-11，所以+1，月份小于10时补个0
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        return date.getFullYear() + "-" + month + "-" + currentDate  + "  "+ hour + " : " + minute + " : " + second;
    }
    return "";
}

//本页面会涉及很多要将自己的评论查出来的操作 所以将这个抽出来作为一个公共部分 传入的参数就是要查询的参数
function queryCommon(url, data) {
	 //查询将数据渲染出来
	 
	 //将vm清空
	 vm.wholeData = [];
	 vm.checkValue = [];
	 
	 $.ajax({
			url :  url,
		 	type : 'POST',
		 	dataType : 'json',
		 	data : data,
		 	async : false,
		 	success : function(result) {
		 		if(result != null && result.code == "200" && result.data != null) {
		 			for(var i = 0;i < result.data.length; i++) {
			 			//请求到数据后 判断code为200 并且要求data不为NULL（null和undefined）
			 			result.data[i].createDate  = ChangeDateFormat(result.data[i].createDate);
			 			result.data[i].updateDate  = ChangeDateFormat(result.data[i].updateDate);
			 			vm.wholeData.push(result.data[i]);
			 			//将隐藏的checkbox初始渲染为不选中状态
			 			vm.checkValue.push(false);
			 		}
		 		} else {
		 			layer.alert("",{content : result.message}); 
		 		}
		 	},
		 	error : function() {
		 		layer.alert("",{content : "查询失败，请重新加载页面！error"}); 
		 	}
		 });
}

//查询按钮的点击事件 
function query() {
	//先获取查询条件
	 var data = {};
	 data.createDate = $("input[name='createDate']").val();
	 data.updateDate = $("input[name='updateDate']").val();

//	 去数据库中查询 将得到的数据渲染在页面上
	 queryCommon('/htmlintegration/thought/queryMine', data);	 
}

var flag;
function getFlag() {
	return flag;
}

// 添加按钮的点击事件
function add() {
	flag = "add";
	layer.open({
		type : 2,  //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
		area : ['650px','350px'],
		fix : false,
		offset: '100px',
		maxmin : true,
		title : "发表新看法",
		shade : 0.4,   //默认是0.3透明度的黑色背景（'#000'）。如果你想定义别的颜色，可以shade: [0.8, '#393D49']；如果你不想显示遮罩，可以shade: 0
		content : '/htmlintegration/pages/ordinaryuser/thought/addThought.html' //弹出层是个iframe，这里就写iframe的url
	});
}

//当点击某条记录的编辑和修改时 获取当前点击的顺序
var index = 0;
function getIndex() {
	return index-1;
}
function computeIndex(tag) {
	index = 0
	$(tag).parent().parent().parent().prevAll().each(function() {
		index ++;
	});
//	debugger
}

// 每条记录后面的编辑按钮
function edit(tag) {
	
	computeIndex(tag);
	flag = "edit";
	layer.open({
		type : 2,  //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
		area : ['650px','350px'],
		fix : false,
		offset: '100px',
		maxmin : true,
		title : "修改", 
		shade : 0.4,   //默认是0.3透明度的黑色背景（'#000'）。如果你想定义别的颜色，可以shade: [0.8, '#393D49']；如果你不想显示遮罩，可以shade: 0
		content : '/htmlintegration/pages/ordinaryuser/thought/addThought.html' //弹出层是个iframe，这里就写iframe的url
//		end : function() {//层销毁的回调
//			location.reload();
//		}
	});
}

function editShow() {
	$(".editShow").show();
	$(".editShow").css({"width":"30px","text-align":"center"});
}

function editHide() {
	$(".editShow").hide();
}

function deleteMutil() {
	var ids = new Array();
	for(var i = 0; i < vm.checkValue.length; i++) {
		if(vm.checkValue[i] == true) {
			ids.push(vm.wholeData[i].id);
		}
	}
	debugger
	$.ajax({
		url : "/htmlintegration/thought/deleteLogical",
		traditional: true,
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"ids" : ids
		},
		success : function(result) {
			if(result != null) {
				if(result.code == "200") {
					layer.alert("",{content : result.message, icon : 1});
					query();
					editHide();
				} else if(result.code == "400") {
					layer.alert("",{content : result.message, icon : 2});
				} else {
					layer.alert("",{content :"删除失败，请重新操作！", icon : 2});
				}
			} else {
				layer.alert("",{content :"删除失败，请重新操作！", icon : 2});
			}
		},
		error : function() {
			layer.alert("",{content :"删除失败，请重新操作！", icon : 2});
		}
	});
}

function restore() {
	$(".editShow").hide();
}