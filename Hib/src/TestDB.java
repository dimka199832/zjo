import dao.UserDAO;
import dao.impl.UserDAOImpl;
import logic.User;

import java.sql.SQLException;

import static java.lang.Class.forName;

/**
 * Created by dimal on 24.05.2017.
 */
public class TestDB {
    public static void main(String[] args) {
        //Factory factory = Factory.getInstance();
        //UserDAO userDao = factory.getUserDao();
        User user = new User();
        user.setId(0);
        user.setLogin("user");
        user.setPassword("password");
        UserDAO userDAO = new UserDAOImpl();
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
