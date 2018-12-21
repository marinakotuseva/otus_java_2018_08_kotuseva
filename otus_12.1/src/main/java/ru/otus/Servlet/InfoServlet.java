package ru.otus.Servlet;

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

    @SuppressWarnings("WeakerAccess")
    public InfoServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @SuppressWarnings("WeakerAccess")
    public InfoServlet() throws IOException {
        this(new TemplateProcessor());
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();

        HibernateDBServiceImpl DBService = new HibernateDBServiceImpl();
        List<UserDataSet> users = DBService.loadAll();
        System.out.println(users);
        String s = "";
        for (UserDataSet u: users
        ) {
            s+= u.toString() + "\n";
        }

        pageVariables.put("userInfo", s);
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
