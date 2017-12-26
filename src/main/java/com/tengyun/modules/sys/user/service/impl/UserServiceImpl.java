package com.tengyun.modules.sys.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.sys.menu.entity.Menu;
import com.tengyun.modules.sys.role.entity.Role;
import com.tengyun.modules.sys.user.dao.UserDao;
import com.tengyun.modules.sys.user.entity.User;
import com.tengyun.modules.sys.user.service.UserService;

/**
 * 用户Service接口实现类
 * @author x67658
 * @version 2017-12-08
 */
@Service("UserService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService<User>{

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDao<User> userDao;
	
	@Override
	public User findUser(Map<String,Object> params) {
		//根据登录名查询有无匹配的用户 在登录和注册匹配用户名时调用
		List<User> userList = userDao.findUserByMap(params);
		if(userList.size() == 1 &&  userList.get(0) != null) {
			String token ="Bearea" + new StringBuffer(params.get("loginName") + "-tengyun").reverse().toString();
			userList.get(0).setToken(token);
			return userList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int add(User user) {
		int i= userDao.insertUser(user);
		return i;
	}
	
	@Override
	public List<Role> queryRole(Map<String,Object> param) {
		List<Role> roleList = userDao.queryRole(param);
		return roleList;
	}
	
	@Override
	public List<Menu> queryMenu(Map<String,Object> param) {
		List<Menu> menuList = userDao.queryMenu(param);
		return menuList;
	}
	

}
