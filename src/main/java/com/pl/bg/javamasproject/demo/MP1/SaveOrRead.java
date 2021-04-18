package com.pl.bg.javamasproject.demo.MP1;

import com.pl.bg.javamasproject.demo.MP1.Reception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

import java.io.*;


public class SaveOrRead {

    public static final Logger logger = LoggerFactory.getLogger(SaveOrRead.class);



    public void saveObject(String filepath ,Object o)  {

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(o);
            oos.flush();
            oos.close();
            logger.warn("Object saved to file");
        } catch (IOException e) {
            logger.error("Object no saved " +e );
        }
    }

    public Object loadObject(String filepath) {

        FileInputStream fileInputStream = null;
        Object obj = null;
        try {
            fileInputStream = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            obj = ois.readObject();
            ois.close();
            logger.warn("Object loaded");
        } catch (IOException | ClassNotFoundException e) {
            logger.warn("Object could not be loaded " + e);
        }
        return  obj;

    }

}
