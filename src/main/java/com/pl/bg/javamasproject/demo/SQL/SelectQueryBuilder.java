package com.pl.bg.javamasproject.demo.SQL;

import javafx.beans.property.ObjectProperty;
import org.hibernate.Session;
import javax.persistence.criteria.*;
import java.util.*;



public class SelectQueryBuilder<T,V> {

    T t;
    private String field;
    private Object id;
    private String set;
    private SqlCommends<T> sqlCommends ;
    private List<Object> values = new ArrayList<>();
    /**
     * creating CriteriaBuilder and CriteriaQuery for joinSelect and basiSelect
     * @param <T> entity with @OneToMany annotation
     * @param <V> enitity which is annotated by @ManyToOne
     *       if  Select Join :
     *         - joinSet(Enum val) , set<V> field in T class entity
     *     for all :
     *         -  where(Enum field) ,@JoinColumn field in V class
     *  *      -  equal ,value of where "id = "  condition
     */
    public static class Builder<T,V> {

        T t;
        private String field;
        private Object id;
        private String set;


        public Builder joinSet(Enum val) {

            set =val.toString().toLowerCase();
            return this;
        }
        public Builder where (Enum val){

            field =val.toString().toLowerCase();
            return this;
        }

        public Builder equal (Object val){
            id=val;
            return this;
        }
        public SelectQueryBuilder build() {
            return new SelectQueryBuilder(this);
        }

    }

    public SelectQueryBuilder(Builder builder) {
        field = builder.field;
        set=builder.set;
        id=builder.id;
        t= (T) builder.t;
    }

    public List<T> GenerateJoinSelectResult(Class<T> tClass) {

        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        Join<T, V> joinPat = root.join(set);
        cq.where(cb.equal(joinPat.get(field),cb.parameter(Integer.class,"id")));

      return sqlCommends.getJoinSelectResult(cq,id);

    }

    public List<T> GenerateBasicSelectResult(Class<T> tClass) {

        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        CriteriaQuery<T> fromT = cq.select(root);
        if(field !=null) {
            cq.select(root);
            cq.where(session.getCriteriaBuilder().equal(root.get(field), id));

        }

        return sqlCommends.getBasicSelectResult(fromT);
    }
    public  List<T> getFromSet(Set<T> set ){
            List<T> list = new ArrayList<>();
        for(Iterator<T> it = set.iterator();it.hasNext();) {
            list.add(it.next());
        }
        return list;
    }
}
