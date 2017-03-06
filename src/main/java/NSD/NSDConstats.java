package NSD;

/**
 * Created by NSD on 05.03.17.
 */
public class NSDConstats {

    private static String DBURL = null;


    public static String getDBURL() {
        if(DBURL == null){

         DBURL = "jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/heroku_4574124234c67bc?reconnect=true";
        }

        return DBURL;
    }
}
