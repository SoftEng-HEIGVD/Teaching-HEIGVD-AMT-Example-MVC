package ch.heigvd.amt.mvcdemo.web.controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet demonstrates that servlets and JSPs can generate other content
 * than HTML markup. This servlet dynamically generates a PNG image. While the
 * example is basic (the image simply is a circle painted over a rectangle), the
 * idea can be used to generated histograms, processed images, etc.
 * 
 * The servlet is invoked via the URL specified in the WebServlet annotation and
 * expects two query string parameters (fg for the color of the circle and bg for
 * the color of the rectangle):
 * 
 * /data/graph?bg=0;0;0&fg=255;255;255
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@WebServlet(name = "GraphServlet", urlPatterns = {"/data/graph"})
public class GraphGeneratorServlet extends HttpServlet {

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("image/png");

    int width = 210;
    int height = 210;
    int radius = width / 3;
    int centerX = width / 2;
    int centerY = height / 2;

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();
    g.setColor(getColorFromRequestParameter(request.getParameter("bg")));
    g.fillRect(0, 0, width, height);
    g.setColor(getColorFromRequestParameter(request.getParameter("fg")));
    g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    g.dispose();

    java.io.OutputStream outputStream = response.getOutputStream();
    javax.imageio.ImageIO.write(image, "png", outputStream);
    outputStream.close();  }

  private Color getColorFromRequestParameter(String param) {
    Color color;
    try {
      String[] components = param.split(";");
      color = new Color(Integer.parseInt(components[0]), Integer.parseInt(components[1]), Integer.parseInt(components[2]));
    } catch (Exception e) {
      color = Color.RED;
    }
    return color;
  }

}
