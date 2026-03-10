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

        // Insert records
        Movie m1 = new Movie(1,"RRR","2022","Released");
        Movie m2 = new Movie(2,"Pushpa","2021","Released");

        session.save(m1);
        session.save(m2);

        tx.commit();

        // HQL Update using positional parameters
        Transaction tx2 = session.beginTransaction();

        Query q = session.createQuery(
        "update Movie set name=?1, status=?2 where id=?3");

        q.setParameter(1,"Salaar");
        q.setParameter(2,"Upcoming");
        q.setParameter(3,1);

        int n = q.executeUpdate();

        System.out.println("Records Updated = "+n);

        tx2.commit();

        session.close();
        sf.close();
    }
}