package com.nagarro.remotelearning.week16.p2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckRequirements {

    private final static String PACKAGE = "com.nagarro.remotelearning.week16.p2.";
    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    public CheckRequirements(String className) {
        String cl = PACKAGE + className;
        Class<?> classT;
        try {
            classT = Class.forName(cl);
            while (classT.getSuperclass() != null) {
                fields.addAll(Arrays.asList(classT.getDeclaredFields()));
                methods.addAll(Arrays.asList(classT.getDeclaredMethods()));
                classT = classT.getSuperclass();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }

    public boolean hasPrivateModifier() {
        boolean allPrivate = true;
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (!Modifier.toString(mod).equals("private")) {
                allPrivate = false;
            }
        }
        return allPrivate;
    }

    public boolean nameLowercase() {
        boolean allStartWithLowercase = true;
        for (Field field : this.fields) {
            char firstLetter = field.getName().charAt(0);
            if (Character.toLowerCase(firstLetter) != firstLetter) {
                allStartWithLowercase = false;
            }
        }
        return allStartWithLowercase;
    }

    public boolean methodLowercase() {
        boolean allStartWithLowercase = true;
        for (Method method : this.methods) {
            char firstLetter = method.getName().charAt(0);
            if (Character.toLowerCase(firstLetter) != firstLetter) {
                allStartWithLowercase = false;
            }
        }
        return allStartWithLowercase;
    }
}
