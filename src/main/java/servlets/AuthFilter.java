package main.java.servlets;

import main.java.User;
import org.apache.commons.lang3.StringUtils;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by N on 30.01.2017.
 */
public class AuthFilter implements Filter {

    private List<String> pathFilters = Arrays.asList
            (new String[]{"chat"});

    public AuthFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {

        String uri = ((HttpServletRequest)req).getRequestURI();
        String path = StringUtils.substringAfterLast(uri, "/");

        if(pathFilters.contains(path)){
            filterChain.doFilter(req, resp);
            return;
        }

        HttpSession session =((HttpServletRequest)req).getSession();
        User user = (User) session.getAttribute("PRINCIPAL");

        if(user != null){
            filterChain.doFilter(req, resp);
            return;
        }

//        ((HttpServletResponse) resp).sendRedirect("login/123");
    }

    public void destroy() {

    }
}
