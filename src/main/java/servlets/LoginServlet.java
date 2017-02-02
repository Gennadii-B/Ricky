package main.java.servlets;

import main.java.dao.CreatorTables;
import main.java.dao.UserListDao;
import main.java.entity.User;
import main.java.dao.ConnectDS;
import main.java.utils.PageGen;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by N on 30.01.2017.
 */
public class LoginServlet extends HttpServlet {

    private PageGen gen = PageGen.getInstance();
    private static final long serialVersionUID = 1002L;
    private User user;


    public LoginServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        CreatorTables.newTables();
        String login = req.getParameter("login");
        String pass =  req.getParameter("pass");

        ////////////
        HttpSession session = req.getSession();
        ////////////

        System.out.println("[INF] LOGIN [ before ]");
        user = UserListDao.userSearch(login, pass);
        if(user != null){
            System.out.println("[INF] LOGIN [ process ]");
            session.setAttribute("LOGIN_USER", user);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/chat");
            rd.forward(req, resp);
        }

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.write(gen.getPage("login.html"));
        out.close();

        }

    }


