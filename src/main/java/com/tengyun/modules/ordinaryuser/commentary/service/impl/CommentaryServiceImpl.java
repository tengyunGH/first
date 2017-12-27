package com.tengyun.modules.ordinaryuser.commentary.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.ordinaryuser.commentary.entity.Commentary;
import com.tengyun.modules.ordinaryuser.commentary.service.CommentaryService;

@Transactional(readOnly=true)
@Service("CommentaryService")
public class CommentaryServiceImpl implements CommentaryService<Commentary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
