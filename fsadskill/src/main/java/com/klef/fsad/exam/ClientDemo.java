package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Movie.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        Movie m1 = new Movie(1,"Sholay","2003","Released");
        Movie m2 = new Movie(2,"Bhramastra","2024","Released");

        session.save(m1);
        session.save(m2);

        tx.commit();

        Transaction tx2 = session.beginTransaction();

        Query q = session.createQuery(
        "update Movie set name=?1, status=?2 where id=?3");

        q.setParameter(1,"Dhurandhar 2");
        q.setParameter(2,"Upcoming next month");
        q.setParameter(3,1);

        int n = q.executeUpdate();

        System.out.println("Records Updated = "+n);

        tx2.commit();

        session.close();
        sf.close();
    }
}