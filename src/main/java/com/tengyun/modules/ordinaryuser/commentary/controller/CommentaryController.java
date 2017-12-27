package com.tengyun.modules.ordinaryuser.commentary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tengyun.modules.ordinaryuser.commentary.entity.Commentary;
import com.tengyun.modules.ordinaryuser.commentary.service.CommentaryService;

@Controller
@RequestMapping(value="commentary")
public class CommentaryController {

	@Autowired
	private CommentaryService<Commentary> commentaryService;
	
	
}
