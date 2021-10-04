package servletcontext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletContextDemo02", value = "/ServletContextDemo02")
public class ServletContextDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletContext对象的获取(1和2获取到的ServletContext对象完全一样)

//        //1. 通过Request对象获取
//        ServletContext servletContext = request.getServletContext();

        //2. 通过HttpServlet获取
        ServletContext servletContext = this.getServletContext();
        String filename = "a.jpg";
        String mimeType = servletContext.getMimeType(filename);
        System.out.println(mimeType);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
