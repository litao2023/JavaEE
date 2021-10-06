package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebFilter(filterName = "FilterDemo06", value = "/*")
public class FilterDemo06 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo06开始执行了");
        chain.doFilter(request, response);
        System.out.println("FilterDemo06执行回来了");
    }
}
