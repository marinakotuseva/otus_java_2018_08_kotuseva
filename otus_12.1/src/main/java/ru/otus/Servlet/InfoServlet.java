package ru.otus.Servlet;

import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.hibernate.HibernateDBServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoServlet extends HttpServlet {
    //private static final String DEFAULT_USER_NAME = "UNKNOWN";
    private static final String INFO_PAGE_TEMPLATE = "info.html";

    private final TemplateProcessor templateProcessor;
    private final DBService dbService;


    @SuppressWarnings("WeakerAccess")
    public InfoServlet(DBService dbService) throws IOException {
        this.templateProcessor = new TemplateProcessor();
        this.dbService = dbService;
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();

        List<UserDataSet> users = dbService.loadAll();
        System.out.println(users);
        pageVariables.put("userInfo", users.size());
        return pageVariables;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);

        response.setContentType("text/html;charset=utf-8");
        String page = templateProcessor.getPage(INFO_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
