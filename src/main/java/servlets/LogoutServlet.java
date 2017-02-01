package main.java.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by N on 30.01.2017.
 */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1003L;

    public LogoutServlet() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getSession().invalidate();
        RequestDispatcher rD = getServletContext().getRequestDispatcher("/login");
        rD.forward(req, resp);

    }
}
