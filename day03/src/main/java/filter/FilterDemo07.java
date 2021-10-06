package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebFilter(filterName = "FilterDemo07", value = "/*")
public class FilterDemo07 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo07开始执行了");
        chain.doFilter(request, response);
        System.out.println("FilterDemo07执行回来了");
    }
}
