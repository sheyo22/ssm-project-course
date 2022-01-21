package org.example.crm.workbench.service.impl;

import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviseVO;
import org.example.crm.workbench.dao.ClueDao;
import org.example.crm.workbench.domain.Activity;
import org.example.crm.workbench.domain.Clue;
import org.example.crm.workbench.service.ClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueDao clueDao;

    @Override
    public ReviseVO saveClue(Clue clue) {
        ReviseVO re = new ReviseVO();
        re.setSuccess(clueDao.saveClue(clue)==1);
        re.setTotal(1);
        return re;
    }

    @Override
    public PaginationVO<Clue> getClueList(Clue clue, int pageNo, int pageSize) {
        PaginationVO<Clue> res = new PaginationVO<>();
        List<Clue> pageList = clueDao.selectPageList(clue,(pageNo-1)*pageSize, pageSize);
        res.setPageList(pageList);
        res.setTotal(clueDao.totalCount(clue));
        return res;
    }
}
