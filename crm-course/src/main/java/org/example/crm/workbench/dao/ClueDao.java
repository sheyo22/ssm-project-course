package org.example.crm.workbench.dao;

import org.apache.ibatis.annotations.Param;
import org.example.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueDao {
    int saveClue(Clue clue);
    int totalCount(Clue clue);
    List<Clue> selectPageList(@Param("clue") Clue clue, @Param("skipCount") int skipCount, @Param("pageSize") int pageSize);
}
