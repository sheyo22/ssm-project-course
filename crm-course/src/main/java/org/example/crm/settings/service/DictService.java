package org.example.crm.settings.service;

import org.example.crm.exception.LoginException;
import org.example.crm.settings.domain.DicValue;
import org.example.crm.settings.domain.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface DictService {
    Map<String,List<DicValue>> getDic();
}
