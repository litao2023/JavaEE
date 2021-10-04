package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ResponseDemo04", value = "/ResponseDemo04")
public class ResponseDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.获取输出流之前，设置流的默认编码为UTF-8
        response.setCharacterEncoding("UTF-8");
//        //1.告知浏览器，服务器发送的消息体数据的编码，建议浏览器使用该编码进行解码
//        response.setHeader("content-type", "text/html;charset=UTF-8");
        //步骤1的简单形式来设置编码
        response.setContentType("text/html;charset=UTF-8");
        //2.获取字节输出流
        ServletOutputStream sos = response.getOutputStream();

        //3.输出数据
        sos.write("Hello".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
