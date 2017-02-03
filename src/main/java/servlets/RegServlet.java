package main.java.servlets;

import main.java.dao.CreatorTables;
import main.java.dao.UserListDao;
import main.java.utils.PageGen;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by N on 01.02.2017.
 */
public class RegServlet extends HttpServlet {

    private static final long serialVersionUID = 1004L;
    PageGen gen = PageGen.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        CreatorTables.newTables();

        String login = req.getParameter("login");
        if(login != null) {
            String password = req.getParameter("pass");
            String nickname = req.getParameter("nickname");
            UserListDao.regNewUser(login, password, nickname);
        }

        Writer out = resp.getWriter();
        out.write(gen.getPage("registration.html"));

    }
}
