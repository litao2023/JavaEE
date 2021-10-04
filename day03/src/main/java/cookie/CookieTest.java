package cookie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CookieTest", value = "/CookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //1. 获取所有Cookie
        Cookie[] cookies = request.getCookies();
        boolean lastTimeFlag = false;
        //2. 遍历cookies数组
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                //3. 获取Cookie名称
                String name = cookie.getName();
                if ("lastTime".equals(name)){
                    lastTimeFlag = true;
                    //有lastTime Cookie，不是第一次访问
                    //响应数据

                    //获取lastTime Cookie的value
                    String time = cookie.getValue();
                    //URL解码
                    time = URLDecoder.decode(time, "UTF-8");
                    response.getWriter().write("欢迎回来，您上次访问时间为：" + time);

                    //重新设置并发送lastTime Cookie的value
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String formatDate = sdf.format(date);
                    //URL编码
                    formatDate = URLEncoder.encode(formatDate, "UTF-8");
                    cookie.setValue(formatDate);
                    //设置Cookie的存活时间
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        if (!lastTimeFlag){
            //没有lastTime Cookie，是第一次访问
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String formatDate = sdf.format(date);
            //URL编码
            formatDate = URLEncoder.encode(formatDate, "UTF-8");
            Cookie cookie = new Cookie("lastTime", formatDate);
            cookie.setValue(formatDate);
            //设置Cooike的存活时间
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            response.getWriter().write("欢迎首次访问！");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
