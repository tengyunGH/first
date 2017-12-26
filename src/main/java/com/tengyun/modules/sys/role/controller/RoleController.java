package com.tengyun.modules.sys.role.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengyun.modules.sys.role.entity.Role;
import com.tengyun.modules.sys.role.service.RoleService;

/**
 * 角色Controller
 * @author x67658
 * @version 2017-12-08
 */
@Controller
@RequestMapping(value="sys/role")
public class RoleController{
	@Autowired
	private RoleService<Role> roleService;
	
	/**
	 * 根据当前用户的id查询该用户的所有角色名
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="identity")
	@ResponseBody
	public Map<String,Object> queryIdentity() {
		
		Object userId = SecurityUtils.getSubject().getSession().getAttribute("userId");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		
		List<Role> roleList = roleService.queryIdentity(params);
		
		List<String> roleNameList = new ArrayList<String>();
		Map<String,Object> result = new HashMap<String,Object>();
		//如果查询成功了 就将当前用户的所有角色名放入一个list中
		if(roleList != null && roleList.size() > 0) {
			for(int i = 0;i < roleList.size(); i++) {
				roleNameList.add(roleList.get(i).getEnname());
			}
			result.put("code", "200");
			result.put("roleNames", roleNameList);
		} else {
			result.put("code", "400");
			result.put("message", "查询当前用户角色失败，请重新加载当前页面");
		}
		return result;
	}
}
