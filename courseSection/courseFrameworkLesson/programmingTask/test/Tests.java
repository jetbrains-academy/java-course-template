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
    private static TestMethod invokeSayByeFunction;
    private static final String BYE = "Bye";

    @BeforeAll
    static void beforeAll() {
        invokeSayByeFunction = new TestMethod(
                "invokeSayBye",
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
                List.of(invokeSayByeFunction),
                false,
                emptyList(),
                emptyList()
        );
        mainClazz = mainClass.checkBaseDefinition();
    }

    static Stream<Arguments> invokeSayByeArguments() {
        return Stream.of(
                Arguments.of(1, String.join(System.lineSeparator(), BYE)),
                Arguments.of(2, String.join(System.lineSeparator(), BYE, BYE)),
                Arguments.of(3, String.join(System.lineSeparator(), BYE, BYE, BYE))
        );
    }

    @Test
    public void invokeSayByeFunction() {
        mainClass.checkMethod(mainClazz, invokeSayByeFunction);
    }

    @ParameterizedTest
    @MethodSource("invokeSayByeArguments")
    void invokeSayByeImplementation(int howManyTimes, String output) throws InvocationTargetException, IllegalAccessException {
        Method userMethod = mainClass.findMethod(mainClazz, invokeSayByeFunction);
        String message = "For howManyTimes = " + howManyTimes + " the function " + invokeSayByeFunction.getName() + " should return " + output;
        Assertions.assertEquals(
                output,
                userMethod.invoke(mainClazz, howManyTimes).toString().trim(),
                message
        );
    }

    @Test
    public void testSolution() {
        String expectedOutput = ("How many times should I print " + BYE + "?" + CourseUtils.NEW_LINE_SEPARATOR + BYE + CourseUtils.NEW_LINE_SEPARATOR + BYE).trim();
        Runnable callMain = () -> Main.main(new String[]{});
        Assertions.assertEquals(expectedOutput, CourseUtils.runMainMethod(callMain, "2", false).trim());
    }
}
