package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 */
//@WebFilter(filterName = "SensitiveWordsFilter", value = "/*")
public class SensitiveWordsFilter implements Filter {
    private List<String> sensitiveWordsList = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        try {
            //1. 加载SensitiveWords.txt文件
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/SensitiveWords.txt");
            //2. 读取SensitiveWords.txt文件
            BufferedReader bf = new BufferedReader(new FileReader(realPath));
            String line;
            while ((line = bf.readLine()) != null) {
                //3. 将SensitiveWords.txt文件每行数据添加进sensitiveWordsList中
                sensitiveWordsList.add(line);
            }
            bf.close();
            System.out.println(sensitiveWordsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1. 创建代理对象，增强getParameter()方法
        ServletRequest proxyRequest = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            //增强getParameter()方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断是否是getParameter()方法
                if (method.getName().equals("getParameter")) {
                    //增强getParameter()方法返回值
                    String value = (String) method.invoke(request, args);
                    if (value != null) {
                        for (String s : sensitiveWordsList) {
                            if (value.contains(s)) {
                                value = value.replaceAll(s, "***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(request, args);
            }
        });

//        System.out.println("Filter执行");
        //2. 放行
        chain.doFilter(proxyRequest, response);
    }
}
