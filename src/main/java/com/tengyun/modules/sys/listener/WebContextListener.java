package com.tengyun.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n=======================================\r\n");
		sb.append("\r\n    yes, you made it!    \r\n");
		sb.append("\r\n========================================\r\n");
		System.out.println(sb.toString());
		return super.initWebApplicationContext(servletContext);
	}
}
