package servlet;

import domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SuccessServlet", value = "/SuccessServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request域中共享的User对象
        User user = (User) request.getAttribute("user");
        if (user != null) {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write("登录成功！" + user.getUsername() + "，欢迎你！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
