package servlets;
import server.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> cookieValue = Auth.getCookieValue(req);
        if (cookieValue.isEmpty()) {
            Auth.setCookieValue(UUID.randomUUID().toString(), resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Логіка обробки POST запиту для логіну
        // Збереження даних користувача у cookies
    }
}

