package servlet.API;

import DBControllers.DBUser;
import NSD.IOperation;
import com.google.gson.Gson;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by nsd on 09.08.17.
 */

@WebServlet(name= "Auth", urlPatterns = {"/API/Auth"} )
public class Authorizator extends HttpServlet{

    private static final String kPassword = "password";
    private static final String kLogin = "login";

    public static final String kAuthUser = "auth";

    private static final String kErrorReqCode = "err";
    private static final String kSuccess = "1";
    private static final String kBadAuth = "0";

    @Override
    protected void doPost(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        String password = null;
        String login = null;

        try {

            password = req.getParameter(kPassword);
            login = req.getParameter(kLogin);
        }catch (Exception e){

            e.printStackTrace();
        }

        String request = null;

        IOperation operation = new IOperation() {
            @Override
            public void operate(Object data) {

                try {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    PrintWriter out = resp.getWriter();
                    out.write((String)data);
                    out.flush();
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };


        if(password!=null&&login!=null){

            User checkedUser = DBUser.getInstance().getUser(login);

            if(checkedUser!=null&&checkedUser.getPassword().equals(password)){
                checkedUser.setPassword("");
                req.getSession().setAttribute(kAuthUser,checkedUser);
                request = kSuccess;
                operation.operate(request);

                return;
            }else {

                request = kBadAuth;
                return;
            }



        }

        request = kErrorReqCode;
        operation.operate(request);

    }

    @Override
    protected void doGet(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        IOperation operation = new IOperation() {
            @Override
            public void operate(Object data) {

                try {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    PrintWriter out = resp.getWriter();
                    out.write((String)data);
                    out.flush();
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        if(req.getSession().getAttribute(kAuthUser)==null){
            operation.operate(kBadAuth);
        }else {
            Gson gson = new Gson();

            User user = (User)req.getSession().getAttribute(kAuthUser);

            operation.operate(gson.toJson(user));

        }

    }
}
