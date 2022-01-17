package org.example.crm.web.listener;

import org.example.crm.settings.domain.DicValue;
import org.example.crm.settings.service.DictService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        DictService dictService = context.getBean(DictService.class);
        Map<String, List<DicValue>> map = dictService.getDic();
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            application.setAttribute(key,map.get(key));
        }
    }
}
