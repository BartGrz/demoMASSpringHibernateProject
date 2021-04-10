package com.pl.bg.javamasproject.demo.models;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

abstract class EntityTemplate {

    /**
     * @method template impl {
     *
     *      public List<String> fields () {
     *         List<String> fieldsList = new ArrayList<>();
     *         Field[] tab =new (ModelClass).getClass().getDeclaredFields();
     *         Looper.forLoop(0,tab.length,i -> fieldsList.add(tab[i].getName()));
     *         return fieldsList;
     *     }
     * }
     * @return list of declared fields
     */
    public abstract List<String> fields();
    public abstract EnumSet fieldsEnum();

}
