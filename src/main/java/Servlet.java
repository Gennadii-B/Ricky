package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 24.01.2017.
 */
public class Servlet extends HttpServlet {

    private String nickname;
    private String message;
    private String answer;
    Cookie[] cookie;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");

       nickDispatcher(req);
//        String newNick = req.getParameter("nickname");
//        if(!(newNick == null))
//        nickname = req.getParameter("nickname");

        /////////////// обработка полученного сообщения //////////////////
       message = req.getParameter("message");
       if(message == null) message = "";
        if(!(message.equals("")))
       packing(message, nickname);
       System.out.println("--first mes------" + message);
       if(!(message.equals(""))) {
           answer = Ricky.answer(message);
           packing(answer, "Ricky");
       }
        //////// ответ на страницу //////////////
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(createPage(nickname, chatHistory()));
        resp.addCookie(new Cookie("nickname", nickname));
        writer.close();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private String createPage(String nickname, String messages){
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

    private void packing(String message, String nick){
        /////////// формирование времени и даты поступления сообщения////////
        Date curTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String present = sdf.format(curTime);

        /////////// формирование объекта сообщения///////////
        ObjMessage packagedFullMessage = new ObjMessage();
        packagedFullMessage.setTime(present);
        packagedFullMessage.setNick(nick);
        packagedFullMessage.setMessage(message);

        /////////// отправка сформированного сообщения в бд /////////
        ConnectDS.putMessageToDB(packagedFullMessage);
    }

        private String chatHistory(){
        /////////// забираем 30 последних сообщений с БД //////////////
        ArrayList<ObjMessage> history = ConnectDS.getMessagesFromDB();
        String chatHistory = "";

        ///////// формируем историю сообщений /////////////
        for(ObjMessage om : history){
            String mesOneLine = "";
            mesOneLine += om.getTime() + " - ";
            mesOneLine += om.getNick() + ": ";
            mesOneLine += om.getMessage() + "\n";

            chatHistory = mesOneLine + chatHistory;
        }
            return chatHistory;
    }

    ///////////// диспетчер ника следит за его изменениями ////////////////////
    private void nickDispatcher(HttpServletRequest req){
        cookie = req.getCookies();
        if(cookie != null)
            for(Cookie c : cookie){
                if(c.getName().equals("nickname")) {
                    nickname = c.getValue();

                    System.out.println("NICK FOUND --- [ " + c.getValue() + " ]");
                }
            }
        String newNick = req.getParameter("nickname");
        if(nickname == null) nickname = "user";
        else if(!(newNick == null)) nickname = req.getParameter("nickname");

    }
}