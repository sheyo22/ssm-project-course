package org.example.crm.settings.service.impl;

import org.example.crm.settings.dao.DicTypeDao;
import org.example.crm.settings.dao.DicValueDao;
import org.example.crm.settings.domain.DicType;
import org.example.crm.settings.domain.DicValue;
import org.example.crm.settings.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictServiceImpl implements DictService {
    @Resource
    private DicTypeDao dicTypeDao;
    @Resource
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getDic() {
        Map<String,List<DicValue>> re = new HashMap<>();
        List<DicType> typeList = dicTypeDao.getDicTypeList();
        for(DicType dicType : typeList){
            re.put(dicType.getCode(),dicValueDao.getDicValueList(dicType.getCode()));
        }
        return re;
    }
}