package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(filterName = "FilterDemo03", value = "/*")//访问所有资源之前，都会执行该过滤器
public class FilterDemo03 implements Filter {
    /**
     * 在服务器启动后，会创建Filer对象，然后调用init方法，只执行一次
     * 用于加载资源
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init....");
    }

    /**
     * 在服务器关闭后，Filter对象被销毁。如果服务器正常关闭，则会执行destroy()方法，只执行一次
     * 用于释放资源
     */
    public void destroy() {
        System.out.println("destroy....");
    }

    /**
     * 每一次请求被拦截时，会执行，可多次执行
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //对request对象的请求消息增强
        System.out.println("FilterDemo03来了....");

        chain.doFilter(request, response);

        //对response对象的响应消息增强
        System.out.println("FilterDemo03走了....");
    }
}
