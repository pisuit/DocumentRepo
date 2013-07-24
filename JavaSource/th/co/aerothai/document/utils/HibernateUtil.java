package th.co.aerothai.document.utils;

import java.util.Locale;

import org.hibernate.*;
import org.hibernate.cfg.*;

public abstract class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final SessionFactory sessionFactoryORG;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Locale.setDefault(Locale.US);
            //sessionFactory = new Configuration().configure().buildSessionFactory();
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            sessionFactoryORG = new Configuration().configure("org_struct_hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	public static SessionFactory getSessionfactoryorg() {
		return sessionFactoryORG;
	}

}