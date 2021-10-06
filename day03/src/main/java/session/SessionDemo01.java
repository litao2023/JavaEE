package session;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SessionDemo01", value = "/SessionDemo01")
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用session一次会话的多次请求中共享数据
        //session的实现依赖于cookie

        //session与cookie的区别
        //(1) session存储数据在服务器端，cookie在客户端
        //(2) session没有数据大小限制，cookie有
        //(3) session数据安全，cookie相对不安全

        //1. 获取session
        HttpSession session = request.getSession();
        //2. 存储数据
        session.setAttribute("msg", "hello session");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
