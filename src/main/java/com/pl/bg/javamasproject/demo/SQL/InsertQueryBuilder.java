package com.pl.bg.javamasproject.demo.SQL;

import com.pl.bg.javamasproject.demo.models.Patient;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public class InsertQueryBuilder<T> {

    T t;
    private List<String> columnsList = new ArrayList<>();
    private List<Object> valuesToAdd = new ArrayList<>();
    private String tableName ;


    public static class Builder<T> {
        T t;
        private List<String> columnsList = new ArrayList<>();
        private List<Object> valuesToAdd = new ArrayList<>();
        private String tableName ;

        public Builder insertInto(Class<T> clazz) {

            tableName = clazz.getSimpleName()+"s";

            return this;
        }
        public Builder fields(List<String> val) {

            columnsList =val;

            return this;
        }

        public Builder value(Object val) {

            valuesToAdd.add(val);

        return this;
        }

        public InsertQueryBuilder end() {
            return new InsertQueryBuilder(this);
        }

    }

    public InsertQueryBuilder(Builder builder) {

      t = (T) builder.t;
      columnsList = builder.columnsList;
      valuesToAdd = builder.valuesToAdd;
      tableName=builder.tableName;

    }

    public void generateAndExecuteSQL(){

     String sql = " Insert into " + tableName+" "+ new SqlTools<T>( t).formatFieldsToInsert(columnsList)
             + " values " + SqlTools.formatValuesToInsertQuery(valuesToAdd); ;

         new SqlCommends().executeSqlCommend_insert(sql);

    }

    public static void main(String[] args) {

        new InsertQueryBuilder.Builder<Patient>()
                .insertInto(Patient.class)
                .fields(Patient.getListOfTableFields())
                .value("testest")
                .value(1)
                .value(1)
                .end()
                .generateAndExecuteSQL();
    }

}
