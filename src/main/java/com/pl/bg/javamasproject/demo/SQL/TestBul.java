package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.SQL.SqlCommends;
import lombok.*;
import org.hibernate.Session;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TestBul <T,V>{

    T t;
    private Enum where;
    private Object equal;
    private Enum set;
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

    public List<T> generateJoinSelectResult(Class<T> tClass) {

        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        Join<T, V> joinPat = root.join(set.toString().toLowerCase());
        cq.where(cb.equal(joinPat.get(where.toString().toLowerCase()),cb.parameter(Integer.class,"id")));
        return sqlCommends.getJoinSelectResult(cq,equal);

    }

    public List<T> generateBasicSelectResult(Class<T> tClass) {

        sqlCommends = new SqlCommends<>();

        Session session =  sqlCommends.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> root  = cq.from(tClass);
        CriteriaQuery<T> fromT = cq.select(root);
        if(where !=null) {
            cq.select(root);
            cq.where(session.getCriteriaBuilder().equal(root.get(where.toString().toLowerCase()), equal));
        }

        return sqlCommends.getBasicSelectResult(fromT);
    }


    public  List<T> getFromSet(Set<T> set ){
        List<T> list = new ArrayList<>();
        for(Iterator<T> it = set.iterator(); it.hasNext();) {
            list.add(it.next());
        }
        return list;
    }

}