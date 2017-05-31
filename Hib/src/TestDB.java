import dao.UserDAO;
import dao.impl.UserDAOImpl;
import logic.User;
import logic.UserInfo;

import java.sql.SQLException;

/**
 * Created by dimal on 24.05.2017.
 */
public class TestDB {
    public static void main(String[] args) {
        User user = new User();
        //user.setId_User(1L);
        user.setLogin("user");
        user.setPassword("password");

        UserInfo userInfo = new UserInfo();
        //userInfo.setId_UserInfo(1L);
        userInfo.setFullName("User User");
        userInfo.setAge(12);
        userInfo.setGender("Male");

        user.setInfo(userInfo);
        UserDAO userDAO = new UserDAOImpl();
        try {
            userDAO.addUser(user);
            //userInfoDAO.addUserInfo(userInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
