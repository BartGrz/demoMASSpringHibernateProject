package com.pl.bg.javamasproject.demo.SQL;

import java.util.List;

public class Repository <T> extends SqlCommends<T> {

    public List<T> selectAll(T t) {
      return executeSqlCommend_SelectAll(t);
   }

   public void insert(T t) {

        insertQuery(t).executeSqlCommend_insert();
    }
   public void delete(T t,int id) {
       deleteQuery(t).executeSqlCommend_delete(id);
   }

}
