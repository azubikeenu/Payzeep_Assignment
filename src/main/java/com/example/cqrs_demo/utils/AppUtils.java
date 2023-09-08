package com.example.cqrs_demo.utils;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppUtils {
    static Random RANDOM = new Random();

    private static String createNumerics() {
        return IntStream.rangeClosed(0, 4).boxed().map(n ->
                String.valueOf(RANDOM.nextInt(10))).collect(Collectors.joining(""));
    }

    private static String createHalfNumeric() {
        return IntStream.rangeClosed(0, 2).boxed().map(n -> String.valueOf(RANDOM.nextInt(10)))
                .collect(Collectors.joining("")).concat("XX");
    }

    private static String createAllMasked() {
        return IntStream.rangeClosed(0, 3).boxed().map(x -> "X")
                .collect(Collectors.joining(""));
    }

    public static String createMaskedPan() {
        return createNumerics() +
                "-" + createHalfNumeric() +
                "-" + createAllMasked() +
                "-" + createNumerics();
    }
}
