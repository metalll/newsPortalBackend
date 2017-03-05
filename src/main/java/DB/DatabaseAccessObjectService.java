package DB;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Banners;

import java.sql.SQLException;

/**
 * Created by NSD on 05.03.17.
 */
public class DatabaseAccessObjectService {

    private String DBURL;
    private ConnectionSource DBConnSourse;
    private Dao<String,Object> dao;
    private DatabaseAccessObjectService()throws SQLException{

        DBURL = "";
        DBConnSourse = new JdbcConnectionSource(DBURL);
        dao = DaoManager.createDao(DBConnSourse, Banners.class);
        if(!dao.isTableExists()){

            dao.executeRaw("table");


        }



    }

}
