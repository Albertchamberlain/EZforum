package com.Amos.Dao;

import com.Amos.entity.Article;

import java.util.List;


/**
 * @author Amos
 */
public class ArticleDao extends BaseDao{
    public List<Article> getArticlesByZoneId(String zoneId){
        String sql = "select * from bbs_article where zoneId = ? order by isTop desc,sendTime desc,upvoteCount desc";
        Object[] params = {zoneId};
        return this.findList(sql, Article.class, params);
    }


    public List<Article> searchArticle(String search){
        String sql = "select * from bbs_article where title like \"%\"?\"%\" or content like \"%\"?\"%\" or senderName like \"%\"?\"%\" order by isTop desc";
        Object[] params = {search,search,search};
        return this.findList(sql,Article.class,params);
    }

    public long countTodayarti() {
        String sql = "select count(*) from bbs_article where to_days(sendTime) = to_days(now())";
        return count(sql);
    }

    public long countTotalArticle() {
        String sql = "select count(*) from bbs_article ";
        return count(sql);
    }



    public  void saveArticle(String title,String content,String zoneId,String senderName){
        String sql = "insert into bbs_article(title,content,zoneId,senderName) values (?,?,?,?)";
        Object[] params = {title,content,zoneId,senderName};
        this.update(sql,params);
    }

    public Article getArticlesByArticleId(String articleId) {
        String sql = "select * from bbs_article where articleId = ?";
        Object[] params= {articleId};

        return (Article) this.findOne(sql,Article.class,params);

    }







}
