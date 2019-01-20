package ru.otus.Servlet;

import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserServlet extends HttpServlet {
    private static final String ADDUSER_PAGE_TEMPLATE = "adduser.html";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_AGE = "age";
    private static final String PAGE_PARAM = "userInfo";
    private static final String RESULT_SUCCESS = "User Saved";

    private final TemplateProcessor templateProcessor;
    private final DBService dbService;

    public AddUserServlet(DBService dbService) throws IOException {
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
        Map<String, Object> pageVariables = new HashMap<>();
        String saveResult = "";

        if (parMap.containsKey(PARAM_NAME) && parMap.containsKey(PARAM_AGE)){
            String name = request.getParameter(PARAM_NAME);
            String age = request.getParameter(PARAM_AGE);
            if (!name.isEmpty() && !age.isEmpty()) {
                try {
                    dbService.save(new UserDataSet(name,Integer.parseInt(age)));
                    saveResult = RESULT_SUCCESS;
                } catch (Exception e) {
                    response.sendError(400, e.getMessage());
                }
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.sendError(400, "Name and Age parameters are not filled");
            }
        }
        pageVariables.put(PAGE_PARAM, saveResult);
        String page = templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, pageVariables);
        response.getWriter().println(page);
    }
}
