package org.example.crm.workbench.controller;

import org.example.crm.settings.domain.User;
import org.example.crm.settings.service.UserService;
import org.example.crm.utils.DateTimeUtil;
import org.example.crm.utils.MD5Util;
import org.example.crm.utils.UUIDUtil;
import org.example.crm.vo.PaginationVO;
import org.example.crm.vo.ReviseVO;
import org.example.crm.workbench.domain.Clue;
import org.example.crm.workbench.service.ClueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClueController {
    @Resource
    private UserService userService;
    @Resource
    private ClueService clueService;

    @RequestMapping("/workbench/clue/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        return userService.selectAll();
    }
    @RequestMapping("/workbench/clue/saveClue.do")
    @ResponseBody
    public ReviseVO saveClue(Clue clue, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        clue.setCreateBy(user.getName());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        clue.setId(UUIDUtil.getUUID());
        return clueService.saveClue(clue);
    }
    @RequestMapping("/workbench/clue/pageList.do")
    @ResponseBody
    public PaginationVO<Clue> getClueList(Clue clue, int pageNo, int pageSize) {
        return clueService.getClueList(clue, pageNo, pageSize);
    }
}
