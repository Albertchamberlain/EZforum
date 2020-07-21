package com.Amos.servlet;

import com.Amos.Dao.userDao;
import com.Amos.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Amos
 * @date 7/20/2020 11:41 AM
 */
@WebServlet(name = "SaveUserPwdServlet", urlPatterns = "/saveUserPwd.do")
public class SaveUserPwdServlet extends HttpServlet {
    private userDao userDao = new userDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String userName = (String) request.getSession().getAttribute("loginUser");
        User user = userDao.getUserByNameAndPass(userName, oldPassword);
        if (user != null) {
            userDao.updateUserPwd(userName, newPassword,oldPassword);
            request.setAttribute("message", "密码修改成功！");
        }else{
            request.setAttribute("message", "原密码错误！");
        }
        User newUser = userDao.getUserByUserName(userName);
        request.setAttribute("user", newUser);
        request.getRequestDispatcher("/user_pwd.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}