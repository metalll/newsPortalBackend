package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */
public class DBService {
    private static volatile DBService instance;

    private Dao<Service,String> dao;

    public static DBService getInstance() {
        DBService localInstance = instance;
        if (localInstance == null) {
            synchronized (DBService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBService();
                }
            }
        }
        return localInstance;
    }

    private DBService(){
        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),Service.class);
        }catch (Exception e){}

    }


    //CRUD
    public void AddOrUpdate(Service banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<Service> QueryAll(){
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
