package com.pl.bg.javamasproject.demo.SQL;

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



}
