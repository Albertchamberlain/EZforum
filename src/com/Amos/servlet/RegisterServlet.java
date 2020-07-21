package com.Amos.servlet;

import com.Amos.Dao.userDao;
import com.Amos.entity.User;
import com.Amos.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * register
 * MD5 Hash password
 */
@SuppressWarnings("ALL")
@WebServlet(name = "RegisterServlet",urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {
    private userDao  userDao = new userDao();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理中文乱码问题
        request.setCharacterEncoding("UTF-8");
        // 设置输出编码
        response.setContentType("text/html;charset=utf-8");

        // 获得用户名
        String userName = request.getParameter("userName");
        // 获得密码
        String userPass = request.getParameter("userPass");
        // 获得email
        String email = request.getParameter("email");

        // 判断用户名是否为null或""
        if (userName == null || "".equals(userName)) {
            response.getWriter().write("<script>alert('用户名不能为空!');history.back()</script>");
            return;  // 直接返回，不再执行下面的语句
        }
        // 判断密码
        if (userPass == null || "".equals(userPass)) {
            response.getWriter().write("<script>alert('密码不能为空!');history.back()</script>");
            return;  // 直接返回，不再执行下面的语句
        }
        // 根据用户名userName查询用户
        User user = this.userDao.getUserByUserName(userName);
        // 判断用户是否存在
        if (user != null) {
            response.getWriter().write("<script>alert('该用户已存在!');history.back()</script>");
            return;  // 直接返回，不再执行下面的语句
        }

        userPass = MD5.md5Password(userPass);

        this.userDao.addUser(userName, userPass, email);
        // 转发到success.jsp页面
        request.getRequestDispatcher("/success.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
