

/**
 * 工号匹配
 * 任意六位数字 且不存在于数据库中
 * @returns
 * @param url是查询工号是否已存在于数据库中的url
 */
function checkNumberLegal(url) {
	var pattern =new RegExp(/^[1-9]\d{5}(?!\d)$/); //工号 任意六位数字
	var content =  $(".noValidate").val();
	if(content.match(pattern)) {
		//正则验证成功
        var data = {};
        data.no = content;
        $.ajax({		    
        	type:'POST',
        	url:url,
        	async:false,
        	dataType:'json',
        	data:data,
        	success:function(result) {
        		if(result != undefined && result.code == '200') {
        			//数据库中没有当前工号
        			$(".noErr").hide();
        			$(".noSuccess").show();
        		} else {
        			layer.alert("提示",{content:'这个工号已经存在了，请再选一个！',offset: ['100px', '200px']});
        		}
        	},
        	error:function() {
        		layer.alert("提示",{content:'填入工号发生错误，请重新填入！',offset: ['100px', '200px']});
        	}
        });
	} else {//正则验证有错
		layer.alert("提示",{content:'工号要满足六位数字的规则哟！',offset: ['100px', '200px']});
	}
}


/**
 * 手机号验证
 * @returns
 */
function checkPhoneLegal() {
	var pattern =new RegExp(/^1[3,5,8]\d{9}$/); //手机号 
	var content =  $(".phoneValidate").val();
	if(content.match(pattern)) {
		$(".phoneErr").hide();
		$(".phoneSuccess").show();
	} else {//正则验证有错
		layer.alert("提示",{content:'手机号填写错误！',offset: ['100px', '200px']});
	}
}

/**
 * 电话号验证
 * @returns
 */
function checkMobileLegal() {
	var pattern =new RegExp(/^\d{3}-\d{8}|\d{4}-\d{7}$/); //电话号
	var content =  $(".mobileValidate").val();
//	debugger
	if(content.match(pattern)) {
		$(".mobileErr").hide();
		$(".mobileSuccess").show();
	} else {
		layer.alert("提示",{content:'电话号填写错误！',offset: ['100px', '200px']});
	}
}

/**
 * 密码验证
 * 8到17位字符由字母、数字及英文符号组合
 * @returns
 */
function checkPasswordLegal() {
	var pattern =new RegExp(/^[a-zA-Z0-9_.@~!?]{8,17}$/); 
	var content =  $(".passwordValidate").val();
	if(content.match(pattern)) {
		$(".passwordErr").hide();
		$(".passwordSuccess").show();
	} else {
		layer.alert("提示",{content:'密码要按照8到17位字符由字母、数字及英文符号组合的规则哟！',offset: ['100px', '200px']});
	}
}

/**
 * 登录名的验证
 * 5到17位字符由字母开头、数字及英文符号组合
 * @param url是查询用户名是否已存在于数据库中的url
 * @returns
 */
function checkLoginNameUniqueAndLegal(url) {
//	debugger
	    	var pattern =new RegExp(/^[a-zA-Z][a-zA-Z0-9_.@~!?]{4,16}$/); 
	    	var content =  $(".loginNameValidate").val();

	    	if(content.match(pattern)) {
	    		//正则验证成功
		        var data = {};
		        data.loginName = content;
		        $.ajax({		    
		        	type:'POST',
		        	url:url,
		        	async:false,
		        	dataType:'json',
		        	data:data,
		        	success:function(result) {
		        		if(result != undefined && result.code == '200') {
		        			//数据库中没有当前登录名
		        			$(".loginNameErr").hide();
		        			$(".loginNameSuccess").show();
		        		} else {
		        			layer.alert("提示",{content:'这个名称已被注册了哟，请再选一个！',offset: ['100px', '200px']});
		        		}
		        	},
		        	error:function() {
		        		layer.alert("提示",{content:'填入登录名发生错误，请重新填入！',offset: ['100px', '200px']});
		        	}
		        });
		    } else {
		    	//正则验证失败
		    	layer.alert("提示",{content:'用户名要满足5到17位字符由字母开头、数字及英文符号组合的规则哟！',offset: ['100px', '200px']});
		    	$(".loginNameValidate").val('');
		    }
}
