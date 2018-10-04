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
    public static void AfterMethod(){
        System.out.println("Result of method which is executed after all other annotated methods and methods w/o them");
    }

    public void NonAnnotatedMethod(){
        System.out.println("Result of method without any annotations");
    }

    @AnnotationClass.SomeTestAnnotation
    public static void SomethingAdditional(){
        System.out.println("Some additional results here");
    }

    @AnnotationClass.Test
    public static void ExecTestMethod(){
        System.out.println("Test method executed");
    }

    @AnnotationClass.Before
    public static void BeforeMethod(){
        System.out.println("Result of method which is executed before all others");
    }
    @AnnotationClass.Before
    public static void BeforeMethod2(){
        System.out.println("Result of ANOTHER method with is executed before all others");
    }
}

