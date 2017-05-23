package dao.impl;

import dao.UserInfoDAO;
import logic.UserInfo;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by dimal on 23.05.2017.
 */
public class UserInfoDAOImpl implements UserInfoDAO{
    @Override
    public void addUserInfo(UserInfo info) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(info);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public void updateUserInfo(long id, UserInfo info) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(info);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public UserInfo getInfoById(long id) throws SQLException {
        Session session = null;
        UserInfo info = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            info = (UserInfo)session.load(UserInfo.class, id);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return info;
    }

    @Override
    public void deleteUser(UserInfo info) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(info);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inserting Error",
                    JOptionPane.OK_OPTION);
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
