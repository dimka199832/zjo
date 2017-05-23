package dao;

import logic.User;

import java.sql.SQLException;

/**
 * Created by dimal on 23.05.2017.
 */
public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public void updateUser(long id, User user) throws SQLException;
    public User getUserById(long id) throws SQLException;
    public void deleteUser(User user) throws SQLException;
}
