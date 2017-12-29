
$(document).ready(function() {
//	$(".commentaryDiv").hide();
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
		wholeData : [],
		commentarys : []
	},
	methods : {
		readCommentary : function(index) {
//			获取当前的Thought的顺序  i-1  然后获取他的id
//			var index = 0;
//			$(tag).parent().parent().parent().prevAll().each(function() {
//				index++;
//			});
			debugger
			//准备传输的数据
			var data = {
					"thoughtId" : vm.wholeData[index].id,
					"userId" : vm.wholeData[index].userId
			};       
			debugger
			//查询
			$.ajax({
				url : '/htmlintegration/commentary/query',
				type : 'POST',
				dataType : 'json',
				async : false,
				data : data,
				success : function(result) {
					if(result != null) {
						if(result.code == '200') {
							var  commentarys = [];
							for(var i = 0;i < result.data.length; i++) {
								result.data[i].createDate = ChangeDateFormat(result.data[i].createDate);
//								vm.wholeData[index].commentarys.push(result.data[i]);
								vm.commentarys[index] = new Array();
							    vm.commentarys[index].push(result.data[i]);
							}
//							vm.wholeData[index].commentarys = new Array();
							
//							vm.$set(vm.wholeData[index].commentarys, commentarys);
//							$(".commentaryDiv").eq(index).show();
						} else {
							layer.alert("", {content:result.message,offset:'100px'});
						}
					} else {
						layer.alert("", {content:"加载失败，请重新加载！Result为null",offset:'100px'});
					}
				},
				error : function() {
					layer.alert("", {content:"加载失败，请重新加载！error!",offset:'100px'}); 
				}
			});
		}
	}
});

//获取vue实例 在编辑时弹出的框中需要用到
function getVm() {
	return vm;
}

//本页面会涉及很多要将自己的thought查出来的操作 所以将这个抽出来作为一个公共部分 传入的参数就是要查询的参数
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
//			 			vm.wholeData[i] = new Object();
//			 			vm.$set(vm.wholeData[i].thought, result.data[i]);
			 			vm.wholeData.push(result.data[i]);
			 			//vm.wholeData[i].commentarys = [];
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


var index = 0;
function getIndex() {
	return index-1;
}

// 每条记录的续写功能
function writing(tag) {
	//当点击某条记录的续写时 获取当前点击的顺序 然后在续写的那个layer弹出层里获得index 进而获取所需数据
	index = 0;
	$(tag).parent().parent().parent().prevAll().each(function() {
		index ++;
	});
	//做标志，标明，这是续写
	flag = "writing";
	layer.open({
		type : 2,  //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
		area : ['650px','350px'],
		fix : false,
		offset: '100px',
		maxmin : true,
		title : "续写", 
		shade : 0.4,   //默认是0.3透明度的黑色背景（'#000'）。如果你想定义别的颜色，可以shade: [0.8, '#393D49']；如果你不想显示遮罩，可以shade: 0
		content : '/htmlintegration/pages/ordinaryuser/thought/addThought.html' //弹出层是个iframe，这里就写iframe的url
//		end : function() {//层销毁的回调
//			location.reload();
//		}
	});
}


//点击管理按钮 每条记录后出现checkbox 还有一些其他的div什么的都出现
function arrangeShow() {
	$(".arrangeShow").show();
	$(".arrangeShow").css({"width":"30px","text-align":"center"});
}
//点击顶部的编辑按钮后  完成了批量删除后 让不该出现的再次隐藏
function editHide() {
	$(".arrangeShow").hide();
}
// 批量删除的点击事件
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
					//	删除成功，重新查询，隐藏checkbox和其余的div				
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
//点击编辑后取消的点击事件
function restore() {
	$(".arrangeShow").hide();
}

//看评论  根据当前的thought的id查这条Thought的评论
function readCommentary(tag) {

	
}



