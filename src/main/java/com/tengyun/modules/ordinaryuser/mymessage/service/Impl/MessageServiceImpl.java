package com.tengyun.modules.ordinaryuser.mymessage.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.ordinaryuser.mymessage.service.MessageService;
import com.tengyun.modules.ordinaryuser.mythought.entity.Thought;


@Transactional(readOnly=true)
@Service("MessageService")
public class MessageServiceImpl implements MessageService<Thought>  {

}
