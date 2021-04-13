package com.pl.bg.javamasproject.demo.tools.FXML_tools;

import com.pl.bg.javamasproject.demo.models.Patient;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewCreator <T,V>{

    private  String columnName;
    private String field;
    private TableColumn<T, V> column;

    public static class Builder <T,V> {

            private  String columnName;
            private String field;
            private TableColumn<T, V> column;

        public Builder setField(Enum val) {
                field =val.toString().toLowerCase();
                return this;
            }
            public Builder setColumnName(String val) {
                columnName =val;
                return this;
        }

        public TableViewCreator build() {

            return new TableViewCreator(this);
        }
    }

    public TableViewCreator(Builder builder) {

        field =builder.field;
        columnName =builder.columnName;
        column = builder.column;
    }

    public TableColumn buildColumn() {
        column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(field.toLowerCase()));
        return column;
    }


}
