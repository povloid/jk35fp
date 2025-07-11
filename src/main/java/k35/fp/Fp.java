package k35.fp;

import java.util.function.Function;


public final class Fp {

    private Fp() {
    }

    public static <A, B, C> Function<A, C> compose2(Function<A, B> f1, Function<B, C> f2) {
        return (A value) -> f2.apply(f1.apply(value));
    }

    public static <V> V value(V value) {
        return value;
    }

    public static <A, V> Function<A, V> set(V value) {
        return (A a) -> value;
    }

}
