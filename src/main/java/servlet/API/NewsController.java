package servlet.API;

import DBControllers.DBNews;
import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import jdk.nashorn.internal.runtime.ECMAException;
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
import java.util.Map;
/**
 * Created by NSD on 05.03.17.
 */

@WebServlet(name = "NewsController", urlPatterns = {"/API/News"})
public class NewsController extends HttpServlet {

    @Override

    //todo description GET /* getJSON + maybe Query?!? */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String retVal  = "";


        Map<String,String[]> paramMap = req.getParameterMap();
        String Id = null;
        try {

         Id = paramMap.get("id")[0];
        }catch (Exception e){

        }
        Gson gson = new Gson();
        if(Id != null){
            retVal = gson.toJson(DBNews.getInstance().QueryForID(Id));
        }else {

            List<News> Newss = DBNews.getInstance().QueryAll();


            retVal = gson.toJson(new ModelForJSONBorn<News>(Newss, Newss.size()));
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.write(retVal);
        out.flush();
        out.close();
    }


    //todo decription POST /* create if not exist else update exist */ NO Query !!! (ибо нах нада)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Map<String,String[]> paramMap = req.getParameterMap();

        String dataOfAdded = null;
        String image = null;
        String headerDescrition = null;
        String content = null;
        String id = null;
        try {

            dataOfAdded = paramMap.get("dataOfAdded")[0];
            image = paramMap.get("image")[0];
            headerDescrition = paramMap.get("headerDescrition")[0];
            content = paramMap.get("content")[0];

        }catch (Exception e){}

        if(dataOfAdded==null||image==null||headerDescrition==null||content==null){
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = resp.getWriter();
            out.write("-1");
            out.flush();
            out.close();
            return;
        }

        try {
            id = paramMap.get("id")[0];
        }catch (Exception e){}

//        @DatabaseField(generatedId = true)
//        private int id;
//
//        @DatabaseField
//        private String dataOfAdded;
//
//        @DatabaseField
//        private String image;
//        @DatabaseField
//        private String numberOfViewed;
//        @DatabaseField
//        private String headerDescription;
//        @DatabaseField
//        private String minimalDescription;
//        @DatabaseField
//        private String content;


        News puttedNews = new News();

        puttedNews.setDataOfAdded(dataOfAdded);
        puttedNews.setImage(image);
        puttedNews.setNumberOfViewed("0");
        puttedNews.setHeaderDescription(headerDescrition);
        puttedNews.setMinimalDescription("");
        puttedNews.setContent(content);

        if(id!=null){
            puttedNews.setId(Integer.parseInt(id));
            DBNews.getInstance().Add(puttedNews);
        }
        else {



        }




        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.write(puttedNews.getId());
        out.flush();
        out.close();
        return;
    }

    //todo description DELETE /* delete if exist else response 0 + status 201;
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,String[]> paramMap = req.getParameterMap();

        String Id = null;

        try{
            Id = paramMap.get("id")[0];



        }catch (Exception e){


        }

        if(Id != null) {

            DBNews.getInstance().DeleteForID(Id);
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.write(Id!=null?"Ok":"-1");
        out.flush();
        out.close();

    }


}
