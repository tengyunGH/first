package com.tengyun.modules.sys.role.dao;

import java.util.List;
import java.util.Map;

import com.tengyun.modules.sys.role.entity.Role;

/**
 * 角色DAO接口
 * @author x67658
 * @version 2017-12-08
 */
public interface RoleDao<Role> {

	public List<Role> queryIdentity(Map<String,Object> param);
	
}
