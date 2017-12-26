package com.tengyun.modules.ordinaryuser.myfriends.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * 管理friends的service
 * @author x67658
 * @param <Friends>
 * @version 2017-12-20
 */
@Transactional(readOnly=true)
public interface FriendsService<Friends> extends Serializable  {
	
	public List<Friends> query(Map<String,Object> param);
	
}
