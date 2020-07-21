package com.Amos.servlet;

import com.Amos.Dao.ArticleDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("ALL")
@WebServlet(name = "SaveArticleServlet",urlPatterns = "/saveArticle.do")
public class SaveArticleServlet extends HttpServlet {
    private ArticleDao articleDao  = new ArticleDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String zoneID = request.getParameter("zoneId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        String userName = (String) request.getSession().getAttribute("loginUser");

        articleDao.saveArticle(title, content, zoneID, userName);


        response.sendRedirect(request.getContextPath() + "/index.do?zoneId=" + zoneID);





    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
