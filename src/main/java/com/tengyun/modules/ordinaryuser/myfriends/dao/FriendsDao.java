package com.tengyun.modules.ordinaryuser.myfriends.dao;

import java.util.List;
import java.util.Map;

public interface FriendsDao<Friends> {

	public List<Friends> query(Map<String,Object> param);
}
