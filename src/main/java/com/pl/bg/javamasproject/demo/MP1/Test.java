package com.pl.bg.javamasproject.demo.MP1;


import com.pl.bg.javamasproject.demo.SQL.SelectQueryBuilder;
import com.pl.bg.javamasproject.demo.SQL.SqlCommends;
import com.pl.bg.javamasproject.demo.SQL.SqlTools;
import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;
import com.pl.bg.javamasproject.demo.tools.Looper;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.apache.commons.lang3.reflect.Typed;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
public class Test <T,V>{

    T t;
    private String where;
    private Object id;
    private String set;
    private SqlCommends<T> sqlCommends ;
    @Singular
    private List<Enum> values = new ArrayList<>();
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

    public List<T> GenerateJoinSelectResult(Class<T> tClass) {

        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        Join<T, V> joinPat = root.join(set);
        cq.where(cb.equal(joinPat.get(where),cb.parameter(Integer.class,"id")));

        return sqlCommends.getJoinSelectResult(cq,id);

    }

    public List<T> GenerateBasicSelectResult(Class<T> tClass) {

        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        CriteriaQuery<T> fromT = cq.select(root);
        if(where !=null) {
            cq.select(root);
            cq.where(session.getCriteriaBuilder().equal(root.get(where), id));
        }

        return sqlCommends.getBasicSelectResult(fromT);
    }

    public List GenerateSpecificSelectResult(Class<T> tClass) {
            //@TODO znalezc inna mozliwosc niz reczna iteracja po kolekcji


        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        //FIXME :  reczna iteracja, nie ma innej mozliwosci,
        //
        CriteriaQuery<T> fromT =  cq.multiselect(root.get(values.get(0).toString().toLowerCase())
                ,root.get(values.get(1).toString().toLowerCase())); // wymagany jest konstruktor na ktorego podstawie sie tworzy aliasy

        return sqlCommends.getBasicSelectResult(fromT);

    }
    public  List<T> getFromSet(Set<T> set ){
        List<T> list = new ArrayList<>();
        for(Iterator<T> it = set.iterator(); it.hasNext();) {
            list.add(it.next());
        }
        return list;
    }

    public static void main(String[] args) {

   new Test.TestBuilder<Patient,Class>()
                .value(Patient.fieldsNames.PATIENT_NAME)
                .value(Patient.fieldsNames.ID_CARD)
                .build()
                .GenerateSpecificSelectResult(Patient.class);

    }
    public void tryThat() {

    }
}