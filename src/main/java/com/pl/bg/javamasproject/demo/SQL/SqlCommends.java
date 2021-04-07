package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.tools.Looper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlCommends<T> implements Repo<T>  {

    T t ;
    String sql;

    public SqlCommends(String sql) {
        this.sql = sql;
    }

    public SqlCommends() {
    }

    public SqlCommends<T> insertQuery (T t) {

        String sql ;
        Field[] tab = t.getClass().getDeclaredFields();
        List <String> fields = new ArrayList<>();

        Looper.forLoop(1, tab.length, i -> fields.add(tab[i].getName()));

        sql = "Insert into "+ t.getClass().getSimpleName().toLowerCase() +"s "
                + new SqlTools<T>().formatFieldsToInsert(fields) +" values " + t.toString() ;

    return new SqlCommends<>(sql);

    }

    public SqlCommends<T> deleteQuery(T t) {

        Field[] tab = t.getClass().getDeclaredFields();
        List <String> fields = new ArrayList<>();

        Looper.forLoop(0, tab.length, i -> fields.add(tab[i].getName()));

               String sql = "Delete from " + t.getClass().getSimpleName() + "s where " +fields.get(0) +" = :id";

        return new SqlCommends<>(sql);
    }

    public void executeSqlCommend_insert() {

        Session session =  session(sessionFactory());
        session.beginTransaction();

        if(session.isOpen()) {
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
            session.close();
        }else {
            System.out.println("ERROR session is closed");
        }
    }

    @Override
    public void executeSqlCommend_delete(int id) {

        Session session =  session(sessionFactory());
        session.beginTransaction();

        if(session.isOpen()) {
            session.createNativeQuery(sql).setParameter("id",id).executeUpdate();
            session.getTransaction().commit();
            session.close();
        }else {
            System.out.println("ERROR session is closed");
        }
    }

    public Query selectQuery(String sql) {

        Query query = session(sessionFactory()).createQuery(sql);

        return query;
    }
    public List<T>  executeSqlCommend_SelectAll(T t) {

        String sql = " from " + t.getClass().getSimpleName();

        return   selectQuery(sql).getResultList();
    }


    private Session session(SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();

        return session;
    }

    private SessionFactory sessionFactory() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        return sessionFactory;
    }


}
