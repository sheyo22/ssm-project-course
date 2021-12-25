package org.example.crm.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        if("/login.jsp".equals(path)||"/user/login.do".equals(path)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        Object user = request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
