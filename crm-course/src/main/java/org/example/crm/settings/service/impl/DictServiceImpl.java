package org.example.crm.settings.service.impl;

import org.example.crm.settings.dao.DicTypeDao;
import org.example.crm.settings.dao.DicValueDao;
import org.example.crm.settings.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DictServiceImpl implements DictService {
    @Resource
    private DicTypeDao dicTypeDao;
    @Resource
    private DicValueDao dicValueDao;
}