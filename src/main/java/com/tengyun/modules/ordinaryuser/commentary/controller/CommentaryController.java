package com.tengyun.modules.ordinaryuser.commentary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengyun.modules.ordinaryuser.commentary.entity.Commentary;
import com.tengyun.modules.ordinaryuser.commentary.service.CommentaryService;

@Controller
@RequestMapping(value="commentary")
public class CommentaryController {

	@Autowired
	private CommentaryService<Commentary> commentaryService;
	
	/**
	 * 根据thoughtId 或者commentaryId 来查询commentary
	 * @param thoughtId 要查询的Thought的id
	 * @param commentaryId 要查询的评论的id
	 * @param UserId 这篇Thought的userId 或者 这条评论的userId
	 * @return
	 */
	@RequestMapping(value="query")
	@ResponseBody
	public Map<String,Object> queryByThoughtId(Long thoughtId,Long commentaryId,Long userId) {
		
		Map<String,Object> result = new HashMap<String,Object>();//存放返回结果
		
		Map<String,Object> params = new HashMap<String,Object>();//存放查询参数
		 
		//获取当前用户的userId
		Object myUserId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		//要查询的不是当前用户的Thought或者commentary的评论 所以可见性要设置为所有人可见
		if(!myUserId.equals(userId)) {
			params.put("visibility", "1");
		}
		if(thoughtId != null) {
			params.put("thoughtId", thoughtId);
			params.put("type", "1");
		}
		if(commentaryId != null) {
			params.put("commentaryId", commentaryId);
			params.put("type", "2");
		}

		List<Commentary> commentaryList = commentaryService.queryByParam(params);
		
		if(commentaryList != null && commentaryList.size() >= 0) {
			result.put("code", "200");
			result.put("data", commentaryList);
		} else {
			result.put("code", "400");
			result.put("message", "查询失败，请重新加载");
		}
		return result;
	}
	
}
