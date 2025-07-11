package k35.fp.tools;

import java.util.function.Function;

public class FP {

    /**
     * Композиция из двух элементов
     *
     * @param f1
     * @param f2
     * @returns
     */
    public final static <A, B, C> Function<A, C> compose2(Function<A, B> f1, Function<B, C> f2) {
        return (A value) -> f2.apply(f1.apply(value));
    }

}
