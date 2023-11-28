package org.jetbrains.academy.java.template;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

// Since the file from the first lesson will be propagated,
// you can put the solution here
public class Main {
    public static String invokeSayBye(int howManyTimes) {
        Collection<String> list = Collections.nCopies(howManyTimes, "Bye");
        return String.join(System.lineSeparator(), list);
    }

    public static void main(String[] args) {
        System.out.println("How many times should I print Bye?");
        Scanner scanner = new Scanner(System.in);
        int howManyTimes = scanner.nextInt();
        System.out.println(invokeSayBye(howManyTimes));
    }
}