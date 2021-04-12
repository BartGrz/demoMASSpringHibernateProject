package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.tools.Looper;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> readFromResultQuery(String sql) {
        List<String> results = new ArrayList<>();
       List<Object[]> list = executeSqlCommend_Select(sql);
        Looper.forLoop(0,list.size(),i -> {
            for(int j = 0;j<list.get(0).length;j++) {

               results.add(String.valueOf(list.get(i)[j]));
            }

        });
        return results;
    }

    public List<Object[]> getCategoryList () throws SQLException, ClassNotFoundException, IOException {


        Session session = session(sessionFactory());

        return session.createCriteria(Client.class)
                .setFetchMode("patients", FetchMode.JOIN)
                .add(Restrictions.eq("id", 1))
                .list();

    }

    public List executeSELECTTEST(String sql) {

    List result = new ArrayList();

        Session session = session(sessionFactory());

        if (session.isOpen()) {

            Query query = session.createQuery("from Patient p join Client  c on p.id=c.id where c.id =1");
            result = query.getResultList();
            System.out.println(result);

            session.getTransaction().commit();
            session.close();
        } else {
            System.out.println("ERROR session is closed");
        }
        return result;
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