package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.Looper;
import javafx.collections.ObservableList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.*;

public class SqlCommends<T> implements Repo<T> {

    T t;
    String sql;

    public SqlCommends(String sql) {
        this.sql = sql;
    }

    public SqlCommends() {
    }



    public SqlCommends<T> insertQuery(T t) {

        String sql;
        Field[] tab = t.getClass().getDeclaredFields();
        List<String> fields = new ArrayList<>();

        Looper.forLoop(1, tab.length, i -> fields.add(tab[i].getName()));

        sql = "Insert into " + t.getClass().getSimpleName().toLowerCase() + "s "
                + new SqlTools<T>().formatFieldsToInsert(fields) + " values " + t.toString();

        return new SqlCommends<>(sql);

    }

    public SqlCommends<T> updateQuery(T t, String column) {

        String sql;
        Field[] tab = t.getClass().getDeclaredFields();
        List<String> fields = new ArrayList<>();

        Looper.forLoop(1, tab.length, i -> fields.add(tab[i].getName()));

        sql = "Update " + t.getClass().getSimpleName().toLowerCase() + "s set "+ column  + " = " + ":column"  + " where id "
                + "= " + ":id";//moze enum na podstawie column ?


        return new SqlCommends<>(sql);

    }




    public void executeSqlCommend_insert() {

        Session session = session(sessionFactory());

        if (session.isOpen()) {
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } else {
            System.out.println("ERROR session is closed");
        }
    }

    public SqlCommends<T> deleteQuery(T t) {

        Field[] tab = t.getClass().getDeclaredFields();
        List<String> fields = new ArrayList<>();

        Looper.forLoop(0, tab.length, i -> fields.add(tab[i].getName()));

        String sql = "Delete from " + t.getClass().getSimpleName() + "s where " + fields.get(0) + " = :id";

        return new SqlCommends<>(sql);
    }

    @Override
    public void executeSqlCommend_delete(int id) {

        Session session = session(sessionFactory());

        if (session.isOpen()) {
            session.createNativeQuery(sql).setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } else {
            System.out.println("ERROR session is closed");
        }
    }

    public Query selectQuery(String sql) {

        Query query = session(sessionFactory()).createQuery(sql);

        return query;
    }

    @Override
    public List<T> executeSqlCommend_Select(String sql) {

        Query query = session(sessionFactory()).createQuery(sql);
        return  query.getResultList();
    }


    @Override
    public void executeSqlCommend_update(int id, String newValue) {

        Session session = session(sessionFactory());

        if (session.isOpen()) {
            session.createNativeQuery(sql)
                    .setParameter("column",newValue)
                    .setParameter("id", id)

                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } else {
            System.out.println("ERROR session is closed");
        }
    }


    private Session session(SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        return session;
    }

    private SessionFactory sessionFactory() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        return sessionFactory;

    }

}