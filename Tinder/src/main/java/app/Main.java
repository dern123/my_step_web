package app;

import dao.users.DAOUserProfileSQL;
import dao.users.UserProfileDAO;
import database.Database;
import httpfilter.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.*;
import servlets.HelloServlet;
import servlets.LikedProfilesServlet;
import servlets.MessagesServlet;
import servlets.UserServlet;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception{
        Server server =  new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        CalculatorService service = new CalculatorService();
        Connection conn = Database.mkConn();
        History history = new HistorySQL(conn);
        UserProfileDAO userProfileDAO = new DAOUserProfileSQL(conn);
        MessageSQL messageSQL = new MessageSQL(conn);
        EnumSet<DispatcherType> tpe = EnumSet.of(DispatcherType.REQUEST);

        handler.addServlet(new ServletHolder(new HistoryServlet(history)), "/history");
//    handler.addFilter(new FilterHolder(new AuthFilter()), "/history", tpe);
        handler.addFilter(LoginFilter.class, "/history", tpe);

        handler.addServlet(new ServletHolder(new MessagesServlet(messageSQL)), "/messages");
        handler.addFilter(LoginFilter.class, "/messages", tpe);
        //hello
        handler.addServlet(new ServletHolder(new HelloServlet()), "/hello");
        //users
        handler.addServlet(new ServletHolder(new UserServlet(userProfileDAO)), "/users");
        //liked
        handler.addServlet(new ServletHolder(new LikedProfilesServlet()), "/liked");
        handler.addServlet(new ServletHolder(new IdentifyServlet()), "/login");
        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        handler.addServlet(new ServletHolder(new RedirectServlet("/login")), "/*");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}