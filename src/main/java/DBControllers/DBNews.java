package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.Banners;
import model.News;
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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),News.class);
        }catch (Exception e){e.printStackTrace();}

    }


    //CRUD
    public void AddOrUpdate(News banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<News> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<News> list = dao.queryForAll();
            dao.getConnectionSource().close();


            for (News item : list) {
                String img = item.getImage();
                String imgLink = NSDConstats.HOST + "/file?key=" + img;
                item.setImage(imgLink);

            }

            return list;


        }
        catch (SQLException e) {
            e.printStackTrace();
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
