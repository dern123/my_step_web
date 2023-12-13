package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class IdentifyServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Optional<String> cookieValue = Auth.getCookieValue(req);
    if (cookieValue.isEmpty()) {
      Auth.setCookieValue(UUID.randomUUID().toString(), resp);
    }

    resp.sendRedirect("/calc");
  }

}
