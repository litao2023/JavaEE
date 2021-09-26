package servlet;

import dao.UserDao;
import domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("UTF-8");
        //2.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.封装User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        //4.调用UserDao的login方法
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        if (user == null){
            //登录失败
            request.getRequestDispatcher("/FailServlet").forward(request, response);
        }else {
            //登录成功
            //存储数据
            request.setAttribute("user", user);
            request.getRequestDispatcher("/SuccessServlet").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
