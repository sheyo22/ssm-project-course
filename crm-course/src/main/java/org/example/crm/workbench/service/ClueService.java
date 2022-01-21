package org.example.crm.workbench.service;

import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviseVO;
import org.example.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueService {
    ReviseVO saveClue(Clue clue);
    PaginationVO<Clue> getClueList(Clue clue, int pageNo, int pageSize);
}
