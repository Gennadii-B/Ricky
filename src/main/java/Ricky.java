package main.java;

/**
 * Created by N on 26.01.2017.
 */
public class Ricky {

    static String answer(String message){
        String ans = "не понимаю тебя(";
        if(message.equals("Hello")) ans = "Привет, я Ricky)";
        else if(message.equals("Привет")) ans = "Привет, я Ricky)";
        else if(message.equals("как дела?")) ans = "круто! А у тебя как?";
        else if(message.equals("кто ты?")) ans = "да я тупая программа";
        else if(message.equals("нормально")) ans = "ОТЛИЧНО! а кто я?";
        else if(message.equals("я тебя не понимаю")) ans = "да, я тебя тоже";
        else if(message.equals("хочешь есть?")) ans = "да, очень";
        else if(message.equals("какой в тебе смысл?")) ans = "никакого, а в тебе? ";
        return ans;
    }
}
