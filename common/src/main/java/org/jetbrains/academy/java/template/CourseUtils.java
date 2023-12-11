package org.jetbrains.academy.java.template;

import kotlin.NotImplementedError;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class CourseUtils {
    public static final String NEW_LINE_SEPARATOR = System.lineSeparator();

    public static void throwInternalCourseError() {
        throw new Error("Internal course error!");
    }

    public static void setSystemIn(String input) {
        if (input != null) {
            System.setIn(new ByteArrayInputStream(replaceLineSeparator(input).getBytes(StandardCharsets.UTF_8)));
        }
    }

    public static String replaceLineSeparator(String original) {
        return String.join(NEW_LINE_SEPARATOR, original.split("\\r?\\n"));
    }

    public static ByteArrayOutputStream setSystemOut() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8);
        System.setOut(ps);
        return baos;
    }

    public static boolean isSystemInEmpty() {
        try {
            return System.in.readAllBytes().length == 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String runMainMethod(Runnable mainMethod, String input, boolean toAssertSystemIn) {
        try {
            setSystemIn(input);
            ByteArrayOutputStream baos = setSystemOut();
            mainMethod.run();
            if (toAssertSystemIn) {
                if (isSystemInEmpty()) {
                    return "You are asking the user to enter data fewer times than required in the task!";
                }
            }
            return replaceLineSeparator(baos.toString(StandardCharsets.UTF_8));
        } catch (IllegalStateException e) {
            String userInput = input != null ? "the user input: " + input : "the empty user input";
            String errorPrefix =
                    "Try to run the main method with " + userInput + ", the method must process the input and exit, but the current version of the method";
            if (e.getMessage() == null || !e.getMessage().contains("Your input is incorrect")) {
                return errorPrefix + " waits more user inputs, it must be fixed.";
            }
            return errorPrefix + " throws an unexpected error, please, check the method's implementation.";
        } catch (NotImplementedError e) {
            return "You call not implemented methods (that use TODO()) inside the main method. Please, don't do it until the task asks it";
        }
    }
}