package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.EmailForSubcribe;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 18.04.17.
 */
public class DBEmailForSubcribe {


    private static volatile DBEmailForSubcribe instance;

    private Dao<EmailForSubcribe,String> dao;



    public static DBEmailForSubcribe getInstance() {
        DBEmailForSubcribe localInstance = instance;
        if (localInstance == null) {
            synchronized (DBEmailForSubcribe.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBEmailForSubcribe();
                }
            }
        }
        return localInstance;
    }

    private DBEmailForSubcribe(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),EmailForSubcribe.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),EmailForSubcribe.class);
            }


        }catch (Exception e){e.printStackTrace();}

    }


    //CRUD
    public void AddOrUpdate(EmailForSubcribe banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<EmailForSubcribe> QueryAll(){
        try {
            System.out.print(dao.toString());
            List<EmailForSubcribe> list = dao.queryForAll();
            dao.getConnectionSource().close();
            return list;


        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmailForSubcribe ElementByID(String id){
        EmailForSubcribe retVal = null;
        try {
            retVal = dao.queryForId(id);
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    public EmailForSubcribe ElementByID(int id){
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
