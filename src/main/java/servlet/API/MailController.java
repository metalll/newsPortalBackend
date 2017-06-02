package servlet.API;

import Mail.MailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by NSD on 05.04.17.
 */

@WebServlet(name = "MailController", urlPatterns = {"/API/Mail"})
public class MailController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
try {
    Map<String, String[]> paramMap = req.getParameterMap();

    String toEmail = paramMap.get("email_dest")[0];
    String message = paramMap.get("message")[0];


    String theme = null;
    try {
        theme = paramMap.get("theme")[0];
    } catch (NullPointerException e) {
        theme = null;
    }


    MailSender.getInstance().send(theme == null ? "Ниуккцпб" : theme, message, "linuxoid6666@gmail.com", toEmail);
}catch (Exception e){
    // if basic sender dont work deserialise form
    Map<String,String[]> paramMap = req.getParameterMap();

    String icon_prefix = paramMap.get("icon_prefix")[0];
    String icon_telephone = paramMap.get("icon_telephone")[0];
    String call_theme_select = paramMap.get("call_theme_select")[0];

    MailSender.getInstance().send("Обратный звонок тест" , "Имя : "+icon_prefix + "\n Телефон: " + icon_telephone + "\n Что интересует : "+ call_theme_select , "pbezpekamanager@mail.ru", "pbezpekamanager@mail.ru");


}






    }
}
