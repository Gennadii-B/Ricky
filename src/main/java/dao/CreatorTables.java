package main.java.dao;

import java.sql.*;

/**
 * Created by N on 02.02.2017.
 */
public class CreatorTables {
    private  static Connection con = ConnectDS.getCon();
    private  static PreparedStatement ps = ConnectDS.getPs();
    private  static Statement st = ConnectDS.getSt();
    private  static ResultSet rs = ConnectDS.getRs();

    private static String sqlTableChatUsers = "CREATE TABLE public.\"chat_users\"\n" +
            "(\n" +
            "    nickname character varying COLLATE pg_catalog.\"default\",\n" +
            "    login character varying COLLATE pg_catalog.\"default\",\n" +
            "    password character varying COLLATE pg_catalog.\"default\",\n" +
            "    id integer NOT NULL DEFAULT nextval('chat_users_id_seq'::regclass),\n" +
            "    data_registration character varying COLLATE pg_catalog.\"default\")";
    private static String sqlTableChat = "CREATE TABLE public.\"chat\"\n" +
            "(\n" +
            "    id integer NOT NULL DEFAULT nextval('\"Chat_id_seq\"'::regclass),\n" +
            "    time character varying COLLATE pg_catalog.\"default\",\n" +
            "    nickname character varying COLLATE pg_catalog.\"default\",\n" +
            "    message character varying COLLATE pg_catalog.\"default\",\n" +
            "    CONSTRAINT Chat_pkey PRIMARY KEY (id)\n" +
            ")";



    public static void TEST(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            con = ConnectDS.connect();
            st = con.createStatement();
            st.execute(sqlTableChat);
            System.out.println(" [OK] [OK] [OK] [OK] [OK] [OK] [OK] [OK]");
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectDS.disconnect(con,ps,st,rs);
        }
    }

    public static void TEST2(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            con = ConnectDS.connect();
            st = con.createStatement();
            st.execute(sqlTableChatUsers);
            System.out.println(" [OK] [OK] [OK] [OK] [OK] [OK] [OK] [OK]");
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            ConnectDS.disconnect(con,ps,st,rs);
        }
    }

    public static void newTables(){
        String select = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
        String temp = "";
        boolean isHaveTableChat = false;
        boolean isHaveTableChatUsers = false;

        try {
        con = ConnectDS.connect();
        st = con.createStatement();
        rs = st.executeQuery(select);
        while(rs.next()){
            temp = rs.getString("TABLE_NAME");
            if(temp.equals("chat"))isHaveTableChat = true;
            if(temp.equals("chat_users")) isHaveTableChatUsers = true;
            System.out.println(isHaveTableChat + "|" + isHaveTableChatUsers);
        }

        if(!(isHaveTableChat)) {
//            createNewTable(con, sqlTableChat);
            st.execute(sqlTableChat);
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
        }
//        if(!(isHaveTableChatUsers)) {
//            createNewTable(con, sqlTableChatUsers);
//            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//        }
    } catch (SQLException e) {
            e.printStackTrace();
    }finally{
            ConnectDS.disconnect(con,ps,st,rs);
        }
    }

    private static void createNewTable(Connection con, String sqlCommand){
        try{
            st = con.createStatement();
            st.execute(sqlCommand);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
