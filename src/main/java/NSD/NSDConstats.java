package NSD;

/**
 * Created by NSD on 05.03.17.
 */
public class NSDConstats {

    private static String DBURL = null;


    public static String getDBURL() {
        if(DBURL == null){

            String dbUri = System.getenv("CLEARDB_DATABASE_URL");
            DBURL = "jdbc:" + dbUri;

        }

        return DBURL;
    }
}
