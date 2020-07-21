package com.Amos.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Amos
 * @date 7/20/2020 2:19 PM
 */
@WebFilter(filterName = "LoginFilter",
        urlPatterns = {"/saveArticle.do", "/saveComment.do", "/saveReply.do", "/saveUpvote.do", "/saveUserInfo.do", "/saveUserPwd.do", "/getUser.do","/admin/*"})
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String loginUser = (String) request.getSession().getAttribute("loginUser");
        request.setCharacterEncoding("UTF-8");
        if(loginUser != null && !"".equals(loginUser)){
            chain.doFilter(req, resp);
        }else{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('PLz login！');history.back()</script>");
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}



//在经过为期5天的实训后，在老师的帮助下，对IDEA开发工具的熟练度有了大的提升，也学会了多种调试代码的方式，从不同的角度来发现代码的中bug。编码要从用户的角度来考虑问题，这样也有助于发现代码的边界判断以及找到新的业务逻辑。同时又学了一种新的连接池Druid，大大提高了编码效率，老师讲的很仔细，原理细节也有涉及，对我帮助很大
