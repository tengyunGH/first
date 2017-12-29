package com.tengyun.modules.ordinaryuser.commentary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.ordinaryuser.commentary.dao.CommentaryDao;
import com.tengyun.modules.ordinaryuser.commentary.entity.Commentary;
import com.tengyun.modules.ordinaryuser.commentary.service.CommentaryService;

@Transactional(readOnly=true)
@Service("CommentaryService")
public class CommentaryServiceImpl implements CommentaryService<Commentary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CommentaryDao<Commentary> commentaryDao;
	
	@Override
	public List<Commentary> queryByParam(Map<String,Object> param) {
		return commentaryDao.queryByParam(param);
	}
}
