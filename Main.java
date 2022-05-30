package org.example;

import java.util.Comparator;

public class Main {


    // [A-Za-z0-9]

    public static void main(String... args) {
        Arrays.stream(args).map(Main::sort).forEach(System.out::println);
    }

    private static String sort(String input) {
        return input.chars() // Convert to an IntStream
                .mapToObj(i -> (char) i).sorted(new ComplexComparator())
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
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
            } else {
                if (a % 2 == 0 && b % 2 != 0) {
                    return -1;
                } else if (a % 2 != 0 && b % 2 == 0) {
                    return 1;
                }
            }
            return a - b;
        }
    }
}
