package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.query.In;
import model.Banners;
import model.Industry;
import model.Industry;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */
public class DBIndustry {

    private static volatile DBIndustry instance;

    private Dao<Industry,String> dao;



    public static DBIndustry getInstance() {
        DBIndustry localInstance = instance;
        if (localInstance == null) {
            synchronized (DBIndustry.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBIndustry();
                }
            }
        }
        return localInstance;
    }

    private DBIndustry(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),Industry.class);
        }catch (Exception e){e.printStackTrace();}

    }


    //CRUD
    public void AddOrUpdate(Industry banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<Industry> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<Industry> list = dao.queryForAll();
            dao.getConnectionSource().close();

            for (Industry item : list) {
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
