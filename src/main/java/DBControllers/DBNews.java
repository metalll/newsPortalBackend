package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.News;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */
public class DBNews {
    private static volatile DBNews instance;

    private Dao<News,String> dao;

    public static DBNews getInstance() {
        DBNews localInstance = instance;
        if (localInstance == null) {
            synchronized (DBNews.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBNews();
                }
            }
        }
        return localInstance;
    }

    private DBNews(){
        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),News.class);
        }catch (Exception e){}

    }


    //CRUD
    public void AddOrUpdate(News banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<News> QueryAll(){
        try {
            return dao.queryForAll();
        }
        catch (SQLException e) {
            return null;
        }
    }


    public void DeleteForID(int id){
        try {
            dao.deleteById(String.valueOf(id));
        }catch (Exception e){

        }
    }

    public void DeleteForID(String id) { //simple override
        try {
            dao.deleteById(id);
        }catch (Exception e){

        }
    }

}
