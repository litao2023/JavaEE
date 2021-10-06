package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebFilter(filterName = "FilterDemo02", value = "/*")//访问所有资源之前，都会执行该过滤器
public class FilterDemo02 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //对request对象的请求消息增强
        System.out.println("FilterDemo02来了....");

        chain.doFilter(request, response);

        //对response对象的响应消息增强
        System.out.println("FilterDemo02走了....");
    }
}
