package servlet.API;

import DBControllers.DBNews;
import com.google.gson.Gson;
import model.News;
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

@WebServlet(name = "NewsController", urlPatterns = {"/API/News"})
public class NewsController extends HttpServlet {

    @Override

    //todo description GET /* getJSON + maybe Query?!? */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String retVal  = "";
        List<News> Newss = DBNews.getInstance().QueryAll();
        Gson gson = new Gson();

        retVal = gson.toJson(new ModelForJSONBorn<News>(Newss,Newss.size()));


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
