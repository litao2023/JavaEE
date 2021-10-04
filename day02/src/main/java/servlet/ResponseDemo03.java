package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseDemo03", value = "/ResponseDemo03")
public class ResponseDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.获取输出流之前，设置流的默认编码为UTF-8
        response.setCharacterEncoding("UTF-8");
//        //1.告知浏览器，服务器发送的消息体数据的编码，建议浏览器使用该编码进行解码
//        response.setHeader("content-type", "text/html;charset=UTF-8");
        //步骤1的简单形式来设置编码
        response.setContentType("text/html;charset=UTF-8");
        //2.获取字符输出流
        PrintWriter pw = response.getWriter();
        //3.输出数据
        pw.write("<h1>Hello world! 你好世界！</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
