package server;

import java.util.function.BiFunction;

public record Operation(
    BiFunction<Integer, Integer, Integer> f,
    String show
) { }