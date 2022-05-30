package org.example;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {


    // [A-Za-z0-9]

    public static void main(String... args) {
        for (String input : args) {
            System.out.println(sort(input));
        }
    }

    private static String sort(String input) {

        return input.chars() // Convert to an IntStream
                .mapToObj(i -> (char) i).sorted(new ComplexComparator())
                .map(String::valueOf)   // Stream<String>
                .collect(Collectors.joining());
    }

    static class ComplexComparator implements Comparator<Character> {

        @Override
        public int compare(Character a, Character b) {
            if (Character.isLetter(a) && Character.isDigit(b)) {
                return -1;
            } else if (Character.isDigit(a) && Character.isLetter(b)) {
                return 1;
            } else if (Character.isLetter(a) && Character.isLetter(b)) {
                if (Character.isUpperCase(a) && Character.isLowerCase(b)) {
                    return -1;
                } else if (Character.isLowerCase(a) && Character.isUpperCase(b)) {
                    return 1;
                }
                return a - b;
            } else {
                if (a % 2 == 0 && b % 2 != 0) {
                    return -1;
                } else if (a % 2 != 0 && b % 2 == 0) {
                    return 1;
                }
                return a - b;
            }
        }
    }
}