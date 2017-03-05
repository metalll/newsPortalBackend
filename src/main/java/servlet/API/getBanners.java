package servlet.API;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by NSD on 05.03.17.
 */



@WebServlet( name = "MyServlet",
        urlPatterns = {"/api/Banner" })
public class getBanners extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
       PrintWriter out  = resp.getWriter();
       out.write("i am work");
       out.flush();
       out.close();
    }
}
