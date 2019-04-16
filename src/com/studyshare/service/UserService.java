package com.studyshare.service;

import com.studyshare.dao.UserDao;
import com.studyshare.domain.User;

import java.sql.SQLException;

public class UserService {

    /*
     * 用户登陆方法
     * username为用户名
     * password为密码
     * 返回的是一个user用户
     * */
    public User login(String username, String password) throws SQLException {
        //调用dao
        UserDao dao = new UserDao();
        return dao.getUserByUsernameAndPwd(username , password);
    }
}
