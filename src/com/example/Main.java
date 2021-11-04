package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String a[] = new String[]
                {"Ali", "Veli", "Muhammed", "Osman", "Ahmet", "Anıl"};
        List<String> test = Arrays.asList(a);
        List<String> result = test.stream()
                .filter(i -> i.startsWith("A"))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(result);

        List<Integer> test2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> result2 = test2.stream().map(number -> number * 3).collect(Collectors.toList());
        System.out.println(result.getClass().getSimpleName());

        List<Integer> numbers = List.of(4, 8, 15, 16, 23, 42);
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Function<Integer, Integer> squared = y -> y * y;
        BinaryOperator<Integer> accumulate = (acc, v) -> acc + v;
        int sum = numbers.stream()
                .filter(isEven)
                .map(squared)
                .reduce(0, accumulate);
        System.out.println(sum);
    }
}
