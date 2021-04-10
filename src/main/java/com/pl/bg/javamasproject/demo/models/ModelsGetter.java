package com.pl.bg.javamasproject.demo.models;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ModelsGetter <T>{

    T modelClass;


    public ModelsGetter(T modelClass) {
        this.modelClass = modelClass;
    }

    public T getModelClass() {
        return this.modelClass;
    }
    public String getModelName() {

        return modelClass.getClass().getSimpleName()+"s";
    }

}
