package com.tengyun.modules.ordinaryuser.myfriends.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.ordinaryuser.myfriends.dao.FriendsDao;
import com.tengyun.modules.ordinaryuser.myfriends.entity.Friends;
import com.tengyun.modules.ordinaryuser.myfriends.service.FriendsService;

@Service("FriendsService")
@Transactional(readOnly=true)
public class FriendsServiceImpl implements FriendsService<Friends>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FriendsDao<Friends> friendsDao;
	
	@Override
	public List<Friends> query(Map<String,Object> param) {
		return friendsDao.query(param);
	}

}
