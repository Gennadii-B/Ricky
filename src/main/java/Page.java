package main.java;

/**
 * Created by N on 26.01.2017.
 */
public class Page {

    private static String HTMLcode_01 =
                  "<html>" +
                        "<head>" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                            "<meta charset=\"utf-8\">" +
//                            "<meta http-equiv=\"refresh\" content=\"10; URL=./\">" +
                            "<title>Simle_ChatBot</title>" +
                        "</head>";
    private static String HTMLcode_02 =
                         "<body> " +
                                 "<div id=\"container\">" +
                                    "<h2>Ricky</h2><br/>" +
                                    "<FORM action=\"./\" method=\"get\">" +
                                            "История чата:<br/>" +
                                            "<TEXTAREA readonly=\"readonly\" WRAP=\"virtual\" COLS=\"60\" ROWS=\"15\">";
    private static String HTMLcode_03 =
                         "</TEXTAREA><br\\><br\\>" +
                          "Ник пользователя:<br/>" +
                                 "<INPUT TYPE=\"text\" SIZE=\"58px\" name=\"nickname\" value=\"";
    private static String HTMLcode_04 =
                                        "\"><br/>" +
                                         "Сообщение:<br/>" +
                                         "<TEXTAREA NAME=\"message\" WRAP=\"virtual\" maxlength=\"200\" placeholder=\"введите сообщение\" COLS=\"60\" ROWS=\"3\"></TEXTAREA><br>" +
                                         "<INPUT TYPE=\"submit\" VALUE=\"Отправить\">" +
                                         "<INPUT TYPE=\"reset\" value=\"Сброс\"><br/>" +
                                 "</FORM>" +
                             "</div>" +
                        "<style>" +
                              "textarea { " +
                                   "resize: none;" +
                              "}" +
                               "#container{" +
                                  "font-family: 'Roboto', sans-serif;" +
                                  "font-size:16px;" +
                                  "padding: 8px;" +
                                  "border: 1px solid #dfdfdf;" +
                                  "color: #3f3f3f;width: 450px; " +
                                  "margin: 150px auto;" +
                               "}" +
                        "</style>" +
                     "</body>" +
                  "</html>";


    public static String getHTMLcode_01() {
        return HTMLcode_01;
    }

    public static String getHTMLcode_02() {
        return HTMLcode_02;
    }

    public static String getHTMLcode_03() {
        return HTMLcode_03;
    }

    public static String getHTMLcode_04() {
        return HTMLcode_04;
    }
}
