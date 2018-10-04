package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnalizatorClass {
    public static void AnalizeAnnotations(Class<Test1> classToAnalize) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Method> methodsToExecute = new ArrayList<>();

        // First Before
        for (Method methodInfo : classToAnalize.getDeclaredMethods()) {
            if (methodInfo.isAnnotationPresent(AnnotationClass.Before.class))
            {
                methodsToExecute.add(methodInfo);
            }
        }
        // Than Test
        for (Method methodInfo : classToAnalize.getDeclaredMethods()) {
            if (methodInfo.isAnnotationPresent(AnnotationClass.Test.class))
            {
                methodsToExecute.add(methodInfo);
            }
        }

        // Do we have some methods with other annotations or methods without annotations? If so execute in order of appearance
        Method[] classMethods = classToAnalize.getDeclaredMethods();
        for (Method m:classMethods){
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a:annotations) {
                String aPath = a.toString();
                String aName = aPath.substring(aPath.indexOf("$") + 1, aPath.indexOf("("));
                if (!aName.equals("Before") && !aName.equals("Test") && !aName.equals("After")) {
                    methodsToExecute.add(m);
                }
            }
            if (annotations.length == 0) {
                methodsToExecute.add(m);
            }
        }


        // Finally After
        for (Method methodInfo : classToAnalize.getDeclaredMethods()) {
            if (methodInfo.isAnnotationPresent(AnnotationClass.After.class))
            {
                methodsToExecute.add(methodInfo);
            }
        }

        Object instance = classToAnalize.newInstance();
        for (Method m : methodsToExecute) {
            System.out.println(m.invoke(instance));
        }
    }
}
