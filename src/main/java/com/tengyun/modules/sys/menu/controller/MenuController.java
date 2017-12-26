package com.tengyun.modules.sys.menu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tengyun.modules.sys.menu.service.MenuService;

/**
 * 菜单Controller
 * @author x67658
 * @version 2017-12-08
 */
@Controller
@RequestMapping(value="sys/menu")
public class MenuController{

	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MenuService menuService;
	
	
}
