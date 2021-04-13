package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class SelectQueryBuilder<T,V> {

    T t;
    private CriteriaQuery<T> criteriaQuery;
    private String OnJoinValue;
    private int id;
    private String set;

    public static class Builder<T,V> {

        T t;
        private String OnJoinValue;
        private int id;
        private String set;


        public Builder joinSet(Enum val) {

            set =val.toString().toLowerCase();
            return this;
        }
        public Builder setForeignKey(Enum val ){

            OnJoinValue =val.toString().toLowerCase();
            return this;
        }
        public Builder setIdVaule(int val){
            id=val;
            return this;
        }
        public SelectQueryBuilder build() {
            return new SelectQueryBuilder(this);
        }

    }

    public SelectQueryBuilder(Builder builder) {
        OnJoinValue = builder.OnJoinValue;
        set=builder.set;
        id=builder.id;
        t= (T) builder.t;
    }

    public List<T> GenerateJoinSelectResult(Class<T> tClass) {

        SqlCommends<T> sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        Join<T, V> joinPat = root.join(set);
        cq.where(cb.equal(joinPat.get(OnJoinValue),cb.parameter(Integer.class,"id")));

      return sqlCommends.GenerateJoinSelectResult(cq,id);


    }

}
