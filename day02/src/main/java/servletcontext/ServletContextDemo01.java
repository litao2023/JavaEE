package servletcontext;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletContextDemo01", value = "/ServletContextDemo01")
public class ServletContextDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletContext对象的获取(1和2获取到的ServletContext对象完全一样)

//        //1. 通过Request对象获取
//        ServletContext servletContext = request.getServletContext();

        //2. 通过HttpServlet获取
        ServletContext servletContext = this.getServletContext();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
