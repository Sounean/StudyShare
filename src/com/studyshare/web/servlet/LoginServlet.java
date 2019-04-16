package com.studyshare.web.servlet;

import com.studyshare.domain.User;
import com.studyshare.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.设置编码（因为要页面要显示的东西里有中文）
        response.setContentType("text/html;charset=utf-8");

        //1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.调用userservice里的login(username , password)  返回值：User user;
        User user = null;
        try {
            user = new UserService().login(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("网络异常,请稍后再试!");
        }

        //3.判断user是否为空
        if (user == null){
            //3.1若为空，则表示账号密码错了，写“用户名和密码不匹配”
            response.getWriter().print("用户名和密码不正确");
            // 3秒后进行跳转
           // response.setHeader("refresh" , "3;url=/StudyShare/web/index.html");
        }else {
            //3.2若不为空，写“xxx:欢迎回来”
            //response.getWriter().print(user.getUsername() + "欢迎回来");

            //成功就跳转到login_success.html
            //2. 跳转到成功的界面
            //设置状态码? 重新定位 状态码
            response.setStatus(302);
            //定位跳转的位置是哪一个页面。
            response.setHeader("Location", "content.html");

        }


    }

}
