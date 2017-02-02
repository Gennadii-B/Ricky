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

    static String timeNow(){
        java.util.Date curTime = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String present = sdf.format(curTime);
        return present;
    }

    static Connection connect(){
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
            }
    }

    static void disconnect(Connection con, PreparedStatement ps, Statement st, ResultSet rs){
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

    public static PreparedStatement getPs() {
        return ps;
    }

    public static Connection getCon() {
        return con;
    }

    public static Statement getSt() {
        return st;
    }

    public static ResultSet getRs() {
        return rs;
    }
}
