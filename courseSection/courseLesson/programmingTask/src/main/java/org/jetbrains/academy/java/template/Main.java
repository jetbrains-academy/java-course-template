package org.jetbrains.academy.java.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static String invokeSayHello(int howManyTimes) {
        ArrayList<String> list = new ArrayList<>(Collections.nCopies(howManyTimes, "Hello"));
        return String.join(System.lineSeparator(), list);
    }

    public static void main(String[] args) {
        System.out.println("How many times should I print Hello?");
        Scanner scanner = new Scanner(System.in);
        int howManyTimes = scanner.nextInt();
        System.out.println(invokeSayHello(howManyTimes));
    }
}

