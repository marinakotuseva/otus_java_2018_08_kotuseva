package ru.otus.Servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.DataSet;
import ru.otus.DBService.hibernate.HibernateDBServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FindUserServlet extends HttpServlet {
    private static final String ADDUSER_PAGE_TEMPLATE = "finduser.html";
    private static final String PARAM_ID = "id";
    private static final String PAGE_PARAM = "userInfo";

    private final TemplateProcessor templateProcessor;
    private DBService dbService;


    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                            "SpringBeans.xml");
        dbService = context.getBean("dbService", HibernateDBServiceImpl.class);
    }

    public FindUserServlet(DBService dbService) throws IOException {
        this.templateProcessor = new TemplateProcessor();
        this.dbService = dbService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        Map parMap = request.getParameterMap();
        String loadResult = "";
        if (parMap.containsKey(PARAM_ID)){
            String id = request.getParameter(PARAM_ID);
            if (!id.isEmpty()) {
                try {
                    DataSet user = dbService.load(Long.parseLong(id));
                    loadResult = user.toString();
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    response.sendError(400, e.getMessage());
                }
            } else {
                response.sendError(400, "ID parameter id not filled");
            }
        }
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(PAGE_PARAM, loadResult);
        String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
    }
}
