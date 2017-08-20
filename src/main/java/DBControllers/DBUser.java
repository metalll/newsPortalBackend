package DBControllers;

import NSD.NSDConstats;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.User;

import java.util.List;

/**
 * Created by nsd on 17.08.17.
 */
public class DBUser {

    private static volatile DBUser instance;

    private Dao<User, String> dao;

    public static DBUser getInstance() {
        DBUser localInstance = instance;
        if (localInstance == null) {
            synchronized (DBUser.class) {
                localInstance = instance;
                if (localInstance == null) {

                    instance = localInstance = new DBUser();
                }
            }
        }
        return localInstance;
    }

    private DBUser() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dao = DaoManager.createDao(new JdbcConnectionSource(NSDConstats.getDBURL()), User.class);
            if (!dao.isTableExists()) {

                TableUtils.createTable(dao.getConnectionSource(), User.class);
                dao.getConnectionSource().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User getUser(String login) {
        User retVal = null;
        try {

            List<User> tList = dao.queryForAll();

            if (tList.size() < 0) {
                retVal = null;
            }else {

                for(User tUser:tList){

                    if(tUser.getName().equals(login)){
                        retVal = tUser;
                        break;
                    }

                }

            }

            dao.getConnectionSource().close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return retVal;
    }


    public User getUser(long id) {

        User retVal = null;
        try {

            retVal = dao.queryForId(String.valueOf(id));

            dao.getConnectionSource().close();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return retVal;
    }
}