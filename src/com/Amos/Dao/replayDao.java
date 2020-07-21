package com.Amos.Dao;
import com.Amos.entity.Reply;
import java.util.List;
/**
 * @author Amos
 */
public class replayDao extends BaseDao{

    public List<Reply> getReplysByCommentId(String commentId){
        String sql = "select * from bbs_reply where commentId = ? order by replyTime desc";
        Object[] params = {commentId};
        return this.findList(sql, Reply.class, params);
    }
    public void addReply(String replyContent, String commentId, String userName){
        String sql = "insert into bbs_reply(replyContent,commentId,replyUserName) values(?, ?, ?)";
        Object[] params = {replyContent, commentId, userName};
        update(sql, params);
    }

}
