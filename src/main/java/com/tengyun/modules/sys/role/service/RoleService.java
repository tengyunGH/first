package com.tengyun.modules.sys.role.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.sys.role.entity.Role;

/**
 * 角色Service
 * @author x67658
 * @version 2017-12-08
 */
@Transactional(readOnly=true)
public interface RoleService <Role> extends Serializable {
	
	public List<Role> queryIdentity(Map<String,Object> param);

}
