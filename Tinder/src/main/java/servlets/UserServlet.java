package servlets;

import app.Main;
import dao.users.UserProfileDAO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Configuration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    private UserProfileDAO userProfileDAO;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/user_choices";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "mysecretpassword";
    private int currentIndex = 0;

    public UserServlet(UserProfileDAO userProfileDAO) {
        //saved users
        this.userProfileDAO = userProfileDAO;
//        userProfileDAO = new ProfilesInMemory();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(Main.class, "templates");

        //передача в шаблон
        Map<String, Object> input = new HashMap<>();
        input.put("profiles", userProfileDAO);
        input.put("currentIndex", currentIndex);

        //шаблон FreeMarker
//        StringWriter writer = new StringWriter();
//        try {
//            Template template = new Template("template", new StringReader(Templates.USERS_PAGE), cfg);
//            template.process(input, writer);
//        } catch (TemplateException e) {
//            e.printStackTrace();
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Template processing error");
//            return;
//        }

        // Вывод результата
        resp.setContentType("text/html");
//        resp.getWriter().println(writer.toString());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");

        if (choice != null) {
            //получить ид профиля и пользователя из значения кнопки
            String[] parts = choice.split("_");
            int profileId = Integer.parseInt(parts[0]);
            String userChoice = parts[1];

            //сохраненяем выбор в базу данных
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO choices (profile_id, choice) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, profileId);
                    preparedStatement.setString(2, userChoice);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
                return;
            }

            //индекс текущего профиля, показывает по кругу
            try {
                currentIndex = (currentIndex + 1) % userProfileDAO.findAll().size();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //если все профили уже просмотрены, переправляем на страницу /liked
            if (currentIndex == 0) {
                resp.sendRedirect("/liked");
                return;
            }

            resp.sendRedirect("/users");
            //response.getWriter().println("Choice for profile " +profileId+ ": " +userChoice);
            //System.out.println("Choice for profile " +profileId+ ": " +userChoice);
        } else {
            //сообщения об ошибке, если параметр "choice" не передан
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Choice parameter is missing");
        }
    }
}
