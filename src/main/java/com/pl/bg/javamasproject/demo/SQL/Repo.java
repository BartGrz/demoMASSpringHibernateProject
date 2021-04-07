package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Client;

import javax.persistence.Query;
import java.util.List;

public interface Repo<T>  {

    List<T> executeSqlCommend_SelectAll(T t);
    void executeSqlCommend_insert();
    void executeSqlCommend_delete(int id);
//    void executeSqlCommend_update();


}
