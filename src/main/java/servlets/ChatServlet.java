package main.java.servlets;

import main.java.dao.MessagesDao;
import main.java.dao.UserListDao;
import main.java.entity.User;
import main.java.dao.ConnectDS;
import main.java.utils.PageGen;
import main.java.utils.Ricky;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 24.01.2017.
 */
public class ChatServlet extends HttpServlet {

    private static final long serialVersionUID = 1000L;
    private String nickname;
//    Cookie[] cookie;
    Map<String, Object> pageVar;
    PageGen pageGen = PageGen.getInstance();
    String msg = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

     ///////// функционал кнопки logout ///////////////
        String logout = req.getParameter("logout");
        if(logout != null && logout.equals("Выход")){
            req.getSession().invalidate();
            resp.sendRedirect("login");
    ///////////////////////////////////////////////////
        }

     ////////////// обработка ника //////////////
        nickDispatcher(req);

     ////////////// обработка полученного сообщения //////////////////
        msg = req.getParameter("message");
        msgDispatcher(msg);

     //////// ответ на страницу //////////////
        pageVar = createPageVarMap(nickname, req);

        PrintWriter out = resp.getWriter();
        out.write(pageGen.getPage("chat.html", pageVar));
//        resp.encodeRedirectURL("http://127.0.0.5:8080/Ricky/chat");
        out.close();
        resp.setStatus(HttpServletResponse.SC_OK);
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
        MessagesDao.putMessageToDB(message, nick);
    }

    ///////////// диспетчер ника следит за его изменениями ////////////////////
    private void nickDispatcher(HttpServletRequest req)
            throws UnsupportedEncodingException{

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        String uName = user.getNickname();

        if(uName != null)nickname = uName;
        else nickname = "user";

    }

    private static Map<String, Object> createPageVarMap
            (String nickname, HttpServletRequest req)
            throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        Map<String, Object> Var = new HashMap<String, Object>();
        Var.put("nickname", nickname);
        Var.put("messages", MessagesDao.getMessagesFromDB());
        Var.put("users", UserListDao.getUsers());

        return Var;
    }
}