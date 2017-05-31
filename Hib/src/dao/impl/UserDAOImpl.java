package dao.impl;

import dao.UserDAO;
import logic.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dimal on 23.05.2017.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
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
    public void updateUser(long id, User user) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
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
    public User getUserById(long id) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User)session.load(User.class, id);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return user;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
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
