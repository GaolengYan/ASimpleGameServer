package com.simple.server.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;
    static {
        Configuration configuration = new Configuration().configure();
        SESSION_FACTORY = configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }
    public static Session getCurrentSession(){
        return SESSION_FACTORY.getCurrentSession();
    }

    public static Transaction beginTransaction(Session session) {
        return session.beginTransaction();
    }

}
