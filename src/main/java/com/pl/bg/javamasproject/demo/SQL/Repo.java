package com.pl.bg.javamasproject.demo.SQL;

import java.util.List;

public interface Repo<T>  {

    List<Object[]> executeSqlCommend_Select(String sql);
    void executeSqlCommend_insert(String sql);
    void executeSqlCommend_delete(int id);
    void executeSqlCommend_update(int id,String value);


}
