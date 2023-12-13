package server;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import web2.ResourcesOps;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class CalculatorServlet extends HttpServlet {

  private final CalculatorService calc;
  private final History history;
  private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

  public CalculatorServlet(CalculatorService calc, History history) throws IOException {
    this.calc = calc;
    this.history = history;
    cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("static/templates")));
  }

  /** drawing form */
  @Override
  protected void doGet(HttpServletRequest rq, HttpServletResponse resp) throws ServletException, IOException {
    String cookieValue = Auth.getCookieValueUnsafe(rq);
    HashMap<String, Object> data = new HashMap<>();
    data.put("user_id", cookieValue);

    try (PrintWriter w = resp.getWriter()) {
      Template template = cfg.getTemplate("calc-form.ftl");
      template.process(data, w);
    } catch (TemplateException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest rq, HttpServletResponse resp) throws ServletException, IOException {
    Optional<String> cookieValue = Auth.getCookieValue(rq);
    if (cookieValue.isEmpty()) {
      resp.sendRedirect("/identify");
      return;
    }

    String xs = rq.getParameter("x");
    String ys = rq.getParameter("y");
    String op = rq.getParameter("op");

    Optional<Item> result = calc.doOp(xs, ys, op, cookieValue.get());

    try (PrintWriter w = resp.getWriter()) {
      Template template = cfg.getTemplate("calc-result.ftl");
      HashMap<String, Object> data = new HashMap<>();

      result.ifPresentOrElse(
          x -> {
            data.put("result", result.toString());
            try {
              history.put(x);
            } catch (SQLException e) {
              throw new RuntimeException(e);
            }
          },
          () -> data.put("result", "Error parsing data")
      );
      template.process(data, w);
    } catch (TemplateException e) {
      throw new RuntimeException(e);
    }
  }
}
