package NSD;

/**
 * Created by NSD on 05.03.17.
 */
public class NSDConstats {

    private static String DBURL = null;


    public static String getDBURL() {
        if(DBURL == null){
            DBURL = System.getenv(""); //todo DBURL

        }

        return DBURL;
    }
}
