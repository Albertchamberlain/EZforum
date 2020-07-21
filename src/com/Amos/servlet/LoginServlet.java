package com.Amos.servlet;

import com.Amos.Dao.userDao;
import com.Amos.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private  userDao userDao= new userDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String userPass = req.getParameter("userPass");

        User user = userDao.getUserByNameAndPass(userName, userPass);

        if (user != null) {  // 用户不为null，帐号密码正确
            // 将用户名放入session中
            req.getSession().setAttribute("loginUser", user.getUserName());
            // 更新用户状态，1为登录
            this.userDao.updateLoginStatus(userName, 1);
            // 重定向到首页
            resp.sendRedirect(req.getContextPath() + "/index.do");
        }else{  // 用户为null，提示帐号密码错误，并返回上个页面
            // 设置返回数据编码格式
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("<script>alert('帐号或密码错误，登录失败!');history.back()</script>");
        }
    }
}
