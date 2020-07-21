package com.Amos.servlet;

import com.Amos.Dao.ArticleDao;
import com.Amos.Dao.ZoneDao;
import com.Amos.Dao.userDao;
import com.Amos.entity.Article;
import com.Amos.entity.User;
import com.Amos.entity.Zone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author Amos
 */
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@WebServlet(name = "IndexServlet",urlPatterns = "/index.do")
public class IndexServlet extends HttpServlet {

    private ZoneDao zoneDao = new ZoneDao();
    private ArticleDao articleDao = new ArticleDao();
    private userDao userDao = new userDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Zone> zones = zoneDao.findZones();
        request.setAttribute("zones", zones);

        String zoneId = request.getParameter("zoneId");
        if (zoneId == null || "".equals(zoneId)) {
            zoneId = zones.get(0).getZoneId() + "";
        }


        List<Article> articles = articleDao.getArticlesByZoneId(zoneId);

        String title=request.getParameter("title");

        if (title != null && !"".equals(title.trim())) {
            articles = articleDao.searchArticle(title);
        }

        long todayArticle = articleDao.countTodayarti();
        long toAllArticle = articleDao.countTotalArticle();


        List<User> onlineUsers = userDao.countOnlineUser();
        long onlineUsersSize = onlineUsers != null ? onlineUsers.size() : 0;




        request.setAttribute("onlineUsers",onlineUsers);
        request.setAttribute("onlineUsersSize",onlineUsersSize);
        request.setAttribute("todayArticle",todayArticle);
        request.setAttribute("toAllArticle",toAllArticle);
        request.setAttribute("articles", articles);
        request.setAttribute("zoneId", zoneId);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}