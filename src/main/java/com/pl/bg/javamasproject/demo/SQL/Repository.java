package com.pl.bg.javamasproject.demo.SQL;

import java.util.List;

public class Repository<T> extends SqlCommends<T> {


   public void delete(T t,int id) {
       deleteQuery(t).executeSqlCommend_delete(id);
   }
   public void update(T t, int id, String valueToChange,String column ) {

        updateQuery(t,column).executeSqlCommend_update(id,valueToChange);
   }

}
