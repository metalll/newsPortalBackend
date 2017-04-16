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

        Map<String,String[]> paramMap = req.getParameterMap();

        String toEmail = paramMap.get("email_dest")[0];
        String message = paramMap.get("message")[0];


        String theme = null;
        try {
            theme = paramMap.get("theme")[0];
        }catch (NullPointerException e){
            theme=null;
        }


        MailSender.getInstance().send(theme==null?"Ниуккцпб":theme,message,"linuxoid6666@gmail.com",toEmail);







    }
}
