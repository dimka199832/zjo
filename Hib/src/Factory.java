import dao.UserDAO;
import dao.impl.UserDAOImpl;

/**
 * Created by dimal on 24.05.2017.
 */
public class Factory {
    private static Factory instance = new Factory();
    private UserDAO userDao;

    private Factory(){}

    public static Factory getInstance(){
        return Factory.instance;
    }

    public UserDAO getUserDao(){
        if(userDao == null) userDao = new UserDAOImpl();
        return userDao;
    }
}