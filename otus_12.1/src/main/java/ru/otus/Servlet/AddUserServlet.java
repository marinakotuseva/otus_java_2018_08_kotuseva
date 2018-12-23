package ru.otus.Servlet;

import ru.otus.DBService.DBService;
import ru.otus.DBService.DataSet.UserDataSet;
import ru.otus.DBService.hibernate.HibernateDBServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddUserServlet extends HttpServlet {
    private static final String ADDUSER_PAGE_TEMPLATE = "adduser.html";
    private static final String NAME_VARIABLE_NAME = "name";

    private final TemplateProcessor templateProcessor = new TemplateProcessor();
    private final HibernateDBServiceImpl hibernateDBService;

    public AddUserServlet(HibernateDBServiceImpl dbService) throws IOException {
        this.hibernateDBService = dbService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println(name);
        System.out.println(age);

        //DBService hibernateDBService = new HibernateDBServiceImpl();
        System.out.println("===SAVING===");
        hibernateDBService.save(new UserDataSet(name, Integer.parseInt(age)));;

//        if (name != null) {
//            saveToVariable(name);
//            saveToSession(request, name); //request.getSession().getAttribute("login");
//            saveToServlet(request, name); //request.getAttribute("login");
//            saveToCookie(response, name); //request.getCookies();
//        }

        setOK(response);
        String l = (String) request.getSession().getAttribute("login");
        String page = getPage(l); //save to the page
        response.getWriter().println(page);
    }
    private void saveToServlet(HttpServletRequest request, String requestLogin) {
        request.getServletContext().setAttribute("login", requestLogin);
    }

    private void saveToSession(HttpServletRequest request, String requestLogin) {
        request.getSession().setAttribute("login", requestLogin);
    }

//    private void saveToVariable(String requestName) {
//        name = requestName != null ? requestName : name;
//    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void saveToCookie(HttpServletResponse response, String requestLogin) {
        response.addCookie(new Cookie("L12.1-login", requestLogin));
    }
    private String getPage(String login) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put(NAME_VARIABLE_NAME, login == null ? "" : login);
        return templateProcessor.getPage(ADDUSER_PAGE_TEMPLATE, pageVariables);
    }

}
