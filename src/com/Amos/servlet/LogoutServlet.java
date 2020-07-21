package com.Amos.servlet;

import com.Amos.Dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",urlPatterns = "/logout.do")
public class LogoutServlet extends HttpServlet {
    private com.Amos.Dao.userDao userDao = new userDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userName = (String) request.getSession().getAttribute("loginUser");

        userDao.updateLoginStatus(userName,0);

        request.getSession().invalidate();

        response.sendRedirect(request.getContextPath() + "/index.do");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
