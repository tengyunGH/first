package com.tengyun.modules.ordinaryuser.myfriends.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengyun.modules.ordinaryuser.myfriends.entity.Friends;
import com.tengyun.modules.ordinaryuser.myfriends.service.FriendsService;

@Controller
@RequestMapping(value="friends")
public class FriendsController {

	@Autowired
	private FriendsService<Friends> friendsService;
	
	@RequestMapping(value="query") 
	@ResponseBody
	public Map<String,Object> queryFriends(String friendState) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		//获取当前用户的id
		Object userId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		param.put("userId", userId.toString());
		param.put("friendState",friendState);
		
		//根据当前用户id查询所有的好友
		List<Friends> friendsList = friendsService.query(param);
		
		//判断查回来的数据并整理返回给前台的数据
		Map<String,Object> result = new HashMap<String,Object>();
		if(friendsList != null && friendsList.size() >= 0) {
			result.put("code", "200");
			result.put("friends",friendsList);
		} else {
			result.put("code", "400");
		}
		return result;
	}
}
