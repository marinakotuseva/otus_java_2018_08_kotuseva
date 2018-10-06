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

}

class Test1{
    @AnnotationClass.After
    public static void AfterMethod(Integer test){
        System.out.println("Result of method which is executed after test method");
    }

    public void NonAnnotatedMethod(Integer test){
        System.out.println("Result of method without any annotations");
    }

    @AnnotationClass.Test
    public static void SomethingAdditional(Integer test){
        try {
            System.out.println("Some additional test results here");
        } catch (Exception e) {
            System.out.println("Error while performing method");
        }

    }

    @AnnotationClass.Test
    public static void ExecTestMethod(Integer test){
        try {
            System.out.println("Test method executed");
        } catch (Exception e) {
            System.out.println("Error while performing method");
        }
    }

    @AnnotationClass.Before
    public static void BeforeMethod(Integer test){
        System.out.println("Result of method which is executed before test method");
    }
    @AnnotationClass.Before
    public static void BeforeMethod2(Integer test){
        System.out.println("Result of ANOTHER method with is executed before test method");
    }
}

