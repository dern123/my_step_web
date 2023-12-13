package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet  {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter w = resp.getWriter()) {
            w.println(
                    """
                    <html>
                      <body>
                        <b>Hello</b>, Jim
                      </body>
                    </html>
                    """);
        }
    }
}