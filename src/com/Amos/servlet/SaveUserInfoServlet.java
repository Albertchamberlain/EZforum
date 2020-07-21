package com.Amos.servlet;

import com.Amos.Dao.userDao;
import com.Amos.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author Amos
 * @date 7/20/2020 11:07 AM
 */
@WebServlet(name = "SaveUserInfoServlet", urlPatterns = "/saveUserInfo.do")
@MultipartConfig
public class SaveUserInfoServlet extends HttpServlet {
    private userDao userDao = new userDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        Part part = request.getPart("picUrl");
        String picUrl = null;
        String userName = (String) request.getSession().getAttribute("loginUser");
        String fileName = part.getSubmittedFileName();
        if (fileName != null && !"".equals(fileName)) {
            String uploadPath = request.getServletContext().getRealPath("upload/images");
            part.write(uploadPath + "/" + fileName);
            picUrl = "upload/images/" + fileName;
        }
        userDao.updateUserInfo(email, picUrl, userName);
        User user = userDao.getUserByUserName(userName);
        request.setAttribute("user", user);
        request.setAttribute("message", "用户信息修改成功！");
        request.getRequestDispatcher("/user_info.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
