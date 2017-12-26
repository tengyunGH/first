package com.tengyun.modules.sys.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tengyun.modules.sys.office.service.OfficeService;


/**
 * 机构controller
 * @author x67658
 * @version 2017-12-08
 */
@Controller
@RequestMapping(value="sys/office")
public class OfficeController{

	@Autowired
	private OfficeService officeService;
	

}
