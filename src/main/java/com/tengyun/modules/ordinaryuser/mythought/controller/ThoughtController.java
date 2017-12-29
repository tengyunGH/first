package com.tengyun.modules.ordinaryuser.mythought.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengyun.modules.ordinaryuser.myfriends.entity.Friends;
import com.tengyun.modules.ordinaryuser.myfriends.service.FriendsService;
import com.tengyun.modules.ordinaryuser.mythought.entity.Thought;
import com.tengyun.modules.ordinaryuser.mythought.service.ThoughtService;

/**
 * 评论模块的conntroller
 * @author x67658
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value="thought")
public class ThoughtController {
	
	@Autowired
	private ThoughtService<Thought> thoughtService;
	
	@Autowired
	private FriendsService<Friends> friendstService;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 实现的功能是
	 *  1、查询自己的所有Thought  参数是createDate  updateDate
	 * 2、根据登录名查询 可能是自己 可能是朋友 可能是陌生人   参数是loginName 
	 * 3、根据某个人的userId查这个人的Thought  参数是userId和visibilities
	 * @return Map[code,thoughts]
	 */
	@RequestMapping(value="queryMine")
	@ResponseBody
	public Map<String,Object> query(String createDate,String updateDate) {
		Map<String,Object> result = new HashMap<String,Object>();//存放返回结果
		
		Map<String,Object> params = new HashMap<String,Object>();//存放查询参数
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		
		List<Thought> myThoughts = new ArrayList<Thought>();//存放查询结果
		
		//获取当前用户的userId
		Object myUserId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		
		params.put("userId", myUserId);
		params.put("visibilities", Arrays.asList('0', '1','2'));
		try {
			if(createDate != null && createDate != "") {
				params.put("createDate",sdf.parse(createDate));
			}
			if(updateDate != null && updateDate != "") {
				params.put("updateDate",sdf.parse(updateDate));
			}
			myThoughts = thoughtService.querySomebody(params);
			if(myThoughts != null && myThoughts.size() >= 0) {
				result.put("code", "200");
				result.put("data", myThoughts);
			} else {
				result.put("code", "400");
				result.put("message","数据库查询异常");
			}
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
			result.put("code", "400");
			result.put("message","日期格式不对");
			return result;
		}
	}
	
	@RequestMapping(value="queryOneFriend")
	@ResponseBody
	public Map<String,Object> queryOneFriend(String userId) {

		Map<String,Object> result = new HashMap<String,Object>();//存放返回结果
		
		Map<String,Object> params = new HashMap<String,Object>();//存放查询参数

		params.put("userId", userId);
		String loginName = thoughtService.queryLoginNameByUserId(params);
		params.put("visibilities", Arrays.asList( '1','2'));
		List<Thought> friendThoughts = thoughtService.querySomebody(params);
		if(friendThoughts != null && friendThoughts.size() >= 0) {
			result.put("code", "200");
			result.put("loginName", loginName);
			result.put("data", friendThoughts);
		} else {
			result.put("code", "400");
			result.put("message","数据库查询异常");
		}
		return result;
	}
	
	
	@RequestMapping(value="querySomeBody")
	@ResponseBody
	public Map<String,Object> querySomeBodyByUserId(String userId, String loginName) {
		Map<String,Object> result = new HashMap<String,Object>();//存放返回结果
		
		Map<String,Object> params = new HashMap<String,Object>();//存放查询参数
		
		List<Thought> someBodyThoughts = new ArrayList<Thought>();//存放查询结果
		
		//获取当前用户的userId
		Object myUserId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		if(loginName != null && userId == null) {
			params.put("loginName", loginName);
			userId = thoughtService.queryIdByLoginName(params).toString();
		}
		if(userId != null && userId.equals(myUserId)) {//如果这个人是我自己
			params.put("userId", myUserId);
			params.put("visibilities", Arrays.asList('0', '1','2'));
		} else {
			//查出这个人的所有朋友  看这个人是不是我的朋友
			List<Friends> friendsList = friendstService.query(params);
			if(friendsList != null && friendsList.size() > 0) {
				int flag = 0;
				for(int j= 0;j < friendsList.size(); j++) {
					//这个人是我的朋友
					if(myUserId.equals(friendsList.get(j).getFriendId())) {//如果我是他的朋友
						params.put("visibilities", Arrays.asList( '1','2'));
						flag = 1;
						break;
					}
				}
				if(flag == 0) {
					//这个人是我的陌生人
					params.put("visibilities", Arrays.asList('2'));
				}
			} else {
				//这个人是我的陌生人
				params.put("visibilities", Arrays.asList('2'));
			}
		}
		someBodyThoughts = thoughtService.querySomebody(params);
		if(someBodyThoughts != null && someBodyThoughts.size() >= 0) {
			result.put("code", "200");
			result.put("data", someBodyThoughts);
		} else {
			result.put("code", "400");
			result.put("message","数据库查询异常");
		}
		return result;
	}
	
	/**
	 * flag为1  查询当前用户所有可以看见的Thought 所有人的2 + 朋友的 1+ 我的0和1  按createDate排序
	 * flag为2  查询当前用户所有朋友的2和1 所有我的2和1和0
	 * @return
	 */
	@RequestMapping(value="queryThought")
	@ResponseBody
	public Map<String,Object> queryAll(String flag) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		Object userId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		params.put("userId", userId.toString());
		
		List<Map<String,Object>> thoughtList = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(flag.equals("1")) {//表示前台要查所有我能看见的thought
			
			thoughtList = thoughtService.queryAll(params); 
			
		} else if (flag.equals("2")) {//表示前台要查我能看见的所有的我的朋友的Thought
			
			thoughtList = thoughtService.queryFriends(params); 
			
		} else { //传输的flag不是1、2 传输错了
			result.put("code", "400");
			result.put("message","数据传输异常，请重新加载");
			return result;
		}

		if(thoughtList != null && thoughtList.size() >= 0) {
			result.put("code", "200");
			result.put("data", thoughtList);
		} else {
			result.put("code", "400");
			result.put("message","数据库查询异常");
		}
		return result;
	}
	
	
	@RequestMapping(value="addThought")
	@ResponseBody
	public Map<String,Object> addThought(String headLine, String thought, String visibility) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		Object userId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId.toString());
		params.put("createDate", sdf.format(new Date()));
		params.put("headLine", headLine);
		params.put("thought", thought);
		params.put("visibility", visibility);
		params.put("state", "0");
		
		int i = thoughtService.addThought(params);
		if(i > 0) {
			result.put("code", "200");
			result.put("message","发表成功" );
		} else {
			result.put("code", "400");
			result.put("message","数据库插入异常");
		}
		return result;
	}
	
	@RequestMapping(value="writingThought")
	@ResponseBody
	public Map<String,Object> editThought(String headLine, String thought, String id, String visibility) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟

		Map<String,Object> params = new HashMap<String,Object>();
		//更改where条件
		params.put("id", id);
		//要改的字段
		params.put("updateDate", sdf.format(new Date()));
		params.put("headLine", headLine);
		params.put("thought", thought);
		params.put("visibility", visibility);
		int i = thoughtService.updateThought(params);
		
		if(i > 0) {
			result.put("code", "200");
			result.put("message","修改成功" );
		} else {
			result.put("code", "400");
			result.put("message","数据库更新异常");
		}
		return result;
	}
	
	@RequestMapping(value="deleteLogical")
	@ResponseBody
	public Map<String,Object> deleteLogical(String[] ids) {
		Map<String,Object> result = new HashMap<String,Object>();
		//构造一个日期格式
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟

		Map<String,Object> params = new HashMap<String,Object>();
		//更改的where条件
		params.put("ids", ids);
		//更改的字段
		params.put("delDate", sdf.format(new Date()));
		params.put("state", "1");
		
		int i = thoughtService.updateThought(params);
		if(i == ids.length) {
			result.put("code", "200");
			result.put("message","删除成功" );
		} else {
			result.put("code", "400");
			result.put("message","数据库逻辑删除异常");
		}
		return result;
	}
	
}
