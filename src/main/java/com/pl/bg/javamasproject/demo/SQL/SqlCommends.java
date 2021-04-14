package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.Looper;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.criteria.JoinType.INNER;

public class SqlCommends<T> implements Repo<T> {

    T t;
    String sql;

    public SqlCommends(String sql) {
        this.sql = sql;
    }

    public SqlCommends() {
    }

    public SqlCommends<T> updateQuery(T t, String column) {

        String sql;
        Field[] tab = t.getClass().getDeclaredFields();
        List<String> fields = new ArrayList<>();

        Looper.forLoop(1, tab.length, i -> fields.add(tab[i].getName()));

        sql = "Update " + t.getClass().getSimpleName().toLowerCase() + "s set "+ column  + " = " + ":column"  + " where id "
                + "= " + ":id";


        return new SqlCommends<>(sql);

    }

    public void executeSqlCommend_insert(String sql) {

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


    @Override
    public List<Object[]> executeSqlCommend_Select(String sql) {

        Query query = session(sessionFactory())
                .createNativeQuery(sql);

        return  query.getResultList();
    }

    public List<Object> readFromResultQuery(String sql) {
        List<Object> results = new ArrayList<>();
       List<Object[]> list = executeSqlCommend_Select(sql);
        Looper.forLoop(0,list.size(),i -> {
           // for(int j = 0;j<2;j++) {
                System.out.println(list.get(i));

              // results.add((list.get(i)[j]));
               //FIXME dodac przerobienbie na zwrracanie zbudowanego obiektu

         //   }

        });
        return results;
    }
    public List<T> getJoinSelectResult(CriteriaQuery<T> cq,Object id) {

        Session session = session(sessionFactory());
        TypedQuery allQuery = session.createQuery(cq);
        allQuery.setParameter("id",id);

        return allQuery.getResultList();
    }
    public List<T> getBasicSelectResult(CriteriaQuery<T> cq) {

        Session session = session(sessionFactory());
        TypedQuery allQuery = session.createQuery(cq);

        return allQuery.getResultList();
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
    public  Session getSession() {
        Session session = session(sessionFactory());
        return session;
    }

}