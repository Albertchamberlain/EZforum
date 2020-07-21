package com.Amos.Dao;

import com.Amos.entity.User;
import com.Amos.utils.MD5;

import java.util.List;

/**
 * @author Amos
 * Dao by user
 */
public class userDao extends BaseDao{



    public static void main(String[] args) {

    }

    public boolean addUser(String userName, String userPass, String email) {
        boolean isAdd;
        String sql = "insert into bbs_user(userName,userPass,email) values (?,?,?)";
        Object[] params = {userName,userPass,email};
        update(sql,params);
        isAdd = true;
        return isAdd;

    }

    public User getUserByUserName(String userName) {
            String sql = "select * from bbs_user where userName = ?";
            Object[] params = {userName};
            User user = (User)  findOne(sql, User.class, params);
            return user;
    }
    public User getUserByNameAndPass (String userName, String userPass) {
        String sql = "select * from bbs_user where userName = ? and userPass = ?";
        userPass = MD5.md5Password(userPass);
        Object[] params = {userName, userPass};
        User user = (User) this.findOne(sql, User.class, params);
        return user;
    }

    public void updateLoginStatus(String userName, int status){
        String sql = "update bbs_user set loginStatus = ? where userName = ?";
        Object[] params = {status, userName};
        this.update(sql, params);
    }


    public List<User> countOnlineUser() {
        String sql =" select * from bbs_user where loginStatus = 1";
        return this.findList(sql,User.class);
    }

    public void updateUserInfo(String email, String picUrl, String userName) {
        String sql = "update bbs_user set email = ?, picUrl = ? where userName = ?";
        Object[] params = {email, picUrl, userName};
        this.update(sql, params);
    }
    public void updateUserPwd(String newPassword, String userName,String oldPass) {
        String sql = "update bbs_user set userPass =  ? where userName = ? and userPass = ?";
        Object[] params ={newPassword,userName};
        update(sql,params);
    }
}
