package com.tengyun.modules.ordinaryuser.mythought.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.ordinaryuser.mythought.dao.ThoughtDao;
import com.tengyun.modules.ordinaryuser.mythought.entity.Thought;
import com.tengyun.modules.ordinaryuser.mythought.service.ThoughtService;

/**
 * 评论模块的service实现
 * @author x67658
 * @version 2017-12-15
 */
@Service("ThoughtService")
@Transactional(readOnly=true)
public class ThoughtServiceImpl implements ThoughtService<Thought>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ThoughtDao<Thought> thoughtDao;
	
	@Override
	public List<Thought> querySomebody(Map<String,Object> param) {
		return thoughtDao.querySomebody(param);
	}
	
	@Override
	public int addThought(Map<String,Object> param) {
		return thoughtDao.addThought(param);
	}

	@Override
	public int updateThought(Map<String, Object> param) {
		return thoughtDao.updateThought(param);
	}

	@Override
	public List<Map<String,Object>> queryAll(Map<String, Object> param) {
		return thoughtDao.queryAll(param);
	}

	@Override
	public List<Map<String,Object>> queryFriends(Map<String, Object> param) {
		return thoughtDao.queryFriends(param);
	}

	@Override
	public Integer queryIdByLoginName(Map<String, Object> param) {
		return thoughtDao.queryIdByLoginName(param);
	}

	@Override
	public String queryLoginNameByUserId(Map<String, Object> param) {
		return thoughtDao.queryLoginNameByUserId(param);
	}

}
