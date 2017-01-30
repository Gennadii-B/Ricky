package main.java.servlets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by N on 26.01.2017.
 */
public class Ricky {
    private static String ans;
    private static Set keys;
    private static HashMap<String, String> thoughts = new HashMap<String, String>();

    private static void init(){
        put("Hello", "Привет, я Ricky)");
        put("Привет", "Привет, я Ricky)");
        put("как дела?", "круто! А у тебя как?");
        put("кто ты?", "да я тупая программа");
        put("нормально", "ОТЛИЧНО! а кто я?");
        put("я тебя не понимаю", "да, я тебя тоже");
        put("хочешь есть?", "да, очень");
        put("какой в тебе смысл?", "никакого, а в тебе?");
        keys = thoughts.keySet();
    }

    static String answer(String message){
        if(thoughts.size() <1) init();
        String ans = "не понимаю тебя(";
        for(Map.Entry<String, String> m : thoughts.entrySet()){
            if(m.getKey().equals(message))
                ans = m.getValue();
        }
        return ans;
    }

    private static void put(String key, String value){
        thoughts.put(key, value);
    }

}
