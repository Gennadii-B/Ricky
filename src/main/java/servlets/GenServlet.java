package main.java.servlets;

import main.java.ConnectDS;
import main.java.PageGen;
import main.java.Soso.Page;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * Created by admin on 24.01.2017.
 */
public class GenServlet extends HttpServlet {

    private static final long serialVersionUID = 1000L;
    private String nickname;
    Cookie[] cookie;
    Map<String, Object> pageVar;
    PageGen pageGen = PageGen.getInstance();
    String msg = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
     ////////////// обработка ника //////////////
        nickDispatcher(req);

        HttpSession session = req.getSession();
        System.out.println(session.getAttribute("userInfo"));

//        resp.setStatus(SC_UNAUTHORIZED);

     ////////////// обработка полученного сообщения //////////////////
        msg = req.getParameter("message");
        msgDispatcher(msg);

     //////// ответ на страницу //////////////
        pageVar = createPageVarMap(nickname, req);

        PrintWriter out = resp.getWriter();
        out.write(pageGen.getPage("chat.html", pageVar));
        resp.addCookie(new Cookie
                ("nickname", URLEncoder.encode(nickname, "UTF-8")));
//        resp.addCookie(new Cookie("nickname", nickname), "UTF-8");
        resp.encodeRedirectURL("http://127.0.0.5:8080/Ricky/chat");
        out.close();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String createPageChat(String nickname, String messages){
    ////////// создается страница с данными //////////////////
        String page;
        page = Page.getHTMLcode_01() +
               Page.getHTMLcode_02() +
               messages +
               Page.getHTMLcode_03() +
               nickname +
               Page.getHTMLcode_04();
        return page;
    }


    private void msgDispatcher(String message) {
    ///////////// проверка на null и пустое сообщение //////////////
        if (message == null)
            message = "";
        if (!(message.equals(""))) {
            packingMsg(message, nickname);
            packingMsg(Ricky.answer(message), "Ricky");
        }
    }

    private void packingMsg(String message, String nick){
    /////////// формирование времени и даты поступления сообщения////////
        Date curTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String present = sdf.format(curTime);
        ConnectDS.putMessageToDB(present, message, nick);
    }

    ///////////// диспетчер ника следит за его изменениями ////////////////////
    private void nickDispatcher(HttpServletRequest req)
            throws UnsupportedEncodingException{
        req.setCharacterEncoding("UTF-8");
        cookie = req.getCookies();
        URLDecoder decoder = new URLDecoder();
        if(cookie != null)
            for(Cookie c : cookie){
                if(c.getName().equals("nickname")) {
//                    nickname = c.getValue();
                    nickname = decoder.decode(c.getValue(), "UTF-8");

//                    System.out.println("NICK FOUND --- [ " + c.getValue() + " ]");
                }
            }
        String newNick = req.getParameter("nickname");
        if(nickname == null) nickname = "user";
        else if(!(newNick == null)) nickname = req.getParameter("nickname");
    }

    private static Map<String, Object> createPageVarMap
            (String nickname, HttpServletRequest req)
            throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> Var = new HashMap<String, Object>();
        Var.put("nickname", nickname);
        Var.put("messages", ConnectDS.getMessagesFromDB());

        return Var;
    }
}