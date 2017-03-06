package servlet.API;

import DBControllers.DBComments;
import com.google.gson.Gson;
import model.Comments;
import model.ModelForJSONBorn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */

@WebServlet(name="CommentsController", urlPatterns = {"/API/Comment"})

public class CommentsController extends HttpServlet {

    @Override
    //todo description GET /* getJSON + maybe Query?!? */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String retVal  = "";
        List<Comments> Commentss = DBComments.getInstance().QueryAll();

        for(Comments comment : Commentss){




        }


        Gson gson = new Gson();

        retVal = gson.toJson(new ModelForJSONBorn<Comments>(Commentss,Commentss.size()));


        //return text;


        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.write(retVal);
        out.flush();
        out.close();
    }


    //todo decription POST /* create if not exist else update exist */ NO Query !!! (ибо нах нада)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //todo description DELETE /* delete if exist else response 0 + status 201;
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
