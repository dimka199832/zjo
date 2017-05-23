package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by dimal on 23.05.2017.
 */
public class HibernateUtil {
    private static final SessionFactory SessionFactory;
    static{
        try {
            SessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return SessionFactory;
    }
}
