package servlet.API;

import DBControllers.DBService;
import com.google.gson.Gson;
import model.Service;

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
 * Created by NSD on 16.05.17.
 */
@WebServlet(name= "SearchController", urlPatterns = {"/API/Search"} )
public class SearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String,String[]> paramMap = req.getParameterMap();

        String query = null;

        try{
            query = paramMap.get("q")[0];
        }
        catch (Exception e){
            query = null;
        }

        if(query == null){

            // TODO: 16.05.17 return status 404 error query;

            return;
        }




        List<Service> queryServicesList = DBService.getInstance().QueryAll();
        List<Service> servicesQueryResult = new ArrayList<Service>();
        for(Service item : queryServicesList){


            if(item.getHeaderText().toLowerCase().contains(query.toLowerCase())){
                servicesQueryResult.add(item);
            }

        }


        Gson gson = new Gson();
        String result = gson.toJson(servicesQueryResult);




        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();

        out.write(result);
        out.flush();
        out.close();




    }
}
