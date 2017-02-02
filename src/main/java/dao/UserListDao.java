package main.java.dao;

import main.java.entity.User;

import java.sql.*;

import static main.java.dao.ConnectDS.connect;
import static main.java.dao.ConnectDS.disconnect;

/**
 * Created by N on 02.02.2017.
 */
public class UserListDao {

    private  static Connection con = ConnectDS.getCon();
    private  static PreparedStatement ps = ConnectDS.getPs();
    private  static Statement st = ConnectDS.getSt();
    private  static ResultSet rs = ConnectDS.getRs();

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
            disconnect(con, ps, st, rs);
            return users;
        }
    }

    public static void regNewUser(String login, String pass, String nickname){
        try {
            con = connect();
            PreparedStatement ps = con.prepareStatement(insertNewUser);
            ps.setString(1, nickname);//nick
            ps.setString(2, login);//log
            ps.setString(3, pass);//pass
            ps.setString(4, ConnectDS.timeNow());//time
            System.out.println("REG [ OK ]");
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            disconnect(con, ps, st, rs);
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
            disconnect(con, ps, st, rs);
        }
        return null;
    }


}
