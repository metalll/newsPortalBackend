package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.Comments;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */
public class DBComments {


    private static volatile DBComments instance;

    private Dao<Comments,String> dao;



    public static DBComments getInstance() {
        DBComments localInstance = instance;
        if (localInstance == null) {
            synchronized (DBComments.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBComments();
                }
            }
        }
        return localInstance;
    }

    private DBComments(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),Comments.class);
        }catch (Exception e){e.printStackTrace();}

    }


    //CRUD
    public void AddOrUpdate(Comments banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<Comments> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<Comments> list = dao.queryForAll();




            dao.getConnectionSource().close();

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
