package com.Amos.servlet;

import com.Amos.Dao.replayDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Amos
 * @date 7/20/2020 9:49 AM
 */
@WebServlet(name = "SaveReplyServlet",urlPatterns = "/saveReply.do")
public class SaveReplyServlet extends HttpServlet {
    private replayDao replyDao = new replayDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String articleId = request.getParameter("articleId");
        String commentId = request.getParameter("commentId");
        String replyContent = request.getParameter("replyContent");
        String userName = (String) request.getSession().getAttribute("loginUser");
        replyDao.addReply(replyContent,commentId,userName);
        response.sendRedirect(request.getContextPath() + "/getArticle.do?articleId=" + articleId);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
        }
}
