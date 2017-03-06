package NSD;

import java.io.StringReader;

/**
 * Created by NSD on 05.03.17.
 */
public class NSDConstats {

    private static String DBURL = null;
    public static final String HOST = "http://blooming-tundra-50496.herokuapp.com";

    public static String getDBURL() {
        if(DBURL == null){

         DBURL = "jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/heroku_4574124234c67bc?reconnect=true&user=bae2125aeae8cb&password=0a4755709189264";

        }

        return DBURL;
    }
}
