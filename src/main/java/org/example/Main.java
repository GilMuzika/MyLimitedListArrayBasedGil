package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyLimitedList<Integer> intLst = new MyLimitedList<>(10);
        MyLimitedList<Tumbler> tmbrLst = new MyLimitedList<>(10);
        for(int i = 0; i < intLst.maxSize(); i++) {
            intLst.addItem(i + 1);
            tmbrLst.addItem(new Tumbler(i + 1));
        }
        System.out.println(intLst);
        System.out.println(tmbrLst);
        tmbrLst.removeItem(5);
        tmbrLst.removeItem(3);
        tmbrLst.removeItem(7);
        tmbrLst.addItem(new Tumbler(1000));
        System.out.println(tmbrLst);

        var r  = tmbrLst.getItem(8);
        System.out.println(tmbrLst.count());
        System.out.println(tmbrLst.maxSize());
        tmbrLst.enlargeBy(100);
        System.out.println(tmbrLst.maxSize());
        for (int i = 0; i < 70; i++) {
            tmbrLst.addItem(new Tumbler(1000 + i));
        }
        System.out.println(tmbrLst);

    }
}