package main.java.servlets;

import main.java.PageGen;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by N on 30.01.2017.
 */
public class LoginServlet2 extends HttpServlet {

    PageGen gen = PageGen.getInstance();
    private static final long serialVersionUID = 3252356436436L;

    public LoginServlet2(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String pass =  req.getParameter("pass");
        System.out.println("[00]");
        if(login != null && pass != null) {
            System.out.println("[11]");
            if ((login.equals("admin")) && (pass.equals("admin"))) {
                System.out.println("[ OK ]");
                RequestDispatcher rD = getServletContext().getRequestDispatcher("/chat");
                rD.forward(req, resp);
//                resp.sendRedirect("chat");
            }
        }
        HttpSession session = req.getSession();
        System.out.println("IDNDICATE [HERE]");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(gen.getPage("login.html"));
        out.close();



//        User admin = new User();
//        admin.setPass("admin");
//        admin.setLogin("admin");
//        if((login.equals("admin")) && (pass.equals("admin"))){
//            RequestDispatcher rD = getServletContext().getRequestDispatcher("/home");
//            rD.forward(req, resp);
//            resp.sendRedirect("chat");
        }

//        resp.sendRedirect("login");

    }


