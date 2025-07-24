package k35.fp;

import java.util.function.Function;


/**
 * Functional programming tools
 */
public final class Fp {

    private Fp() {
    }

    /**
     * Composition of two funcitons
     *
     * @param f1  - function(a) -> b
     * @param f2  - function(b) -> c
     * @param <A>
     * @param <B>
     * @param <C>
     * @return f3 - function(a) -> c
     */
    public static <A, B, C> Function<A, C> compose2(Function<A, B> f1, Function<B, C> f2) {
        return (A value) -> f2.apply(f1.apply(value));
    }

    /**
     * Create a function that return a value
     *
     * @param value
     * @param <V>
     * @return value function() -> v
     */
    public static <V> V value(V value) {
        return value;
    }

    /**
     * Simple set function
     *
     * @param value
     * @param <A>
     * @param <V>
     * @return function(a, b) -> b
     */
    public static <A, V> Function<A, V> set(V value) {
        return (A a) -> value;
    }

}
