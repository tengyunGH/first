package com.tengyun.modules.ordinaryuser.mymessage.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface MessageService<Thought> extends Serializable{

}
