package com.tengyun.modules.sys.user.dao;

import java.util.List;
import java.util.Map;

import com.tengyun.modules.sys.menu.entity.Menu;
import com.tengyun.modules.sys.role.entity.Role;

/**
 * 用户DAO接口
 * @author x67658
 * @version 2017-12-08
 */
public interface UserDao<User>{

	/**
	 * 登录函数 在shiro的realm里面被调用
	 * @param username
	 * @param string
	 * @return 一个map记录登录结果
	 */
	public List<User> findUserByMap(Map<String,Object> param);
	
	/**
	 * 注册函数 
	 * @param user
	 * @return 返回插入记录数
	 */
	public int insertUser (User user);
	
	/**
	 * 查询当前用户的角色 返回所有该用户的角色
	 * @param param
	 * @return
	 */
	public List<Role> queryRole(Map<String,Object> param);
	
	/**
	 * 查询当前用户可访问的菜单 也就是权限
	 * @param param 里面放的是登录名
	 * @return 用户可访问的菜单的地址的list
	 */
	public List<Menu> queryMenu(Map<String,Object> param);
	
}
