package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/RequestDemo03")
public class RequestDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get 获取请求参数
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Post 获取请求参数
        String username = request.getParameter("username");
        System.out.println(username);
        System.out.println("--------");

        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        System.out.println("--------");

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            System.out.println(name + "----" + value);
        }
        System.out.println("--------");

        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keyset = parameterMap.keySet();
        for (String name : keyset) {
            String[] values = parameterMap.get(name);
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("----" + name + "----");
        }
    }
}
