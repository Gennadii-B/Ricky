package main.java;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by N on 26.01.2017.
 */
public class ConnectDS {

    private static DataSource ds = null;
    private static PreparedStatement ps = null;
    private static Connection con = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static String insert =
            "INSERT INTO CHAT (TIME, NICKNAME, MESSAGE) VALUES (?, ?, ?)";


    public static void putMessageToDB(ObjMessage objMessage){
        try {
            con = connect();
            ps = con.prepareStatement(insert);
            //кладем по очереди 1)время 2)ник 3)сообщение
            ps.setString(1, objMessage.getTime());
            ps.setString(2, objMessage.getNick());
            ps.setString(3, objMessage.getMessage());
            ps.executeUpdate();


        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            disconnect();
        }
    }

    public static ArrayList<ObjMessage> getMessagesFromDB(){
        String select;
        int maxID = 0;
        ObjMessage FM;
        ArrayList<ObjMessage> result = new ArrayList<ObjMessage>();
        result.clear();
        try {
            con = connect();
            st = con.createStatement();
            rs = st.executeQuery(
                    "select * from CHAT where id=(select max(id) from CHAT)");
            rs.next();
            maxID = rs.getInt("ID");
            System.out.println(maxID);

            for(int i=maxID, c=0; c<30 || i==0; c++, i--){
                rs = st.executeQuery("SELECT TIME, NICKNAME, MESSAGE FROM CHAT WHERE ID=" + i);
                while(rs.next()) {
                    System.out.println("||" + rs.getString("MESSAGE") + "||");
                    FM = new ObjMessage();
                    FM.setTime(rs.getString("TIME"));
                    FM.setNick(rs.getString("NICKNAME"));
                    FM.setMessage(rs.getString("MESSAGE"));

                    if(FM.getMessage() == null)//проверка  полученного сообщения на null
                        continue;

                    result.add(FM);
                }
            }
            System.out.println("RS [ OK ]");
            return result;


        }catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            disconnect();
        }
        return result;
    }


    private static Connection connect() throws NamingException, SQLException{
        Context context;
        Connection con;
        System.out.println("connect");
        context = new InitialContext();
        ds = (DataSource) context.lookup("PostgresDS");
        con = ds.getConnection();
        System.out.println("connect [ OK ]");
        return con;
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
