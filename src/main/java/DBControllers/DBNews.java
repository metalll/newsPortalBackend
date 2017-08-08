package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import model.Banners;
import model.News;
import model.News;

import java.sql.SQLException;
import java.util.ArrayList;
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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),News.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),News.class);
            }


       }catch (Exception e){e.printStackTrace();}



    }

    public void Add(News banner) {
        try {
            dao.create(banner);
            dao.getConnectionSource().close();
        }catch (Exception e){  }
    }

    //CRUD
    public void AddOrUpdate(News banner) {
        try {
            dao.createOrUpdate(banner);
          dao.getConnectionSource().close();
        }catch (Exception e){  }
    }

    public List<News> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<News> list = dao.queryForAll();
            dao.getConnectionSource().close();



            return list;


        }
        catch (SQLException e) {
            e.printStackTrace();
          return new ArrayList<News>();
        }
    }

    public News QueryForID(String id){
        News retVal = null;
        try {
            retVal = dao.queryForId(id);
            dao.getConnectionSource().close();
        }catch (Exception e){

        }

        return retVal;
    }

    public void DeleteForID(int id){
        try {
            dao.deleteById(String.valueOf(id));
            dao.getConnectionSource().close();
        }catch (Exception e){

        }
    }

    public void DeleteForID(String id) { //simple override
        try {
            dao.deleteById(id);
            dao.getConnectionSource().close();
        }catch (Exception e){

        }
    }

}
