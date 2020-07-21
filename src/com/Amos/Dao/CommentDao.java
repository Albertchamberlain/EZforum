package com.Amos.Dao;

import com.Amos.entity.Article;
import com.Amos.entity.Comment;

import java.util.List;

/**
 * @author Amos
 */
public class CommentDao extends BaseDao{

    public List<Comment> getCommentsByArticleId(String articleId, String orderType){
        String sql = "select * from bbs_comment where articleId = ? order by commentTime " + orderType;
        Object[] params = {articleId};
        return this.findList(sql, Comment.class,params);
    }

    public List<Comment> getCommentsByArticleId(String articleId, String commentContent, String orderType){
        String sql = "select * from bbs_comment where articleId = ? and commentContent like ? order by commentTime " + orderType;
        Object[] params = {articleId ,commentContent};
        return this.findList(sql, Comment.class, params);
    }

    public List<Comment> searchComment(String search){
        String sql = "select * from bbs_comment where  commentContent like \"%\"?\"%\" or CommentUserName like \"%\"?\"%\" order by commentTime desc";
        Object[] params = {search,search};
        return this.findList(sql,Comment.class,params);
    }


    public void addComment(String commentContent, String articleId, String userName) {
        String sql = "insert into bbs_comment(commentContent,articleId,commentUserName) values (?,?,?)";
        Object[] params = {commentContent,articleId,userName};
        update(sql,params);
    }
}
