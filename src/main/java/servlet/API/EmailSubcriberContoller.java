package servlet.API;

import DBControllers.DBEmailForSubcribe;
import com.google.gson.Gson;
import model.EmailForSubcribe;
import model.ModelForJSONBorn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by NSD on 18.04.17.
 */

@WebServlet(name="CommentsController", urlPatterns = {"/API/Subscriber"})
public class EmailSubcriberContoller extends HttpServlet{

//get
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();

        List<EmailForSubcribe> emails = DBEmailForSubcribe.getInstance().QueryAll();
        out.write(new Gson().toJson(new ModelForJSONBorn<EmailForSubcribe>(emails,emails.size())));
        out.flush();
        out.close();


    }
  //add


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,String[]> paramMap = req.getParameterMap();

        String putEmail = null;
        try {
            putEmail = paramMap.get("email")[0];
        }
        catch (Exception e){
            //todo interrprut
        }

        if(putEmail==null){
            //todo interrprut
        }

        EmailForSubcribe putEmailS = new EmailForSubcribe();
        putEmailS.setEmail(putEmail);


       DBEmailForSubcribe.getInstance().AddOrUpdate(putEmailS);


    }
}
