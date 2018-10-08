package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnalizatorClass {
    public static void AnalizeAnnotations(Class<Test1> classToAnalize) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<Method> methodsToExecute = new ArrayList<>();
        List<Method> beforeExecute = new ArrayList<>();
        List<Method> afterExecute = new ArrayList<>();

        // First Before
        for (Method methodInfo : classToAnalize.getDeclaredMethods()) {
            if (methodInfo.isAnnotationPresent(AnnotationClass.Before.class))
            {
                beforeExecute.add(methodInfo);
            }
        }
        // Than Test
        for (Method methodInfo : classToAnalize.getDeclaredMethods()) {
            if (methodInfo.isAnnotationPresent(AnnotationClass.Test.class))
            {
                methodsToExecute.add(methodInfo);
            }
        }
        // Finally After
        for (Method methodInfo : classToAnalize.getDeclaredMethods()) {
            if (methodInfo.isAnnotationPresent(AnnotationClass.After.class))
            {
                afterExecute.add(methodInfo);
            }
        }

        for (Method m : methodsToExecute) {
            Object instance = classToAnalize.newInstance();
            for (Method b : beforeExecute) {
                try {
                    b.invoke(instance);
                } catch (InvocationTargetException e) {
                    System.out.println("Error while performing method " + b.getName() + ": " + e.getCause().getMessage());
                    //e.printStackTrace();
                }
            }
            try {
                m.invoke(instance);
            } catch (InvocationTargetException e) {
                System.out.println("Error while performing method " + m.getName() + ": " + e.getCause().getMessage());
                //e.printStackTrace();
            }

            for (Method a: afterExecute){
                try {
                    a.invoke(instance);
                } catch (InvocationTargetException e) {
                    System.out.println("Error while performing method " + a.getName() + ": " + e.getCause().getMessage());
                    //e.printStackTrace();
                }
            }
        }
    }
}
