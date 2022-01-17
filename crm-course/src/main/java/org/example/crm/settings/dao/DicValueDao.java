package org.example.crm.settings.dao;

import org.example.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getDicValueList(String typeCode);
}
