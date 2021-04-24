package com.pl.bg.javamasproject.demo.MP1;

import com.pl.bg.javamasproject.demo.MP1.Reception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class SaveObject {

    public static final Logger logger = LoggerFactory.getLogger(SaveObject.class);

    //ekstensja trwalosc
    public  void saveObject(String filepath ,Object o)  {

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

}
