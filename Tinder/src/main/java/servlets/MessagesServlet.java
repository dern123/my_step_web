package servlets;

import dao.users.UserProfileDAO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import server.Auth;
import server.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class MessagesServlet extends HttpServlet {
    private final Message message;
    private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
    public MessagesServlet(Message message) {
        this.message = message;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = Auth.getCookieValueUnsafe(req);
        try (PrintWriter w = resp.getWriter()) {
            w.println("<html><body><table>");
//      StreamSupport.stream(history.get().spliterator(), false)
//          .filter(i -> i.user_id().equals(userId)) // TODO: ANTIPATTERN !!!!!!!!!!!!
            message.get(userId)
                    .forEach(x -> w.println(x.toHtml()));
            w.println("</table></body></html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Логіка збереження нового повідомлення у базі даних
    }
}
