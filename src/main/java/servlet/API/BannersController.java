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
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */


@WebServlet(name= "BannerController", urlPatterns = {"/API/Banner"} )
public class BannersController extends HttpServlet {

    @Override

    //todo description GET /* getJSON + maybe Query?!? */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String retVal  = "";
        List<Banners> bannerss = DBBanners.getInstance().QueryAll();
         Gson gson = new Gson();

        retVal = gson.toJson(new ModelForJSONBorn<Banners>(bannerss,bannerss.size()));


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
