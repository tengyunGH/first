package com.tengyun.modules.ordinaryuser.mythought.dao;

import java.util.List;
import java.util.Map;

/**
 * Thought接口实现
 * @author x67658
 * @version 2017-12-15
 */
public interface ThoughtDao<Thought> {

	public List<Thought> querySomebody(Map<String,Object> param);
	
	public List<Map<String,Object>> queryAll(Map<String,Object> param);
	
	public List<Map<String,Object>> queryFriends(Map<String,Object> param);

	public int addThought(Map<String,Object> param);
	
	public int updateThought(Map<String,Object> param);
	
	public Integer queryIdByLoginName (Map<String,Object> param);
	
	public String queryLoginNameByUserId(Map<String,Object> param);
}
