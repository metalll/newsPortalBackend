package servlet.API;

import DBControllers.DBBanners;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Banners;
import model.ModelForJSONBorn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NSD on 05.03.17.
 */


@WebServlet(name= "BannerController", urlPatterns = {"/API/Banner"} )
public class BannersController extends HttpServlet {

    @Override

    //todo description GET /* getJSON + maybe Query?!? */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = null;
        String q = null;


        Map<String, String[]> paramMap = req.getParameterMap();
        //todo get and check query params
        try {
            id = paramMap.get("id")[0];
        } catch (NullPointerException ex) {
            id = null;
        }

        try {
            q = paramMap.get("q")[0];
        } catch (NullPointerException ex) {
            q = null;
        }
        //todo end check


        //todo Mark switch quering


        //todo if has two params return server error
        if (id != null && q != null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            PrintWriter out = resp.getWriter();
            out.write("id and q params don't сompatible. \n Server error!!! ");
            out.flush();
            out.close();
            return;
        }


        String retVal = "";


        if (id == null && q == null){
        List<Banners> bannerss = DBBanners.getInstance().QueryAll();
        Gson gson = new Gson();
        retVal = gson.toJson(new ModelForJSONBorn<Banners>(bannerss, bannerss.size()));
        }
        else {

            if(id!=null){
                Banners banner = DBBanners.getInstance().ElementByID(id);
                List<Banners> bannerss = new ArrayList<Banners>();
                bannerss.add(banner);
                Gson gson = new Gson();
                retVal = gson.toJson(new ModelForJSONBorn<Banners>(bannerss, bannerss.size()));

            }

            if(q!=null){
                //todo this maybe don't work

                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                PrintWriter out = resp.getWriter();
                out.write("I'm sorry but this feature don't work now!");
                out.flush();
                out.close();
                return;
            }


        }
        //return text;


        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.write(retVal);
        out.flush();
        out.close();

    }


    //mysql://c0arc3m3i445zhr4:wcz4b1uyh3qavoe3@y0nkiij6humroewt.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/gh6jsy7w2kl6l0x6
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
