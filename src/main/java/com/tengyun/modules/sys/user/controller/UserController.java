package com.tengyun.modules.sys.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengyun.modules.sys.user.entity.User;
import com.tengyun.modules.sys.user.service.UserService;

/**
 * 用户Controller
 * @author x67658
 * @version 2017-12-08
 */
@Controller
@RequestMapping(value="sys/user")
public class UserController{
	
	@Autowired
	private UserService<User> userService;
	
	
	
	protected Logger logger = LoggerFactory.getLogger("tengyun");
	
	/**
	 * 登录控制 传入登录名和密码 进行shiro验证
	 * @param username
	 * @param password
	 * @return shiro验证成功 返回200  验证失败 返回400
	 */
	@RequestMapping(value="login")
	@ResponseBody
	public Map<String,Object> login(String username,String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(),true);
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		 try {
			 Subject subject = SecurityUtils.getSubject();
			 subject.login(token);
			 logger.info("+++++++++++++++++++++++++++======登陆成功=======+++++++++++++++++++++++++++");
			 result.put("code","200");
			 List<String> roleNames = new ArrayList<String>();
			 if(subject.hasRole("admin")) {//会调用doGetAuthorizationInfo这个方法
				 roleNames.add("admin");
			 }
			 if(subject.hasRole("ordinaryUser")) {
				 roleNames.add("ordinaryUser");
			 }
			 result.put("role",roleNames);
			 return result;
		 } catch(Exception e) {
			 logger.error("======登陆异常=======");
			 e.printStackTrace();
			 result.put("code","400");
			 return result;
		 }
	}
	
	/**
	 * 检查注册名是否已被注册
	 * @param username
	 * @return 若已被注册 返回400  没有注册返回200
	 */
	@RequestMapping(value="checkLoginName")
	@ResponseBody
	public Map<String,Object> checkLoginName(String loginName) {
	
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("loginName", loginName);
		
		Map<String,Object> result = new HashMap<String,Object>();
		User user = userService.findUser(params);
		if(user == null) {
			result.put("code", "200");
		} else {
			result.put("code", "400");
		}
		 return result;
	}
	
	@RequestMapping(value="checkNumber")
	@ResponseBody
	public Map<String,Object> checkNumber(String number) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("Nmuber", number);
		
		Map<String,Object> result = new HashMap<String,Object>();
		User user = userService.findUser(params);
		if(user == null) {
			result.put("code", "200");
		} else {
			result.put("code", "400");
		}
		 return result;
	}
	
	/**
	 * 注册控制 将用户所填信息填入数据库
	 * @param user
	 * @return 注册成功 返回200  失败 返回200
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Map<String,Object> add(User user) {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		int i = userService.add(user);
		if(i == 1) {
			result.put("code", "200");
		} else {
			result.put("code", "400");
		}
		 return result;
	}
	
}
