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
 * @date 7/20/2020 10:40 AM
 */
@WebServlet(name = "GetUserServlet",urlPatterns = "/getUser.do")
public class GetUserServlet extends HttpServlet {
    private com.Amos.Dao.userDao userDao = new userDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("loginUser");
        User user = userDao.getUserByUserName(username);
        request.setAttribute("user",user);
        String methond = request.getParameter("method");
        if("userInfo".equals(methond)){
            request.getRequestDispatcher("/user_info.jsp").forward(request, response);
        }
        else if("userPwd".equals(methond))
        {
            request.getRequestDispatcher("/user_pwd.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
