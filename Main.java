package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    // [A-Za-z0-9]

    public static void main(String... args) {
        Arrays.stream(args)
                .map(Main::sort)
                .forEach(System.out::println);
    }

    private static String sort(String input) {
        return input.chars()
                .mapToObj(i -> (char) i).sorted(new ComplexComparatorOptimized())
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static class ComplexComparator implements Comparator<Character> {
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

    // The optimal solution suggested by @rosti-il
    private static class ComplexComparatorOptimized implements Comparator<Character> {
        @Override
        public int compare(Character a, Character b) {
            return getPrioritizedIntValue(a) - getPrioritizedIntValue(b);
        }

        private int getPrioritizedIntValue(Character c) {
            int cc = c;

            if (Character.isLetter(c)) {
                cc += Character.MAX_VALUE;
            } else {
                cc += Character.MAX_VALUE << 1;
                if (((c - '0') & 1) != 0) {
                    cc += Character.MAX_VALUE;
                }
            }
            return cc;
        }
    }
}

