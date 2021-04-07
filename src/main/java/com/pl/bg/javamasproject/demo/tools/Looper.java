package com.pl.bg.javamasproject.demo.tools;

public class Looper {

    public static void forLoop(int beginIndex,int collectionSize, LooperFor looperFor){

        for (int i = beginIndex;i<collectionSize;i++) {
            looperFor.looperFor(i);
        }

    }

}
