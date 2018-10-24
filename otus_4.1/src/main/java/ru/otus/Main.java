package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        AnalizatorClass analizator = new AnalizatorClass();
        Class classToAnalize = Test1.class;
        analizator.AnalizeAnnotations(classToAnalize);

    }

    public static int calc(){
        int i = 0xff - 0xc0;
        return i;
    }
}

class Test1{

    @AnnotationClass.After
    public static void AfterMethod() throws InterruptedException {
        System.out.println("Result of method which is executed after test method");
        System.out.println(Main.calc());
    }


    @AnnotationClass.Test
    public static void SomethingAdditional() throws InterruptedException {
        System.out.println("TEST1 results here");
    }

    @AnnotationClass.Test
    public static void ExecTestMethod() throws InterruptedException {
        System.out.println("Test2 method executed");
    }

    @AnnotationClass.Before
    public static void BeforeMethod() throws InterruptedException {
        System.out.println("Result of method which is executed before test method");
    }
    @AnnotationClass.Before
    public static void BeforeMethod2() throws Exception {
            //System.out.println("Result of ANOTHER method with is executed before test method");
            throw new Exception("Custom Error message");

    }
}

