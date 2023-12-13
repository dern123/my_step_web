package server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class HistoryServlet extends HttpServlet {

  private final History history;

  public HistoryServlet(History history) {
    this.history = history;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userId = Auth.getCookieValueUnsafe(req);
    try (PrintWriter w = resp.getWriter()) {
      w.println("<html><body><table>");
//      StreamSupport.stream(history.get().spliterator(), false)
//          .filter(i -> i.user_id().equals(userId)) // TODO: ANTIPATTERN !!!!!!!!!!!!
      history.get(userId)
        .forEach(x -> w.println(x.toHtml()));
      w.println("</table></body></html>");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
