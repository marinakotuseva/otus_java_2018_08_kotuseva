package ru.otus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.DBService.DBService;
import ru.otus.Servlet.AddUserServlet;
import ru.otus.Servlet.FindUserServlet;
import ru.otus.Servlet.InfoServlet;

import java.io.IOException;

public class ServerHelper {
    private final static String HTML_FOLDER = "src/main/HTML";
    private final static int PORT = 8090;
    public Server setServer() throws IOException {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(HTML_FOLDER);

        //DBInitializer dbInitializer = new DBInitializer();
        ApplicationContext classPathContext =
                new ClassPathXmlApplicationContext(
                        "SpringBeans.xml");
        DBInitializer dbInitializer = classPathContext.getBean("dbInitializer", DBInitializer.class);

        DBService hibernateDBService = dbInitializer.InitializeDB();

        AddUserServlet addUserServlet = new AddUserServlet(hibernateDBService);
        InfoServlet infoServlet = new InfoServlet(hibernateDBService);
        FindUserServlet findUserServlet = new FindUserServlet(hibernateDBService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(addUserServlet), "/adduser");
        context.addServlet(new ServletHolder(findUserServlet), "/finduser");
        context.addServlet(new ServletHolder(infoServlet), "/info");


        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        return server;
    }
}
