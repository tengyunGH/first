package com.tengyun.modules.sys.office.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengyun.modules.sys.office.service.OfficeService;

/**
 * 机构Service接口实现类
 * @author x67658
 * @version 2017-12-08
 */

@Service("OfficeService")
@Transactional(readOnly=true)
public class OfficeServiceImpl implements OfficeService{

}
