package servlet.API;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by nsd on 09.08.17.
 */

@WebServlet(name= "Auth", urlPatterns = {"/API/Auth"} )
public class Authorizator extends HttpServlet{

    public static String UUID = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pass = null;
        try {


        pass =  req.getParameter("pass");
        }catch (Exception e){



        }


        if(pass!=null){

            if(pass.equals("QazWsx321")) {

                this.UUID = java.util.UUID.randomUUID().toString();

                req.getSession().setAttribute("auth",UUID);
            }
        }

    }
}
