import org.jetbrains.academy.java.template.CourseUtils;
import org.jetbrains.academy.java.template.Main;
import org.jetbrains.academy.test.system.core.models.Visibility;
import org.jetbrains.academy.test.system.core.models.classes.ClassType;
import org.jetbrains.academy.test.system.core.models.classes.TestClass;
import org.jetbrains.academy.test.system.core.models.method.TestMethod;
import org.jetbrains.academy.test.system.core.models.variable.TestVariable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class Tests {
    private static Class<?> mainClazz;
    private static TestClass mainClass;
    private static TestMethod invokeSayHelloFunction;
    private static final String HELLO = "Hello";

    @BeforeAll
    static void beforeAll() {
        invokeSayHelloFunction = new TestMethod(
                "invokeSayHello",
                "String",
                List.of(new TestVariable("howManyTimes", "Int")),
                Visibility.PUBLIC
        );
        mainClass = new TestClass(
                "Main",
                "org.jetbrains.academy.java.template",
                Visibility.PUBLIC,
                ClassType.CLASS,
                emptyList(),
                List.of(invokeSayHelloFunction),
                false,
                emptyList(),
                emptyList()
        );
        mainClazz = mainClass.checkBaseDefinition();
    }

    static Stream<Arguments> invokeSayHelloArguments() {
        return Stream.of(
                Arguments.of(1, String.join(System.lineSeparator(), HELLO)),
                Arguments.of(2, String.join(System.lineSeparator(), HELLO, HELLO)),
                Arguments.of(3, String.join(System.lineSeparator(), HELLO, HELLO, HELLO))
        );
    }

    @Test
    public void invokeSayHelloFunction() {
        mainClass.checkMethod(mainClazz, invokeSayHelloFunction);
    }

    @ParameterizedTest
    @MethodSource("invokeSayHelloArguments")
    void invokeSayHelloImplementation(int howManyTimes, String output) throws InvocationTargetException, IllegalAccessException {
        Method userMethod = mainClass.findMethod(mainClazz, invokeSayHelloFunction);
        String message = "For howManyTimes = " + howManyTimes + " the function " + invokeSayHelloFunction.getName() + " should return " + output;
        Assertions.assertEquals(
                output,
                userMethod.invoke(mainClazz, howManyTimes).toString().trim(),
                message
        );
    }

    @Test
    public void testSolution() {
        String expectedOutput = ("How many times should I print " + HELLO + "?" + CourseUtils.NEW_LINE_SEPARATOR + HELLO + CourseUtils.NEW_LINE_SEPARATOR + HELLO).trim();
        Runnable callMain = () -> Main.main(new String[]{});
        Assertions.assertEquals(expectedOutput, CourseUtils.runMainMethod(callMain, "2", false).trim());
    }
}
