package DBControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.Banners;

import java.sql.SQLException;
import java.util.List;
import NSD.NSDConstats;
/**
 * Created by NSD on 05.03.17.
 */
public class DBBanners {

    private static volatile DBBanners instance;

    private Dao<Banners,String> dao;



    public static DBBanners getInstance() {
        DBBanners localInstance = instance;
        if (localInstance == null) {
            synchronized (DBBanners.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBBanners();
                }
            }
        }
        return localInstance;
    }

    private DBBanners(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),Banners.class);
        }catch (Exception e){e.printStackTrace();}

    }


    //CRUD
    public void AddOrUpdate(Banners banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<Banners> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<Banners> list = dao.queryForAll();
            dao.getConnectionSource().close();

            for (Banners item : list) {
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

    public Banners ElementByID(String id){
        Banners retVal = null;
        try {
           retVal = dao.queryForId(id);
           dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    public Banners ElementByID(int id){
        return ElementByID(String.valueOf(id));
    }


    public void DeleteForID(int id){
       DeleteForID(String.valueOf(id));
    }

    public void DeleteForID(String id) { //simple override
        try {
            dao.deleteById(id);
        }catch (Exception e){

        }
    }


}
