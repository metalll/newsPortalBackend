package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.KeyCacheElem;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 06.03.17.
 */
public class DBKeyCacheElem {

    private static volatile DBKeyCacheElem instance;

    private Dao<KeyCacheElem,String> dao;



    public static DBKeyCacheElem getInstance() {
        DBKeyCacheElem localInstance = instance;
        if (localInstance == null) {
            synchronized (DBKeyCacheElem.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBKeyCacheElem();
                }
            }
        }
        return localInstance;
    }

    private DBKeyCacheElem(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),KeyCacheElem.class);
        }catch (Exception e){e.printStackTrace();}

    }


    //CRUD
    public void AddOrUpdate(KeyCacheElem banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public String getValueForKey(String key){

        try {
            return dao.queryForId(key).getValueOfKey() ;
        } catch (SQLException e) {
            return null;
           // e.printStackTrace();
        }

    }

    public List<KeyCacheElem> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<KeyCacheElem> list = dao.queryForAll();
            dao.getConnectionSource().close();

            return list;


        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




    public void DeleteForKey(String KEY) { //simple override
        try {
            dao.deleteById(KEY);
        }catch (Exception e){

        }
    }

}
