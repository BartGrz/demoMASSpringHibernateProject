/*
package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Client;
import com.pl.bg.javamasproject.demo.models.Patient;

import java.util.*;
import java.util.stream.Collectors;

public class CompleksSelectQuery<T> {

    T t;
    private Object condition;
    private List<String> columns = new ArrayList<>();
    private String condition_test;
    private Class joinedTable;
    private String onSequence;

    public static class Builder<T> {

        T t;
        private Object condition;
        private List<String> columns = new ArrayList<>();
        private Class joinedTable;
        private String condition_test;
        private String onSequence;

        public Builder setColumn(Enum val) {

            if (joinedTable != null) {
                if(findColumnParentTable(val).equals(t.getClass().getSimpleName())) {
                    columns.add(getTableFirstLetter(t)+
                            "." + val.toString().toLowerCase());
                }else {
                    columns.add(getTableFirstLetter(joinedTable.getClass())+
                            "." + val.toString().toLowerCase());
                }
            }else {
                columns.add(val.toString().toLowerCase());
            }
            return this;
        }

        public Builder from(T val) {

            t = val;
            return this;
        }
        public Builder joinTable(Class<T> val) {

            joinedTable =val;
            return this;
        }
        public Builder on(Enum firstTableId ,Enum secondTableId) {

            StringBuilder stb = new StringBuilder();

          stb.append( " on " + getTableFirstLetter(t) +"." + firstTableId.toString().toLowerCase() +
                  "= " + getTableFirstLetter(joinedTable.getClass().getSimpleName())+"."+secondTableId.toString().toLowerCase()+ " ");
            onSequence = stb.toString();

            return this;
        }

        public Builder where(Enum val) {

            if(joinedTable.getClass()!=null) {
                if (findColumnParentTable(val).equals(t.getClass().getSimpleName())) {
                    condition_test = getTableFirstLetter(val.getClass()) + "."+val.toString().toLowerCase();
                }else {
                    condition_test = getTableFirstLetter(val.getClass()) + "."+val.toString().toLowerCase();
                }
            }else {
                condition_test = val.toString().toLowerCase();
            }

            return this;
        }

        public Builder equal(Object val) {

            condition = val;

            return this;
        }

        public Builder condition(Object val) {

            condition = val;
            return this;
        }

        public CompleksSelectQuery end() {

            return new CompleksSelectQuery(this);
        }

    }

    public CompleksSelectQuery(Builder builder) {

        condition = builder.condition;
        columns = builder.columns;
        condition_test = builder.condition_test;
        t = (T) builder.t;
        onSequence =builder.onSequence;
        joinedTable =builder.joinedTable;

    }

    public List<Object> buildSelectQuery() {

        String tableName = t.getClass().getSimpleName()+"s";

        String sqlQuery = null;

        if(joinedTable!=null) {
            String tableNameJoined = joinedTable.getSimpleName()+"s";
            sqlQuery = "Select " +  SqlTools.formatFieldsToSelectQuery(columns) +
                    " from " + tableName + " " + getTableFirstLetter(t) + " join "+
            tableNameJoined+ " " + getTableFirstLetter(joinedTable.getClass()) + onSequence ;
            if (condition_test != null) {
                sqlQuery = sqlQuery +" where " + condition_test + " = " + condition;
            }else {

            }
            System.out.println(sqlQuery);
          //  return new SqlCommends().GenerateJoinSelectResult(joinedTable);
        }

        if (columns.isEmpty() && condition_test == null) {
            sqlQuery = "Select * from " + tableName;
        } else if (!columns.isEmpty() && condition_test == null) {
            sqlQuery = "Select " +  SqlTools.formatFieldsToSelectQuery(columns) + " from " + tableName;
        } else if (columns.isEmpty() && condition_test != null) {
            sqlQuery = "Select * from " + tableName + " where " + condition_test + " = " + condition;
        } else {
            sqlQuery = "Select " +  SqlTools.formatFieldsToSelectQuery(columns.stream().collect(Collectors.toList())) +
                    " from " + tableName
                    + " where " + condition_test + " = " + condition;
        }

       // return new SqlCommends().GenerateJoinSelectResult(joinedTable);
        return null;
    }

    public static String findColumnParentTable(Enum val) {

        StringBuilder stb = new StringBuilder();
        stb.append(val.getDeclaringClass().toString());
        stb.delete(0,43);
        char tab [] = stb.toString().toCharArray();
        int index = 0;
        for (int i = 0;i<tab.length;i++) {
            if(tab[i]=='$') {
                break;
            }
            index+=1;
        }
        stb.delete(index,stb.length());

        return stb.toString();
    }
    public static String getTableFirstLetter(Object o) {

        return o.getClass().getSimpleName().substring(0,1);
    }
}


 */