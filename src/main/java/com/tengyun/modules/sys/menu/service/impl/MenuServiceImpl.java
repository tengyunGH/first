package com.tengyun.modules.sys.menu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.sys.menu.entity.Menu;
import com.tengyun.modules.sys.menu.service.MenuService;

/**
 * 菜单Service接口实现类
 * @author x67658
 * @version 2017-12-08
 */
@Service("MenuService")
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService<Menu>{

}
