package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.tools.Looper;

import java.util.List;

public class SqlTools <T>{

    T t;

    public String formatFieldsToInsert(List<String> fields) {

        StringBuilder stb = new StringBuilder();
        stb.append("(");

        for(int i = 0;i<fields.size();i++) {
            stb.append(fields.get(i));

            if ((i + 1) != fields.size()) {
                stb.append(",");
            }else {
                stb.append(")");
            }
        }

        return stb.toString();
    }

    public String formatFieldsToSelectQuery(List<String> columns ) {
        StringBuilder stb = new StringBuilder();

        Looper.forLoop(0,columns.size(),i -> {
            if(i+1 == columns.size()) {
                stb.append(columns.get(i));
            }else {
                stb.append(columns.get(i) + ",");

            }
        });

        return stb.toString();
    }


}
