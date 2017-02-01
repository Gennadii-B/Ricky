package main.java.dao;


import main.java.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by N on 26.01.2017.
 */
public class ConnectDS {

    private static DataSource ds = null;
    private static PreparedStatement ps = null;
    private static Connection con = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static String insertMsgs =
            "INSERT INTO CHAT (TIME, NICKNAME, MESSAGE) VALUES (?, ?, ?)";
    private static String insertNewUser =
            "INSERT INTO CHAT_USERS (nickname, login, password, data_registration)" +
                    "VALUES (?, ?, ?, ?)";

    public static String getUsers(){
        String users = "";
        try {
        con = connect();
        st = con.createStatement();
        rs = st.executeQuery("SELECT ALL NICKNAME FROM CHAT_USERS");
        while(rs.next()){
            users = users + rs.getString("NICKNAME") + "\n";
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
            return users;
        }
    }

    public static void putMessageToDB(String msg, String nick){
        try {
            con = connect();
            ps = con.prepareStatement(insertMsgs);
            ps.setString(1, timeNow());
            ps.setString(2, nick);
            ps.setString(3, msg);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            disconnect();
        }
    }

    public static String getMessagesFromDB(){
        int maxID = 0;
        String msgHistory = "";

        try {
            con = connect();
            st = con.createStatement();
            rs = st.executeQuery(
                    "select * from CHAT where id=(select max(id) from CHAT)");
            rs.next();
            maxID = rs.getInt("ID");
            System.out.println("LAST ID ---> [ " + maxID + " ]");

            for(int i=maxID, c=0; c<50 || i==0; c++, i--){
                rs = st.executeQuery("SELECT TIME, NICKNAME, MESSAGE FROM CHAT WHERE ID=" + i);
                while(rs.next()) {
                    String temp = "";
                    temp += (rs.getString("TIME")) + " - ";
                    temp += (rs.getString("NICKNAME")) + ": ";
                    temp += (rs.getString("MESSAGE")) + "\n";

                    msgHistory = temp + msgHistory;
                }
            }
            System.out.println("RS [ OK ]");
            return msgHistory;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            disconnect();
        }
        return "empty";
    }

    public static void regNewUser(String login, String pass, String nickname){
        try {
            con = connect();
            PreparedStatement ps = con.prepareStatement(insertNewUser);
            ps.setString(1, nickname);//nick
            ps.setString(2, login);//log
            ps.setString(3, pass);//pass
            ps.setString(4, timeNow());//time
            System.out.println("REG [ OK ]");
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            disconnect();
        }
    }

    public static User userSearch(String login, String password) {
        try {
            con = connect();
            st = con.createStatement();
            rs = st.executeQuery(
                    "SELECT nickname, login, password, id, data_registration  " +
                            "FROM CHAT_USERS");
            while (rs.next()) {
                if (login.equals(rs.getString("LOGIN")) &&
                    password.equals(rs.getString("PASSWORD"))) {
                    User u = new User();
                    u.setLogin(rs.getString("LOGIN"));
                    u.setPass(rs.getString("PASSWORD"));
                    u.setNickname(rs.getString("NICKNAME"));
                    u.setRegData(rs.getString("DATA_REGISTRATION"));
                    u.setId(rs.getInt("ID"));

                    System.out.println("[ OK ] login [ " + u.getNickname() + " ]");
                    return u;
                }
            }
        }catch(NullPointerException e){
            System.out.println("nullPOINTER");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return null;
    }

    private static String timeNow(){
        java.util.Date curTime = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String present = sdf.format(curTime);
        return present;
    }


    private static Connection connect(){
        try {
            Context context;
            Connection con;
            System.out.println("connect");
            context = new InitialContext();
            ds = (DataSource) context.lookup("PostgresDS");
            con = ds.getConnection();
            System.out.println("connect [ OK ]");
            return con;
        }catch (NamingException e) {
                e.printStackTrace();
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }finally {

        }
    }

    private static void disconnect(){
        try {
            if(rs != null)rs.close();
            if(st != null)st.close();
            if(ps != null) ps.close();
            if(con != null)con.close();
            System.out.println("disconnect");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
