package util;

import logic.ChatGroup;
import logic.User;
import logic.UserInfo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by dimal on 23.05.2017.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration conf = new Configuration();
            conf.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder sr = new StandardServiceRegistryBuilder();
            sr.applySettings(conf.getProperties());
            MetadataSources ms = new MetadataSources();
            ms.addAnnotatedClass(User.class);
            ms.addAnnotatedClass(ChatGroup.class);
            ms.addAnnotatedClass(UserInfo.class);
            Metadata md = ms.buildMetadata(sr.build());
            sessionFactory = md.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
