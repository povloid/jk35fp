package k35.fp;

import java.util.function.Function;

/**
 * Functional pipe line.
 *
 * @param <A>
 */
public final class Pipe<A> {

    private final A value;

    private Pipe(A value) {
        this.value = value;
    }

    public static <A> Pipe<A> of(A value) {
        return new Pipe<>(value);
    }

    public <B> Pipe<B> map(Function<A, B> transform) {
        return new Pipe<B>(transform.apply(this.value));
    }

    public A out() {
        return this.value;
    }
}
