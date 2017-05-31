package dao.impl;

import dao.ChatGroupDAO;
import logic.ChatGroup;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dimal on 24.05.2017.
 */
public class ChatGroupDAOImpl implements ChatGroupDAO {
    @Override
    public void addChatGroup(ChatGroup group) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
            transaction.rollback();
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void updateChatGroup(long id, ChatGroup group) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
            transaction.rollback();
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public ChatGroup getGroupById(long id) throws SQLException {
        Session session = null;
        ChatGroup group = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            group = (ChatGroup) session.load(ChatGroup.class, id);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return group;
    }

    @Override
    public void deleteGroup(ChatGroup group) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(group);
            transaction.commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
            transaction.rollback();
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
