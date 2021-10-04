package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ResponseDemo01", value = "/ResponseDemo01")
public class ResponseDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Demo01");

//        //重定向，访问/ResponseDemo01会自动跳转到/ResponseDemo02资源
//        //1.设置状态码为302
//        response.setStatus(302);
//        //2.设置响应头location
//        response.setHeader("location", "ResponseDemo02");

        //另一个简单的重定向方法
//        response.sendRedirect("ResponseDemo02");  //表达"./ResponseDemo02"，"./"可以省略
        response.sendRedirect("https://www.baidu.com");

        //重定向的特点：
        //1. 地址栏发生变化
        //2. 重定向可以访问其他站点（服务器）的资源
        //3. 重定向是两次请求，不能使用Request对象来共享数据

        //转发的特点：
        //1. 转发地址栏路径不变
        //2. 转发只能访问当前服务器下的资源
        //3. 转发是一次请求，可以使用Request对象来共享数据
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
