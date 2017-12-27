package com.tengyun.modules.ordinaryuser.commentary.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public interface CommentaryService<Commentary> extends Serializable {

}
