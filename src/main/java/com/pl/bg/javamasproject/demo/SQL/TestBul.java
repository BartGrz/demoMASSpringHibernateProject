package com.pl.bg.javamasproject.demo.SQL;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.List;


/**
 * only for learning purposes, all is managed by JPA repositories
 * @param <T>
 * @param <V>
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TestBul <T,V>{

    T t;
    private Enum where;
    private Object equal;
    private Enum set;
    @Singular
    private List<Enum> values = new ArrayList<>();
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     * creating CriteriaBuilder and CriteriaQuery for joinSelect and basiSelect
     * @param T entity with @OneToMany annotation
     * @param V enitity which is annotated by @ManyToOne
     *       if  Select Join :
     *         - joinSet(Enum val) , set<V> field in T class entity
     *     for all :
     *         -  where(Enum field) ,@JoinColumn field in V class
     *  *      -  equal ,value of where "id = "  condition
     */

    public List<T> generateJoinSelectResult(Class<T> tClass) {


        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        Join<T, V> joinPat = root.join(set.toString().toLowerCase());
        cq.where(cb.equal(joinPat.get(where.toString().toLowerCase()),cb.parameter(Integer.class,"id")));
       TypedQuery query= session.createQuery(cq);
       query.setParameter("id",equal);


        return query.getResultList();

    }

    public List<T> generateBasicSelectResult(Class<T> tClass) {



        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        CriteriaQuery<T> fromT = cq.select(root);
        if(where !=null) {
            cq.select(root);
            cq.where(session.getCriteriaBuilder().equal(root.get(where.toString().toLowerCase()), equal));
        }

        TypedQuery query = session.createQuery(cq);

        return query.getResultList();
    }


    public  List<T> getFromSet(Set<T> set ){
        List<T> list = new ArrayList<>();
        for(Iterator<T> it = set.iterator(); it.hasNext();) {
            list.add(it.next());
        }
        return list;
    }

}