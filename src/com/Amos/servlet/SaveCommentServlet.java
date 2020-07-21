package com.Amos.servlet;

import com.Amos.Dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Amos
 * @date 7/20/2020 9:11 AM
 */
@WebServlet(name = "SaveCommentServlet",urlPatterns = "/saveComment.do")
public class SaveCommentServlet extends HttpServlet {
    private CommentDao commentDao = new CommentDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String commentContent= request.getParameter("commentContent");
        String articleId = request.getParameter("articleId");
        String userName = (String) request.getSession().getAttribute("loginUser");

        commentDao.addComment(commentContent,articleId,userName);

        response.sendRedirect(request.getContextPath()+"/getArticle.do?articleId="+articleId);





    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
