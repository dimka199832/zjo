package dao;

import logic.*;

import java.sql.SQLException;

/**
 * Created by dimal on 23.05.2017.
 */
public interface UserInfoDAO {
    public void addUserInfo(UserInfo info) throws SQLException;
    public void updateUserInfo(long id, UserInfo info) throws SQLException;
    public UserInfo getInfoById(long id) throws SQLException;
    public void deleteUser(UserInfo info) throws SQLException;
}
