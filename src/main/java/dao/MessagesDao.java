package main.java.dao;

import java.sql.*;

import static main.java.dao.ConnectDS.connect;
import static main.java.dao.ConnectDS.disconnect;
import static main.java.dao.ConnectDS.timeNow;

/**
 * Created by N on 02.02.2017.
 */
public class MessagesDao {

    private  static Connection con = ConnectDS.getCon();
    private  static PreparedStatement ps = ConnectDS.getPs();
    private  static Statement st = ConnectDS.getSt();
    private  static ResultSet rs = ConnectDS.getRs();
    private static String insertMsgs =
            "INSERT INTO CHAT (TIME, NICKNAME, MESSAGE) VALUES (?, ?, ?)";

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
            disconnect(con, ps, st, rs);
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
            System.out.println("[INF] LAST ID ---> [ " + maxID + " ]");

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
            System.out.println("[INF] RS [ OK ]");
            return msgHistory;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            disconnect(con, ps, st, rs);
        }
        return "История пуста...";
    }
}
