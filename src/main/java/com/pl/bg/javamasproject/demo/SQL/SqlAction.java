package com.pl.bg.javamasproject.demo.SQL;

import java.util.List;

public interface SqlAction<T> {

   void sqlAction(T t, String sql);

}
