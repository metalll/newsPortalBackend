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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,String[]> paramMap = req.getParameterMap();

        String toEmail = paramMap.get("email_dest")[0];
        String message = paramMap.get("message")[0];


        MailSender.getInstance().send("Сайт",message,"linuxoid6666@gmail.com",toEmail);







    }
}
