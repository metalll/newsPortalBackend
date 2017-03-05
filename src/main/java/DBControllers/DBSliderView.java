package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import model.SliderView;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by NSD on 05.03.17.
 */
public class DBSliderView {
    private static volatile DBSliderView instance;

    private Dao<SliderView,String> dao;

    public static DBSliderView getInstance() {
        DBSliderView localInstance = instance;
        if (localInstance == null) {
            synchronized (DBSliderView.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBSliderView();
                }
            }
        }
        return localInstance;
    }

    private DBSliderView(){
        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()),SliderView.class);
        }catch (Exception e){}

    }


    //CRUD
    public void AddOrUpdate(SliderView banner) {
        try {
            dao.createOrUpdate(banner);
        }catch (Exception e){  }
    }

    public List<SliderView> QueryAll(){
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
