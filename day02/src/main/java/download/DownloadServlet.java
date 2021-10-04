package download;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DownLoadUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.SortedSet;

@WebServlet(name = "DownloadServlet", value = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数，文件名称
        String filename = request.getParameter("filename");

        //2. 使用字节输入流加载文件进内存
        //2.1 找到文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/img/" + filename);
//        System.out.println(realPath);
        //2.2 用字节流关联
        FileInputStream fis = new FileInputStream(realPath);

        //3. 设置response的响应头
        //3.1 设置响应头类型content-type
        String mimeType = servletContext.getMimeType(filename);  //获取文件的mime类型
        response.setHeader("content-type", mimeType);

        //解决中文文件名问题
        //获取user-agent请求头
        String agent = request.getHeader("user-agent");
        //使用工具类DownLoadUtils方法编码文件名
        filename = DownLoadUtils.getFileName(agent, filename);

        //3.2 设置响应头打开方式content-disposition
        response.setHeader("content-disposition", "attachment;filename=" + filename);

        //4. 将输入流数据写入到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(bytes)) != -1){
            sos.write(bytes, 0, len);
        }
        fis.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
