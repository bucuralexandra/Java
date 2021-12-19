package com.iquestgroup.remotelearning.week2.p2.Alexandra;

public class Main {

    public static void main(String[] args) {

        Tank t1 = new Tank();
        t1.pushTank();
        t1.pushTank();
        t1.pushTank();
        t1.pushTank();
        System.out.println(t1.getNrElements());
        t1.popTank();
        System.out.println(t1.getNrElements());
        t1.popTank();
        t1.popTank();
        t1.popTank();

        System.gc();
    }


}
