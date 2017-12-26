package com.tengyun.modules.ordinaryuser.mythought.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * 评论的controller 
 * @author x67658
 * @param <Thought>
 * @version2017-12-15
 */
@Transactional(readOnly=true)
public interface ThoughtService<Thought> extends Serializable {

	public List<Thought> querySomebody(Map<String,Object> param);
	
	public List<Map<String,Object>> queryAll(Map<String,Object> param);
	
	public List<Map<String,Object>> queryFriends(Map<String,Object> param);

	public int addThought(Map<String,Object> param);
	
	public int updateThought(Map<String,Object> param);
	
	public Integer queryIdByLoginName (Map<String,Object> param);
	
	public String queryLoginNameByUserId(Map<String,Object> param);
}
