package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RequestDemo05")
public class RequestDemo05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get 获取请求参数
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Post 获取请求参数
        request.setCharacterEncoding("UTF-8");
        System.out.println("RequestDemo05被访问了");
        request.setAttribute("msg", "hello");
        request.getRequestDispatcher("/RequestDemo06").forward(request, response);
    }
}
