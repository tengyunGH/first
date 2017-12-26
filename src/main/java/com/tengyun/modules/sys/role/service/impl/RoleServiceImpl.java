package com.tengyun.modules.sys.role.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.sys.role.dao.RoleDao;
import com.tengyun.modules.sys.role.entity.Role;
import com.tengyun.modules.sys.role.service.RoleService;

/**
 * 角色Service实现类
 * @author x67658
 * @version 2017-12-08
 */
@Service("RoleService")
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService<Role>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleDao<Role> roleDao;
	
	/**
	 * 查询当前登录用户的roleName
	 */
	@Override
	public List<Role> queryIdentity(Map<String,Object> param) {
		return roleDao.queryIdentity(param);
	}

}
