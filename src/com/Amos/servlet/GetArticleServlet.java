package com.Amos.servlet;

import com.Amos.Dao.ArticleDao;
import com.Amos.Dao.CommentDao;
import com.Amos.Dao.replayDao;
import com.Amos.Dao.userDao;
import com.Amos.entity.Article;
import com.Amos.entity.Comment;
import com.Amos.entity.Reply;
import com.Amos.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos
 */
@WebServlet(name = "GetArticleServlet",urlPatterns = "/getArticle.do")
public class GetArticleServlet extends HttpServlet {
    private com.Amos.Dao.userDao userDao= new userDao();
    private ArticleDao articleDao = new ArticleDao();
    private CommentDao commentDao = new CommentDao();
    private com.Amos.Dao.replayDao replayDao = new replayDao();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        Article article = articleDao.getArticlesByArticleId(articleId);
        User user = userDao.getUserByUserName(article.getSenderName());

        String orderType = request.getParameter("orderType");
        if (orderType == null || "".equals(orderType.trim())) {
            orderType = "asc";
        }
        request.setAttribute("articleId",articleId);
        //request.setAttribute("orderType", orderType);
        request.setAttribute("user",user);

        List<Comment> comments=null;


        String commentContent = request.getParameter("commentContent");



        if(commentContent != null &&  !"".equals(commentContent)){
            comments = commentDao.searchComment(commentContent);
        }else{
            comments=commentDao.getCommentsByArticleId(articleId,orderType);
        }

        List<Map<String, Object>> commentsMap = new ArrayList<>();
        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                User commentUser = userDao.getUserByUserName(comment.getCommentUserName());
                List<Reply> replys = replayDao.getReplysByCommentId(comment.getCommentId() + "");
                List<Map<String, Object>> replysMap = new ArrayList<>();
                if (replys != null) {
                    for (int j = 0; j < replys.size(); j++) {
                        Reply reply = replys.get(j);
                        User replyUser = userDao.getUserByUserName(reply.getReplyUserName());
                        Map<String, Object> replyMap = new HashMap<>();
                        replyMap.put("reply", reply);
                        replyMap.put("replyUser", replyUser);
                        replysMap.add(replyMap);
                    }
                }
                Map<String, Object> commentMap = new HashMap<>();
                commentMap.put("comment", comment);
                commentMap.put("commentUser", commentUser);
                commentMap.put("replysMap", replysMap);
                commentsMap.add(commentMap);
            }
        }
        request.setAttribute("commentsMap", commentsMap);
        request.setAttribute("article",article);
        request.getRequestDispatcher("/article_detail.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
