package com.nagarro.remotelearning.week5.p2;

public class Main {

    public static void main(String[] args) {

        StringList s = new StringList();
        s.add("20");
        s.add("14");
        try {
            s.get(14);
        } catch (CustomException e) {
            System.out.println(e.getExceptionMessage());
        }
        System.out.println(s.contains("20"));
        System.out.println(s.contains("320"));
        System.out.println(s.contains("Ale"));

        StringList anotherList = new StringList();
        anotherList.add("14");
        anotherList.add("20");
        System.out.println(s.containsAll(anotherList));
        anotherList.add("13");
        System.out.println(anotherList.size());
        System.out.println(s.containsAll(anotherList));
    }
}
