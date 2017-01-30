package main.java.servlets;

import main.java.PageGen;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N on 29.01.2017.
 */
public class LoginServlet extends HttpServlet {

    PageGen gen = PageGen.getInstance();

    public LoginServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("BEFORE BLOCK REQ [" + req.getCharacterEncoding() +"]" +
                                "RESP [" + resp.getCharacterEncoding() + "]");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> mapO = new HashMap<String, Object>();
        PrintWriter out = resp.getWriter();
        out.write(gen.getPage("login.html", mapO));
        System.out.println("AFTER BLOCK REQ [" + req.getCharacterEncoding() +"]" +
                "RESP [" + resp.getCharacterEncoding() + "]");
        out.close();
    }

//        System.out.println("I'M HERE [ 0 ]");
//        req.setCharacterEncoding("UTF-8");
//
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        String username = req.getParameter("username");
//        String page;
//
//        HttpSession session = req.getSession();
//
//        UserInf user = (UserInf) session.getAttribute("userInf");
//
//        if(user == null)
//            user = new UserInf();
//
//        if(username.equals("hello")){
//            user.Login(username);
//            page = "chat.html";
////            gen.getPage(page, map);
//            out.write(gen.getPage(page, map));
//            session.setAttribute("userInf", user);
//        }else{
////            page = "login.html";
//            page = "chat.html";
//            out.write(gen.getPage(page, map));
//            user.setError("Incorrect user name - " + username);
//            session.setAttribute("userInf", user);
//        }
//
//        ServletContext SC = getServletContext();
//        RequestDispatcher Disp = SC.getRequestDispatcher(page);
//        Disp.forward(req, resp);
//    }
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//    {
//        System.out.println("I'M HERE [ 0 ]");
//        try
//        {
//            String IsLogout = req.getParameter("logout");
//            PrintWriter out = resp.getWriter();
//            HttpSession session = req.getSession();
//
//            Map<String, Object> map = new HashMap<String, Object>();
//            String ResultPage;
//            UserInf User = (UserInf) session.getAttribute("userInfo");
//
//            if (User == null)
//                User = new UserInf();
//
//            if (IsLogout != null)
//                if (IsLogout.equals("true"))
//                    User.logout();
//
//            ResultPage = "/login.jsp";
//
//            session.setAttribute("userInfo", User);
//
//            ServletContext SC = getServletContext();
//            RequestDispatcher Disp = SC.getRequestDispatcher(ResultPage);
//            Disp.forward(req, resp);
//            out.write(gen.getPage("login.html", map));
//        }
//        catch (Throwable theException)
//        {
//            theException.printStackTrace();
//        }
//    }

}



