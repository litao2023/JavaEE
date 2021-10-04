package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CheckCodeDemo", value = "/CheckCodeDemo")
public class CheckCodeDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int widht = 100;
        int height = 50;
        BufferedImage image = new BufferedImage(widht, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, widht, height);
        g.setColor(Color.RED);
        g.drawRect(0, 0, widht - 1, height - 1);

        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(s.length());
            char ch = s.charAt(index);
            g.drawString(ch + "", widht / 5 * i, height / 2);
        }

        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
