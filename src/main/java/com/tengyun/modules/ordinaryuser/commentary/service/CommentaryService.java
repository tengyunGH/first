package com.tengyun.modules.ordinaryuser.commentary.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public interface CommentaryService<Commentary> extends Serializable {

	public List<Commentary> queryByParam(Map<String,Object> param);
}
