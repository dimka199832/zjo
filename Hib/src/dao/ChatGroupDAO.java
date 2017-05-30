package dao;

import logic.ChatGroup;

import java.sql.SQLException;

/**
 * Created by dimal on 23.05.2017.
 */
public interface ChatGroupDAO {
    public void addChatGroup(ChatGroup group) throws SQLException;
    public void updateChatGroup(long id, ChatGroup group) throws SQLException;
    public ChatGroup getGroupById(long id) throws SQLException;
    public void deleteGroup(ChatGroup group) throws SQLException;
}
