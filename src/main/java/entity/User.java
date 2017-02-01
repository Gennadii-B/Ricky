package main.java.entity;

/**
 * Created by N on 30.01.2017.
 */
public class User {
    private String login;
    private String pass;
    private String nickname;
    private String regData;
    private int id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegData() {
        return regData;
    }

    public void setRegData(String regData) {
        this.regData = regData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
