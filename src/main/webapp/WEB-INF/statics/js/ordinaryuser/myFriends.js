//当前页面加载时 默认执行allThought函数 加载当前网站所有我可见的thought 还要加载出所有我的好友列表
$(document).ready(function() {
	var data = {
			"friendState" : "1"
	};
	//查询所有我的好友 加载在friendsList这个table中
	$.ajax({
		url : "/htmlintegration/friends/query",
		type : "POST",
		async : false,
		dataType : "json",
		data : data,
		success : function(result) {
			debugger
			if(result.code == "200" && result.friends != null) {
				for(var i = 0;i < result.friends.length; i++) {
					vmFriendsList.friends.push(result.friends[i]);
				}
			}
		},
		error : function() {
			layer.alert("",{content : "加载朋友名单失败，请重新加载！"});
		}
	});
	
	//执行allThought函数加载当前所有用户我可见的thought
	allThought();
	
	$(".friends").show();
	$(".friendsThought").show();
});


var vmFriendsList = new Vue({
	el : ".bodyDiv",
	data : {
		friends : [],
		thought : []
	},
	updated : function() {
		
	}
});



//点击所有人 在body右边的那个div中的table中加载网站所有用户的 我 可见的Thought
function thoughtQuery(url, data) {
	debugger
	$.ajax({
		url : url,
		dataType : "json",
		type : "POST",
//		contentType : "application/json",//据说很重要 ,这里没有这一行的话就传不到后台 
		data : data,
		async : false,
		success : function(result) {
			if(result.code == "200" && result.data != null) {
				vmFriendsList.thought = [];
				for(var i =0; i < result.data.length; i++) {
					vmFriendsList.thought.push(result.data[i]);
				}
			} else {
				layer.alert("",{content : "加载失败，请重新加载！"});
			}
		},
		error : function() {
			layer.alert("",{content : "加载失败，请重新加载！error"});
		}
	});
}

//加载所有我能见的该网站的所有Thought   返回的结果中有loginName
function allThought() {
	var data = {
			"flag" : '1',
	};
	thoughtQuery('/htmlintegration/thought/queryThought', data);
}

//点击好友 在body右边那个div中的table里加载我的所有好友的 我 可见的thought   返回的结果中有loginName
function friendsThought() {
	var data = {
			"flag" : "2",
	};
	thoughtQuery('/htmlintegration/thought/queryThought', data);
}

//点击查询 根据右边输入的昵称去查询网站中所有这个登录名（昵称）是这个的人  登录名是唯一的
function query() {
	var loginName = $("input[name='loginName']").val();
	querySomeone(loginName);
}
//根据登录名查询某一个人的所有Thought
function querySomeone(loginName) {
	var data = {
			"loginName" : loginName
	};
	thoughtQuery('/htmlintegration/thought/querySomeBody', data);
	for(var i = 0;i < vmFriendsList.thought.length; i++) {
		vmFriendsList.thought[i].loginName = $("input[name='loginName']").val();
	}
}

//在右边的thought的Div里面展示这个朋友的所有我可见的thought 并在顶端展示这个朋友的一些相关信息
function displayFriendThought(userId) {
	debugger
	//获取当前点击的那个朋友的序号
//	var i = 0;
//	$(tag).parent().prevAll().each(function() {
//		i++;
//	});
	//获取那个朋友的id
//	var userId = vmFriendsList.friends[i-1].friendId;
	var data = {
			"userId" : userId
	};
	$.ajax({
		url : '/htmlintegration/thought/queryOneFriend',
		dataType : "json",
		type : "POST",
		data : data,
		async : false,
		success : function(result) {
			if(result.code == "200" && result.data != null) {
				vmFriendsList.thought = [];
				for(var i =0; i < result.data.length; i++) {
					vmFriendsList.thought.push(result.data[i]);
					vmFriendsList.thought[i].loginName = result.loginName;
				}
			} else {
				layer.alert("",{content : "加载失败，请重新加载！"});
			}
		},
		error : function() {
			layer.alert("",{content : "加载失败，请重新加载！error"});
		}
	});
}
