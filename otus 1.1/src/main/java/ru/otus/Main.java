package ru.otus;

import java.util.Arrays;
import com.google.common.base.Joiner;

public class Main {
    public static void main(String args[]) {
        Main example = new Main();
        example.testExample();

    }

    private void testExample() {
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1, 2, 3, 4, 5, null, 6)));
    }
}
