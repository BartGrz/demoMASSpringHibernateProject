package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.ModelsGetter;
import com.pl.bg.javamasproject.demo.models.Patient;


import java.util.*;
import java.util.stream.Collectors;

public class CompleksSelectQuery<T> {

    T t;

    private Object condition;
    private List<String> columns = new ArrayList<>();
    private String condition_test;


    public static class Builder <T>{

        T t;

        private Object condition;
        private List<String> columns = new ArrayList<>();
        private String condition_test;



        public Builder setColumn(Enum val) {
            columns.add(val.toString().toLowerCase());
            return this;
        }
        public Builder from(T val) {

            t= val;

            return this;
        }
        public Builder where (Enum val) {

            condition_test =val.toString().toLowerCase();
            return this;
        }
        public Builder equal(Object val) {

            condition =val;

            return this;
        }
        public Builder condition(Object val) {

            condition =val;
            return this;
        }

        public CompleksSelectQuery end() {

            return new CompleksSelectQuery(this);
        }

    }
    public CompleksSelectQuery(Builder builder) {

        condition=builder.condition;
        columns =builder.columns;
        condition_test = builder.condition_test;
        t = (T) builder.t;

    }

    public  List<T> buildSelectQuery() {

        String tableName = t.getClass().getSimpleName();
        String sqlQuery = null;

        if(columns.isEmpty() && condition_test==null) {
            sqlQuery= "Select * from " + tableName ;
        }else if (!columns.isEmpty()&&condition_test==null){
            sqlQuery= "Select " + new SqlTools().formatFieldsToSelectQuery(columns) + " from " +tableName ;
        } else if (columns.isEmpty()&&condition_test!=null) {
            sqlQuery= "Select * from " +tableName +" where " +condition_test +" = "+condition;
        }else  {
            sqlQuery= "Select " + new SqlTools<>().formatFieldsToSelectQuery(columns.stream().collect(Collectors.toList())) +
                    " from " + tableName
                    +" where " +condition_test +" = "+condition;
        }
        return new SqlCommends().executeSqlCommend_Select(sqlQuery);
    }

    public static void main(String[] args) {
       List<Patient> lista =  new CompleksSelectQuery.Builder<Patient>().setColumn(Patient.fieldsNames.NAME)
                .from(new Patient())
                .where(Patient.fieldsNames.ID)
                .equal(1)
                .end()
                .buildSelectQuery();

        System.out.println(lista);

    }
}

enum SelectType {
    ORDINARY_SELECT, JOIN_SELECT, JOIN_ONE_CONDITION_ID
}

