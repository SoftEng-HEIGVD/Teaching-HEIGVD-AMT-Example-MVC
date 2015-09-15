package ch.heigvd.amt.mvcdemo.web.controllers;

import ch.heigvd.amt.mvcdemo.util.Chance;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet demonstrates that a servlet (and a JSP) can generate other
 * content than HTML. In this case, we generate a JSON payload.
 *
 * We do not follow the MVC pattern, in the sense that we generate the payload
 * directly in the servlet, but in this case it is a reasonable choice.
 *
 * In the example, we also show one method for creating JSON objects via a Java
 * API and for serializing these objects in string payloads. Note that later in
 * the course, we will study alternative methods (less verbose).
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@WebServlet(name = "JsonServlet", urlPatterns = {"/data/json"})
public class JsonServlet extends HttpServlet {

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
    /*
     Firstly, set the Content-type header to the right value
     */
    response.setContentType("application/json;charset=UTF-8");

    /*
     Secondly, build a JSON object with a "fluent" API
     */
    JsonObjectBuilder builder = Json.createObjectBuilder().add("people", Json.createArrayBuilder());

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    int numberOfPeople = 2 + (int)(Math.random() * 5);
    for (int i=0; i<numberOfPeople; i++) {
      arrayBuilder.add(Json.createObjectBuilder()
          .add("firstName", Chance.randomFirstName())
          .add("lastName", Chance.randomLastName())
          .add("id", (int)(Math.random() * 10000)));
    }
    
    JsonObject model = builder
      .add("people", arrayBuilder)
      .build();
    
    /*
     Thirdly, serialize the JSON object to a string value
     */
    StringWriter stWriter = new StringWriter();
    JsonWriter jsonWriter = Json.createWriter(stWriter);
    jsonWriter.writeObject(model);
    jsonWriter.close();
    String jsonPayload = stWriter.toString();

    /*
     Finally, send the serialized JSON payload to the client
     */
    try (PrintWriter out = response.getWriter()) {
      out.println(jsonPayload);
    }
  }

}
