import org.jetbrains.academy.java.template.CourseUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Tests {
    private static Class<?> mainClazz;
    private static final String HELLO = "Hello";

    @BeforeAll
    static void beforeAll() {
        //TODO: implement it
    }

    static Stream<Arguments> invokeSayHelloArguments() {
        return Stream.of(
                Arguments.of(1, String.join(System.lineSeparator(), "Hello")),
                Arguments.of(2, String.join(System.lineSeparator(), "Hello", "Hello")),
                Arguments.of(3, String.join(System.lineSeparator(), "Hello", "Hello", "Hello"))
        );
    }

    @ParameterizedTest
    @MethodSource("invokeSayHelloArguments")
    void invokeSayHelloImplementation(int howManyTimes, String output) {
        //TODO: implement it
    }

    @Test
    public void testSolution() {
        String expectedOutput = ("How many times should I print " + HELLO + "?" + CourseUtils.NEW_LINE_SEPARATOR + HELLO + CourseUtils.NEW_LINE_SEPARATOR + HELLO).trim();
        // TODO: implement it
    }
}