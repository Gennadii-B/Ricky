package main.java;

/**
 * Created by N on 30.01.2017.
 */
public class UserInf {

    String   UserName  = "";
    String  ErrorText  = "";
    boolean  LoginFlag = false;

    public void userInfo(){}

    public String getUser(){
        return UserName;
    }

    public void Login(String TheUser){
        LoginFlag = true;
        ErrorText = "";
        UserName = TheUser;
    }

    public void setError(String TheText){
        ErrorText = TheText;
    }

    public String getError(){
        return ErrorText;
    }

    public String isLogin(){
        if (LoginFlag)
            return "true";
        return "false";
    }

    public void logout(){
        LoginFlag = false;
        UserName = "";
        ErrorText = "";
    }
}

