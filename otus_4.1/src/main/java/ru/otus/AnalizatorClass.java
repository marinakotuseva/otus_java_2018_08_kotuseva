package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnalizatorClass {
    public static void AnalizeAnnotations(Class<Test1> classToAnalize) throws IllegalAccessException, InvocationTargetException, InstantiationException {
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

        Object instance = classToAnalize.newInstance();
        for (Method m : methodsToExecute) {
            for (Method b: beforeExecute){
                b.invoke(instance, new Object[]{null});
            }
            m.invoke(instance, new Object[]{null});
            for (Method a: afterExecute){
                a.invoke(instance, new Object[]{null});
            }
        }
    }
}
